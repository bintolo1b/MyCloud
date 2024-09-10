package tem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DownloadController")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getParameter("path");
		String file = req.getParameter("file");

		// ma hoa ten file de khong gay ra loi ki tu o cac trinh duyet khi tao ten tap tin
		String encodedFileName = URLEncoder.encode(file, "UTF-8").replace("+", "%20");
		
		// (MIME) du lieu tra ve se la kieu nhi phan, phu hop voi tat ca cac kieu tep (img, pdf...)
		resp.setContentType("APPLICATION/OCTET-STREAM");
		//trinh duyet se xu li du lieu tra ve la tep tin tai ve, khong xem truc tiep, co ten phu thuoc vao filename
		resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);

		// doc file (tu thu muc tren server)
		FileInputStream fis = new FileInputStream(new File(path + File.separator + file));
		// ghi du lieu vao response cua http tra ve cho client
		ServletOutputStream out = resp.getOutputStream();
		
		//bo dem de chua du lieu doc 1kb
		byte[] buffer = new byte[1024];
		int bytesRead;

		//doc du lieu tu thu muc server va ghi vao tep tai ve cua response
		while ((bytesRead = fis.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
		}

		// Close streams
		fis.close();
		out.close();
	}
}
