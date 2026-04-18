package scoremanager.main;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExcecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		
		
		SubjectDao subdao = new SubjectDao();
		

		boolean resuit = subdao.subject_insert(teacher.getSchool(), cd, name);
		
		
		if(!resuit) {
			
		}
		return "subject_create_done.jsp";
		
	}
}
