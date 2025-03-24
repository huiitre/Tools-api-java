package fr.huiitre.tools.middlewares;

import fr.huiitre.tools.annotations.RequireToken;
import fr.huiitre.tools.tools_core.config.service.ConfigService;
import fr.huiitre.tools.tools_core.user.model.User;
import fr.huiitre.tools.tools_core.user.repository.UserRepository;
import fr.huiitre.tools.tools_core.utils.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfigService configService;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {

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

        if (tokenRequired) {
            String authHeader = request.getHeader("Authorization");
            
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"msg\":\"Token API obligatoire\"}");
                return false;
            }

            String token = StringUtils.cleanToken(authHeader);

            //* récupération de l'utilisateur depuis son token
            User user = userRepository.findByRememberToken(token);
            
            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"msg\":\"Token invalide\"}");
                return false;
            }

            //* on récupère le paramètre TIME_EXPIRED_TOKEN
            Integer timeExpiredToken = Integer.parseInt(configService.getConfig("user", "TIME_EXPIRED_TOKEN", user.getId()));

            LocalDateTime tokenExpiredDate = user.getTokenDate().plusMinutes(timeExpiredToken);
            LocalDateTime currentDate = LocalDateTime.now();

            //* si le paramètre vaut 0, on considère qu'il est désactivé */
            if (timeExpiredToken > 0 && (currentDate.isAfter(tokenExpiredDate))) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"msg\":\"Token expiré\"}");
                return false;
            }
            request.setAttribute("user", user);
        }
        return true;
    }
}
