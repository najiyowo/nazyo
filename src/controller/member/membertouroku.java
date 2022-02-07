package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MymemberDAO;
import dto.MymemberVO;


@WebServlet("/membertouroku.do")
public class membertouroku extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public membertouroku() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("member/touroku.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MymemberDAO mdao = MymemberDAO.getinstancememberdao();
		MymemberVO mvo = new MymemberVO();
		
		String pw = mdao.pwkae(request.getParameter("pw")); 
		
		mvo.setId(request.getParameter("id"));
		mvo.setPw(pw);
		mvo.setEmail(request.getParameter("email"));
		mvo.setPhone(request.getParameter("phone"));

		
		int result = mdao.insertintomember(mvo);
		
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
