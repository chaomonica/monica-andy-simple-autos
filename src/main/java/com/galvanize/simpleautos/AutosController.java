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
    public ResponseEntity<AutoList> getAutos(@RequestParam(required = false) String color, @RequestParam(required = false) String make){
        AutoList temp;
        if (color == null && make == null) {
            temp = autosService.getAutomobiles();          
        } else if (color != null){
            temp = autosService.getAutomobilesByColor(color);
        } else {
            temp = autosService.getAutomobilesByMake(make);
        }
        return temp.isEmpty() ? ResponseEntity.noContent().build() :ResponseEntity.ok(temp);
    }
}
