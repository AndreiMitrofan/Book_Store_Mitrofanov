package com.demoqa.automation.business.services.api;

import io.restassured.response.Response;
import org.testng.Assert;

public class BaseService {

    protected static void verifyResponseCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Status code differs for response: " + response.prettyPrint());
    }
}
