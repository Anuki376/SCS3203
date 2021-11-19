package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PetTypeResourceTest {

    @Test
    public void testGetPetTypesEndpoint() {
        given()
                .when().get("/v1/pets-types")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void testGetPetTypeEndpoint() {
        Response response =
                given()
                        .pathParam("petTypeId", 1)
                        .when()
                        .get("/v1/pets-types/{petTypeId}")
                        .then()
                        .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(1, response.jsonPath().getInt("petTypeId"));
    }

    @Test
    public void testAddPetTypeEndpoint() {
        given()
                .contentType("application/json")
                .body("{\"petTypeName\":\"Bird\"}")
                .when()
                .post("/v1/pets-types/add")
                .then()
                .assertThat().statusCode(201);
    }

    @Test
    public void testDeletePetTypeEndpoint() {
        given()
                .pathParam("petTypeId", 3)
                .when()
                .delete("/v1/pets-types/delete/{petTypeId}")
                .then()
                .assertThat().statusCode(200);
    }

}
