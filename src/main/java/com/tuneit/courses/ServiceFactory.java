package com.tuneit.courses;

import com.tuneit.courses.db.DBTaskGeneratorService;

public class ServiceFactory {

    public static TaskGeneratorService getDataSourceService() {
        return new DBTaskGeneratorService();
    }


}
