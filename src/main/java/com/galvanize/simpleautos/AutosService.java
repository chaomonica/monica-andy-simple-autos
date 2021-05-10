package com.galvanize.simpleautos;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutosService {
    private AutoList automobiles;

    public AutosService() {

    }

    public void setAutomobiles(AutoList automobiles) {
        this.automobiles = automobiles;
    }

    public AutoList getAutomobiles() {
        return automobiles;
    }

    public AutoList getAutomobiles(String color) {
        List<Automobile> result = new ArrayList<>();
        List<Automobile> list = automobiles.getAutomobileList(); 
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getColor().equals(color)) {
                result.add(list.get(i));
            }
        }
        return new AutoList(result);
//        automobiles.getAutomobileList(color);
    }
}
