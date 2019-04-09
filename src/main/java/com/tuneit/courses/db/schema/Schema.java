package com.tuneit.courses.db.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.lab1.db.schema.Schema01;

import javax.xml.bind.annotation.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@XmlRootElement(name = "schema")
@XmlSeeAlso(Schema01.class)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Schema {

    @XmlAttribute(name = "name")
    protected String name;

    @XmlTransient
    protected SchemaConnection connection;


    public abstract Schema load(String schemaName, String connectionName);

    public abstract List<? extends Table> getTables();

    public abstract void setTables(List<? extends Table> tables);

    public abstract List<? extends Lab> getLabs();

    public abstract void setLabs(List<? extends Lab> labs);

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

}
