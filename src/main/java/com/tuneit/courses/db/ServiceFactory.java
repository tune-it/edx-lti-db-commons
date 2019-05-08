package com.tuneit.courses.db;

public class ServiceFactory {

    public static TaskGeneratorService getDataSourceService() {
        return new DBTaskGeneratorService();
    }


}
