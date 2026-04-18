package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();// セッション
		Teacher teacher = (Teacher) session.getAttribute("teacher");// ログイン中の先生

		// リクエストパラメーターの取得
		String f1 = request.getParameter("f1");// 入学年度
		String f2 = request.getParameter("f2");// クラス番号
		String f3 = request.getParameter("f3");// 科目コード

		// 科目Daoを初期化
		SubjectDao subjectDao = new SubjectDao();
		// 科目コードをもとに科目を取得
		Subject subject = subjectDao.get(f3);

		// 学校を取得
		School school = teacher.getSchool();
		// 入学年度を数値に変換
		int entYear = Integer.parseInt(f1);

		// 成績Daoを初期化
		TestListSubjectDao dao = new TestListSubjectDao();
		// 条件をもとに成績一覧を取得
		List<TestListSubject> tests = dao.filter(entYear, f2, subject, school);

		// JSPへ渡すデータをセット
		request.setAttribute("tests", tests);
		request.setAttribute("subject", subject);
		request.setAttribute("f1", f1);
		request.setAttribute("f2", f2);
		request.setAttribute("f3", f3);

		return "test_list_subject.jsp";
	}
}