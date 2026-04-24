package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
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
		Teacher teacher = (Teacher) session.getAttribute("user");// ログイン中の先生

		
		// 入学年度のセレクトをセット------------------
    	LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
    	int year = todaysDate.getYear();// 現在の年を取得
	    // リストを初期化
	    List<Integer> entYearSet = new ArrayList<>();

	    // 10年前から1年後まで年をリストに追加
	    for (int i = year - 10; i < year + 1; i++) {
	        entYearSet.add(i);
	    }
    	request.setAttribute("ent_year_set", entYearSet);
    	// ----------------------------------------
    	
    	// 	クラスのセレクターをセット--------------
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumSet = classNumDao.filter(teacher.getSchool()); // 学校でフィルタ
        request.setAttribute("class_num_set", classNumSet);
    	
    	// -----------------------------------------
    	// 科目のセレクターをセット-----------------
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjects = subjectDao.filter(teacher.getSchool(), null); // 学校でフィルタ
        request.setAttribute("subjects", subjects);
    	
    	// -----------------------------------------
        
        TestListStudentDao tDao = new TestListStudentDao();
        List<Test> tests = null;
        
        //リクエストパラメータの取得
        String studentNo = request.getParameter("f4");// 選択された学生番号
		
        tests = tDao.filter(studentNo);


		if (tests == null) {
			// 学生が存在しない場合
			request.setAttribute("error", "学生が見つかりませんでした");
			return "test_list_student.jsp";
		}


		// JSPへ渡すデータをセット
		request.setAttribute("tests", tests);
		request.setAttribute("f4", studentNo);

		return "test_list_student.jsp";
	}
}