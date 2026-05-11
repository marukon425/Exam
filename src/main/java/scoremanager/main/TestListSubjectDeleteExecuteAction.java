package scoremanager.main;

import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListSubjectDeleteExecuteAction extends Action {
	@Override
    public String execute(
            HttpServletRequest request,HttpServletResponse response
    ) throws Exception {
		String studentNo = request.getParameter("student_no");
		String subjectCd = request.getParameter("subject_cd");
		int no = Integer.parseInt(request.getParameter("no"));
		TestListSubjectDao tDao = new TestListSubjectDao();
        boolean result = tDao.delete(studentNo, subjectCd, no);
        String entYear = request.getParameter("ent_year");
		String classNum = request.getParameter("class_num");
        // 削除結果で画面分岐
        if (!result) {
            // 削除失敗時
            request.setAttribute("error", "成績の削除に失敗しました。");
            return "TestListSubject.action";

        }
        request.setAttribute("entYear",entYear);
        request.setAttribute("classNum",classNum);
        request.setAttribute("subjectCd",subjectCd);
        //削除完了画面へ
        return "test_subject_delete_done.jsp";
	}
}
