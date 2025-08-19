package tests;

import base.BaseAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.AuthService;
import utils.RetryAnalyzer;

public class AuthTests extends BaseAPI {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void login_shouldReturnToken() {
        String username = "mor_2314"; // valid test user in fakestore
        String password = "83r5^_";   // sample password per fakestore docs
        Response res = AuthService.login(username, password);

        Assert.assertTrue(res.statusCode() == 200 || res.statusCode() == 201,
                "Expected status 200 or 201 but found: " + res.statusCode());
        String token = res.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token should not be null");
        System.out.println("Token: " + token);
    }
}
