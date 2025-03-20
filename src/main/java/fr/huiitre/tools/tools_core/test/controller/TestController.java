package fr.huiitre.tools.tools_core.test.controller;

import fr.huiitre.tools.tools_core.user.model.User;
import fr.huiitre.tools.tools_core.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public List<User> getUsers() {
        // Appelle le repository pour exécuter le select * from user et renvoie la liste des utilisateurs en JSON
        return testRepository.getAllUsers();
    }
}
