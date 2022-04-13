package ru.example.marshaller.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.marshaller.domain.Country;
import ru.example.marshaller.service.Jaxb2MarshalService;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class UnmarshalController {
    private final Jaxb2MarshalService marshalService;

    @PostMapping(value = "unmarshal-from-request",
            consumes = MediaType.TEXT_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Country> unmarshalFromRequest(HttpServletRequest request) {
        Country country = null;
        try (ServletInputStream stream = request.getInputStream()) {
            country = marshalService.unmarshallXml(stream);
        } catch (IOException | JAXBException e) {
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(country);
    }

    @GetMapping(value = "unmarshal-from-file")
    public ResponseEntity<Country> unmarshalFromLocalFile() {
        Country country = null;
        //for example - use local file
        try (FileInputStream stream = new FileInputStream(Jaxb2MarshalService.RESULT_XML_PATH)) {
            country = marshalService.unmarshallXml(stream);
        } catch (IOException | JAXBException e) {
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(country);
    }
}