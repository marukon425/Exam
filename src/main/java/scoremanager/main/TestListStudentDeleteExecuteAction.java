package scoremanager.main;

import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentDeleteExecuteAction extends Action {
	@Override
    public String execute(
            HttpServletRequest request,HttpServletResponse response
    ) throws Exception {
		String studentNo = request.getParameter("student_no");
		String subjectCd = request.getParameter("subject_cd");
		int no = Integer.parseInt(request.getParameter("no"));
		TestListStudentDao tDao = new TestListStudentDao();
        boolean result = tDao.delete(studentNo, subjectCd, no);
        // 削除結果で画面分岐
        if (!result) {
            // 削除失敗時
            request.setAttribute("error", "成績の削除に失敗しました。");
            return "TestListStudent.action";

        }
        request.setAttribute("studentNo",studentNo);
        //削除完了画面へ
        return "test_student_delete_done.jsp";
	}
}
