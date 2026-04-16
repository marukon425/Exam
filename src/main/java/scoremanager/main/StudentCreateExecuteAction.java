package scoremanager.main;

import bean.School;
import bean.Student;
import bean.Teacher;
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

        HttpSession session = request.getSession();
        StudentDao dao = new StudentDao();

        // セッションからteacherを取得（キャスト必須）
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // schoolに学校コードをセット
        School school = new School();
        school.setCd(teacher.getSchool().getCd());

        // studentにリクエストパラメータをセット
        Student student = new Student();
        student.setNo(request.getParameter("no"));
        student.setName(request.getParameter("name"));
        student.setEntYear(Integer.parseInt(request.getParameter("ent_year")));
        student.setClassNum(request.getParameter("class_num"));
        student.setSchool(school);
        student.setAttend(true);

        // 重複チェック → DAOのgetに任せる
        Student old = dao.get(student.getNo());

        if (old != null) {
            // 重複あり
            session.setAttribute("notaddd", "学生番号が重複しています");
            System.out.println("失敗");
            return "student_create.jsp";
        }else {
        	// 入学年度が選択されてなければ選択するように促す
        	if (student.getEntYear() == 0) {
                session.setAttribute("notent", "入学年度を選択してください");
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