package scoremanager.main;

import bean.Test;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentDeleteAction extends Action {
	@Override
    public String execute(
        HttpServletRequest request,HttpServletResponse response
    ) throws Exception {
		
		String studentNo = request.getParameter("student_No");
		String subjectCd = request.getParameter("subject_Cd");
		int no = Integer.parseInt(request.getParameter("no"));
		TestListStudentDao tDao = new TestListStudentDao();
		Test tests = tDao.get(studentNo, subjectCd, no);
		if (studentNo == null) {
			 request.setAttribute("error", "指定された成績情報が見つかりません。");
	         return "TestListStudent.action";
		}
		
		request.setAttribute("tests",tests);
		
		return "test_student_delete.jsp";

		
	}
}
