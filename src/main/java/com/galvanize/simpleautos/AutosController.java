package com.galvanize.simpleautos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutosController {
    AutosService autosService;
    
    public AutosController(AutosService autoservice) {
        this.autosService = autoservice;
    }
    
    @GetMapping("/api/autos")
    public AutoList getAutos(){
        return autosService.getAutomobiles();
    }
}
