package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MymemberDAO;

@WebServlet("/checkid.do")
public class checkid extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public checkid() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		MymemberDAO mdao = MymemberDAO.getinstancememberdao();
		
		int result = mdao.serchid(id);
		
		PrintWriter out = response.getWriter();
		out.print(result);
		
	}

}
