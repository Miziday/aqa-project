package api.base;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApiMethods {

    public static <T> T sendGetWithPathParamsAndExtract(Class<T> responseClass, String endPoint, String... pathParams) {
        return given().when().get(endPoint, pathParams).then().statusCode(200).extract().as(responseClass);
    }

    public static Response sendGetRequestWithPathParams(String endPoint, String... pathParams) {
        return given().when().get(endPoint, pathParams);
    }

}
