package com.tuneit.courses.lab2.db.schema;

import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * @author serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table02 extends Table implements Cloneable {

    @XmlElement(name = "reference")
    @XmlElementWrapper(name = "references")
    List<ReferenceToTable> refTables = new ArrayList<>();

    public List<ReferenceToTable> getRefTables() {
        return refTables;
    }

}
