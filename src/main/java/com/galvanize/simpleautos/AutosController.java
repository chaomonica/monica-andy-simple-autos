package com.galvanize.simpleautos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutosController {
    AutosService autosService;

    public AutosController(AutosService autoservice) {
        this.autosService = autoservice;
    }

    @GetMapping("/api/autos")
    public ResponseEntity<AutoList> getAutos(@RequestParam(required = false) String color){
        if (color == null) {
            AutoList temp = autosService.getAutomobiles();
            return temp.isEmpty() ? ResponseEntity.noContent().build() :ResponseEntity.ok(temp);
        } else {
            AutoList temp = autosService.getAutomobiles(color);
            return temp.isEmpty() ? ResponseEntity.noContent().build() :ResponseEntity.ok(temp);           
        }        
    }
}
