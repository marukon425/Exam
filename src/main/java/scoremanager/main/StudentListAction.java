package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();//セッション
	    Teacher teacher = (Teacher)session.getAttribute("user");
	    
	    
	    int entYear = 0;// 入学年度
	    boolean isAttend = false;// 在学フラグ
	    List<Student> students = null;// 学生リスト
	    LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
	    int year = todaysDate.getYear();// 現在の年を取得
	    StudentDao sDao = new StudentDao();// 学生Dao
	    ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
	    Map<String, String> errors = new HashMap<>();// エラーメッセージ
	    
	    
	    //リクエストパラメーターの取得 2
	    String entYearStr = request.getParameter("f1");// 入力された入学年度
	    String classNum = request.getParameter("f2");// 入力されたクラス番号
	    String isAttendStr = request.getParameter("f3");// 入力された在学フラグ
	    
	    
	    // ビジネスロジック 4
	    if (entYearStr != null) {
	        // 数値に変換
	        entYear = Integer.parseInt(entYearStr);
	    }

	    // リストを初期化
	    List<Integer> entYearSet = new ArrayList<>();

	    // 10年前から1年後まで年をリストに追加
	    for (int i = year - 10; i < year + 1; i++) {
	        entYearSet.add(i);
	    }
	    
		  //DBからデータ取得 3
		 // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		 List<String> list = cNumDao.filter(teacher.getSchool());
	
		 if (entYear != 0 && !classNum.equals("0")) {
		     // 入学年度とクラス番号を指定
		     students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		 } else if (entYear != 0 && classNum.equals("0")) {
		     // 入学年度のみ指定
		     students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		 } else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
		     // 入学年度もクラス番号も指定なし
		     students = sDao.filter(teacher.getSchool(), isAttend);
		 } else {
		     errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
		     request.setAttribute("errors", errors);
		     // 全学年情報を取得
		     students = sDao.filter(teacher.getSchool(), isAttend);
		 }
		 
		 
		 request.setAttribute("students", students);
		 request.setAttribute("ent_year_set", entYearSet);
		 request.setAttribute("class_num_set", list);
		 request.setAttribute("f1", entYear);
		 request.setAttribute("f2", classNum);
		 request.setAttribute("f3", isAttendStr);
		 
		 //JSPへフォワード
		 //request.getRequestDispatcher("student_list.jsp").forward(request, response);
		 return "student_list.jsp";
	}

}
