package com.sakib.bubt.volunternetwork2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donations/")
public class DonateItemController {
    @GetMapping("donateItems")
    public String DonateItemController() {
        return "donateitems";
    }
}
