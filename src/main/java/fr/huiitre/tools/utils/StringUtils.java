package fr.huiitre.tools.utils;

import java.security.SecureRandom;
import java.util.regex.Pattern;

public class StringUtils {

    // Vérifie si l'email est valide
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-z]{2,}$";
        return Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }

    // Validation du mot de passe
    public static boolean isValidPassword(String password) {
        // Doit comporter au moins 6 caractères, une lettre, un chiffre et un caractère spécial
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*.]).{6,}$";
        return Pattern.matches(passwordRegex, password);
    }

    /**
     * Génère une chaîne hexadécimale aléatoire.
     *
     * @param length longueur de la chaîne souhaitée
     * @return chaîne hexadécimale
     */
    public static String generateRandomHexString(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length / 2];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Nettoie le token en supprimant le préfixe "Bearer " s'il est présent.
     *
     * @param token le token brut depuis l'en-tête Authorization
     * @return le token nettoyé
     */
    public static String cleanToken(String token) {
        if (token == null) {
            return null;
        }
        token = token.trim();
        if (token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
}
