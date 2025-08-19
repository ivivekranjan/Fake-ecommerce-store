package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class CustomLogFilter implements Filter {
    private static final String LOG_FILE = "logs/api.log";

    @Override
    public Response filter(FilterableRequestSpecification req,
                           FilterableResponseSpecification res,
                           FilterContext ctx) {
        Response response = ctx.next(req, res);

        // Mask basics
        String auth = req.getHeaders().getValue("Authorization");
        if (auth != null) auth = "Bearer ****masked****";

        String line = String.format(
                "%s | %s %s | Auth:%s | Status:%d%nReqBody:%s%nResBody:%s%n%n",
                LocalDateTime.now(),
                req.getMethod(), req.getURI(),
                (auth != null ? auth : "none"),
                response.statusCode(),
                safe(req.getBody() == null ? "" : req.getBody()),
                safe(response.getBody() == null ? "" : response.getBody().asPrettyString())
        );

        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private String safe(Object o) {
        String s = String.valueOf(o);
        // Basic masking (extend as needed)
        return s.replaceAll("(?i)\"password\"\\s*:\\s*\"[^\"]+\"", "\"password\":\"****\"");
    }
}
