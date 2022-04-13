package ru.example.marshaller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.example.marshaller.domain.Country;

@Configuration
public class MarshalConfig {
    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setClassesToBeBound(Country.class);
        // marshaller.setContextPath(<jaxb.context-file>)
         marshaller.setPackagesToScan("ru.example.marshaller.domain");

        marshaller.setMarshallerProperties(new HashMap<String, Object>() {{
            put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        }});

        return marshaller;
    }
}
