package org.ibs.cds.gode.terminal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TerminalController {

    @RequestMapping("/")
    public String home(){
        return "terminal";
    }
}
