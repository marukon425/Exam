package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action  {
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
        return "test_regist.jsp";
        }
}
