package tests;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


import base.BaseAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.ProductService;
import utils.RetryAnalyzer;
import utils.SchemaValidator;

public class ProductTests extends BaseAPI {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void getAllProducts_shouldReturnList_andMatchSchema() {
        Response res = ProductService.getAllProducts();

        Assert.assertEquals(res.statusCode(), 200);
        res.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/products.json"));
        int count = res.jsonPath().getList("$").size();
        Assert.assertTrue(count > 0, "Products list should not be empty");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void getProductById_shouldReturnSingle_andMatchSchema() {
        Response res = ProductService.getProductById(1);

        Assert.assertEquals(res.statusCode(), 200);
        res.then().assertThat().body(SchemaValidator.matchesSchema("schemas/product.json"));
        Assert.assertEquals((int) res.jsonPath().getInt("id"), 1);
    }
}
