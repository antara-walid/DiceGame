package com.web.helpers;

import com.web.dao.GameDatabaseManagement;
import jakarta.servlet.ServletContext;


// this class is the one responsible for the dependency injection
// we have two implementations for IGameDataManagement Interface : GameContextManagement and ......

public class DataManagementFactory {
    // the methode getFactory will be used in ApplicationInitializer listener
    public static IGameDataManagement getFactory(String pType, ServletContext context) {

        if (!"mysql".equals(pType)) {
            return GameContextManagement.getInstance(context);
        }

        return GameDatabaseManagement.getInstance();


    }
}
