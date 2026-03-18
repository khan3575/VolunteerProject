package com.sakib.bubt.volunternetwork2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AboutUsController {
    @GetMapping("aboutus")
    public  String Us()
    {
        return "aboutus";
    }

}
