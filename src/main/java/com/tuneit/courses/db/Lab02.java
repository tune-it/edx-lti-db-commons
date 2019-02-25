package com.tuneit.courses.db;

import com.tuneit.courses.Task;
import static com.tuneit.courses.db.LabTask.removeForbidenElements;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author serge
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Lab02 extends Lab {
    
    @XmlElements({
        @XmlElement(name="task01", type=Task01.class),
        @XmlElement(name="task02", type=Task02.class),
        @XmlElement(name="task03", type=Task03.class),
        @XmlElement(name="task04", type=Task04.class),

    })
    private List<LabTask> labTask = new ArrayList<>();

    @Override
    public List<LabTask> getLabTask() {
        return labTask;
    }

    public void setLabTask(List<LabTask> labTask) {
        this.labTask = labTask;
    }

    @Override
    public String toString() {
        return "Lab02{" + super.toString()+", labTask=" + labTask + '}';
    }

}

@XmlAccessorType(XmlAccessType.NONE)
class Task01 extends LabTask {
    @XmlElement(name = "forbidden-list")
    protected List<String> forbiddenList = new ArrayList<>();
    @XmlTransient static HashMap<String,List<Table>> allowed = new HashMap<>();

    public List<String> getForbiddenList() {
        return forbiddenList;
    }

    public void setForbiddenList(List<String> forbiddenList) {
        this.forbiddenList = forbiddenList;
    }

    @Override
    public String toString() {
        return "Task01{" + super.toString()+ ", forbiddenList=" + forbiddenList + '}';
    }

    @Override
    public LabTaskQA generate(Schema s, Task t) {
        if (!allowed.containsKey(s.getName())) {
            allowed.put(s.getName(),removeForbidenElements(s, forbiddenList));
        }
        List<Table> tables = allowed.get(s.getName());
        Random r = getRandom(t);
        r.nextBoolean();
        String tableName = tables.get(r.nextInt(tables.size())).getTableName();
        return new LabTaskQA(t.getId(), getProlog()+tableName+getEpilog(),
                             "SELECT * FROM "+tableName+";");
    }
}

@XmlAccessorType(XmlAccessType.NONE)
class Task02 extends LabTask {
    @Override
    public LabTaskQA generate(Schema s, Task t) {
        StringBuilder qb = new StringBuilder();
        StringBuilder ab = new StringBuilder();
        List<Table> tables = s.getTables();
        Random r = getRandom(t);
        r.nextBoolean();
        int tableIdx = r.nextInt(tables.size()); 
        Table table = tables.get(tableIdx);
        List<Column> columns = table.getColumns();
        int colCnt = columns.size();
        int colNum = r.nextInt(colCnt-1)+1;
        qb.append(getProlog());
        ab.append("SELECT ");
        ArrayList<Integer> used_columns = new ArrayList<Integer>();
        for(int j=0;j<colNum;j++) {
            int colIdx = r.nextInt(colCnt);
            if (used_columns.contains(colIdx)) {
                j--;
                continue;
            }
            used_columns.add(colIdx);
            if (j!=0) { 
                qb.append(", ");
                ab.append(", ");
            }
            qb.append(columns.get(colIdx).getColumnName());
            ab.append(columns.get(colIdx).getColumnName());
        }
        qb.append(getEpilog()).append(table.getTableName()).append('.');
        ab.append(" FROM ").append(table.getTableName()).append(';');
        
        return new LabTaskQA(t.getId(), qb.toString(), ab.toString());
    }
}

@XmlAccessorType(XmlAccessType.NONE)
class Task03 extends LabTask {
    @XmlElement(name = "forbidden-list")
    protected List<String> forbiddenList = new ArrayList<>();
    @XmlTransient static HashMap<String,List<Table>> allowed = new HashMap<>();

    public List<String> getForbiddenList() {
        return forbiddenList;
    }

    public void setForbiddenList(List<String> forbiddenList) {
        this.forbiddenList = forbiddenList;
    }

