package com.tuneit.courses.lab1.schema;

import com.tuneit.courses.db.schema.Clone;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
public class DiffDateOption implements Cloneable, Clone<DiffDateOption> {

    @XmlAttribute(name = "sql")
    private String sqlOption;

    @XmlAttribute(name = "native")
    private String nativeOption;

    @Override
    public DiffDateOption clone() {
        try {
            DiffDateOption diffDateOption = (DiffDateOption) super.clone();
            diffDateOption.sqlOption = sqlOption;
            diffDateOption.nativeOption = nativeOption;
            return diffDateOption;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
