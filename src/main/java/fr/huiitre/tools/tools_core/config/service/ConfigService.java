package fr.huiitre.tools.tools_core.config.service;

import fr.huiitre.tools.tools_core.config.dto.UserConfigRequest;
import fr.huiitre.tools.tools_core.config.repository.ConfigRepository;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    /**
     * Récupère la valeur de configuration pour un module et une clé donnés,
     * en retournant la configuration spécifique à l'utilisateur ou la valeur par défaut.
     *
     * @param entity Le module (ex: "user"). Le suffixe "_has_config" sera ajouté.
     * @param key La clé de configuration (ex: "TIME_EXPIRED_TOKEN")
     * @param userId L'identifiant de l'utilisateur (0 pour la config par défaut)
     * @return La valeur de la configuration ou false si introuvable
     * @throws Exception si la requête échoue ou la configuration n'est pas trouvée
     */
    public String getConfig(String entity, String key, Integer userId) throws Exception {
        // Dans la version PHP, la variable entity est transformée en entity_has_config,
        // mais ici nous supposons que la table user_has_config est utilisée directement.
        String configValue = configRepository.findConfigValue(key, userId);
        if (configValue == null) {
            throw new Exception("Configuration non trouvée pour module '" + entity + "' et clé '" + key + "'");
        }
        return configValue;
    }

    public List<UserConfigRequest> getUserConfig(Integer userId) throws Exception {
        List<Map<String, Object>> results = configRepository.findUserConfig(userId);
        List<UserConfigRequest> dtoList = new ArrayList<>();
        for (Map<String, Object> row : results) {
            UserConfigRequest dto = new UserConfigRequest(
                (Integer) row.get("idconfig_category"),
                (String) row.get("code_config_category"),
                (String) row.get("name_config_category"),
                (Integer) row.get("idconfig_subcategory"),
                (String) row.get("code_config_subcategory"),
                (String) row.get("name_config_subcategory"),
                (Integer) row.get("idconfig"),
                (String) row.get("config_name"),
                (String) row.get("config_code"),
                (String) row.get("config_value"),
                (String) row.get("config_comments"),
                (String) row.get("config_type")
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
}
