package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();// セッション
		Teacher teacher = (Teacher) session.getAttribute("teacher");// ログイン中の先生

		// リクエストパラメーターの取得
		String studentId = request.getParameter("studentId");// 学生番号

		// 学生Daoを初期化
		StudentDao studentDao = new StudentDao();
		// 学生番号をもとに学生を取得
		Student student = studentDao.get(studentId);

		if (student == null) {
			// 学生が存在しない場合
			request.setAttribute("error", "学生が見つかりませんでした");
			return "test_list_student.jsp";
		}

		// 成績Daoを初期化
		TestListStudentDao dao = new TestListStudentDao();
		// 学生をもとに成績一覧を取得
		List<TestListStudent> tests = dao.filter(student);

		// JSPへ渡すデータをセット
		request.setAttribute("tests", tests);
		request.setAttribute("student", student);

		return "test_list_student.jsp";
	}
}