package controller.osirase;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Onepage;
import dto.KeisibanVO;

@WebServlet("/osirase.writepro")
public class osirasewritepro extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public osirasewritepro() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		KeisibanVO ovo = new KeisibanVO();

		ovo.setContent(request.getParameter("content"));
		ovo.setTitle(request.getParameter("title"));
		ovo.setWriter(request.getParameter("writer"));
		
		Onepage onepage = Onepage.onepageinstance();
		onepage.insert(ovo, "osirase");
		
		response.sendRedirect("osirase.do");
	}

}
