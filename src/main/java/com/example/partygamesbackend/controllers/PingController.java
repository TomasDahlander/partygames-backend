package com.example.partygamesbackend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2022-08-24 <br>
 * Time: 23:00 <br>
 * Project: partygames-backend <br>
 */
@CrossOrigin
@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping()
    public String ping(){
        return "OK";
    }
}
