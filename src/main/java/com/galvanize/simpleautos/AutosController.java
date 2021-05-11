package com.galvanize.simpleautos;

import org.springframework.http.HttpStatus;
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

    @GetMapping("/api/autos/{vin}")
    public ResponseEntity<Automobile> getAutoWithVin(@PathVariable String vin) {
        Automobile temp = autosService.getAutomobileWithVin(vin);

        return temp.getVin() == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(temp);


    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidAutoException(InvalidAutoException exception) {

    }
}
