package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StudentCreateExecuteAction {
	public String execute(
	        HttpServletRequest request, HttpServletResponse response
	    ) throws Exception {
		
		int ent_year =Integer.parseInt(request.getParameter("ent_year"));
        int no = Integer.parseInt(request.getParameter("no"));
		String name =request.getParameter("name");
        int class_num =Integer.parseInt(request.getParameter("class_num"));
	    	return "subject_create.jsp";
	    }

}
