package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
		SubjectDao subDao = new SubjectDao();
		
		String cd = request.getParameter("cd");
		Subject checksubject = subDao.get(cd); 
		if (checksubject == null) {
			request.setAttribute("cd",cd);
			request.setAttribute("error_subject", "科目が存在していません");
			return "subject_update.jsp"; 
		}
		Subject subject = new Subject();
		subject.setName(request.getParameter("name"));
		subject.setCd(request.getParameter("cd"));

		
		if(subDao.save(subject)) {
			return "subject_update_done.jsp";
		}else {
			return "error.jsp";
		}
	}
}
