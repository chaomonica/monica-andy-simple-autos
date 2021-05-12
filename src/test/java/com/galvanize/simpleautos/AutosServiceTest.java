package com.galvanize.simpleautos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
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
        List<Automobile> automobiles = new ArrayList<>();
        automobiles.add(new Automobile(2020, "Ford", "Mustang", "GREEN", "John Doe", "7F03Z01025"));

        when(autosRepository.findByColor(anyString()))
                .thenReturn(automobiles);
        AutoList autosList = autosService.getAutomobilesByColor("GREEN");

        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isFalse();
    }

    @Test
    void getAutomobilesByMake() {
        List<Automobile> automobiles = new ArrayList<>();
        automobiles.add(new Automobile(2020, "Ford", "Mustang", "GREEN", "John Doe", "7F03Z01025"));

        when(autosRepository.findByMake(anyString()))
                .thenReturn(automobiles);
        AutoList autosList = autosService.getAutomobilesByMake("Mustang");

        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isFalse();
    }

    @Test
    void getAutomobilesByColorAndMake() {
        List<Automobile> automobiles = new ArrayList<>();
        automobiles.add(new Automobile(2020, "Ford", "Mustang", "GREEN", "John Doe", "7F03Z01025"));

        when(autosRepository.findByColorAndMake(anyString(), anyString()))
                .thenReturn(automobiles);
        AutoList autosList = autosService.getAutomobilesByColorAndMake("GREEN", "Mustang");

        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isFalse();

    }

    @Test
    void addAutomobile() {
        Automobile automobileToAdd = new Automobile(2020, "Toyota", "Camry", "GREEN", "John Doe", "7F03Z01025");

        when(autosRepository.save(any(Automobile.class)))
                .thenReturn(automobileToAdd);
        Automobile auto = autosService.addAutomobile(automobileToAdd);

        assertThat(auto).isNotNull();
        assertThat(auto.getMake()).isEqualTo("Toyota");
    }

    @Test
    void getAutomobileWithVin_returnsAutomobile() {
        Automobile automobileWithVin = new Automobile(2020, "Toyota", "Camry", "GREEN", "John Doe", "7F03Z01025");

        when(autosRepository.findByVin(anyString()))
                .thenReturn(Optional.of(automobileWithVin));
        Automobile auto = autosService.getAutomobileWithVin("helloworld");

        assertThat(auto).isNotNull();
        assertThat(auto.getMake()).isEqualTo("Toyota");
    }

    @Test
    void updateAutomobileWithVin() {
        Automobile automobileWithVin = new Automobile(2020, "Toyota", "Camry", "GREEN", "John Doe", "7F03Z01025");

        when(autosRepository.findByVin(anyString()))
                .thenReturn(Optional.of(automobileWithVin));
        when(autosRepository.save(any(Automobile.class)))
                .thenReturn(automobileWithVin);
        Automobile auto = autosService.updateAutomobileWithVin(automobileWithVin.getVin(), "BLACK", "Jane Doe");

        assertThat(auto).isNotNull();
        assertThat(auto.getMake()).isEqualTo("Toyota");
    }

    @Test
    void deleteAuto_byVin() {
        Automobile automobileWithVin = new Automobile(2020, "Toyota", "Camry", "GREEN", "John Doe", "7F03Z01025");

        when(autosRepository.findByVin(anyString())).thenReturn(Optional.of(automobileWithVin));
        autosService.deleteAuto(automobileWithVin.getVin());

        verify(autosRepository).delete(any(Automobile.class));
    }

    @Test
    void deleteAuto_byVin_notExists() {

        when(autosRepository.findByVin(anyString())).thenReturn(Optional.empty());

        assertThatExceptionOfType(AutoNotFoundException.class)
            .isThrownBy(() -> {
                autosService.deleteAuto("VIN NOT EXIST");
            });
    }
}
