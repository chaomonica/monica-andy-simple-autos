package com.galvanize.simpleautos;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

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
        return new AutoList(autosRepository.findAll());
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
        return null;
    }
    
    public Automobile addAutomobile(Automobile auto) {
        return null;
    }

    public Automobile getAutomobileWithVin(String vin) {
        return null;
    }

    public Automobile updateAutomobileWithVin(String vin, String color, String owner) {
        return null;
    }

    public void deleteAuto(String vin) {
    }
}
