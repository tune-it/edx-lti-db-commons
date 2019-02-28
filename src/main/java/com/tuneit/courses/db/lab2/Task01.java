package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
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
class Task01 extends LabTask {
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
        List<Table> tables = allowed.get(s.getName());
        Random r = getRandom(t);
        r.nextBoolean();
        String tableName = tables.get(r.nextInt(tables.size())).getTableName();
        return new LabTaskQA(t.getId(), getProlog()+tableName+getEpilog(),
                             "SELECT * FROM "+tableName+";");
    }
}
