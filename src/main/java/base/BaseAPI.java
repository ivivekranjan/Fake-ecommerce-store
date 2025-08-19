package base;

import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import utils.CustomLogFilter;

import static io.restassured.RestAssured.requestSpecification;

public class BaseAPI {
    protected static RequestSpecification req;

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        req = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.get("baseURI"))
                .setRelaxedHTTPSValidation()
                .addFilter(new CustomLogFilter()) // structured file logs + masking
                .addFilter(RequestLoggingFilter.logRequestTo(System.out))
                .addFilter(ResponseLoggingFilter.logResponseTo(System.out))
                .build();

        // Set as global if you want
        requestSpecification = req;
    }
}
