package config;

import helper.TemporaryFileCleaner;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class WebAppListener implements ServletContextListener {
    private TemporaryFileCleaner fileCleaner;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        fileCleaner = new TemporaryFileCleaner();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (fileCleaner != null) {
            fileCleaner.shutdown();
        }
    }
}