package com.tuneit.courses.db.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.lab1.schema.Schema01;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "schema")
@XmlSeeAlso(Schema01.class)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Schema {

    @XmlAttribute(name = "name")
    protected String name;

    public abstract Schema load(String schemaName, String connectionName);

    public abstract Lab getLab();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected List<Table> cloneListTable(List<Table> tables) {
        List<Table> cloneTables = new ArrayList<>();
        for (Table table : tables) {
            cloneTables.add(table.clone());
        }
        return cloneTables;
    }
}
