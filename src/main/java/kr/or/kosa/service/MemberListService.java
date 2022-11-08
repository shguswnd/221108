package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.koreamemberDao;
import kr.or.kosa.dto.koreamemberDto;

public class MemberListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		   koreamemberDao dao = new koreamemberDao();
		   List<koreamemberDto> dtolist;
		   
		   ActionForward forward = null;
		   forward = new ActionForward();
 		   forward.setRedirect(false);
 		   
		   dtolist = dao.getAllTableList();
		   request.setAttribute("dtolist", dtolist);
		   forward.setPath("/WEB-INF/views/admin.jsp");
		   
		return forward;
	}

}
