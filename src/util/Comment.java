package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CommentDAO;
import dto.CommentVO;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment.do")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String doko = request.getParameter("doko");
		
		CommentDAO cdao = CommentDAO.Commentinstance();
		List<CommentVO> clist = cdao.selectlist(bno,doko);
		
		
		Gson gson = new Gson();
		String cmtList = gson.toJson(clist); // json을 변경?
		
		PrintWriter out = response.getWriter();
		out.print(cmtList);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		CommentVO cvo = new CommentVO();
		cvo.setBno(Integer.parseInt(request.getParameter("bno")));
		cvo.setComment_content(request.getParameter("comment_text"));
		cvo.setKeisiban(request.getParameter("doko"));
		cvo.setUserid(request.getParameter("userid"));

		CommentDAO.Commentinstance().insertcomment(cvo);
	}

}
