package ru.example.marshaller.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.marshaller.domain.Country;
import ru.example.marshaller.service.Jaxb2MarshalService;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class MarshalController {
    private final Jaxb2MarshalService marshalService;

    @PostMapping(value = "marshal-to-stream",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_XML_VALUE
    )
    public ResponseEntity<String> marshallToStreamResult(@RequestBody Country country) {
        country.setName(country.getName() + "-STREAM");
        String result = null;
        try {
            result = marshalService.marshallToStreamResult(country);
        } catch (JAXBException e) {
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "marshal-to-file",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> marshallToFile(@RequestBody Country country) {
        country.setName(country.getName() + "-FILE");
        try {
            country = marshalService.marshallToFile(country);
        } catch (JAXBException e) {
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(country);
    }
}
