package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@XmlAccessorType(XmlAccessType.NONE)
class Task03 extends LabTask {
    @XmlElement(name = "forbidden-list")
    protected List<String> forbiddenList = new ArrayList<>();
    @XmlTransient
    static HashMap<String,List<Table>> allowed = new HashMap<>();

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
        Random r = new Random();//getRandom(t);
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
