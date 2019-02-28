package com.tuneit.courses.db.lab2;

import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
class Task03 extends LabTask {
    @Override
    public String toString() {
        return "Task01{" + super.toString()+ ", forbiddenList=" + forbiddenList + '}';
    }

    protected void updateQuery(Table table) {
        updateQueryPL(table);
    }

}
