package scoremanager.main;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateExecuteAction extends Action  {
    public String execute(
            HttpServletRequest request, HttpServletResponse response
        ) throws Exception {
            StudentDao dao = new StudentDao();

            Student student = new Student();
            student.setName(request.getParameter("name"));
            student.setEntYear(Integer.parseInt(request.getParameter("ent_year")));
            student.setClassNum(request.getParameter("class_num"));
            student.setAttend(request.getParameter("is_attend") != null);
            student.setNo(request.getParameter("no"));
            
            if (dao.save(student)) {
            	return "student_update_done.jsp";
            }else {
            	return "../error.jsp";
            }
            
        }
}
