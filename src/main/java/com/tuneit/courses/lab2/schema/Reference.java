package com.tuneit.courses.lab2.schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class Reference implements Cloneable {

    @XmlAttribute(name = "table")
    private String tableReference;

    @XmlAttribute(name = "column-ref")
    private String nameColumnReference;

    @XmlAttribute(name = "join-column-ref")
    private String nameJoinColumnReference;

    @Override
    public Reference clone() {
        try {
            Reference reference = (Reference) super.clone();
            reference.tableReference = tableReference;
            reference.nameColumnReference = nameColumnReference;
            reference.nameJoinColumnReference = nameJoinColumnReference;
            return reference;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
