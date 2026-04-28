package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExcecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = new School();
        school.setCd(teacher.getSchool().getCd());
		String cd =request.getParameter("cd");
		String name = request.getParameter("name");
		if (cd == null || cd.length() != 3) {
            // 画像のメッセージに合わせてセット
            request.setAttribute("errorlength", "科目コードは3文字で入力してください");
            // 入力内容を保持して画面に戻す
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            return "subject_create.jsp";
        }
		 Subject subject = new Subject();
        SubjectDao subDao = new SubjectDao();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school); 
		
		Subject old = subDao.get(subject.getCd());
		if (old != null) {
            // 重複あり
            request.setAttribute("errorcd", "科目番号が重複しています");
         // 入力内容を保持して画面に戻す
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            System.out.println("失敗");
            return "subject_create.jsp";
        }else {
    		// 重複なし → 保存
    		subDao.save(subject);
    		System.out.println("成功");
    		return "subject_create_done.jsp";        	
        }
		
	}
}
