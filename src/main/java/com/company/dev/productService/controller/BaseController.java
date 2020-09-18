package com.company.dev.productService.controller;

import com.company.dev.productService.response.ServiceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    private static final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
    private static final String LOG_FORMAT = "== {} ==";

    public static void printApiRequest(Object payload, String methodName) {
        print(payload, null, methodName, true);
    }

    public static void printApiResponse(ResponseEntity<ServiceResponse> response, String methodName) {
        print(null, response, methodName, false);
    }

    private static void print(Object object, ResponseEntity<ServiceResponse> response, String methodName,
                              boolean incoming) {
        try {
            if (incoming) {
                LOGGER.info("========== REQUEST ==========");
                LOGGER.info(LOG_FORMAT, methodName.toUpperCase());
                if (object != null)
                    LOGGER.info(writer.writeValueAsString(object));

            } else {
                LOGGER.info("========== RESPONSE ==========");
                LOGGER.info(LOG_FORMAT, methodName.toUpperCase());
                if (response != null) {
                    LOGGER.info(writer.writeValueAsString(response.getBody()));
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Print Error {}", ex);

        }
    }
}
