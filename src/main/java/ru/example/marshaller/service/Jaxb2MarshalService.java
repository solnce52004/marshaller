package ru.example.marshaller.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;

@Service
public class Jaxb2MarshalService implements MarshalService {
    public static final String RESULT_XML_PATH = "./result.xml";
    private final Jaxb2Marshaller marshaller;

    @Autowired
    public Jaxb2MarshalService(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    @Override
    public <T> String marshallToStreamResult(T obj) throws JAXBException {
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        marshaller.marshal(obj, result);
        return sw.toString();
    }

    @Override
    public <T> T marshallToFile(T obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(obj, new File(RESULT_XML_PATH));
        return obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unmarshallXml(InputStream xml) throws JAXBException {
        StreamSource source = new StreamSource(xml);
        return (T) marshaller.unmarshal(source);
    }
}
