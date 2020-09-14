package springboot.topjava.ru;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class Controller {

    @Value("${link}")
    private String link;

    private String name = "default_name";
    private final String DEFAULT_RETURN_VALUE = "Default value";

    @PostMapping("/hello")
    public String someFunc(@RequestBody Request request) {
        return name = request.getName();
    }

    @RequestMapping("/greet")
    public Response pay() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ErrorHandler());
        ResponseEntity<Void> page = restTemplate.getForEntity(link, Void.class);
        String returnValue = DEFAULT_RETURN_VALUE;
        if (page.getStatusCode().equals(HttpStatus.OK)) {
            returnValue = name + ", сервис приветствует тебя";
        } else if (page.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            returnValue = "Извините, "+name + ", но я вас не знаю";
        }
        return new Response(returnValue);
    }
}