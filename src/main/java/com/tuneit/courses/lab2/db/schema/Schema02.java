package com.tuneit.courses.lab2.db.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.SchemaConnection;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author serge
 */

@XmlRootElement(name = "schema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Schema02 extends Schema {

    @XmlAttribute(name = "name")
    protected String name;

    @XmlTransient
    protected SchemaConnection connection;

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    private List<Table02> tables;


    public Schema02 load(String schemaName, String connectionName) {
        SchemaConnection schemaConnection = loadConnection(connectionName);
        Schema02 schema02 = loadSchema(schemaName);
        schema02.connection = schemaConnection;
        schema02.updateReferenceTables();
        return schema02;
    }

    public List<? extends Table> getTables() {
        return tables;
    }

    public void setTables(List tables) {
        this.tables = tables;
    }

    public List<? extends Lab> getLabs() {
        return null;
    }

    public void setLabs(List labs) {

    }

    private SchemaConnection loadConnection(String connectionName) {
        SchemaConnection connection;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SchemaConnection.class);
            InputStream inputStream = SchemaConnection.class.getResourceAsStream(connectionName);
            if (inputStream == null)
                throw new JAXBException("Could not get XML schema in application resourses");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            connection = (SchemaConnection) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException ex) {
            Logger.getLogger(Schema02.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema01 " + connectionName + " could not be loaded", ex);
        }
        return connection;
    }

    private Schema02 loadSchema(String schemaName) {
        Schema02 sch;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Schema02.class);
            InputStream inputStream = Schema02.class.getResourceAsStream(schemaName);
            if (inputStream == null)
                throw new JAXBException("Could not get XML schema in application resourses");
            Source source = new StreamSource(inputStream);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            sch = unmarshaller.unmarshal(source, Schema02.class).getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(Schema02.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema01 " + schemaName + " could not be loaded", ex);
        }
        return sch;
    }

    private void updateReferenceTables() {
        tables.forEach(table -> table.getRefTables().forEach(referenceToTable -> {
            referenceToTable.setTable(
                    findTable(referenceToTable.getTableName()));
            referenceToTable.setColumnReference(
                    table.findColumn(referenceToTable.getColumnReferenceName()));
            referenceToTable.setJoinColumnReference(
                    referenceToTable.getTable().findColumn(referenceToTable.getJoinColumnReferenceName()));
        }));
    }

    private Table02 findTable(String name) {
        for (Table02 table : tables) {
            if (table.getTableName().equalsIgnoreCase(name)) {
                return table;
            }
        }
        return null;
    }

}
