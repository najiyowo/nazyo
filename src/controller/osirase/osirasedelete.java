package controller.osirase;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Onepage;

/**
 * Servlet implementation class osirasedelete
 */
@WebServlet("/osirase.delete")
public class osirasedelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public osirasedelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		Onepage onepage = Onepage.onepageinstance();
		
		onepage.delete(bno,"osirase");
		
		response.sendRedirect("osirase.do");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
