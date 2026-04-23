package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
		HttpSession session = request.getSession();
		SubjectDao subDao = new SubjectDao();
		
		Subject subject = new Subject();
		subject.setName(request.getParameter("name"));
		subject.setCd(request.getParameter("cd"));
	
		if(subDao.save(subject)) {
			return "subject_update_done.jsp";
		}else {
			return "../error.jsp";
		}
	}
}
