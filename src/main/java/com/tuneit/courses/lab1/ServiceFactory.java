package com.tuneit.courses.lab1;

import com.tuneit.courses.lab1.db.DBTaskGeneratorService;

public class ServiceFactory {

    public static TaskGeneratorService getDataSourceService() {
        return new DBTaskGeneratorService();
    }


}
