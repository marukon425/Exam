package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		SubjectDao subDao = new SubjectDao();
		Subject subject = new Subject();
		// schoolに学校コードをセット
        School school = new School();
        school.setCd(teacher.getSchool().getCd());
        
        String cd = request.getParameter("cd");
        
        subject = subDao.get(cd);
        
        
        request.setAttribute("cd", subject.getCd());
        request.setAttribute("name", subject.getName());
    	
		return "subject_update.jsp";
	}
}
