package by.oleg.schedule;

import by.oleg.domain.Operation;
import by.oleg.random.RandomOperationGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RandomOperationSender {

    private String PATH_ADDITION_SERVICE = "http://addition/addition/{argFirst}/{argSecond}";
    private String PATH_DIVISION_SERVICE = "http://division/division/{argFirst}/{argSecond}";
    private String PATH_MULTIPLICATION_SERVICE = "http://multiplication/multiplication/{argFirst}/{argSecond}";
    private String PATH_SUBTRACT_SERVICE = "http://subtract/subtract/{argFirst}/{argSecond}";


    private RestTemplate restTemplate;
    private RandomOperationGenerator generator;

    @Autowired
    public RandomOperationSender(RestTemplate restTemplate, RandomOperationGenerator generator) {
        this.restTemplate = restTemplate;
        this.generator = generator;
    }

    @Scheduled(fixedRateString = "${app.operationgenerator.random-operation-sender.fixed-rate}")
    public void sendRandomOperation() {

        String requestPath = "";

        Operation operation = generator.generate();
        switch (operation.getOperationName()) {
            case ADDITION:
                requestPath = PATH_ADDITION_SERVICE;
                break;
            case SUBTRACT:
                requestPath = PATH_SUBTRACT_SERVICE;
                break;
            case MULTIPLICATION:
                requestPath = PATH_MULTIPLICATION_SERVICE;
                break;
            case DIVISION:
                requestPath = PATH_DIVISION_SERVICE;
                break;
        }

        System.out.println("Sending request to " + requestPath + " with operation " + operation.getOperationName());

        var result = restTemplate.getForObject(requestPath, Operation.class,
                operation.getArgFirst(), operation.getArgSecond());

        System.out.println("result: " + result);
    }
}