package springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@RestController
public class Controller {

    @Value("${link}")
    private String link;
    @Value("${timeout}")
    private int timeout;

    private String name = "default_name";
    private final String DEFAULT_RETURN_VALUE = "Default value";

    @PostMapping("/hello")
    public String someFunc(@RequestBody Request request) {
        return name = request.getName();
    }

    @RequestMapping("/greet")
    public Response pay(){
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        Duration duration = Duration.ofMillis(timeout);
        RestTemplate restTemplate = restTemplateBuilder.setConnectTimeout(duration)
                .setReadTimeout(duration)
                .errorHandler(new ErrorHandler())
                .build();
        ResponseEntity<Void> page;
        try {
            page = restTemplate.getForEntity(link, Void.class);
        }
        catch (Exception e) {
            return new Response("Никого нет дома");
        }
        String returnValue = DEFAULT_RETURN_VALUE;
        if (page.getStatusCode().equals(HttpStatus.OK)) {
            returnValue = name + ", сервис приветствует тебя";
        } else if (page.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            returnValue = "Извините, " + name + ", но я вас не знаю";
        }
        return new Response(returnValue);
    }
}