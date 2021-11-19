package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PetResourceTest {

	@Test
    public void testGetPetsEndpoint() {
        given()
          .when().get("/v1/pets")
          .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void testGetPetEndpoint() {
        Response response =
                given()
                .pathParam("petId", 1)
                .when()
                .get("/v1/pets/{petId}")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(1, response.jsonPath().getInt("petId"));
    }

    @Test
    public void testGetPetInvalidIdEndpoint() {
        given()
                .pathParam("petId", 5)
                .when()
                .get("/v1/pets/{petId}")
                .then()
                .assertThat().statusCode(404);
    }

    @Test
    public void testSearchByNameEndpoint() {
        given()
                .pathParam("petName", "Tom")
                .when()
                .get("/v1/pets/name/{petName}")
                .then()
                .assertThat().statusCode(200)
                .body("petName", everyItem(equalTo("Tom")));
    }

    @Test
    public void testSearchByAgeEndpoint() {
        given()
                .pathParam("petAge", 2)
                .when()
                .get("/v1/pets/age/{petAge}")
                .then()
                .assertThat().statusCode(200)
                .body("petAge", everyItem(equalTo(2)));
    }

    @Test
    public void testAddPetEndpoint() {
        given()
                .contentType("application/json")
                .body("{\"petId\":4,\"petAge\":5, \"petName\":\"Sally\", \"petType\":\"Dog\" }")
                .when()
                .post("/v1/pets/add")
                .then()
                .assertThat().statusCode(201);
    }

    @Test
    public void testAddPetDuplicateIdEndpoint() {
        given()
                .contentType("application/json")
                .body("{\"petId\":1,\"petAge\":5, \"petName\":\"Sally\", \"petType\":\"Dog\" }")
                .when()
                .post("/v1/pets/add")
                .then()
                .assertThat().statusCode(409);
    }

    @Test
    public void testUpdatePetEndpoint() {
        given()
                .pathParam("petId", 3)
                .contentType("application/json")
                .body("{\"petId\":3,\"petAge\":1, \"petName\":\"Scruffy\", \"petType\":\"Bird\" }")
                .when()
                .put("/v1/pets/update/{petId}")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void testDeletePetEndpoint() {
        given()
                .pathParam("petId", 2)
                .when()
                .delete("/v1/pets/delete/{petId}")
                .then()
                .assertThat().statusCode(200);
    }

}