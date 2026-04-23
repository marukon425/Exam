package scoremanager.main;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class TestRegistAction extends Action  {

	@Override

    public String execute(

            HttpServletRequest request, HttpServletResponse response

        ) throws Exception {

    	HttpSession session = request.getSession();

    	Teacher teacher = (Teacher) session.getAttribute("user"); // ログイン中の先生
 
    	

    	// 入学年度のセレクトをセット------------------

    	LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得

    	int year = todaysDate.getYear();// 現在の年を取得

	    // リストを初期化

	    List<Integer> entYearSet = new ArrayList<>();

		int entYear = 0;// 入学年度

	    int no = 0;

	    TestDao tDao = new TestDao();// 成績Dao

		List<Test> tests = null;
 
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

    	//回数のセレクターをセット-----------------

    	List<Integer> times = new ArrayList<>();

    	for (int i = 0; i < 10; i++) {

    		times.add(i + 1);

    	}

    	request.setAttribute("times", times);

    	//-----------------------------------------

		String entYearStr = request.getParameter("f1");// 入力された入学年度

	    String classNum = request.getParameter("f2");// 入力されたクラス番号

	    String subjectCd = request.getParameter("f3");// 入力された科目名

	    String noStr = request.getParameter("f4");//


	    if (tests == null) {

	        tests = new ArrayList<>();

	    }

	    if (entYearStr != null) {

	        entYear = Integer.parseInt(entYearStr);

	    }
 
 
	    if (noStr != null) {

	        no = Integer.parseInt(noStr);

	    }

	    tests = tDao.filter(teacher.getSchool(), entYear, classNum, subjectCd,no);

	 	request.setAttribute("tests", tests);
	 	
	 	request.setAttribute("canRegist", true);

	 	request.setAttribute("f1", entYear);

	 	request.setAttribute("f2", classNum);

	 	request.setAttribute("f3", subjectCd);

	 	request.setAttribute("f4", no);
	 	
	 // 成績登録（点数変更）を付け足す 
	 	if (request.getParameterMap().keySet().stream()
	 	        .anyMatch(k -> k.startsWith("point_"))) {

	 	    for (Test test : tests) {

	 	        String pointStr = request.getParameter("point_" + test.getStudentNo());

	 	        if (pointStr == null || pointStr.isEmpty()) {
	 	            continue;
	 	        }

	 	        int point = Integer.parseInt(pointStr);

	 	        // 点数チェック
	 	        if (point < 0 || point > 100) {
	 	            request.setAttribute("error", "点数は0〜100の範囲で入力してください。");
	 	            return "test_regist.jsp";
	 	        }

	 	        test.setPoint(point);

	 	        // ★ ここで UPDATE
	 	        tDao.updatePoint(test);
	 	    }

	 	    // 登録完了画面へ
	 	    return "test_regist_done.jsp";
	 	}
	 	

        return "test_regist.jsp";

        }

}

 