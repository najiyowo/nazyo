package controller.resipi;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dto.PagingVO;
import dto.KeisibanVO;
import util.Criteria;
import util.Kensaku;
import util.Paging;



@WebServlet("/resipi.do")
public class resipi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public resipi() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String type = "";// 검색 타입
		String word = "";// 검색어
		String query ="";
		
		int pagenum = 1; //기본 페이지
		int howmany = 10; //기본 리스트 수 
		
		if(request.getParameter("type") != null && request.getParameter("word")!="") {
			type = request.getParameter("type"); //title
			word = request.getParameter("word"); //일본
			query = type + " like '%" + word + "%'"; //title like '%일본%'
		}

		
		if(request.getParameter("pagenum")!=null) { //받아온 num 이 null이 아닐때 = pagenum이 1이 아닐때
			pagenum = Integer.parseInt(request.getParameter("pagenum"));//보고자 하는 페이지 넘버
		}
		if(request.getParameter("howmany")!=null) { //10개의 리스트가 아닐 때
			howmany = Integer.parseInt(request.getParameter("howmany"));//한 페이지에서 보고자 하는 리스트 수
		}
		
		Criteria cri = new Criteria();
		cri.setHowmany(howmany);
		cri.setPagenum(pagenum);
		cri.setType(type);
		cri.setKeyword(word);
		
		Paging paging = Paging.pageutil();
		Kensaku kutil = Kensaku.kensakuutilinstance();
		
		List<KeisibanVO> list= paging.paging("resipi",cri,query); // 각 게시판 이름 담아서 게시판에 맞게끔 검색
		int count = kutil.noticeAllCount(query,"resipi");
		
		PagingVO pvo = new PagingVO(cri, count);
		
		request.setAttribute("cri", cri);
		request.setAttribute("donopage", "レシピ");
		request.setAttribute("doko", "resipi");
		request.setAttribute("donolist", list);
		request.setAttribute("pageMaker", pvo);
//		request.setAttribute("count", count);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("onekeisiban/keisiban.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
