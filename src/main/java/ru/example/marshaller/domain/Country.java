package ru.example.marshaller.domain;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.beans.Transient;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "country",
        propOrder = {
                "capital",
                "currency",
                "name",
                "population"
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country implements Serializable {
    @XmlID
    @XmlAttribute(required = true)
    protected String id;

    @XmlElement(required = true)
    protected String name;

    protected int population;

    @XmlTransient
    protected int population2;

    @XmlElement(required = true)
    protected String capital;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Currency currency;
}


