package scoremanager.main;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {
    public String execute(
            HttpServletRequest request, HttpServletResponse response
        ) throws Exception {
    	HttpSession session = request.getSession();
    	
    	StudentDao dao = new StudentDao();
    	Student student = new Student();
    	
    	
    	
    	// 学生番号を拾って値をセット
    	String no = request.getParameter("no");
    	
    	student = dao.get(no);
    	
    	
    	
    	session.setAttribute("ent_year", student.getEntYear());
    	session.setAttribute("no", student.getNo());
    	session.setAttribute("name", student.getName());
    	session.setAttribute("num", student.getEntYear());
    	session.setAttribute("class_num_set", student.getEntYear());
    	
    	
    	
        return "student_update.jsp";
        }
}
