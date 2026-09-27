import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ExecuteAnyScript {
    public static void main(String[] args) {
        Response response = given().baseUri("http://localhost:8081")
                .when()
                .get("/api/clients")
                .then().statusCode(200).extract().response();
        response.prettyPrint();
    }
}
