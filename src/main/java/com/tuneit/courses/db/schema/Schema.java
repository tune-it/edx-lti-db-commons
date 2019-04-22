package com.tuneit.courses.db.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.lab1.schema.Schema01;
import com.tuneit.courses.lab2.schema.Schema02;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


@XmlRootElement(name = "schema")
@XmlSeeAlso({Schema01.class, Schema02.class})
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Schema {

    @XmlAttribute(name = "name")
    private String name;
    @XmlElementWrapper(name = "conditions")
    @XmlElement(name = "table")
    private List<ConditionTable> conditionTables;
    @XmlElementWrapper(name = "aggregations")
    @XmlElement(name = "aggregation")
    private List<Aggregation> aggregations;
    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    private List<Table> tables;

    public Schema load(String schemaName) {
        Schema schema;
        try {
            JAXBContext jc = JAXBContext.newInstance(Schema.class);
            InputStream inputStream = Schema.class.getResourceAsStream(schemaName);
            if (inputStream == null)
                throw new JAXBException("Could not get XML schema in application resourses");
            Source source = new StreamSource(inputStream);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            schema = unmarshaller.unmarshal(source, Schema.class).getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(Schema.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalArgumentException("Schema01 " + schemaName + " could not be loaded", ex);
        }
        return schema;
    }

    public Lab getLab() {
        throw new NullPointerException("Couldn't create lab in abstract schema");
    }

    @Override
    protected Schema clone() throws CloneNotSupportedException {
        Schema schema = (Schema) super.clone();
        schema.name = name;
        schema.tables = cloneListTable(tables);
        schema.aggregations = cloneListAggregation(aggregations);
        schema.conditionTables = cloneListConditionTable(conditionTables);
        return schema;
    }

    public void update(Schema schema) {
        schema.setName(name);
        schema.setTables(tables);
        schema.setConditionTables(conditionTables);
        schema.setAggregations(aggregations);
    }

    public Table findTableBySqlName(String string) {
        return tables.stream().filter(table -> table.getTableName().equalsIgnoreCase(string)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Table with name \"" + string + "\" not exist"));
    }

    public ConditionTable getRandomConditionTable(Random random) {
        return conditionTables.get(random.nextInt(conditionTables.size()));
    }

    public Aggregation getRandomAggregation(Random random) {
        return aggregations.get(random.nextInt(aggregations.size()));
    }

    public Table getRandomTable(Random random) {
        return tables.get(random.nextInt(tables.size()));
    }

    protected List<Table> cloneListTable(List<Table> tables) {
        List<Table> cloneTables = new ArrayList<>();
        for (Table table : tables) {
            cloneTables.add(table.clone());
        }
        return cloneTables;
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
}
