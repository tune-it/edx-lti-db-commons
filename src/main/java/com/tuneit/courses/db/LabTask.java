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
        for(Table t : s.getTables()) {
            if (!forbidenElements.stream().filter(str -> str.equalsIgnoreCase(t.getTableName())).findFirst().isPresent()) {
                Table allowed_table = new Table();
                //ACHTUNG govnogod
                allowed_table.setName(t.getName());
                allowed_table.setNameRPL(t.getNameRPL());
                allowed_table.setTableName(t.getTableName());
                allowed_table.setColumns(new ArrayList<>());
                for(Column c:t.getColumns()) {
                    String slist = t.getTableName()+":"+c.getColumnName();
                    if (!forbidenElements.stream().filter(str -> str.equalsIgnoreCase(t.getTableName())).findFirst().isPresent()) {
                        Column allowed_column = new Column();
                        //ACHTUNG govnogod
                        allowed_column.setColumnName(c.getColumnName());
                        allowed_column.setName(c.getName());
                        allowed_column.setNamePL(c.getNamePL());
                        allowed_table.getColumns().add(allowed_column);
                    }
                }
                allowed.add(allowed_table);
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
