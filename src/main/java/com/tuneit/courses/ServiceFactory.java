package com.tuneit.courses;

import com.tuneit.courses.db.DBTaskGeneratorService;
import com.tuneit.courses.example.ExampleTaskGeneratorService;

public class ServiceFactory {

    public static TaskGeneratorService getDataSourceService() {
        return new DBTaskGeneratorService();
    }

    public static TaskGeneratorService getExampleService() {
        return new ExampleTaskGeneratorService();
    }

}
