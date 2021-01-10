package dev.vasishta.invest.track.controller;

import dev.vasishta.invest.track.service.CrashHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crashHandler")
public class CrashHandlingController {

    @Autowired
    private CrashHandlingService crashHandlingService;

    @GetMapping("/crashRecovery")
    public String handleCrash() {
        crashHandlingService.handleCrash();
        return "Recovery is done";
    }
}
