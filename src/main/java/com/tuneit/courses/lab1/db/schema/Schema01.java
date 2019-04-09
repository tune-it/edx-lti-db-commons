package com.tuneit.courses.lab1.db.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.SchemaConnection;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab1.db.Lab01;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author serge
 */

public class Schema01 extends Schema {

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    private List<? extends Table> tables;

    @XmlElementWrapper(name = "labs-config")
    @XmlElements({
            @XmlElement(name = "lab", type = Lab01.class),
    })
    private List<? extends Lab> labs;

    public Schema01 load(String schemaName, String connectionName) {
        Schema01 schema01 = loadSchema(schemaName);
        schema01.connection = loadConnection(connectionName);
        return schema01;
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
            Logger.getLogger(Schema01.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema01 " + connectionName + " could not be loaded", ex);
        }
        return connection;
    }

    private Schema01 loadSchema(String schemaName) {
        Schema01 sch;
        try {
            JAXBContext jc = JAXBContext.newInstance(Schema01.class);
            InputStream inputStream = Schema01.class.getResourceAsStream(schemaName);
            if (inputStream == null)
                throw new JAXBException("Could not get XML schema in application resourses");
            Source source = new StreamSource(inputStream);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            sch = unmarshaller.unmarshal(source, Schema01.class).getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(Schema01.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema01 " + schemaName + " could not be loaded", ex);
        }
        return sch;
    }

    public List<? extends Table> getTables() {
        return tables;
    }

    public void setTables(List<? extends Table> tables) {
        this.tables = tables;
    }

    public List<? extends Lab> getLabs() {
        return labs;
    }

    public void setLabs(List<? extends Lab> labs) {
        this.labs = labs;
    }
}
