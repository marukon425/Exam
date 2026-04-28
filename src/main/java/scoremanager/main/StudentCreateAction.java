package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	HttpSession session = request.getSession();//セッション
	    Teacher teacher = (Teacher)session.getAttribute("user");
    	
    	LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
 	    int year = todaysDate.getYear();// 現在の年を取得
 	    ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
 	    
 	   // リストを初期化
	    List<Integer> entYearSet = new ArrayList<>();

	    // 10年前から1年後まで年をリストに追加
	    for (int i = year - 10; i < year + 1; i++) {
	        entYearSet.add(i);
	    }
	    List<String> list = cNumDao.filter(teacher.getSchool());
	    
	    request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", list);
	    
    	return "student_create.jsp";
    }
  }
