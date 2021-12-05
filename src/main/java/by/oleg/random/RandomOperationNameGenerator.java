package by.oleg.random;

import by.oleg.enumeration.OperationName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
@Getter
@Setter
public class RandomOperationNameGenerator {
    private static final Random RANDOM = new Random();

    @Value("${app.operationgenerator.operation.rate.DIVISION}")
    private Integer divisionRate;
    @Value("${app.operationgenerator.operation.rate.SUBTRACT}")
    private Integer subtractRate;
    @Value("${app.operationgenerator.operation.rate.ADDITION}")
    private Integer additionRate;
    @Value("${app.operationgenerator.operation.rate.MULTIPLICATION}")
    private Integer multiplicationRate;


    @PostConstruct
    public void validateRate() {
        if (divisionRate + subtractRate + additionRate + multiplicationRate != 100) {
            throw new IllegalStateException("Sum of all rates must be 100%");
        }
    }

    public OperationName generate() {
        int randomNum = RANDOM.nextInt(100);
        if (randomNum <= divisionRate) {
            return OperationName.DIVISION;
        } else if (randomNum <= divisionRate + subtractRate) {
            return OperationName.SUBTRACT;
        } else if (randomNum <= (divisionRate + subtractRate + additionRate)) {
            return OperationName.ADDITION;
        } else {
            return OperationName.MULTIPLICATION;
        }
    }

}
