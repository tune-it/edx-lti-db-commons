package com.tuneit.courses;

public class ServiceFactory {

    public static TaskGeneratorService getDataSourceService() {
        return new DBTaskGeneratorService();
    }


}
