package tests;

import base.BaseAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.ProductService;
import services.CartService;
import utils.PayloadUtils;
import utils.RetryAnalyzer;

import java.util.Collections;
import java.util.Map;

public class EndToEndFlowTest extends BaseAPI {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void userBrowsesProduct_addsToCart_placesCart() {
        // 1) Get a product to add
        Response prod = ProductService.getProductById(1);
        Assert.assertEquals(prod.statusCode(), 200);
        int pid = prod.jsonPath().getInt("id");

        // 2) Create cart for userId=3 with that product
        Map<String, Object> cartPayload = PayloadUtils.cartCreatePayload(
                3,
                Collections.singletonList(PayloadUtils.cartProduct(pid, 1))
        );
        Response cart = CartService.createCart(cartPayload);
        Assert.assertTrue(cart.statusCode() == 200 || cart.statusCode() == 201);

        // 3) Verify details
        String date = cart.jsonPath().getString("date");
        Assert.assertNotNull(date);
    }
}
