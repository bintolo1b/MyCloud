package controller;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.ConvertFileToPDF;

@WebServlet(urlPatterns = "/displayfilecontroller")
public class DisplayFileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("folderPath") != null && req.getParameter("fileName") != null) {
            String currentFolderPath = req.getParameter("folderPath");
            String fileName = req.getParameter("fileName");
            String filePath = currentFolderPath + File.separator + fileName;
            
            byte[] pdfData = null;
            if (filePath.endsWith(".txt")) {
                pdfData = ConvertFileToPDF.convertTxtToPDF(filePath);
            } else if (filePath.endsWith(".docx")) {
                pdfData = ConvertFileToPDF.convertDocxToPDF(filePath);
            } else if (filePath.endsWith(".pptx")) {
                pdfData = ConvertFileToPDF.convertPPTXToPDF(filePath);
            } else if (filePath.endsWith(".xls")) {
                pdfData = ConvertFileToPDF.convertXlsToPDF(filePath);
            } else if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) {
                File imgFile = new File(filePath);
                if (imgFile.exists()) {
                    resp.setContentType("image/jpeg");
                    resp.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
                    resp.getOutputStream().write(java.nio.file.Files.readAllBytes(imgFile.toPath()));
                    return; // Kết thúc sau khi gửi hình ảnh
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
                    return;
                }
            } else if (filePath.endsWith(".png")) {
                File imgFile = new File(filePath);
                if (imgFile.exists()) {
                    resp.setContentType("image/png");
                    resp.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
                    resp.getOutputStream().write(java.nio.file.Files.readAllBytes(imgFile.toPath()));
                    return; // Kết thúc sau khi gửi hình ảnh
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
                    return;
                }
            }

            // Nếu file là một trong các loại cần chuyển đổi sang PDF
            if (pdfData != null) {
                resp.setContentType("application/pdf");
                resp.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
                resp.setContentLength(pdfData.length);
                resp.getOutputStream().write(pdfData);
            } else {
                resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "File type not supported");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
        }
    }
}
