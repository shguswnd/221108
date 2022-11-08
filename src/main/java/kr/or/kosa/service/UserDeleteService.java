package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.koreamemberDao;
import kr.or.kosa.dto.koreamemberDto;

public class UserDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		   koreamemberDao dao = new koreamemberDao();
		   List<koreamemberDto> dtolist;
		   String id = request.getParameter("id");
		   int check = dao.delete(id);
		   dtolist = dao.getAllTableList();
    	   request.setAttribute("dtolist", dtolist);
    	   
    	   ActionForward forward = null;
		   forward = new ActionForward();
 		   forward.setRedirect(false);
    	   
		   if(check>0) {
			   forward.setPath("/WEB-INF/views/admin.jsp");
		   }else {
			   forward.setPath("/WEB-INF/views/register.jsp");
		   }
		   
		return forward;
	}
	   
}
