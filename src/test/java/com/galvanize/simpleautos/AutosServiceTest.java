package com.galvanize.simpleautos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutosServiceTest {

    private AutosService autosService;

    @Mock
    AutosRepository autosRepository;

    @BeforeEach
    void Setup() {
        autosService = new AutosService(autosRepository);
    }

    @Test
    void setAutomobiles() {
    }

    @Test
    void getAutomobiles() {
        // Automobile automobile = new Automobile(2020, "Ford", "Toyota", "GREEN", "John Doe", "7F03Z01025");
        List<Automobile> automobiles = new ArrayList<>();

        automobiles.add(new Automobile(2020, "Ford", "Mustang", "GREEN", "John Doe", "7F03Z01025"));
        when(autosRepository.findAll()).thenReturn(automobiles);
        AutoList autoList = autosService.getAutomobiles();

        assertThat(autoList).isNotNull();
        assertThat(autoList.isEmpty()).isFalse();
    }

    @Test
    void getAutomobilesByColor() {
    }

    @Test
    void getAutomobilesByMake() {
    }

    @Test
    void getAutomobilesByColorAndMake() {
    }

    @Test
    void addAutomobile() {
    }

    @Test
    void getAutomobileWithVin() {
    }

    @Test
    void updateAutomobileWithVin() {
    }

    @Test
    void deleteAuto() {
    }
}
