package fr.huiitre.tools.tools_core.auth.service;

import fr.huiitre.tools.tools_core.auth.dto.LoginRequest;
import fr.huiitre.tools.tools_core.auth.dto.UserResponse;
import fr.huiitre.tools.tools_core.auth.dto.GooglePayload;
import fr.huiitre.tools.tools_core.auth.dto.RegistrationRequest;

import fr.huiitre.tools.tools_core.user.model.User;
import fr.huiitre.tools.tools_core.user.repository.UserRepository;
import fr.huiitre.tools.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Gère la connexion d'un utilisateur.
     *
     * @param request le LoginRequest contenant email et mot de passe.
     * @return UserResponse avec les informations mises à jour.
     * @throws Exception en cas d'erreur.
     */
    public UserResponse login(LoginRequest request) throws Exception {
        String email = request.getEmail();
        String password = request.getPassword();

        if (email == null || email.trim().isEmpty()) {
            throw new Exception("L'adresse email est obligatoire");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("Le mot de passe est obligatoire");
        }

        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Email ou mot de passe incorrect");
        }
        if (user.getIsActive() == null || user.getIsActive() != 1) {
            throw new Exception("Le compte est désactivé");
        }

        TokenResponse tokenResponse = generateTokenAndDate();
        updateUserToken(user, tokenResponse);

        User updatedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new Exception("Utilisateur introuvable après mise à jour"));

        return new UserResponse(updatedUser);
    }

    public UserResponse getUserInfosById(Integer id) throws Exception {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new Exception("Utilisateur introuvable"));
        return new UserResponse(user);
    }

    public TokenResponse generateTokenAndDate() {
        String token = UUID.randomUUID().toString();
        LocalDateTime tokenDate = LocalDateTime.now();
        return new TokenResponse(token, tokenDate);
    }

    public UserResponse getUserInfosByRememberToken(String token) {
        User user = userRepository.findByRememberToken(token);
        return user != null ? new UserResponse(user) : null;
    }

    @Transactional
    public void updateUserToken(User user, TokenResponse tokenResponse) {
        user.setRememberToken(tokenResponse.getToken());
        user.setTokenDate(tokenResponse.getTokenDate());
        userRepository.save(user);
    }

    @Transactional
    public void updateUserTokenById(Integer userId, TokenResponse tokenResponse) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Utilisateur introuvable pour mise à jour du token"));
        updateUserToken(user, tokenResponse);
    }

    /**
     * Récupère les informations utilisateur à partir du token.
     *
     * @param token le token API nettoyé
     * @return les informations utilisateur encapsulées dans un UserResponse
     * @throws Exception si l'utilisateur n'est pas trouvé
     */
    @Transactional(readOnly = true)
    public UserResponse getUserInfosByToken(String token) throws Exception {
        User user = userRepository.findByRememberToken(token);
        if (user == null) {
            throw new Exception("Utilisateur introuvable");
        }
        return new UserResponse(user);
    }

    @Transactional
    public User createNewUserWithGoogle(User user) throws Exception {
        try {
            User savedUser = userRepository.save(user);
            userRepository.flush();
            return savedUser;
        } catch (DataIntegrityViolationException e) {
            // Tu peux aussi capturer d'autres exceptions spécifiques si nécessaire
            throw new Exception("Erreur lors de l'enregistrement de l'utilisateur Google : " + e.getMessage(), e);
        }
    }

    /**
     * Enregistre un utilisateur avec les informations Google et le connecte.
     *
     * @param payload les données issues de Google
     * @return les informations utilisateur mises à jour
     * @throws Exception en cas d'erreur
     */
    @Transactional
    public UserResponse registerWithGoogle(GooglePayload payload) throws Exception {
        try {
            // Préparer les données de l'utilisateur
            User newUser = new User();
            newUser.setGoogleId(payload.getSub());
            newUser.setEmail(payload.getEmail());
            newUser.setGoogleEmail(payload.getEmail());
            newUser.setName(payload.getName());
            newUser.setRole(1);
            newUser.setGoogleName(payload.getName());
            newUser.setGoogleGivenName(payload.getGivenName() != null ? payload.getGivenName() : "");
            newUser.setGoogleFamilyName(payload.getFamilyName() != null ? payload.getFamilyName() : "");
            newUser.setGooglePicture(payload.getPicture());
            newUser.setIsActive(1);
            newUser.setCreatedAt(LocalDateTime.now());

            // Générer un mot de passe aléatoire (ici 16 caractères hexadécimaux)
            String randomPassword = StringUtils.generateRandomHexString(16);
            newUser.setPassword(passwordEncoder.encode(randomPassword));

            // Créer l'utilisateur dans la base de données
            createNewUserWithGoogle(newUser);

            // Récupérer l'utilisateur via google_id
            User user = userRepository.findByGoogleId(payload.getSub());
            if (user == null) {
                throw new Exception("Utilisateur introuvable après création");
            }

            // Générer un token et sa date d'expiration
            TokenResponse tokenResponse = generateTokenAndDate();

            // Mettre à jour le token de l'utilisateur
            updateUserToken(user, tokenResponse);

            // Récupérer l'utilisateur mis à jour
            User updatedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new Exception("Utilisateur introuvable après mise à jour"));

            return new UserResponse(updatedUser);
        } catch (Exception e) {
            // En cas d'erreur, la transaction sera annulée automatiquement grâce à @Transactional
            throw new Exception("Une erreur s'est produite lors de l'enregistrement de l'utilisateur Google : " + e.getMessage(), e);
        }
    }

    /**
     * Inscription d'un nouvel utilisateur (non activé)
     *
     * @param request le DTO d'inscription
     * @return un message de succès ou une exception en cas d'erreur
     * @throws Exception en cas d'erreur de validation ou d'insertion
     */
    @Transactional
    public String register(RegistrationRequest request) throws Exception {
        // Récupération des données
        String email = request.getEmail();
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();
        String name = request.getName();

        // Validation email
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("L'adresse email est obligatoire");
        }
        if (!StringUtils.isValidEmail(email)) {
            throw new Exception("Le format de l'adresse email est incorrect");
        }

        // Validation mot de passe
        if (password == null || confirmPassword == null ||
            password.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
            throw new Exception("Le mot de passe est obligatoire");
        }
        if (!password.equals(confirmPassword)) {
            throw new Exception("Le mot de passe doit être identique");
        }
        if (password.length() < 6 || !StringUtils.isValidPassword(password)) {
            throw new Exception("Le mot de passe doit comporter au minimum 6 caractères et contenir au moins une lettre, un chiffre et un caractère spécial");
        }

        // Validation nom
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Le nom est obligatoire");
        }
        if (name.length() < 4 || name.length() > 15) {
            throw new Exception("Le nom doit comporter au minimum 4 caractères et au maximum 15 caractères");
        }

        // Vérification de l'existence de l'utilisateur par email et par nom
        User userByEmail = userRepository.findByEmail(email);
        User userByName = userRepository.findByName(name);

        if (userByEmail != null && userByName != null) {
            throw new Exception("L'adresse email et le nom existent déjà");
        }
        if (userByEmail != null) {
            throw new Exception("L'adresse email existe déjà");
        }
        if (userByName != null) {
            throw new Exception("Le nom existe déjà");
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setIsActive(0);
        newUser.setRole(1);

        // Insertion en base via le repository
        userRepository.save(newUser);

        return "Le compte a bien été créé. Un administrateur va bientôt activer le compte";
    }

    public UserResponse getUserInfosByGoogleId(String googleId) {
        User user = userRepository.findByGoogleId(googleId);
        return user != null ? new UserResponse(user) : null;
    }

    @Transactional
    public void logoutUser(Integer userId, String email) throws Exception {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("Utilisateur introuvable"));
        if (!user.getEmail().equals(email)) {
            throw new Exception("Email utilisateur non valide");
        }
        user.setRememberToken(null);
        user.setTokenDate(null);
        userRepository.save(user);
    }

    public static class TokenResponse {
        private String token;
        private LocalDateTime tokenDate;

        public TokenResponse(String token, LocalDateTime tokenDate) {
            this.token = token;
            this.tokenDate = tokenDate;
        }

        public String getToken() {
            return token;
        }
        public LocalDateTime getTokenDate() {
            return tokenDate;
        }
    }
}
