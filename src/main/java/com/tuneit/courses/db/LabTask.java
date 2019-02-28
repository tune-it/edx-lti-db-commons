package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author serge
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class LabTask {
    @XmlAttribute(name="description") protected String description;    
    @XmlAttribute(name="id") protected String id;
    
    @XmlElement(name="prolog") protected String prolog;
    @XmlElement(name="epilog") protected String epilog;

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

                for (Column column : table.getColumns()) {
                    if (forbidenElements.stream().noneMatch(str -> str.equalsIgnoreCase(table.getTableName()))) {
                        Column allowed_column = column.clone();
                        allowedTable.getColumns().add(allowed_column);
                    }
                }
                allowed.add(allowedTable);
            }
        }
        return allowed;
    }

    @Override
    public String toString() {
        return "LabTask{" + "description=" + description + ", id=" + id + 
                ", prolog=" + prolog + ", epilog=" + epilog + '}';
    }
    
    public Random getRandom (Task t) {     
        int seed = t.getId().toUpperCase().hashCode();
        return new Random(seed);
    }
    
    public abstract LabTaskQA generate(Schema s, Task t);

    
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
