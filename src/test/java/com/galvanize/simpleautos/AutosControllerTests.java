package com.galvanize.simpleautos;

public class AutosControllerTests {
    /*
        GET ("/api/autos")
        Request Params: color (optional), make(optional)

        GET ("/api/autos"):
            - Status code: 200
            - returns a list of automobiles

            - Status code: 204
            - returns "No automobiles found" message

        GET ("/api/autos?color=green"):
            - Status code: 200
            - returns a list of green automobiles

            - Status code: 204
            - returns "No automobiles found" message

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
