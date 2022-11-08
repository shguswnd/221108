package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.koreamemberDao;
import kr.or.kosa.dto.koreamemberDto;

public class UserEditService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		   String id = request.getParameter("id");
		   HttpSession session = request.getSession();
		   koreamemberDao dao = new koreamemberDao();
		   koreamemberDto dto = new koreamemberDto();
		   
		   session.setAttribute("id", id);
		   dto = dao.getUpdateMember(id);
		   request.setAttribute("dao", dto);
		   ActionForward forward = null;
		   forward = new ActionForward();
 		   forward.setRedirect(false);
		   forward.setPath("/WEB-INF/views/edit.jsp");

		return forward;
	}

}
