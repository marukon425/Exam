package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
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
 

        // ===== 検索条件取得 =====
        String f1 = request.getParameter("f1"); // 入学年度
        String f2 = request.getParameter("f2"); // クラス
        String f3 = request.getParameter("f3"); // 科目コード

        // ===== 入力チェック（ユースケース通り）=====
        if (f1 == null || f1.isEmpty()
         || f2 == null || f2.isEmpty()
         || f3 == null || f3.isEmpty()) {

            request.setAttribute(
                "error",
                "入学年度とクラスと科目を選択してください"
            );
            return "test_list_subject.jsp";
        }

        int entYear = Integer.parseInt(f1);
        School school = teacher.getSchool();

        // ===== 科目取得 =====

        Subject subject = subjectDao.get(f3);

        // ===== 成績一覧取得（DAOに全任せ）=====
        TestListSubjectDao dao = new TestListSubjectDao();
        List<TestListSubject> tests =
            dao.filterForSubjectList(entYear, f2, subject, school);

        // ===== 学生が存在しない場合 =====
        if (tests.isEmpty()) {
            request.setAttribute(
                "message",
                "学生情報が存在しませんでした"
            );
        }

        // ===== JSPへ渡す =====
        request.setAttribute("tests", tests);
        request.setAttribute("subject", subject);
        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);

        return "test_list_subject.jsp";
    }
}