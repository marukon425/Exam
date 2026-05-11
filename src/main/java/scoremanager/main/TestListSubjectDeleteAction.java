package scoremanager.main;

import java.util.List;

import bean.Test;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListSubjectDeleteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentNo = request.getParameter("studentNo");
        String subjectCd = request.getParameter("subjectCd");

        TestListSubjectDao tDao = new TestListSubjectDao();
        List<Test> tests = tDao.nofilter(studentNo, subjectCd);
        List<Test> test_select = tDao.selectfilter(studentNo, subjectCd);

        // --- 判定ロジック ---
        if (tests.isEmpty()) {
            // データがなければ一覧へ戻る
            return "TestListSubjectExecute.action";
        }

        if (tests.size() == 1) {
            request.setAttribute("tests", tests.get(0));
            return "test_subject_delete.jsp";

        } else {
            // 【複数あり】回数を選択させる専用のJSPへ
            request.setAttribute("tests_list", test_select);
            request.setAttribute("tests", tests.get(0));
            return "test_subject_delete_select.jsp";
        }
    }
}

