package com.tuneit.courses.lab2.db.schema;

import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author serge
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Table02 extends Table implements Cloneable {

    @XmlTransient
    List<Table02> refTables = new ArrayList<>();
    @XmlElement(name = "references")
    @XmlList
    private List<String> namesReferences;


    public List<Table02> getRefTables() {
        return refTables;
    }

    public List<String> getNamesReferences() {
        return namesReferences;
    }
}
