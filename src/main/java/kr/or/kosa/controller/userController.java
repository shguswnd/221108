package kr.or.kosa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.koreamemberDao;
import kr.or.kosa.dto.koreamemberDto;
import kr.or.kosa.service.LoginIdCheckService;
import kr.or.kosa.service.MemberListService;
import kr.or.kosa.service.UserInsertService;


@WebServlet("*.do")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public userController() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 HttpSession session = request.getSession();
    	 koreamemberDto dto = new koreamemberDto();
    	 koreamemberDao dao = new koreamemberDao();
    	 List<koreamemberDto> dtolist;
    	 
         boolean success =  false;
         int check = 0;
         int age = 0;
         String requestURI = request.getRequestURI();
         String contextPath = request.getContextPath();
         String urlcommand = requestURI.substring(contextPath.length());
         String viewpage="";
         PrintWriter out = response.getWriter();
                 
        //파라미터값
        String id= "";
        String pwd = "";
        String name = ""; 

        String gender = "";
        String email = "";
        String ip = request.getRemoteAddr();
        
        System.out.println("urlcommand : " + urlcommand);

        
        //인터페이스 정의
        
        Action action = null;
        ActionForward forward = null;
          

        if(urlcommand.equals("/index.do")) {
        	//session.setAttribute("id", id);
        	forward = new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("/index.jsp");  
        }else if(urlcommand.equals("/login.do")) {
        	forward = new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("/WEB-INF/views/login.jsp");
        }else if(urlcommand.equals("/loginok.do")) {
 		   action = new LoginIdCheckService();
 		   forward = action.execute(request, response);            
	   }else if(urlcommand.equals("/register.do")) {
	       forward = new ActionForward();
	       forward.setRedirect(false);
	       forward.setPath("/WEB-INF/views/register.jsp");
	   }else if(urlcommand.equals("/insert.do")) {
		   action = new UserInsertService();
 		   forward = action.execute(request, response);     
	   }else if(urlcommand.equals("/logout.do")) {
		   forward = new ActionForward();
	       forward.setRedirect(false);
	       forward.setPath("/WEB-INF/views/logout.jsp");
	   }else if(urlcommand.equals("/memberlist.do")) {
		   action = new MemberListService();
 		   forward = action.execute(request, response);     
	   }else if(urlcommand.equals("/delete.do")) {
		   action = new MemberListService();
 		   forward = action.execute(request, response);
	   }else if(urlcommand.equals("/edit.do")) {		   
		   action = new MemberListService();
 		   forward = action.execute(request, response);
	   }else if(urlcommand.equals("/update.do")) {
		   action = new MemberListService();
 		   forward = action.execute(request, response);
	   }
        

    	if(forward != null) {
    		if(forward.isRedirect()) { //true 페이지 재 요청 (location.href="페이지"
    			response.sendRedirect(forward.getPath());
    		}else { //기본적으로 forward ....
    			    //1. UI 전달된 경우
    			    //2. UI + 로직
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}

    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
