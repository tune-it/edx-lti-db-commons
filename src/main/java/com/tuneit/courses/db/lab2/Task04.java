package com.tuneit.courses.db.lab2;

import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
class Task04 extends LabTask {
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

    protected void updateAnswer(Table table) {
        List<Column> columns = table.getColumns();
        answer.append("SELECT DISTINCT ");
        readColumnFromTable(answer, columns);
        answer.append(" FROM ").append(table.getTableName()).append(';');
    }

    protected void updateQuery(Table table) {
        updateQueryPL(table);
    }

}
