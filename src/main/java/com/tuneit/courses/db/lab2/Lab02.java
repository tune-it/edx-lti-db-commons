package com.tuneit.courses.db.lab2;

import com.tuneit.courses.Task;
import com.tuneit.courses.db.Lab;
import com.tuneit.courses.db.LabTask;
import com.tuneit.courses.db.LabTaskQA;
import com.tuneit.courses.db.schema.Column;
import com.tuneit.courses.db.schema.Schema;
import com.tuneit.courses.db.schema.Table;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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

