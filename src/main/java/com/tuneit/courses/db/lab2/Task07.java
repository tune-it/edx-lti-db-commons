package com.tuneit.courses.db.lab2;


import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Schema;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
public class Task07 extends LabTask {
    @XmlElement(name = "subtask")
    private List<TableColumn> tableColumns;

    @Override
    public LabTaskQA generate(Schema schema, Task task) {
        return super.generate(schema, task);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class TableColumn {
        @XmlAttribute(name = "table")
        private String table;

        @XmlAttribute(name = "column")
        private String column;


        @XmlElement(name = "left-position")
        @XmlList
        private List<String> leftPosition;

        @XmlElement(name = "right-position")
        @XmlList
        private List<String> rightPosition;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Value {
        @XmlValue
        private String value;
        @XmlAttribute(name = "start")
        private boolean start;
        @XmlAttribute(name = "end")
        private boolean end;
    }
}
