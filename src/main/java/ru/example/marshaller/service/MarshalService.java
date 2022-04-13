package ru.example.marshaller.service;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

public interface MarshalService {
    <T> String marshallToStreamResult(final T obj) throws JAXBException;
    <T> T marshallToFile(T obj) throws JAXBException;
    <T> T unmarshallXml(final InputStream xml) throws JAXBException;
}
