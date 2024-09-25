package Helper;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.spire.presentation.Presentation;
import com.spire.xls.Workbook;

public class ConvertFileToPdfByteArray {
	public static byte[] convertFileToPDF(String inputFilePath) {
		byte[] pdfData = null;
        
		if (inputFilePath.endsWith(".pdf")) 
			pdfData = readPdfFile(inputFilePath);
		else if (inputFilePath.endsWith(".txt"))
        	pdfData = ConvertFileToPdfByteArray.convertTxtToPdfByteArr(inputFilePath);
        else if (inputFilePath.endsWith(".docx"))
        	pdfData = ConvertFileToPdfByteArray.convertDocxToPdfByteArr(inputFilePath);
        else if (inputFilePath.endsWith(".pptx"))
        	pdfData = ConvertFileToPdfByteArray.convertPPTXToPdfByteArr(inputFilePath);
        else if (inputFilePath.endsWith(".xls"))
        	pdfData = ConvertFileToPdfByteArray.convertXlsToPdfByteArr(inputFilePath);
	  
        return pdfData;
	}
	
	private static byte[] readPdfFile(String pdfPath) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            FileInputStream inputStream = new FileInputStream(pdfPath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static byte[] convertTxtToPdfByteArr(String inputFilePath) {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    try (PdfWriter writer = new PdfWriter(outputStream)) {
	        PdfDocument pdfDoc = new PdfDocument(writer);
	        Document document = new Document(pdfDoc);
	        boolean hasContent = false;

	        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                document.add(new Paragraph(line));
	                hasContent  = true;
	            }
	        }

	        if (!hasContent) {
	        	document.add(new Paragraph(" "));
	        }

	        document.close();
	        return outputStream.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static byte[] convertDocxToPdfByteArr(String docxPath) {
	    try {
	        // Tạo đối tượng Document từ file DOCX
	    	com.aspose.words.Document doc = new com.aspose.words.Document(docxPath);
	        
	        // Sử dụng ByteArrayOutputStream để lưu file PDF vào bộ nhớ
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        
	        // Lưu tài liệu dưới dạng PDF vào ByteArrayOutputStream
	        doc.save(outputStream, com.aspose.words.SaveFormat.PDF);
	        
	        // Trả về dữ liệu PDF dưới dạng mảng byte
	        return outputStream.toByteArray();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static byte[] convertPPTXToPdfByteArr(String pptxPath) {
        try {
        	Presentation ppt = new Presentation();
        	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        	ppt.loadFromFile(pptxPath);
			ppt.saveToFile(outputStream, com.spire.presentation.FileFormat.PDF);
			return outputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public static byte[] convertXlsToPdfByteArr(String pptxPath) {
        try {
        	Workbook workbook = new Workbook();
        	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        	workbook.loadFromFile(pptxPath);
        	workbook.saveToStream(outputStream, com.spire.xls.FileFormat.PDF);
        	return outputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}
