package com.galvanize.simpleautos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
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

    @Test
    void getAutos_byColorAndMake_exists_returnsColorAndMakeAutoList(){
        int seq = random.nextInt(50);
        String color = testAutos.get(seq).getColor();
        String make = testAutos.get(seq).getMake();

        ResponseEntity<AutoList> response = testRestTemplate.getForEntity(
        String.format("/api/autos?color=%s&make=%s", color, make), AutoList.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isEmpty()).isFalse();
        assertThat(response.getBody().getAutomobileList().size()).isGreaterThanOrEqualTo(1);
        for (Automobile auto : response.getBody().getAutomobileList()) {
           System.out.println(auto);
        }
    }

    @Test
    void addAuto_ReturnsAuto() {
        Automobile automobileToAdd = new Automobile(2020, "Toyota", "Camry", "GREEN", "John Doe", "7F03Z01025");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Automobile> request = new HttpEntity<>(automobileToAdd, headers);

        ResponseEntity<Automobile> response = testRestTemplate.postForEntity("/api/autos", request, Automobile.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getVin()).isEqualTo(automobileToAdd.getVin());
    }

    @Test
    void patchAuto_ReturnsAuto() {
        testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        Automobile automobileToAdd = new Automobile(2020, "Toyota", "Camry", "GREEN", "John Doe", "7F03Z01025");

        Automobile automobileToPatch = new Automobile(2020, "Toyota", "Camry", "GREEN", "John Doe", "7F03Z01025");
        UpdateOwnerRequest update = new UpdateOwnerRequest("BLUE", "Bob Smith");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        //post request to add auto to patch later
        HttpEntity<Automobile> postRequest = new HttpEntity<>(automobileToAdd, headers);
        testRestTemplate.postForEntity("/api/autos", postRequest, Automobile.class);

        //patch request
        HttpEntity<UpdateOwnerRequest> request = new HttpEntity<>(update, headers);
        ResponseEntity<Automobile> response = testRestTemplate.exchange("/api/autos/7F03Z01025", HttpMethod.PATCH, request, Automobile.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getVin()).isEqualTo(automobileToPatch.getVin());
    }

    @Test
    void deleteAuto_returnsNoContent() {
        Automobile automobileToAdd = new Automobile(2020, "Toyota", "Camry", "GREEN", "John Doe", "7F03Z01025");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        //post request to add auto to delete later
        HttpEntity<Automobile> postRequest = new HttpEntity<>(automobileToAdd, headers);
        testRestTemplate.postForEntity("/api/autos", postRequest, Automobile.class);

        testRestTemplate.delete("/api/autos/7F03Z01025");

        ResponseEntity<Automobile> response = testRestTemplate.getForEntity("/api/autos/7F03Z01025", Automobile.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
