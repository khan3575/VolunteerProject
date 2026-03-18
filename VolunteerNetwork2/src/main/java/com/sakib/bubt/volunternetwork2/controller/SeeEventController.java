package com.sakib.bubt.volunternetwork2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event/")
public class SeeEventController {
    @GetMapping("seeEvent")
    public String showEvent()
    {
        return "seeevents";
    }

}
