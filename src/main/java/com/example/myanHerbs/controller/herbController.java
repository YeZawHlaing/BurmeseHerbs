package com.example.myanHerbs.controller;


import com.example.myanHerbs.service.herbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/herb")
public class herbController {

    @Autowired
    private final herbService herbService;

    public herbController(com.example.myanHerbs.service.herbService herbService) {
        this.herbService = herbService;
    }

}
