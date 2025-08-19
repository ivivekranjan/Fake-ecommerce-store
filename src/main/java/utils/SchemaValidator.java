package utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matcher;

public class SchemaValidator {
    public static Matcher<?> matchesSchema(String schemaPath) {
        return JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath);
    }
}
