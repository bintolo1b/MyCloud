package helper;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import config.WebPaths;

public class TemporaryFileCleaner {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public TemporaryFileCleaner() {
        scheduler.scheduleAtFixedRate(this::cleanTemporaryFiles, 0, 30, TimeUnit.MINUTES);
    }

    private void cleanTemporaryFiles() {
        File tempDir = new File(WebPaths.TEMPORARY_IMG_PATH);
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
