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

public class TestRegistExecute extends Action {
	
	@Override
	public String execute(
			HttpServletRequest request,HttpServletResponse response
	) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		int entYear = 0;// 入学年度
		int no = 0;
		List<Test> tests = null;// 学生リスト
	    TestDao tDao = new TestDao();// 学生Dao
	    LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
	    List<Integer> entYearSet = new ArrayList<>();
    	int year = todaysDate.getYear();// 現在の年を取得
    	for (int i = year - 10; i < year + 1; i++) {
	        entYearSet.add(i);
	    }
	    // リストを初期化
	    
	    ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumSet = classNumDao.filter(teacher.getSchool());
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjects = subjectDao.filter(teacher.getSchool(), null);
        List<Integer> times = new ArrayList<>();
    	
    	for (int i = 0; i < 10; i++) {
    		times.add(i + 1);
    	}
//	    Map<String, String> errors = new HashMap<>();// エラーメッセージ
		
		String entYearStr = request.getParameter("f1");// 入力された入学年度
	    String classNum = request.getParameter("f2");// 入力されたクラス番号
	    String subjectCd = request.getParameter("f3");// 入力された科目名
	    String noStr = request.getParameter("f4");// 入力された回数
	    
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
	    // リストを初期化
//	    List<Integer> entYearSet = new ArrayList<>();
	    // 学生をもとに成績一覧を取得
//	 	List<Test> list = cNumDao.filter(teacher.getSchool());
	 	
//	 	if(entYear != 0 && !classNum.equals("0") && !subjectName.equals("0") && no !=0) {
//	 		// 全ての項目を指定した場合
//	 		tests = tDao.filter(teacher.getSchool(), entYear, classNum, subjectName, no);
//    	}// else {
//	 		errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
//		    request.setAttribute("errors", errors);
//	 	}
//	 	
	    request.setAttribute("ent_year_set", entYearSet);
	    request.setAttribute("class_num_set", classNumSet);
	    request.setAttribute("subjects", subjects);
	    request.setAttribute("times", times);
    	
	 	request.setAttribute("tests", tests);
	 	request.setAttribute("f1", entYear);
	 	request.setAttribute("f2", classNum);
	 	request.setAttribute("f3", subjectCd);
	 	request.setAttribute("f4", no);
	 	
	 	
	return "test_regist_done.jsp";	
	}
	

}
