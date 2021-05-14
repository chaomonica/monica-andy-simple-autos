package com.galvanize.simpleautos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class AutoList {

    private List<Automobile> automobileList;

    public AutoList() {
         this.automobileList = new ArrayList<>();
     }

    public AutoList(List<Automobile> automobileList) {
        this.automobileList = automobileList;
    }

    public List<Automobile> getAutomobileList() {
        return automobileList;
    }
//    public AutoList getAutomobileList(String color) {
//         AutoList list = new AutoList();
//         for (int i = 0; i < automobileList.size(); i++) {
//             if (automobileList.get(i).getColor().equals(color)) {
//                 list.add(automobileList.get(i));
//             }
//         }
//    }

    public void setAutomobileList(List<Automobile> automobileList) {
        this.automobileList = automobileList;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return this.automobileList.size() == 0;
    }

//    public int size() {
//         return automobileList.size();
//    }
}
