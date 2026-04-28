package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	System.out.println("セッション");
    	// セッション
        HttpSession session = request.getSession();
        // studentDaoのインスタンス化
        StudentDao dao = new StudentDao();

        // セッションからteacherを取得（キャスト必須）
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // jsp側から
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        int ent_year = Integer.parseInt(request.getParameter("ent_year"));
        String class_num = request.getParameter("class_num");
        // schoolに学校コードをセット
        School school = new School();
        school.setCd(teacher.getSchool().getCd());
        
        // studentにリクエストパラメータをセット
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(ent_year);
        student.setClassNum(class_num);
        student.setSchool(school);
        student.setAttend(true);

        // エラーが起こり、student_create.jspに戻った際に必要となるセレクトボックスの設定
        LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
 	    int year = todaysDate.getYear();// 現在の年を取得
 	    ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
 	    
 	   // リストを初期化
	    List<Integer> entYearSet = new ArrayList<>();

	    // 10年前から1年後まで年をリストに追加
	    for (int i = year - 10; i < year + 1; i++) {
	        entYearSet.add(i);
	    }
	    // 全てのクラス番号の取得
	    List<String> list = cNumDao.filter(teacher.getSchool());
	    
	    request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", list);
		// ------------------------------------------------------------------------------
        
        
        // 重複チェック → DAOのgetに任せる
        Student old = dao.get(student.getNo());

        if (old != null) {
            // 重複あり
            request.setAttribute("notaddd", "学生番号が重複しています");
            // エラー時に選択、入力されたものをjsp側で表示するために送る
            request.setAttribute("no",no);
            request.setAttribute("name",name);
            request.setAttribute("ent_year",ent_year);
            request.setAttribute("class_num",class_num);
            System.out.println("失敗");
            return "student_create.jsp";
        }else {
        	// 入学年度が選択されてなければ選択するように促す
        	if (student.getEntYear() == 0) {
                request.setAttribute("notent", "入学年度を選択してください");
                // エラー時に選択、入力されたものをjsp側で表示するために送る
                request.setAttribute("no",no);
                request.setAttribute("name",name);
                request.setAttribute("ent_year",ent_year);
                request.setAttribute("class_num",class_num);
                System.out.println("入学年度未入力");
                return "student_create.jsp";
        	}else {        		
        		// 重複なし → 保存
        		dao.save(student);
        		System.out.println("成功");
        		return "student_create_done.jsp";        	
        	}
        }

    }
}