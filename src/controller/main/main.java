package controller.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Onepage;
import dto.KeisibanVO;

/**
 * Servlet implementation class main
 */
@WebServlet("/main.do")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public main() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<KeisibanVO> listosirase = Onepage.onepageinstance().selectformain("osirase"); 
		List<KeisibanVO> listgyarari = Onepage.onepageinstance().selectformain("gyarari");
		List<KeisibanVO> listresipi = Onepage.onepageinstance().selectformain("resipi");
		
		request.setAttribute("losirase", listosirase);
		request.setAttribute("lgyarari", listgyarari);
		request.setAttribute("lresipi", listresipi);

		RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
