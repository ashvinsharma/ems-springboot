package com.ems.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping
    public ResponseEntity notFound() {
        return ResponseEntity.status(404).body(new ObjectMapper()
                .createObjectNode()
                .put("content", "Service does not exist")
        );
    }
}
