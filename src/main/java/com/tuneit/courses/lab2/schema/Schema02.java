package com.tuneit.courses.lab2.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab1.schema.Schema01;
import com.tuneit.courses.lab2.Lab02;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class Schema02 extends Schema implements Cloneable {

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    private List<Table> tables;

    private Lab02 lab02 = new Lab02();

    @Override
    public Schema02 load(String schemaName, String connectionName) {
        Schema02 schema;
        try {
            JAXBContext jc = JAXBContext.newInstance(Schema01.class);
            InputStream inputStream = Schema01.class.getResourceAsStream(schemaName);
            if (inputStream == null)
                throw new JAXBException("Could not get XML schema in application resourses");
            Source source = new StreamSource(inputStream);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            schema = unmarshaller.unmarshal(source, Schema02.class).getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(Schema01.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema01 " + schemaName + " could not be loaded", ex);
        }
        return schema;
    }

    @Override
    public Lab getLab() {
        return lab02;
    }

    @Override
    public Schema02 clone() {
        try {
            Schema02 schema02 = (Schema02) super.clone();
            schema02.tables = cloneListTable(schema02.tables);
            return schema02;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
