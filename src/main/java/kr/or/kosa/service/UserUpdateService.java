package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.koreamemberDao;
import kr.or.kosa.dto.koreamemberDto;

public class UserUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		   String name = request.getParameter("name");
		   int age = Integer.parseInt(request.getParameter("age"));
		   String email = request.getParameter("email");
		   String gender = request.getParameter("gender");
		   String id = request.getParameter("id");
		   koreamemberDao dao = new koreamemberDao();
		   koreamemberDto dto = new koreamemberDto();
		   List<koreamemberDto> dtolist;
		   
		   dto.setName(name);
		   dto.setAge(age);
		   dto.setEmail(email);
		   dto.setGender(gender);
		   dto.setId(id);
		   int check = dao.update(dto);
		   
		   ActionForward forward = null;
		   forward = new ActionForward();
 		   forward.setRedirect(false);
		   
		   if(check>0) {
			 dtolist = dao.getAllTableList();
	  		 request.setAttribute("dtolist", dtolist);
	  		 forward.setPath("/WEB-INF/views/admin.jsp");
		   }else {
	  		 forward.setPath("/WEB-INF/views/register.jsp");
		   }
		return forward;
	}

}
