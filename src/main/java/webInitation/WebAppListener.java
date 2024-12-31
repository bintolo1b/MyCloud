package webInitation;

import helper.TemporaryFileCleaner;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class WebAppListener implements ServletContextListener {
    private TemporaryFileCleaner fileCleaner;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	System.out.println(sce.getServletContext().getRealPath("/temporary/img"));
    	WebPaths.TEMPORARY_IMG_PATH = sce.getServletContext().getRealPath("/temporary/img");
        WebPaths.DEFAULT_USER_AVATAR = sce.getServletContext().getRealPath("/assets/img/user.png");
        fileCleaner = new TemporaryFileCleaner();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (fileCleaner != null) {
            fileCleaner.shutdown();
        }
    }
}