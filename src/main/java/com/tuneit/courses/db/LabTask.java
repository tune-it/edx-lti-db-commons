package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 *
 * @author serge
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class LabTask {
    protected StringBuilder query = new StringBuilder();
    protected StringBuilder answer = new StringBuilder();

    @XmlAttribute(name = "description")
    protected String description;
    @XmlAttribute(name="id") protected String id;
    
    @XmlElement(name="prolog") protected String prolog;
    @XmlElement(name="epilog") protected String epilog;

    @XmlElement(name = "forbidden-list")
    protected List<String> forbiddenList = new ArrayList<>();
    @XmlTransient
    static HashMap<String, List<Table>> allowed = new HashMap<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProlog() {
        return prolog;
    }

    public void setProlog(String prolog) {
        this.prolog = prolog;
    }

    public String getEpilog() {
        return epilog;
    }

    public void setEpilog(String epilog) {
        this.epilog = epilog;
    }
    
    protected static List<Table> removeForbidenElements(Schema s, List<String> forbidenElements) {
        ArrayList<Table> allowed = new ArrayList<>();
        for (Table table : s.getTables()) {
            if (forbidenElements.stream().noneMatch(str -> str.equalsIgnoreCase(table.getTableName()))) {
                Table allowedTable = table.clone();

                allowed.add(allowedTable);
            }
        }
        return allowed;
    }


    public List<String> getForbiddenList() {
        return forbiddenList;
    }

    public void setForbiddenList(List<String> forbiddenList) {
        this.forbiddenList = forbiddenList;
    }

    @Override
    public String toString() {
        return "LabTask{" + "description=" + description + ", id=" + id + 
                ", prolog=" + prolog + ", epilog=" + epilog + '}';
    }

    public LabTaskQA generate(Schema s, Task t) {
        Table table = getRandomTable(s).clone();

        Collections.shuffle(table.getColumns());

        updateQuery(table);

        updateAnswer(table);

        return new LabTaskQA(t.getId(), query.toString(), answer.toString());
    }

    protected void updateQuery(Table table) {
        List<Column> columns = table.getColumns();
        query.append(getProlog());
        readColumnFromTable(query, columns);
        query.append(getEpilog()).append(table.getTableName()).append('.');
    }

    protected void updateQueryPL(Table table) {
        List<Column> columns = table.getColumns();
        query.append(getProlog());
        readColumnFromTablePL(query, columns);
        query.append(getEpilog()).append(table.getNameRPL()).append('.');
    }

    protected void updateAnswer(Table table) {
        List<Column> columns = table.getColumns();
        answer.append("SELECT ");
        readColumnFromTable(answer, columns);
        answer.append(" FROM ").append(table.getTableName()).append(';');
    }

    protected void readColumnFromTable(StringBuilder string, List<Column> columns) {
        string.append(columns.get(0).getColumnName());
        for (int i = 1; i < columns.size(); i++) {
            string.append(", ").append(columns.get(i).getColumnName());
        }
    }

    protected void readColumnFromTablePL(StringBuilder string, List<Column> columns) {
        string.append(columns.get(0).getNamePL());
        for (int i = 1; i < columns.size(); i++) {
            string.append(", ").append(columns.get(i).getNamePL());
        }
    }

    protected Table getRandomTable(Schema s) {
        if (!allowed.containsKey(s.getName())) {
            allowed.put(s.getName(), removeForbidenElements(s, forbiddenList));
        }

        List<Table> tables = allowed.get(s.getName());
        return tables.get(new Random().nextInt(tables.size()));
    }

    
}


// old style seed generation. hashCode is much more simply
// keep it hear for posibility to use in future
//        long seed = 120483;
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");            
//            md.update(t.getId().toUpperCase().getBytes());
//            String md5 = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
//            seed = Long.parseUnsignedLong(md5.substring(0, 16), 16);
//            
//        } catch (NoSuchAlgorithmException|NumberFormatException ex) {
//            Logger.getLogger(SchemaLoader.class.getName()).log(Level.SEVERE, null, ex);
//        }
