package services;

import base.BaseAPI;
import constants.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductService extends BaseAPI {

    public static Response getAllProducts() {
        return given().spec(req)
                .when().get(Endpoints.PRODUCTS);
    }

    public static Response getProductById(int id) {
        return given().spec(req)
                .pathParam("id", id)
                .when().get(Endpoints.PRODUCT_BY_ID);
    }
}
