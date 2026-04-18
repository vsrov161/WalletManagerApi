package com.vicsergeev.WalletManagerApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Created by Vic
 * 16.04.2026
 */
@Controller
@RequestMapping("/status")
public class WebController {
    @GetMapping
    public String showHealthPage() {
        return "status";
    }
}
