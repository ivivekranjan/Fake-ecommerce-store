package services;

import base.BaseAPI;
import constants.Endpoints;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CartService extends BaseAPI {

    public static Response createCart(Map<String, Object> cartPayload) {
        return given().spec(req)
                .header("Content-Type", "application/json")
                .body(cartPayload)
                .when().post(Endpoints.CARTS);
    }

    public static Response getCartById(int id) {
        return given().spec(req)
                .pathParam("id", id)
                .when().get(Endpoints.CART_BY_ID);
    }
}
