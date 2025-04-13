package fr.huiitre.tools.tools_dtl.translation.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import fr.huiitre.tools.common.controller.BaseController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dtl/translation")
public class TranslationController extends BaseController {
    
    @PostMapping("/translate")
    public ResponseEntity<?> translate(@RequestBody Map<String, Object> body) {
        try {
            // Vérif rapide
            if (!body.containsKey("value") || !body.containsKey("source") || !body.containsKey("target")) {
                return ResponseEntity.badRequest().body(Map.of("status", 0, "msg", "Champs manquants"));
            }

            List<String> value = (List<String>) body.get("value");
            String source = body.get("source").toString();
            String target = body.get("target").toString();

            // Appel LibreTranslate
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> payload = new HashMap<>();
            payload.put("q", value);
            payload.put("source", source);
            payload.put("target", target);
            payload.put("format", "text");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payload, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "http://localhost:5000/translate",
                    requestEntity,
                    Map.class
            );

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("status", 1);
            responseBody.put("translated", response.getBody().get("translatedText"));

            return ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            logger.error("Erreur de traduction : {}", e.getMessage());
            return ResponseEntity.status(500).body(Map.of(
                "status", 0,
                "msg", e.getMessage()
            ));
        }
    }
}
