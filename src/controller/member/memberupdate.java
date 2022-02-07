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
import dto.MymemberVO;

/**
 * Servlet implementation class memberupdate
 */
@WebServlet("/memberupdate.do")
public class memberupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberupdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userid"); 
		
		MymemberDAO mdao = MymemberDAO.getinstancememberdao();
		MymemberVO mvo = mdao.selectid(id);
		
		request.setAttribute("mvo", mvo);
		
		RequestDispatcher rd = request.getRequestDispatcher("member/memberupdate.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MymemberDAO mdao = MymemberDAO.getinstancememberdao();
		MymemberVO mvo = new MymemberVO();
		String pw = "";
		
		if(request.getParameter("pw")!="") {
		pw = mdao.pwkae(request.getParameter("pw")); 
		}
		
		mvo.setId(request.getParameter("id"));
		mvo.setPw(pw);
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));

		
		int result = mdao.updatemember(mvo);
		
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
