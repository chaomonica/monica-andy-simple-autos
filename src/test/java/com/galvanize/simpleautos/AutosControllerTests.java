package com.galvanize.simpleautos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.Returns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutosController.class)
public class AutosControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutosService autosService;

    ObjectMapper mapper = new ObjectMapper();
    /*
        GET ("/api/autos")
        Request Params: color (optional), make(optional)
    */

    /*
        GET ("/api/autos"):
            - Status code: 200
            - returns a list of automobiles

            - Status code: 204
            - returns "No automobiles found" message
    */
    @Test
    void getAutos_NoArgs_ReturnsListTest() throws Exception {
        List<Automobile> automobiles = new ArrayList<>();

        automobiles.add(new Automobile(2020, "Ford", "Mustang", "GREEN", "John Doe", "7F03Z01025"));

        when(autosService.getAutomobiles()).thenReturn(new AutoList(automobiles));

        mockMvc.perform(get("/api/autos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobileList", hasSize(1)));
    }

    @Test
    void getAutos_NoArgs_ReturnsNoContentTest() throws Exception {
        List<Automobile> automobiles = new ArrayList<>();

        when(autosService.getAutomobiles()).thenReturn(new AutoList(automobiles));

        mockMvc.perform(get("/api/autos"))
                .andDo(print())
                .andExpect(status().isNoContent()) ;
    }
    /*
        GET ("/api/autos?color=green"):
            - Status code: 200
            - returns a list of green automobiles

            - Status code: 204
            - returns "No automobiles found" message
     */
     @Test
     void getAutos_colorGreen_ReturnsListOfGreenAutomobiles() throws Exception {
         List<Automobile> automobiles = new ArrayList<>();

         automobiles.add(new Automobile(2020, "Ford", "Mustang", "GREEN", "John Doe", "7F03Z01025"));

         when(autosService.getAutomobilesByColor("green")).thenReturn(new AutoList(automobiles));

         mockMvc.perform(get("/api/autos?color=green"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.automobileList", hasSize(1)));
     }

     @Test
     void getAutos_colorGreen_ReturnsNoContentIfNotFoundTest() throws Exception {
         List<Automobile> automobiles = new ArrayList<>();
         when(autosService.getAutomobilesByColor("green")).thenReturn(new AutoList(automobiles));

         mockMvc.perform(get("/api/autos?color=green"))
                 .andDo(print())
                 .andExpect(status().isNoContent()) ;
     }
     /*
         GET ("/api/autos?make=Toyota"):
            - Status code: 200
            - returns a list of Toyota automobiles

            - Status code: 204
            - returns "No automobiles found" message
     */
      @Test
      void getAutos_makeToyota_ReturnsListOfToyotaAutomobiles() throws Exception {
          List<Automobile> automobiles = new ArrayList<>();
          automobiles.add(new Automobile(2020, "Ford", "Toyota", "GREEN", "John Doe", "7F03Z01025"));

          when(autosService.getAutomobilesByMake("Toyota")).thenReturn(new AutoList(automobiles));

          mockMvc.perform(get("/api/autos?make=Toyota"))
                         .andDo(print())
                         .andExpect(status().isOk())
                         .andExpect(jsonPath("$.automobileList", hasSize(1)));
      }

    @Test
    void getAutos_makeToyota_ReturnsNoContentIfNotFoundTest() throws Exception {
        List<Automobile> automobiles = new ArrayList<>();
        when(autosService.getAutomobilesByMake("Toyota")).thenReturn(new AutoList(automobiles));

        mockMvc.perform(get("/api/autos?make=Toyota"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
     /*
          GET ("/api/autos?make=Toyota&color=green"):
            - Status code: 200
            - returns a list of green Toyota automobiles

            - Status code: 204
            - returns "No automobiles found" message
     */

    @Test
        void getAutos_makeToyota_colorGreen_ReturnsListOfGreenToyotaAutomobiles() throws Exception {
           List<Automobile> automobiles = new ArrayList<>();
           automobiles.add(new Automobile(2020, "Ford", "Toyota", "GREEN", "John Doe", "7F03Z01025"));

           when(autosService.getAutomobilesByColorAndMake("green", "Toyota")).thenReturn(new AutoList(automobiles));

           mockMvc.perform(get("/api/autos?color=green&make=Toyota"))
                          .andDo(print())
                          .andExpect(status().isOk())
                          .andExpect(jsonPath("$.automobileList", hasSize(1)));
        }

    @Test
    void getAutos_makeToyota_colorGreen_ReturnsNoContentIfNotFoundTest() throws Exception {
        List<Automobile> automobiles = new ArrayList<>();

        when(autosService.getAutomobilesByColorAndMake("green", "Toyota")).thenReturn(new AutoList(automobiles));

        mockMvc.perform(get("/api/autos?color=green&make=Toyota"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    /*
        POST ("/api/autos")
        Request body
            Ex: {
                  "year": 1967,
                  "make": "Ford",
                  "model": "Mustang",
                  "color": "RED",
                  "owner": "John Doe",
                  "vin": "7F03Z01025"
                }
    */
    /*
         POST ("/api/autos"):
            - Status code: 200
            - returns the body back

            - Status code: 400
            - returns error message
                Ex: {
                      "message": "BAD REQUEST"
                    }
     */
    @Test
    void addAutos_valid_ReturnsAutomobile() throws Exception {
        Automobile automobileToAdd = new Automobile(2020, "Ford", "Toyota", "GREEN", "John Doe", "7F03Z01025");

        when(autosService.addAutomobile(any(Automobile.class))).thenReturn(automobileToAdd);

        mockMvc.perform(post("/api/autos").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(automobileToAdd)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("make").value("Ford"));
    }

    @Test
    void addAutos_invalid_ReturnsBadRequest() throws Exception {
        Automobile automobileToAdd = new Automobile(2020, "Ford", "Toyota", "GREEN", "John Doe", "7F03Z01025");

        when(autosService.addAutomobile(any(Automobile.class))).thenThrow(InvalidAutoException.class);

        mockMvc.perform(post("/api/autos").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(automobileToAdd)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    /*
        GET ("/api/autos/{vin}")
        Request Params: vin (required)

        GET ("/api/autos/{vin}"):
            - Status code: 200
            - returns vehicle
                Ex: {
                      "year": 1967,
                      "make": "Ford",
                      "model": "Mustang",
                      "color": "RED",
                      "owner": "John Doe",
                      "vin": "7F03Z01025"
                    }

            - Status code: 204
            - returns "Vehicle not found"
     */
    @Test
    void getAutomobiles_withVin_returnsAutomobile() throws Exception {
        Automobile automobileWithVin = new Automobile(2020, "Ford", "Toyota", "GREEN", "John Doe", "7F03Z01025");

        when(autosService.getAutomobileWithVin(anyString())).thenReturn(automobileWithVin);
        mockMvc.perform(get("/api/autos/" + automobileWithVin.getVin()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("vin").value(automobileWithVin.getVin()));
    }

    @Test
    void getAutomobile_withVin_returnsNoContent() throws Exception {
        Automobile automobileWithVin = new Automobile();

        when(autosService.getAutomobileWithVin(anyString())).thenReturn(automobileWithVin);
        mockMvc.perform(get("/api/autos/" + automobileWithVin.getVin()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    /*
        PATCH ("/api/autos/{vin}")
        Request Params: vin (required)
        Request Body: Details to be patched. Only Color and Owner are updateable.
            Ex: {
                  "color": "string",
                  "owner": "string"
                }

    */
    @Test
    void updateAutomobile_withObject_returnsAutomobile() throws Exception {
        Automobile automobileWithVin = new Automobile(2020, "Ford", "Toyota", "RED", "Bob", "7F03Z01025");
        when(autosService.updateAutomobileWithVin(anyString(), anyString(), anyString())).thenReturn(automobileWithVin);

        UpdateOwnerRequest updated= new UpdateOwnerRequest("RED", "Bob");

        mockMvc.perform(patch("/api/autos/" + automobileWithVin.getVin())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("color").value("RED"))
            .andExpect(jsonPath("owner").value("Bob"));

    }
    /*
        PATCH ("/api/autos/{vin}"):
            - Status code 200
            - Returns vehicle
                Ex: {
                      "year": 1967,
                      "make": "Ford",
                      "model": "Mustang",
                      "color": "RED",
                      "owner": "John Doe",
                      "vin": "7F03Z01025"
                    }

            - Status code 204
            - Returns "Vehicle not found" message
    */
    @Test
    void updateAutomobile_withVin_returnsNoContent() throws Exception {
        Automobile automobileWithVin = new Automobile();

        UpdateOwnerRequest updated= new UpdateOwnerRequest("RED", "Bob");

        when(autosService.updateAutomobileWithVin(anyString(), anyString(), anyString())).thenReturn(automobileWithVin);
        mockMvc.perform(patch("/api/autos/" + automobileWithVin.getVin())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    /*
            - Status code 400
            - Returns error message
                Ex: {
                      "message": "Bad Request"
                    }
     */

    @Test
    void updateAutomobile_withVin_returnsBadRequest() throws Exception {
        Automobile automobileWithVin = new Automobile();

        UpdateOwnerRequest updated= new UpdateOwnerRequest("RED", "Bob");

        when(autosService.updateAutomobileWithVin(anyString(), anyString(), anyString())).thenThrow(InvalidAutoException.class);
        mockMvc.perform(patch("/api/autos/" + automobileWithVin.getVin())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updated)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }


    /*
        DELETE ("/api/autos/{vin}")
        Request Params: vin (required)

        DELETE ("/api/autos/{vin}"):
            - Status code 202
            - Returns "Automobile delete request accepted" message

            - Status code 204
            - Returns "Vehicle not found" message
     */
}
