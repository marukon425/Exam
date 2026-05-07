package scoremanager.main;

import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListSubjectDeleteAction extends Action {
	@Override
    public String execute(
        HttpServletRequest request,HttpServletResponse response
    ) throws Exception {
		String studentNo = request.getParameter("studentNo");
        String subjectCd = request.getParameter("subjectCd");

        TestListSubjectDao tDao = new TestListSubjectDao();
        // 指定した学生・科目の全成績（1回目、2回目など）を取得する
       

	}
}
