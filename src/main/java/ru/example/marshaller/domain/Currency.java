package ru.example.marshaller.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "currency")
@XmlEnum
public enum Currency implements Serializable {
    GBP,
    EUR,
    PLN;

    public String value() {
        return name();
    }

    public static Currency fromValue(String v) {
        return valueOf(v);
    }
}
