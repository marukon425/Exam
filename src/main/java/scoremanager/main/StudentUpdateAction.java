package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
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
    	ClassNumDao class_dao = new ClassNumDao();
    	
    	Teacher teacher = (Teacher) session.getAttribute("teacher");
    	// schoolに学校コードをセット
        School school = new School();
        school.setCd(teacher.getSchool().getCd());
    	
    	
    	// 学生番号を拾って値をセット
    	String no = request.getParameter("no");
    	
    	student = dao.get(no);
    	
    	// 所属している学校の全クラスを取得
    	List<String> claslist = class_dao.filter(school);
    	
    	request.setAttribute("ent_year", student.getEntYear());
    	request.setAttribute("no", student.getNo());
    	request.setAttribute("name", student.getName());
    	request.setAttribute("class_num", student.getClassNum());
    	request.setAttribute("class_num_set", claslist);
    	
    	
        return "student_update.jsp";
        }
}
