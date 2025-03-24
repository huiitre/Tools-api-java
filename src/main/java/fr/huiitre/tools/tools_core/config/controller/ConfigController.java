package fr.huiitre.tools.tools_core.config.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.huiitre.tools.common.BaseController;
import fr.huiitre.tools.tools_core.config.service.ConfigService;
import fr.huiitre.tools.tools_core.user.model.User;

@RestController
@RequestMapping("/core/config")
public class ConfigController extends BaseController {

    @Autowired
    private ConfigService configService;
}