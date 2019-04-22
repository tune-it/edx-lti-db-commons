package com.tuneit.courses.lab1.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.lab1.Lab01;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class Schema01 extends Schema implements Cloneable {

    @XmlElementWrapper(name = "dates")
    @XmlElement(name = "date")
    private List<DiffDate> diffDates;

    private Lab01 lab01 = new Lab01();

    @Override
    public Schema01 load(String schemaName) {
        Schema01 schema01 = loadSchema01(schemaName);
        Schema schema = super.load("lab.xml");
        schema.update(schema01);
        return schema01;
    }

    private Schema01 loadSchema01(String schemaName) {
        Schema01 schema01;
        try {
            JAXBContext jc = JAXBContext.newInstance(Schema01.class);
            InputStream inputStream = Schema01.class.getResourceAsStream(schemaName);
            if (inputStream == null)
                throw new JAXBException("Could not get XML schema in application resourses");
            Source source = new StreamSource(inputStream);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            schema01 = unmarshaller.unmarshal(source, Schema01.class).getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(Schema01.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema01 " + schemaName + " could not be loaded", ex);
        }
        return schema01;
    }

    @Override
    public Lab getLab() {
        return lab01;
    }

    public DiffDate getRandomDiffDate(Random random) {
        return diffDates.get(random.nextInt(diffDates.size()));
    }

    @Override
    public Schema01 clone() {
        try {
            Schema01 schema01 = (Schema01) super.clone();//todo copy diff table
            schema01.diffDates = cloneListDiffDate(diffDates);
            return schema01;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<DiffDate> cloneListDiffDate(List<DiffDate> diffDates) {
        List<DiffDate> cloneList = new ArrayList<>();
        for (DiffDate diffDate : diffDates) {
            cloneList.add(diffDate.clone());
        }
        return cloneList;
    }

}
