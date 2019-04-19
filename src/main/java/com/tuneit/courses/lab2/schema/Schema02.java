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

    @XmlElementWrapper(name = "references")
    @XmlElement(name = "table")
    private List<TableReference> tablesReferences;

    @XmlElementWrapper(name = "substrings")
    @XmlElement(name = "table")
    private List<TableSubstring> tablesSubstrings;

    @XmlElementWrapper(name = "cases")
    @XmlElement(name = "table")
    private List<TableCases> tablesCases;

    @XmlElementWrapper(name = "subqueries")
    @XmlElement(name = "table")
    private List<TableSubquery> tablesSubqueries;

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
            schema02.tables = cloneListTable(tables);
            schema02.tablesReferences = cloneListTableReference(tablesReferences);
            schema02.tablesSubstrings = cloneListTableSubstring(tablesSubstrings);
            schema02.tablesCases = cloneListTableCases(tablesCases);
            schema02.tablesSubqueries = cloneListTableSubqueries(tablesSubqueries);
            return schema02;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<Table, Column> getRandomChainTable(Random random) {
        Map<Table, Column> resultChain = new HashMap<>();

        TableReference firstTableReference = getRandomTableReference(random);
        Table firstTable = findTableBySqlName(firstTableReference.getSqlTableName());

        Reference referenceToSecondTable = firstTableReference.getRandomReference(random);
        Table secondTable = findTableBySqlName(referenceToSecondTable.getTableReference());

        resultChain.put(firstTable, firstTable.findColumn(referenceToSecondTable.getNameColumnReference()));
        resultChain.put(secondTable, secondTable.findColumn(referenceToSecondTable.getNameJoinColumnReference()));

        return resultChain;
    }

    private List<TableSubquery> cloneListTableSubqueries(List<TableSubquery> tablesSubqueries) {
        List<TableSubquery> cloneList = new ArrayList<>();
        for (TableSubquery tableSubquery : tablesSubqueries) {
            cloneList.add(tableSubquery.clone());
        }
        return cloneList;
    }

    private List<TableCases> cloneListTableCases(List<TableCases> tablesCases) {
        List<TableCases> cloneList = new ArrayList<>();
        for (TableCases tableCases : tablesCases) {
            cloneList.add(tableCases.clone());
        }
        return cloneList;
    }

    private List<TableReference> cloneListTableReference(List<TableReference> references) {
        List<TableReference> cloneList = new ArrayList<>();
        for (TableReference tableReference : references) {
            cloneList.add(tableReference.clone());
        }
        return cloneList;
    }

    private List<TableSubstring> cloneListTableSubstring(List<TableSubstring> substrings) {
        List<TableSubstring> cloneList = new ArrayList<>();
        for (TableSubstring tableSubstring : substrings) {
            cloneList.add(tableSubstring.clone());
        }
        return cloneList;
    }

    private TableReference getRandomTableReference(Random random) {
        return tablesReferences.get(random.nextInt(tablesReferences.size()));
    }

    public TableSubstring getRandomTableSubstring(Random random) {
        return tablesSubstrings.get(random.nextInt(tablesSubstrings.size()));
    }

    public TableCases getRandomTableCases(Random random) {
        return tablesCases.get(random.nextInt(tablesCases.size()));
    }

    public TableSubquery getRandomTableSubquery(Random random) {
        return tablesSubqueries.get(random.nextInt(tablesSubqueries.size()));
    }

    public Table findTableBySqlName(String string) {
        return tables.stream().filter(table -> table.getTableName().equalsIgnoreCase(string)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Table with name \"" + string + "\" not exist"));
    }
}
