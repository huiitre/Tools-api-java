package fr.huiitre.tools.middlewares;

import fr.huiitre.tools.annotations.RequireToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        System.out.println("=== TokenInterceptor: Début du preHandle ===");
        System.out.println("Request URI: " + request.getRequestURI());

        // On vérifie si le handler correspond à une méthode de contrôleur
        if (!(handler instanceof HandlerMethod)) {
            System.out.println("Handler non applicable (pas une méthode de contrôleur).");
            return true;
        }
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // On récupère l'annotation @RequireToken sur la méthode
        RequireToken requireToken = handlerMethod.getMethodAnnotation(RequireToken.class);
        
        // Par défaut, si l'annotation n'est pas présente, on considère que la route est sécurisée (token requis)
        boolean tokenRequired = (requireToken != null) && requireToken.value();
        System.out.println("Token requis ? " + tokenRequired);
        
        /* if (tokenRequired) {
            String authHeader = request.getHeader("Authorization");
            System.out.println("Authorization header: " + authHeader);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("Token API obligatoire non présent.");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"msg\":\"Token API obligatoire\"}");
                return false;
            }
            String token = authHeader.substring(7).trim();
            System.out.println("Token extrait: " + token);
            // Ici, pas de validation, juste un test pour s'assurer que le token n'est pas vide
            if (token.isEmpty()) {
                System.out.println("Token vide après extraction.");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"msg\":\"Token invalide\"}");
                return false;
            }
        } */
        
        System.out.println("=== TokenInterceptor: Fin du preHandle, requête autorisée ===");
        // Si tout est OK, on laisse passer la requête
        return true;
    }
}
