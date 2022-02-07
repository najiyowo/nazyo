package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class checkemail
 */
@WebServlet("/checkemail.do")
public class checkemail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkemail() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		String checkemailpw = request.getParameter("checkemailpw");
		String checkkey = (String)request.getSession().getAttribute("checkkey");
		//saveKey.setAttribute("AuthenticationKey", AuthenticationKey); 세션에 저장시킨 값
		
		JSONObject obj = new JSONObject();
		
		if(!checkkey.equals(checkemailpw)) {
			obj.put("msg", "暗証番号が一致しません");
			obj.put("check", "nok");
		}else {
			obj.put("msg", "確認完了です");
			obj.put("check", "ok");
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("allplication/x-json, charset=utf-8");
		response.getWriter().print(obj);
	}

}
