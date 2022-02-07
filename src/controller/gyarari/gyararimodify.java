package controller.gyarari;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Onepage;
import dto.KeisibanVO;
import util.Criteria;

/**
 * Servlet implementation class gyararimodify
 */
@WebServlet("/gyarari.modify")
public class gyararimodify extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public gyararimodify() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		Criteria cri = new Criteria();
		cri.setHowmany(Integer.parseInt(request.getParameter("howmany")));
		cri.setKeyword(request.getParameter("word"));
		cri.setPagenum(Integer.parseInt(request.getParameter("pagenum")));
		cri.setType(request.getParameter("type"));
		
		Onepage onepage = Onepage.onepageinstance();
		KeisibanVO ovo = onepage.selectone(bno, "gyarari");
		
		request.setAttribute("modify", ovo);
		request.setAttribute("donopage", "ギャラリー");
		request.setAttribute("doko", "gyarari");
		request.setAttribute("cri", cri);
		
		RequestDispatcher rd = request.getRequestDispatcher("onekeisiban/update.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		Onepage onepage = Onepage.onepageinstance();
		KeisibanVO ovo = new KeisibanVO();
		
		Criteria cri = new Criteria();
		cri.setHowmany(Integer.parseInt(request.getParameter("howmany")));
		cri.setKeyword(request.getParameter("word"));
		cri.setPagenum(Integer.parseInt(request.getParameter("pagenum")));
		cri.setType(request.getParameter("type"));
		
		ovo.setTitle(request.getParameter("title"));
		ovo.setContent(request.getParameter("content"));
		ovo.setBno(bno);
		onepage.update(ovo, "gyarari");
		
		request.setAttribute("cri", cri);
		
		int howmany = (Integer.parseInt(request.getParameter("howmany")));
		String word=(request.getParameter("word"));
		int pagenum=(Integer.parseInt(request.getParameter("pagenum")));
		String type=(request.getParameter("type"));
		
		
		response.sendRedirect("gyarari.read?bno="+bno+"&pagenum="+pagenum+
				"&howmany="+howmany+"&type="+type+"&word="+word);
	}

}
