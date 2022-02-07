package controller.resipi;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import dao.Onepage;
import dto.CommentVO;
import dto.KeisibanVO;
import util.Criteria;

@WebServlet("/resipi.read")
public class resipiread extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public resipiread() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		Criteria cri = new Criteria();
		
		if(request.getParameter("pagenum")!=null) {
			cri.setPagenum(Integer.parseInt(request.getParameter("pagenum")));
			cri.setHowmany(Integer.parseInt(request.getParameter("howmany")));
			cri.setType(request.getParameter("type"));
			cri.setKeyword(request.getParameter("word"));
		}else {
			cri.setPagenum(1);
			cri.setHowmany(10);
		}
		
		Onepage onepage = Onepage.onepageinstance();
		KeisibanVO siraselist = onepage.selectone(bno, "resipi");
		KeisibanVO prev = onepage.prev(bno, "resipi");
		KeisibanVO next = onepage.next(bno, "resipi");
		onepage.count(bno, "resipi");
		
		List<CommentVO> comment = CommentDAO.Commentinstance().selectlist(bno, "resipi");
		
		request.setAttribute("commentlist", comment);
		request.setAttribute("donopage", "レシピ");
		request.setAttribute("doko", "resipi");
		request.setAttribute("list", siraselist);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("cri", cri);
		

		RequestDispatcher rd = request.getRequestDispatcher("onekeisiban/read.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
