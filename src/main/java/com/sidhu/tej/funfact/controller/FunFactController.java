package com.sidhu.tej.funfact.controller;

import com.sidhu.tej.funfact.service.FunFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fun-fact")
public class FunFactController {

    @Autowired
    private FunFactService funFactService;

    @GetMapping("{category}")
    public String getFact(@PathVariable String category){
        return funFactService.getFact(category);
    }
}
