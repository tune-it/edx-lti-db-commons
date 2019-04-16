package com.tuneit.courses.lab1.db.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;
import com.tuneit.courses.lab1.db.Lab01;
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

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    private List<Table> tables;

    @XmlElementWrapper(name = "conditions")
    @XmlElement(name = "table")
    private List<ConditionTable> conditionTables;

    @XmlElementWrapper(name = "aggregations")
    @XmlElement(name = "aggregation")
    private List<Aggregation> aggregations;

    @XmlElementWrapper(name = "dates")
    @XmlElement(name = "date")
    private List<DiffDate> diffDates;

    private Lab01 lab01 = new Lab01();

    public Schema01 load(String schemaName, String connectionName) {
        Schema01 schema;
        try {
            JAXBContext jc = JAXBContext.newInstance(Schema01.class);
            InputStream inputStream = Schema01.class.getResourceAsStream(schemaName);
            if (inputStream == null)
                throw new JAXBException("Could not get XML schema in application resourses");
            Source source = new StreamSource(inputStream);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            schema = unmarshaller.unmarshal(source, Schema01.class).getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(Schema01.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema01 " + schemaName + " could not be loaded", ex);
        }
        return schema;
    }

    @Override
    public Lab getLab() {
        return lab01;
    }

    public Table getRandomTable(Random random) {
        return tables.get(random.nextInt(tables.size()));
    }

    public ConditionTable getRandomConditionTable(Random random) {
        return conditionTables.get(random.nextInt(conditionTables.size()));
    }

    public Aggregation getRandomAggregation(Random random) {
        return aggregations.get(random.nextInt(aggregations.size()));
    }

    public DiffDate getRandomDiffDate(Random random) {
        return diffDates.get(random.nextInt(diffDates.size()));
    }

    @Override
    public Schema01 clone() {
        try {
            Schema01 schema01 = (Schema01) super.clone();
            schema01.tables = cloneListTable(schema01.tables);
            schema01.conditionTables = cloneListConditionTable(schema01.conditionTables);
            schema01.aggregations = cloneListAggregation(schema01.aggregations);
            return schema01;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Aggregation> cloneListAggregation(List<Aggregation> aggregations) {
        List<Aggregation> cloneList = new ArrayList<>();
        for (Aggregation aggregation : aggregations) {
            cloneList.add(aggregation.clone());
        }
        return cloneList;
    }

    private List<ConditionTable> cloneListConditionTable(List<ConditionTable> conditionTables) {
        List<ConditionTable> cloneList = new ArrayList<>();
        for (ConditionTable condition : conditionTables) {
            cloneList.add(condition.clone());
        }
        return cloneList;
    }

    private List<Table> cloneListTable(List<Table> tables) {
        List<Table> cloneTables = new ArrayList<>();
        for (Table table : tables) {
            cloneTables.add(table.clone());
        }
        return cloneTables;
    }
}
