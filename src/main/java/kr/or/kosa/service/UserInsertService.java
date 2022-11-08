package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.koreamemberDao;
import kr.or.kosa.dto.koreamemberDto;

public class UserInsertService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		   String id = request.getParameter("id");
		   String pwd = request.getParameter("pwd");
		   String name = request.getParameter("name");
		   int age = Integer.parseInt(request.getParameter("age"));
		   String gender = request.getParameter("gender");
		   String email = request.getParameter("email");
		   String ip = request.getRemoteAddr();
		   koreamemberDto dto = new koreamemberDto();
		   int check = 0;
		   koreamemberDao dao = new koreamemberDao();
		   ActionForward forward = null;
		   
	       dto.setId(id);
		   dto.setPwd(pwd);
		   dto.setName(name);
		   dto.setAge(age);
		   dto.setGender(gender);
		   dto.setEmail(email);
		   dto.setIp(ip);
		   check = dao.writeOk(dto);
		   
		   forward = new ActionForward();
 		   forward.setRedirect(false);
 		   
		   
		   if(check>0) {
			   forward.setPath("/WEB-INF/views/login.jsp");
		   }else {
			   forward.setPath("/WEB-INF/views/register.jsp");
		   }
		return forward;
	}

}
