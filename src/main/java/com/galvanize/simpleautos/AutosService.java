package com.galvanize.simpleautos;

import org.springframework.stereotype.Service;

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
    
}
