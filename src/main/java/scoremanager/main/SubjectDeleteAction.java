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
        
        request.setAttribute("subject", subject);
        
        if (subject == null) {
        	return"/error.jsp";
        }
 
        return "subject_delete.jsp";

    }

}
 