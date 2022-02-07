package controller.member;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

@WebServlet("/sendemail.do")
public class sendemail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public sendemail() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String to_email = request.getParameter("email");
		
		//mail server 설정
        String host = "smtp.naver.com";
        String user = "id4459@naver.com"; //자신의 네이버 계정
        String password = "1dmfdlwwlakfwk!";//자신의 네이버 패스워드
        
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        // 모르겠음 
        
        StringBuffer mailcheck = new StringBuffer();
        Random rnd = new Random();
        for(int i=0;i<10;i++) {
        	 int rIndex = rnd.nextInt(3); //0~2까지의 무작위 정수값 생성
             switch (rIndex) {
             case 0:
                 // a-z
            	 mailcheck.append((char) ((int) (rnd.nextInt(26)) + 97));
                 break;
             case 1:
                 // A-Z
            	 mailcheck.append((char) ((int) (rnd.nextInt(26)) + 65));
                 break;
             case 2:
                 // 0-9
            	 mailcheck.append((rnd.nextInt(10)));
                 break;
             }
             //다시한번 공부 할 것
        }
        
        String checkkey = mailcheck.toString();
        System.out.println(checkkey);
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });
        
      //email 전송
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user, "何食べる"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
            
            //메일 제목
            msg.setSubject("誰でも作れるレシピからのメッセージです.");
            //메일 내용
            msg.setText("暗証番号は :"+mailcheck);
            
            Transport.send(msg);
            System.out.println("이메일 전송");
            
        }catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
        HttpSession saveKey = request.getSession(); //모든페이지에서 사용하기 위해 session 객체 생성
        saveKey.setAttribute("checkkey", checkkey);
        //패스워드 바꿀때 뭘 바꿀지 조건에 들어가는 id
        
        JSONObject obj = new JSONObject();
        obj.put("msg", "メールに暗証番号をお送りいたしました");
        
        response.setContentType("application/x-json; charset=utf-8");
        response.getWriter().print(obj);
        
        //https://mkil.tistory.com/339?category=542165 참고
	}
}
