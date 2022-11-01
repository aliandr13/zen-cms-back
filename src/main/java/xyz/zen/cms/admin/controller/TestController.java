package xyz.zen.cms.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(path = "/ping")
    public String ping() {
        return "pong!\n";
    }

}
