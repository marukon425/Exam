package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		List<Subject> subjects = null;// 科目リスト
		SubjectDao subDao = new SubjectDao();// 科目Dao
		
	  //リクエストパラメーターの取得
	    String cd = request.getParameter("cd");
	    String name = request.getParameter("name");
		
		// 全科目情報を取得
	    subjects = subDao.filter(teacher.getSchool(), cd);
	    
	    request.setAttribute("subjects", subjects);
	    request.setAttribute("cd", cd);
	    request.setAttribute("name", name);
		
	    //JSPへフォワード
		 //request.getRequestDispatcher("student_list.jsp").forward(request, response);
		 return "subject_list.jsp";
	}
}
