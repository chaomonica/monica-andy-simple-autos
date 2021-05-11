package com.galvanize.simpleautos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutosController.class)
public class AutosControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutosService autosService;
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

         when(autosService.getAutomobiles("green")).thenReturn(new AutoList(automobiles));

         mockMvc.perform(get("/api/autos?color=green"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.automobileList", hasSize(1)));
     }

     /*
         GET ("/api/autos?make=Toyota"):
            - Status code: 200
            - returns a list of Toyota automobiles

            - Status code: 204
            - returns "No automobiles found" message

          GET ("/api/autos?make=Toyota&color=green"):
            - Status code: 200
            - returns a list of green Toyota automobiles

            - Status code: 204
            - returns "No automobiles found" message
     */

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

         POST ("/api/autos"):
            - Status code: 200
            - returns the body back

            - Status code: 400
            - returns error message
                Ex: {
                      "message": "BAD REQUEST"
                    }
     */

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

    /*
        PATCH ("/api/autos/{vin}")
        Request Params: vin (required)
        Request Body: Details to be patched. Only Color and Owner are updateable.
            Ex: {
                  "color": "string",
                  "owner": "string"
                }

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

            - Status code 400
            - Returns error message
                Ex: {
                      "message": "Bad Request"
                    }
     */

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
