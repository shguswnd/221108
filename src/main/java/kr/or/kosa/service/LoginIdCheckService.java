package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.koreamemberDao;
import kr.or.kosa.dto.koreamemberDto;

public class LoginIdCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String idcheck=null;
		koreamemberDto dto = new koreamemberDto();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd"); 		   
        dto.setId(id);
        dto.setPwd(pwd);
        koreamemberDao dao = new koreamemberDao();
        HttpSession session = request.getSession();
        List<koreamemberDto> dtolist;
        
        boolean success = dao.idCheck(id, pwd);   
            
            if(success==true) {
            	session.setAttribute("id", id);
     	
	            if(id.equals("admin")) {
	               dtolist = dao.getAllTableList();
	     		   
	     		   request.setAttribute("dtolist", dtolist);
	     		   forward = new ActionForward();
	     		   forward.setRedirect(false);
	     		   forward.setPath("/WEB-INF/views/admin.jsp");
	            }
	            else {
	            	forward.setPath("/WEB-INF/views/error-404.jsp");
	            }
            }	
            else {
              	forward.setPath("/WEB-INF/views/web.jsp");
            }
		return forward;
	}

}
