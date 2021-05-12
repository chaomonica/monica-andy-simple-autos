package com.galvanize.simpleautos;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutosService {
    //private AutoList automobiles;
    AutosRepository autosRepository;

    public AutosService(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }
/*
    public AutosService(AutoList automobiles, AutosRepository autosRepository) {
            this.automobiles = automobiles;
            this.autosRepository = autosRepository;
    }*/

/*    public AutosService() {

    }*/

   /* public void setAutomobiles(AutoList automobiles) {
        this.automobiles = automobiles;
    }*/

    public AutoList getAutomobiles() {
        // query database - "select * from autos"
        // put result in a list
        // return a new AutoList automobiles
        List <Automobile> automobiles = autosRepository.findAll();

        if (!automobiles.isEmpty()) {
            return new AutoList(automobiles);
        }
        return null;
        //return automobiles;
    }

    public AutoList getAutomobilesByColor(String color) {
 /*       List<Automobile> result = new ArrayList<>();
        List<Automobile> list = automobiles.getAutomobileList(); 
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getColor().equals(color)) {
                result.add(list.get(i));
            }
        }
        return new AutoList(result);*/

        // return null;
        List<Automobile> automobiles = autosRepository.findByColor(color);
        if (!automobiles.isEmpty()) {
            return new AutoList(automobiles);
        }
        return null;
    }

    public AutoList getAutomobilesByMake(String make) {
/*        List<Automobile> result = new ArrayList<>();
        List<Automobile> list = automobiles.getAutomobileList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMake().equals(make)) {
                result.add(list.get(i));
            }
        }
        return new AutoList(result);*/
        List<Automobile> automobiles = autosRepository.findByMake(make);
        if (!automobiles.isEmpty()) {
            return new AutoList(automobiles);
        }
        return null;
    }

    public AutoList getAutomobilesByColorAndMake(String color, String make) {
/*        List<Automobile> result = new ArrayList<>();
        List<Automobile> list = automobiles.getAutomobileList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getColor().equals(color) && list.get(i).getMake().equals(make)) {
                result.add(list.get(i));
            }
        }
        return new AutoList(result);*/
        List<Automobile> automobiles = autosRepository.findByColorAndMake(color, make);
        if (!automobiles.isEmpty()) {
            return new AutoList(automobiles);
        }
        return null;
    }
    
    public Automobile addAutomobile(Automobile auto) {
        Automobile added = autosRepository.save(auto);
        if (added.isNotNull()) {
            return added;
        }
        return null;
    }

    public Automobile getAutomobileWithVin(String vin) {

        return autosRepository.findByVin(vin).orElse(null);
    }

    public Automobile updateAutomobileWithVin(String vin, String color, String owner) {
        Optional<Automobile> oFound = autosRepository.findByVin(vin);

        if (oFound.isPresent()) {
            oFound.get().setColor(color);
            oFound.get().setOwner(owner);
            return autosRepository.save(oFound.get());
        }
        return null;
    }

    public void deleteAuto(String vin) {
        Optional<Automobile> oFound = autosRepository.findByVin(vin);
        if (oFound.isPresent()) {
            autosRepository.delete(oFound.get());
        }
    }
}
