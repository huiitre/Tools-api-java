package fr.huiitre.tools.tools_dofus_unity.item.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dofus/item")
public class ItemController {

    @GetMapping
    public String getItem() {
        return "Item endpoint reached!";
    }
}