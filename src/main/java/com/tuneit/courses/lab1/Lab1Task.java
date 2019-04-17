package com.tuneit.courses.lab1;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.lab1.schema.Schema01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class Lab1Task implements LabTask {
    protected StringBuilder query;
    protected StringBuilder answer;

    @Override
    public LabTaskQA generate(Schema schema, Task task) {
        return generate((Schema01) schema, task);
    }

    public abstract LabTaskQA generate(Schema01 schema, Task task);

    protected void writeColumnToQuery(List<String> elements, String delimiter, StringBuilder stringBuilderOut) {
        for (int i = 0; i < elements.size(); i++) {
            stringBuilderOut.append(elements.get(i));
            if (i != elements.size() - 1) {
                stringBuilderOut.append(delimiter);
            }
        }
    }
}