    @Override
    public String toString() {
        return "Task01{" + super.toString()+ ", forbiddenList=" + forbiddenList + '}';
    }

    @Override
    public LabTaskQA generate(Schema s, Task t) {
        if (!allowed.containsKey(s.getName())) {
            allowed.put(s.getName(),removeForbidenElements(s, forbiddenList));
        }
        StringBuilder qb = new StringBuilder();
        StringBuilder ab = new StringBuilder();
        List<Table> tables = allowed.get(s.getName());
        Random r = getRandom(t);
        r.nextBoolean();

        int tableIdx = r.nextInt(tables.size()); 
        Table table = tables.get(tableIdx);
        List<Column> columns = table.getColumns();
        int colCnt = columns.size();
        int colNum = r.nextInt(colCnt-1)+1;
        qb.append(getProlog());
        ab.append("SELECT ");
        ArrayList<Integer> used_columns = new ArrayList<Integer>();
        for(int j=0;j<colNum;j++) {
            int colIdx = r.nextInt(colCnt);
            if (used_columns.contains(colIdx)) {
                j--;
                continue;
            }
            used_columns.add(colIdx);
            if (j!=0) { 
                qb.append(", ");
                ab.append(", ");
            }
            qb.append(columns.get(colIdx).getNamePL());
            ab.append(columns.get(colIdx).getColumnName());
        }
        qb.append(getEpilog()).append(table.getNameRPL()).append('.');
        ab.append(" FROM ").append(table.getTableName()).append(';');
        
        return new LabTaskQA(t.getId(), qb.toString(), ab.toString());
    }
}

@XmlAccessorType(XmlAccessType.NONE)
class Task04 extends LabTask {
    @XmlElement(name = "forbidden-list")
    protected List<String> forbiddenList = new ArrayList<>();
    @XmlTransient static HashMap<String,List<Table>> allowed = new HashMap<>();

    public List<String> getForbiddenList() {
        return forbiddenList;
    }

    public void setForbiddenList(List<String> forbiddenList) {
        this.forbiddenList = forbiddenList;
    }

    @Override
    public String toString() {
        return "Task01{" + super.toString()+ ", forbiddenList=" + forbiddenList + '}';
    }

    @Override
    public LabTaskQA generate(Schema s, Task t) {
        if (!allowed.containsKey(s.getName())) {
            allowed.put(s.getName(),removeForbidenElements(s, forbiddenList));
        }
        StringBuilder qb = new StringBuilder();
        StringBuilder ab = new StringBuilder();
        List<Table> tables = allowed.get(s.getName());
        Random r = getRandom(t);
        r.nextBoolean();

        int tableIdx = r.nextInt(tables.size()); 
        Table table = tables.get(tableIdx);
        List<Column> columns = table.getColumns();
        int colCnt = columns.size();
        int colNum = r.nextInt(colCnt-1)+1;
        qb.append(getProlog());
        ab.append("SELECT DISTINCT ");
        ArrayList<Integer> used_columns = new ArrayList<Integer>();
        for(int j=0;j<colNum;j++) {
            int colIdx = r.nextInt(colCnt);
            if (used_columns.contains(colIdx)) {
                j--;
                continue;
            }
            used_columns.add(colIdx);
            if (j!=0) { 
                qb.append(", ");
                ab.append(", ");
            }
            qb.append(columns.get(colIdx).getNamePL());
            ab.append(columns.get(colIdx).getColumnName());
        }
        qb.append(getEpilog()).append(table.getNameRPL()).append('.');
        ab.append(" FROM ").append(table.getTableName()).append(';');
        
        return new LabTaskQA(t.getId(), qb.toString(), ab.toString());
    }
}

@XmlAccessorType(XmlAccessType.NONE)
class Task05 extends LabTask {
    @Override
    public LabTaskQA generate(Schema s, Task t) {
        return new LabTaskQA(t.getId(), "dummyQuestion", "dummyAnswer");
    }
}