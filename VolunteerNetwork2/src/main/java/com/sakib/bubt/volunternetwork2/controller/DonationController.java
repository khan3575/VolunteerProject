package com.sakib.bubt.volunternetwork2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DonationController {
    @GetMapping("donations")
    public String donations() {
        return "donations";
    }
}
