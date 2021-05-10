package com.galvanize.simpleautos;

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

    public void setAutomobileList(List<Automobile> automobileList) {
        this.automobileList = automobileList;
    }
}
