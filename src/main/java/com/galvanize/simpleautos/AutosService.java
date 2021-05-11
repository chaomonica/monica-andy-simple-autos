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

    public AutoList getAutomobilesByColor(String color) {
        List<Automobile> result = new ArrayList<>();
        List<Automobile> list = automobiles.getAutomobileList(); 
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getColor().equals(color)) {
                result.add(list.get(i));
            }
        }
        return new AutoList(result);
    }

    public AutoList getAutomobilesByMake(String make) {
        List<Automobile> result = new ArrayList<>();
        List<Automobile> list = automobiles.getAutomobileList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMake().equals(make)) {
                result.add(list.get(i));
            }
        }
        return new AutoList(result);
    }

    public AutoList getAutomobilesByColorAndMake(String color, String make) {
        List<Automobile> result = new ArrayList<>();
        List<Automobile> list = automobiles.getAutomobileList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getColor().equals(color) && list.get(i).getMake().equals(make)) {
                result.add(list.get(i));
            }
        }
        return new AutoList(result);
    }
}
