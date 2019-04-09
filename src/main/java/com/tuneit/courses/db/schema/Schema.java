package com.tuneit.courses.db.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.lab1.db.Lab01;
import com.tuneit.courses.lab1.db.schema.Schema01;
import com.tuneit.courses.lab1.db.schema.SchemaConnection;
import com.tuneit.courses.lab1.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@XmlRootElement(name = "schema")
@XmlSeeAlso(Schema01.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class Schema {

    @XmlAttribute(name = "name")
    protected String name;

    @XmlTransient
    protected SchemaConnection connection;

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    protected List<Table> tables;

    @XmlElementWrapper(name = "labs-config")
    @XmlElements({
            @XmlElement(name = "lab", type = Lab01.class),
    })
    protected List<Lab> labs;


    public Schema load(String schemaName, String connectionName){
        return null;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            throw new SQLException("Schema01 connection is not properly setup");
        }
        return connection.getConnection();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Lab> getLabs() {
        return labs;
    }

    public void setLabs(List<Lab> labs) {
        this.labs = labs;
    }

}
