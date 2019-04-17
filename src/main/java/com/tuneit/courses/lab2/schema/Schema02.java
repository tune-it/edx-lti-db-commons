package com.tuneit.courses.lab2.schema;

import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;
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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class Schema02 extends Schema implements Cloneable {

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    private List<Table> tables;

    @XmlElementWrapper(name = "referebces")
    @XmlElement(name = "table")
    private List<TableReferences> tablesReferences;

    private Lab02 lab02 = new Lab02();

    @Override
    public Schema02 load(String schemaName, String connectionName) {
        Schema02 schema;
        try {
            JAXBContext jc = JAXBContext.newInstance(Schema02.class);
            InputStream inputStream = Schema02.class.getResourceAsStream(schemaName);
            if (inputStream == null)
                throw new JAXBException("Could not get XML schema in application resourses");
            Source source = new StreamSource(inputStream);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            schema = unmarshaller.unmarshal(source, Schema02.class).getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(Schema02.class.getName()).log(Level.SEVERE, null, ex);
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
            schema02.tablesReferences = cloneListTableReference(schema02.tablesReferences);
            return schema02;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<Table, Column> getRandomChainTable(Random random) {
        Map<Table, Column> resultChain = new HashMap<>();

        TableReferences firstTableReference = getRandomTableReference(random);
        Table firstTable = findTableBySqlName(firstTableReference.getSqlTableName());

        Reference referenceToSecondTable = firstTableReference.getRandomReference(random);
        Table secondTable = findTableBySqlName(referenceToSecondTable.getTableReference());

        resultChain.put(firstTable, firstTable.findColumn(referenceToSecondTable.getNameColumnReference()));
        resultChain.put(secondTable, secondTable.findColumn(referenceToSecondTable.getNameJoinColumnReference()));

        return resultChain;
    }

    private Table findTableBySqlName(String sqlTableName) {
        Optional<Table> first = tables.stream().filter(table -> table.getTableName().equals(sqlTableName)).findFirst();
        if (first.isPresent()) {
            return first.get();
        } else {
            throw new IllegalArgumentException("Table with name " + sqlTableName + " not found");
        }
    }

    private List<TableReferences> cloneListTableReference(List<TableReferences> references) {
        List<TableReferences> cloneList = new ArrayList<>();
        for (TableReferences tableReferences : references) {
            cloneList.add(tableReferences.clone());
        }
        return cloneList;
    }

    private TableReferences getRandomTableReference(Random random) {
        return tablesReferences.get(random.nextInt(tablesReferences.size()));
    }
}
