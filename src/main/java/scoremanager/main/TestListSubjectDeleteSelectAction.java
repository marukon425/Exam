package scoremanager.main;

import java.util.List;

import bean.Test;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListSubjectDeleteSelectAction extends Action {
	@Override
    public String execute(
        HttpServletRequest request,HttpServletResponse response
    ) throws Exception {
		
		String studentNo = request.getParameter("studentNo");
		String subjectCd = request.getParameter("subjectCd");
		int no = Integer.parseInt(request.getParameter("no"));
		TestListSubjectDao tDao = new TestListSubjectDao();
		 List<Test> tests = tDao.noselect(studentNo, subjectCd,no);
		if (studentNo == null) {
			 request.setAttribute("error", "指定された成績情報が見つかりません。");
	         return "TestListSubject.action";
		}
		
		request.setAttribute("tests",tests.get(0));
		
		return "test_subject_delete.jsp";

		
	}
}
