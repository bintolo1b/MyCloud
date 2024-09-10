package tem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Collection;

@WebServlet(urlPatterns = "/UploadController")
@MultipartConfig
public class UploadController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getParameter("path");
        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            if (part.getSubmittedFileName() != null) {
                String fileName = part.getSubmittedFileName();
                File file = new File(path + File.separator + fileName);
                
                int count = 1;
                String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
                String extension = fileName.substring(fileName.lastIndexOf('.'));
                while (file.exists()) {
                    // Tạo tên tệp tin mới với chỉ số trong dấu ngoặc đơn
                    fileName = baseName + "(" + count + ")" + extension;
                    file = new File(path + File.separator + fileName);
                    count++;
                }

                InputStream input = null;
                OutputStream output = null;
                try {
                    input = part.getInputStream();
                    output = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (input != null) input.close();
                        if (output != null) output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        response.sendRedirect("FileController?path=" + URLEncoder.encode(path, "UTF-8"));
    }
}
