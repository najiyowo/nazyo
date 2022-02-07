package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MymemberDAO;

/**
 * Servlet implementation class login
 */
@WebServlet("/login.do")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("member/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MymemberDAO mdao = MymemberDAO.getinstancememberdao();
		
		String id = request.getParameter("id");
		String pw = mdao.pwkae(request.getParameter("pw"));
		
		int result=0;
		int admin=0;
		
		result = mdao.checkidandpw(id,pw);
		admin = mdao.checkadmin(id);
		
		
		HttpSession session = request.getSession();
		
		if(result==1) { //로그인 성공
			session.setAttribute("userid", id);
			if(admin==1) {
				session.setAttribute("admin", admin);
			}
			response.sendRedirect("main.do");
		}else if(result==-1) {//비번이 다름
			PrintWriter out = response.getWriter();
			out.print(-1);
		}else {//아이디가 없음
			PrintWriter out = response.getWriter();
			out.print(0);
		}
	}

}
