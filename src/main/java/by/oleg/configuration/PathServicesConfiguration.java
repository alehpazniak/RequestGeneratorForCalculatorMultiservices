package by.oleg.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "config.operationpath.service")
public class PathServicesConfiguration {

    private String addition;
    private String division;
    private String multiplication;
    private String subtract;
}



