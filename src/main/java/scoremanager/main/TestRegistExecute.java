package scoremanager.main;

import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecute extends Action {
	
	@Override
	public String execute(
			HttpServletRequest request,HttpServletResponse response
	) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		String entYearStr = request.getParameter("f1");// 入力された入学年度
	    String classNum = request.getParameter("f2");// 入力されたクラス番号
	    String subjectName = request.getParameter("f3");// 入力された科目名
	    String no = request.getParameter("f4");// 入力された回数
		
	return "test_regist_done.jsp";	
	}
	

}
