package com.tuneit.courses.lab2.db.schema;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author serge
 */

@XmlRootElement(name = "schema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Schema {

    @XmlAttribute(name = "name")
    private String name;

    @XmlTransient
    private SchemaConnection connection;

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    private List<Table> tables;


    public static Schema load(String schemaName, String connectionName) {
        SchemaConnection schemaConnection = loadConnection(connectionName);
        Schema schema = loadSchema(schemaName);
        schema.connection = schemaConnection;
        schema.updateRef();
        return schema;
    }

    private void updateRef() {
        tables.forEach(table -> table.getNamesReferences().forEach(nameTable -> {
            table.getRefTables().add(findTable(nameTable));
        }));

        System.out.println("eze");
    }

    private static SchemaConnection loadConnection(String connectionName) {
        SchemaConnection connection;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SchemaConnection.class);
            InputStream inputStream = SchemaConnection.class.getResourceAsStream(connectionName);
            if (inputStream == null)
                throw new JAXBException("Could not get XML com.tuneit.courses.lab2.db.schema in application resourses");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            connection = (SchemaConnection) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException ex) {
            Logger.getLogger(Schema.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema " + connectionName + " could not be loaded", ex);
        }
        return connection;
    }

    private static Schema loadSchema(String schemaName) {
        Schema sch;
        try {
            JAXBContext jc = JAXBContext.newInstance(Schema.class);
            InputStream is = Schema.class.getResourceAsStream(schemaName);
            if (is == null)
                throw new JAXBException("Could not get XML com.tuneit.courses.lab2.db.schema in application resourses");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            sch = (Schema) unmarshaller.unmarshal(is);
        } catch (JAXBException ex) {
            Logger.getLogger(Schema.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema " + schemaName + " could not be loaded", ex);
        }
        return sch;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            throw new SQLException("Schema connection is not properly setup");
        }
        return connection.getConnection();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the tables
     */
    public List<Table> getTables() {
        return tables;
    }

    /**
     * @param tables the tables to set
     */
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public Table findTable(String name) {
        for (Table table : tables) {
            if (table.getTableName().equalsIgnoreCase(name)) {
                return table;
            }
        }
        return null;
    }

}
