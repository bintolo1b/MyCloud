package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConvertImgFileToImgByteArray {
	public static byte[] imageToByteArr(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            byte[] bytesArray = new byte[(int) imageFile.length()];
            fis.read(bytesArray);
            return bytesArray;
        }
    }
}
