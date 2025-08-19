package tests;

import base.BaseAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.CartService;
import utils.PayloadUtils;
import utils.RetryAnalyzer;
import utils.SchemaValidator;

import java.util.Arrays;
import java.util.Map;

public class CartTests extends BaseAPI {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void createCart_shouldSucceed_andMatchSchema() {
        Map<String, Object> payload = PayloadUtils.cartCreatePayload(
                3,
                Arrays.asList(
                        PayloadUtils.cartProduct(1, 2),
                        PayloadUtils.cartProduct(2, 1)
                )
        );

        Response res = CartService.createCart(payload);

        Assert.assertTrue(res.statusCode() == 200 || res.statusCode() == 201);
        res.then().assertThat().body(SchemaValidator.matchesSchema("schemas/cart.json"));

        Integer id = res.jsonPath().getInt("id");
        Assert.assertNotNull(id, "Cart id should be present");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = "createCart_shouldSucceed_andMatchSchema")
    public void getCartById_shouldReturnCart() {
        // For demo, using 1; or capture id from previous test via RuntimeData holder if you add one.
        Response res = CartService.getCartById(1);

        Assert.assertEquals(res.statusCode(), 200);
        res.then().assertThat().body(SchemaValidator.matchesSchema("schemas/cart.json"));
    }
}
