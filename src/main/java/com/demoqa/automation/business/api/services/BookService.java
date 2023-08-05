package com.demoqa.automation.business.api.services;

import com.demoqa.automation.business.api.BaseService;
import com.demoqa.automation.core.pojo.book.requests.AddBooksRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.demoqa.automation.core.testdata.BaseData.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class BookService extends BaseService {
    public Response getBooks() {
        Response response = getRequestSpecification().get("/Books");
        verifyResponseCode(response, SC_OK);
        return response;
    }

    public Response addBooks(AddBooksRequest request, String token) {
        Response response = getRequestSpecificationWithToken(token).body(request,
                ObjectMapperType.JACKSON_2).post("/Books");
        verifyResponseCode(response, SC_CREATED);
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
                .setBaseUri(BASE_API_URL)
                .setBasePath(BASE_PATH_BOOK_STORE)
                .build().log().all().contentType(ContentType.JSON).accept(ContentType.JSON);
    }

    private static RequestSpecification setRequestSpecificationWithToken(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_API_URL)
                .setBasePath(BASE_PATH_BOOK_STORE)
                .addHeader("Authorization", "Bearer " + token)
                .build().log().all().contentType(ContentType.JSON).accept(ContentType.JSON);
    }
}
