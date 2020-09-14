package springboot.topjava.ru;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class ErrorHandler extends  DefaultResponseErrorHandler {
    public void handleError(ClientHttpResponse response) throws IOException {
    }
}