package com.demoqa.automation.business.services.api;

import com.demoqa.automation.core.pojo.account.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.demoqa.automation.core.testdata.BaseData.BASE_API_URL;
import static com.demoqa.automation.core.testdata.BaseData.BASE_PATH_ACCOUNT;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;

public class AccountService extends BaseService {

    public Response createUser(User user) {
        Response response = getRequestSpecification().body(user, ObjectMapperType.JACKSON_2).post("/User");
        verifyResponseCode(response, SC_CREATED);
        return response;
    }

    private static RequestSpecification getRequestSpecification() {
        return given().spec(setRequestSpecification());
    }

    private static RequestSpecification setRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_API_URL)
                .setBasePath(BASE_PATH_ACCOUNT)
                .build().log().all().contentType(ContentType.JSON).accept(ContentType.JSON);
    }
}
