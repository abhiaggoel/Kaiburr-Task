package com.abhinav.kaiburr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "API Test Root GET Method")
public class HomeRestController {
    @GetMapping("/")
    @Operation(summary = "API Test")

    public String Home(){
        return "Api Working Fine";
    }
}
