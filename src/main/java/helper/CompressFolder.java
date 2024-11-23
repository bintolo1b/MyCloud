package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressFolder {
	public static void CompressFolderToZipFile(File downloadedFolder, String fatherFolderName, ZipOutputStream zos) throws IOException {
		File[] childfiles  = downloadedFolder.listFiles();
		
		for (File childFile:childfiles) {
			if (childFile.isDirectory()) {
				String zipFolderPath = fatherFolderName + childFile.getName() +"/"; 
				zos.putNextEntry(new ZipEntry(zipFolderPath));
				zos.closeEntry();
				CompressFolderToZipFile(childFile, zipFolderPath , zos);
			}
			else {
				FileInputStream fis = new FileInputStream(childFile);
				String zipFilePath = fatherFolderName + childFile.getName();
				zos.putNextEntry(new ZipEntry(zipFilePath));
				
				byte[] buffered = new byte[1024];
				int bytesRead;
				while((bytesRead = fis.read(buffered)) != -1) {
					zos.write(buffered, 0 , bytesRead);
				}
				
				zos.closeEntry();
				fis.close();
			}
		}
	}
}
