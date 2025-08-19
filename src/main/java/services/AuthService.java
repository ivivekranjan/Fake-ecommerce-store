package services;

import base.BaseAPI;
import constants.Endpoints;
import io.restassured.response.Response;
import utils.PayloadUtils;

import static io.restassured.RestAssured.given;

public class AuthService extends BaseAPI {

    public static Response login(String username, String password) {
        return given().spec(req)
                .header("Content-Type", "application/json")
                .body(PayloadUtils.loginPayload(username, password))
                .when()
                .post(Endpoints.LOGIN);
    }
}
