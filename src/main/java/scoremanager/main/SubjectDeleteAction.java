package scoremanager.main;
 
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
 
public class SubjectDeleteAction extends Action {
    @Override
    public String execute(
            HttpServletRequest request,HttpServletResponse response
    ) throws Exception {
 
    	String cd = request.getParameter("cd");
        System.out.println("削除対象 cd = " + cd);
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd);
 
        if (subject == null) {
            request.setAttribute("error", "指定された科目が見つかりません。");
            return "subject_list.jsp";
        }
        
        request.setAttribute("subject", subject);
 
        return "subject_delete.jsp";

    }

}
 