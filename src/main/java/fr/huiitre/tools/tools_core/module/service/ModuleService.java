package fr.huiitre.tools.tools_core.module.service;

import fr.huiitre.tools.tools_core.module.dto.UserModuleRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.huiitre.tools.tools_core.module.repository.ModuleRepository;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
    
    public List<UserModuleRequest> getUserModule(Integer userId) throws Exception {
        List<Map<String, Object>> results = moduleRepository.findUserModule(userId);

        List<UserModuleRequest> dtoList = new ArrayList<>();

        for (Map<String, Object> row : results) {
            UserModuleRequest dto = new UserModuleRequest(
                (Integer) row.get("idmodule"),
                (String) row.get("name"),
                (String) row.get("code"),
                (Integer) row.get("moduleRole")
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
}