package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MymemberDAO;
import dto.MymemberVO;

/**
 * Servlet implementation class adminupdate
 */
@WebServlet("/admin.update")
public class adminupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public adminupdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MymemberVO mvo = new MymemberVO();
		
		mvo.setId(request.getParameter("userid"));
		mvo.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		
		MymemberDAO.getinstancememberdao().updateadmin(mvo);
		
		response.sendRedirect("admin.do");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
