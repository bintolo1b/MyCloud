package helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class TemporaryFolderHelper {
	public static String SaveImage(byte[] imageData, String username, String tempImgFolder) throws IOException {
		String fileName = username + "_" + UUID.randomUUID().toString() + ".png";
		File imgFile = new File(tempImgFolder + File.separator + fileName);
		try (FileOutputStream fos = new FileOutputStream(imgFile)) {
            fos.write(imageData);
        }
		
		return "/PBL4/temporary/img/" + imgFile.getName();
    }
	
	public static String convertFirstPDFPageToImgAndSave(byte[] pdfData, String username, String tempImgFolder) throws IOException {
		String fileName = username + "_" + UUID.randomUUID().toString() + ".png";
		File imgFile = new File(tempImgFolder + File.separator + fileName);
		
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfData);
        	
            PDDocument document = PDDocument.load(inputStream)) {

            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 100);

            ImageIO.write(image, "png", imgFile);
        }
        
        return "/PBL4/temporary/img/" + imgFile.getName();
    }
}
