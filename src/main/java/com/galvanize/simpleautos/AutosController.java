package com.galvanize.simpleautos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        } else if (color != null && make != null){
            temp = autosService.getAutomobilesByColorAndMake(color, make);
        } else if (make != null){
            temp = autosService.getAutomobilesByMake(make);
        } else {
            temp = autosService.getAutomobilesByColor(color);
        }
        return temp.isEmpty() ? ResponseEntity.noContent().build() :ResponseEntity.ok(temp);
    }

    @PostMapping("/api/autos")
    public Automobile addAuto(@RequestBody Automobile auto) {
        return autosService.addAutomobile(auto);
    }
}
