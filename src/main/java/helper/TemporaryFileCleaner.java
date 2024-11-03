package helper;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TemporaryFileCleaner {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public TemporaryFileCleaner() {
        scheduler.scheduleAtFixedRate(this::cleanTemporaryFiles, 0, 30, TimeUnit.MINUTES);
    }

    private void cleanTemporaryFiles() {
        File tempDir = new File("D:\\Java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\PBL4\\temporary\\img");
        for (File file : tempDir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
