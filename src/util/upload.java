package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/upload.do")
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//서버상의 실제 경로 찾는 방법
		ServletContext svc = request.getServletContext();
		String path = svc.getRealPath("upload");
		System.out.println("server real directory : "+path);
		String encType = "utf-8";
		int maxPostsize = 20 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(
				request,
				path,
				maxPostsize,
				encType,
				new DefaultFileRenamePolicy()
				);
		
		response.setContentType("text/html, charset=utf-8");
		PrintWriter out = response.getWriter();
		String file= multi.getFilesystemName("file");
		out.print("upload"+"/"+file);
		out.close(); 
	}

}
