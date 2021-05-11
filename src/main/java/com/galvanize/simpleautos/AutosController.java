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

    @PatchMapping("/api/autos/{vin}")
    public ResponseEntity<Automobile> updateAuto(@PathVariable String vin,
                                 @RequestBody UpdateOwnerRequest request) {
        Automobile temp = autosService.updateAutomobileWithVin(vin, request.getColor(), request.getOwner());

        if (vin.equals(temp.getVin()) && request.getColor().equals(temp.getColor()) && request.getOwner().equals(temp.getOwner())) {
            return ResponseEntity.ok(temp);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/api/autos/{vin}")
    public ResponseEntity deleteAuto(@PathVariable String vin){
        try {
             autosService.deleteAuto(vin);
        } catch (AutoNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.accepted().build();

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidAutoException(InvalidAutoException exception) {

    }
}
