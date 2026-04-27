package api.base;

import static io.restassured.RestAssured.given;

public class BaseRequests {

    public static <T> T sendGetRequestWithPathParamsAndGetResponse(Class<T> responseClass, String... pathParams) {
        return given().when().get("/users/{user_id}", pathParams).then().statusCode(200).extract().as(responseClass);
    }

}
