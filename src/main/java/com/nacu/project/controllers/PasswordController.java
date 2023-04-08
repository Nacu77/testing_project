package com.nacu.project.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nacu.project.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordController
{
    private final PasswordService service;

    @Autowired
    public PasswordController(PasswordService service) {
        this.service = service;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String validatePassword(@RequestBody String json) throws JsonProcessingException
    {
        String password = new ObjectMapper().readValue(json, ObjectNode.class).get("password").asText();
        return service.validatePassword(password);
    }
}
