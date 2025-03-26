package fr.huiitre.tools.tools_core.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.huiitre.tools.annotations.RequireToken;
import fr.huiitre.tools.common.controller.BaseController;
import fr.huiitre.tools.tools_core.config.dto.UserConfigRequest;
import fr.huiitre.tools.tools_core.config.service.ConfigService;
import fr.huiitre.tools.tools_core.module.dto.UserModuleRequest;
import fr.huiitre.tools.tools_core.module.service.ModuleService;
import fr.huiitre.tools.tools_core.user.model.User;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/core/user")
public class UserController extends BaseController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/getuserconfig")
    @RequireToken(true)
    public ResponseEntity<?> getUserConfig(@RequestAttribute("user") User user) {
        try {
            List<UserConfigRequest> configList = configService.getUserConfig(user.getId());
            return ResponseEntity.ok(Map.of("data", configList));
        } catch(Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of( "msg", e.getMessage()));
        }
    }

    @GetMapping("/getusermodule")
    @RequireToken(true)
    public ResponseEntity<?> getUserModule(@RequestAttribute("user") User user) {
        try {
            List<UserModuleRequest> moduleList = moduleService.getUserModule(user.getId());
            return ResponseEntity.ok(Map.of("data", moduleList));
        } catch(Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of( "msg", e.getMessage()));
        }
    }
}