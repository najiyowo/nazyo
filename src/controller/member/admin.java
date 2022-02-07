package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MymemberDAO;
import dto.MymemberVO;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin.do")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MymemberDAO mdao = MymemberDAO.getinstancememberdao();
		
		List<MymemberVO> list = mdao.selectid();
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("member/admin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
