package com.abhinav.kaiburr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
    @GetMapping("/")
    public String Home(){
        return "Api Working Fine";
    }
}
