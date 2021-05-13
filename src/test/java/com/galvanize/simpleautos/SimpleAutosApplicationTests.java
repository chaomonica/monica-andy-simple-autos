package com.galvanize.simpleautos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleAutosApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    AutosRepository autosRepository;

    Random random = new Random();
    List<Automobile> testAutos;
    @BeforeEach
    void setUp() {
        testAutos = new ArrayList<>();
        Automobile auto;
        String[] colors = {"RED", "BLUE", "GREEN", "ORANGE", "YELLOW", "BLACK", "BROWN"};

        for (int i = 0; i < 50; i++) {
            if (i % 3 == 0) {
                auto = new Automobile(1967, "Ford", "Mustang", colors[random.nextInt(7)], "John Doe", "AABBCC"+(i*13));
            } else if ((i & 2) == 0) {
                auto = new Automobile(2000, "Dodge", "Viper", colors[random.nextInt(7)], "John Doe", "VVBBXX"+(i*12));
            } else {
                auto = new Automobile(2020, "Audi", "Quattro", colors[random.nextInt(7)], "John Doe", "VVBBAA"+(i*12));
            }
            testAutos.add(auto);
        }
        autosRepository.saveAll(testAutos);
    }

    @AfterEach
    void tearDown() {
        autosRepository.deleteAll();
    }

	@Test
	void contextLoads() {
	}

    @Test
    void getAutos_exists_returnsAutoList() {
        ResponseEntity<AutoList> response = testRestTemplate.getForEntity("/api/autos", AutoList.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isEmpty()).isFalse();
        for (Automobile auto : response.getBody().getAutomobileList()) {
            System.out.println(auto);
        }
    }
}
