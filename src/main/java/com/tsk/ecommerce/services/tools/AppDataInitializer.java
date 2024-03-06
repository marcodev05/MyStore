package com.tsk.ecommerce.services.tools;

import com.tsk.ecommerce.services.user.UserDataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppDataInitializer implements CommandLineRunner {
    private static final Logger logger =  LoggerFactory.getLogger(AppDataInitializer.class);

    @Autowired
    private UserDataInitializer userDataInitializer;

    @Override
    public void run(String... args) throws Exception {
    userDataInitializer.initializeRole();
    //userDataInitializer.initAdminUser();
    logger.info("----- Ecommerce application started ------");
    }

}
