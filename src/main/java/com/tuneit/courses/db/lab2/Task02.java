package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
