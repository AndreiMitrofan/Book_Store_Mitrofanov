package com.demoqa.automation.business.api.services;

import com.demoqa.automation.business.api.BaseService;
import com.demoqa.automation.core.pojo.account.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.demoqa.automation.core.testdata.BaseData.BASE_URL;
import static com.demoqa.automation.core.testdata.BaseData.BASE_PATH_ACCOUNT;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class AccountService extends BaseService {

    public Response createUser(User user) {
        Response response = getRequestSpecification().body(user, ObjectMapperType.JACKSON_2).post("/User");
        verifyResponseCode(response, SC_CREATED);
        return response;
    }

    public Response generateTokenForUser(User user) {
        Response response = getRequestSpecification().body(user, ObjectMapperType.JACKSON_2).post("/GenerateToken");
        verifyResponseCode(response, SC_OK);
        return response;
    }

    public Response getUser(String userId, String token) {
        Response response = getRequestSpecificationWithToken(token).get("/User/{UUID}", userId);
        verifyResponseCode(response, SC_OK);
        return response;
    }

    public Response deleteUser(String userId, String token) {
        Response response = getRequestSpecificationWithToken(token).delete("/User/{UUID}", userId);
        verifyResponseCode(response, SC_NO_CONTENT);
        return response;
    }

    private static RequestSpecification getRequestSpecification() {
        return given().spec(setRequestSpecification());
    }

    private static RequestSpecification getRequestSpecificationWithToken(String token) {
        return given().spec(setRequestSpecificationWithToken(token));
    }

    private static RequestSpecification setRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH_ACCOUNT)
                .build().log().all().contentType(ContentType.JSON).accept(ContentType.JSON);
    }

    private static RequestSpecification setRequestSpecificationWithToken(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(BASE_PATH_ACCOUNT)
                .addHeader("Authorization", "Bearer " + token)
                .build().log().all().contentType(ContentType.JSON).accept(ContentType.JSON);
    }
}
