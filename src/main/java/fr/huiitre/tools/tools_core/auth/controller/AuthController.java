package fr.huiitre.tools.tools_core.auth.controller;

import fr.huiitre.tools.annotations.RequireToken;
import fr.huiitre.tools.common.BaseController;
import fr.huiitre.tools.tools_core.auth.dto.GooglePayload;
import fr.huiitre.tools.tools_core.auth.dto.LoginRequest;
import fr.huiitre.tools.tools_core.auth.dto.UserResponse;
import fr.huiitre.tools.tools_core.auth.dto.LoginWithGoogleRequest;
import fr.huiitre.tools.tools_core.auth.dto.RegistrationRequest;
import fr.huiitre.tools.tools_core.auth.service.AuthService;
import fr.huiitre.tools.tools_core.user.model.User;
import fr.huiitre.tools.tools_core.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/core/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @Value("${GOOGLE_CLIENT_ID}")
    private String googleClientId;

    /**
     * Connexion d'un utilisateur.
     *
     * @param request contenant email et mot de passe.
     * @return réponse avec les informations utilisateur ou message d'erreur.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody(required = false) LoginRequest request) {
        try {
            if (request == null) {
                throw new Exception("L'adresse email et le mot de passe sont obligatoires");
            }

            UserResponse userResponse = authService.login(request);
            return ResponseEntity.ok(Map.of( "data", userResponse));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of( "msg", e.getMessage()));
        }
    }

    /**
     * Récupère les informations utilisateur depuis le token API.
     * L'header "Authorization" doit contenir le token.
     * Exemple d'URL : /core/auth/me
     */
    @GetMapping("/me")
    @RequireToken(true)
    public ResponseEntity<?> me(@RequestAttribute("user") User user) {
        try {
            logger.info(user);

            UserResponse userInfos = authService.getUserInfosById(user.getId());
            return ResponseEntity.ok(Map.of("data", userInfos));
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of( "msg", e.getMessage()));
        }
    }

    /**
     * Route de connexion avec Google.
     * Expose l'URL /core/auth/login-with-google
     */
    @PostMapping("/login-with-google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody(required = false) LoginWithGoogleRequest request) {
        try {
            if (request == null) {
                throw new Exception("Le token Google est obligatoire");
            }

            String googleJwt = request.getGoogleJwt().trim();
            if (googleJwt == null || googleJwt.trim().isEmpty()) {
                throw new Exception("JWT google manquant");
            }

            // Récupérer le client ID Google depuis l'environnement ou votre configuration
            if (googleClientId == null || googleClientId.isEmpty()) {
                throw new Exception("GOOGLE_CLIENT_ID non configuré");
            }

            // Configurer le vérificateur du token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

            GoogleIdToken idToken = verifier.verify(googleJwt);
            if (idToken == null) {
                throw new Exception("Payload JWT invalide");
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            if (!"https://accounts.google.com".equals(payload.getIssuer())) {
                throw new Exception("Le token ne provient pas de Google.");
            }
            String audience = (String) payload.getAudience();
            if (!audience.equals(googleClientId)) {
                throw new Exception("Le token n'est pas destiné à cette application.");
            }
            Boolean emailVerified = (Boolean) payload.getEmailVerified();
            if (emailVerified == null || !emailVerified) {
                throw new Exception("L'adresse email Google n'a pas été vérifiée.");
            }

            // Convertir le payload Google en notre DTO GooglePayload
            GooglePayload googlePayload = new GooglePayload();
            googlePayload.setSub(payload.getSubject());
            googlePayload.setEmail((String) payload.get("email"));
            googlePayload.setName((String) payload.get("name"));
            googlePayload.setGivenName((String) payload.get("given_name"));
            googlePayload.setFamilyName((String) payload.get("family_name"));
            googlePayload.setPicture((String) payload.get("picture"));

            // Vérifier si l'utilisateur existe déjà via son google_id
            UserResponse user = authService.getUserInfosByGoogleId(googlePayload.getSub());
            if (user == null) {
                // S'il n'existe pas, on l'enregistre via Google
                user = authService.registerWithGoogle(googlePayload);
                if (user == null) {
                    throw new Exception("Erreur critique : impossible de récupérer les informations de l'utilisateur après création.");
                }
            }

            if (user.getIsActive() == null || user.getIsActive() != 1) {
                throw new Exception("Votre compte est actuellement désactivé. Un administrateur le validera sous peu. Merci de votre patience.");
            }

            // Générer un token et sa date d'expiration, puis mettre à jour l'utilisateur
            AuthService.TokenResponse tokenResponse = authService.generateTokenAndDate();
            authService.updateUserTokenById(user.getId(), tokenResponse);

            // Récupérer les informations utilisateur mises à jour
            UserResponse updatedUser = authService.getUserInfosById(user.getId());

            return ResponseEntity.ok(Map.of( "data", updatedUser));
        }
        catch(IllegalArgumentException e) {
            logger.error("Le token est invalide (décodage impossible).");
            return ResponseEntity.badRequest().body(Map.of("msg", "Le token est invalide (décodage impossible)."));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of( "msg", e.getMessage()));
        }
    }

    /**
     * Route d'inscription d'un nouvel utilisateur (non activé)
     * URL : /core/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            String message = authService.register(request);
            return ResponseEntity.ok(Map.of("msg", message));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    /**
     * Route pour générer un nouveau token API.
     * URL : /core/auth/refresh
     * Nécessite le header Authorization
     */
    @PostMapping("/refresh")
    @RequireToken(true)
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authHeader) {
        try {
            // Nettoyage du token reçu dans l'en-tête
            String currentToken = StringUtils.cleanToken(authHeader);
            if (currentToken == null || currentToken.trim().isEmpty()) {
                throw new Exception("Token API obligatoire");
            }

            // Récupérer les infos de l'utilisateur connecté via son token actuel
            UserResponse userInfos = authService.getUserInfosByRememberToken(currentToken);
            if (userInfos == null) {
                throw new Exception("Utilisateur introuvable");
            }

            // Générer un nouveau token et sa date d'expiration à partir de la config
            AuthService.TokenResponse tokenResponse = authService.generateTokenAndDate();

            // Mettre à jour l'utilisateur avec le nouveau token
            authService.updateUserTokenById(userInfos.getId(), tokenResponse);

            // Récupérer l'utilisateur mis à jour
            UserResponse updatedUser = authService.getUserInfosById(userInfos.getId());

            // Retourner la réponse JSON avec status et data
            return ResponseEntity.ok(Map.of( "data", updatedUser));
        } catch (Exception e) {
            logger.error(e.getMessage());
            // En cas d'erreur, on renvoie le message d'erreur dans la réponse JSON
            return ResponseEntity.badRequest().body(Map.of("msg", e.getMessage()));
        }
    }

    /**
     * Déconnexion d'un utilisateur (suppression de son token en base)
     * URL : /core/auth/logout
     * Nécessite le header Authorization
     */
    @PostMapping("/logout")
    @RequireToken(true)
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        try {
            // Nettoyage du token reçu dans l'en-tête
            String token = StringUtils.cleanToken(authHeader);
            if (token == null || token.trim().isEmpty()) {
                throw new Exception("Token API obligatoire");
            }

            // Récupérer les infos de l'utilisateur via son token actuel
            UserResponse userInfos = authService.getUserInfosByRememberToken(token);
            if (userInfos == null) {
                throw new Exception("Utilisateur introuvable");
            }

            // Déconnecter l'utilisateur en supprimant son token
            authService.logoutUser(userInfos.getId(), userInfos.getEmail());

            // Retourner une réponse indiquant que l'utilisateur est déconnecté
            return ResponseEntity.ok(Map.of( "msg", "Vous êtes déconnecté"));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of( "msg", e.getMessage()));
        }
    }
}