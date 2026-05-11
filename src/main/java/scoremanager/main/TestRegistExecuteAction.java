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

public class TestRegistExecuteAction extends Action {

    @Override
    public String execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user"); // ←元コード維持

        /* ===== 入学年度セレクタ ===== */
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }
        request.setAttribute("ent_year_set", entYearSet);

        /* ===== クラスセレクタ ===== */
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumSet = classNumDao.filter(teacher.getSchool());
        request.setAttribute("class_num_set", classNumSet);

        /* ===== 科目セレクタ ===== */
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjects = subjectDao.filter(teacher.getSchool(), null);
        request.setAttribute("subjects", subjects);

        /* ===== 回数セレクタ ===== */
        List<Integer> times = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            times.add(i + 1);
        }
        request.setAttribute("times", times);

		String f1 = request.getParameter("f1") != null ? request.getParameter("f1").trim() : null;
		String f2 = request.getParameter("f2") != null ? request.getParameter("f2").trim() : null;
		String f3 = request.getParameter("f3") != null ? request.getParameter("f3").trim() : null;
		String f4 = request.getParameter("f4") != null ? request.getParameter("f4").trim() : null;
		
		String subjectCdParam = request.getParameter("subject") != null ? request.getParameter("subject").trim() : null;
		String countStr = request.getParameter("count") != null ? request.getParameter("count").trim() : null;
		
        // 値の調整（hidden から取れなければフィルタの f3, f4 を使う）
        String subjectCd = (subjectCdParam != null) ? subjectCdParam : f3;
        String finalCountStr = (countStr != null) ? countStr : f4;
        
        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("f4", finalCountStr);

        if (request.getParameterMap().keySet().stream().anyMatch(key -> key.startsWith("point_"))) {

            TestDao updateDao = new TestDao();
            int no = 0;
            if (finalCountStr != null && !finalCountStr.isEmpty()) {
                no = Integer.parseInt(finalCountStr);
            }
            int entYear = 0;
            if (f1 != null && !f1.isEmpty()) {
                entYear = Integer.parseInt(f1);
            }
            
            // エラーの学生番号を溜めるリスト
            List<String> errorStudentNos = new ArrayList<>();
            // 保存対象を一時的に溜めるリスト
            List<Test> saveList = new ArrayList<>();
            
            for (String key : request.getParameterMap().keySet()) {
                if (key.startsWith("point_")) {
                    String studentNo = key.replace("point_", "");
                    String pointStr = request.getParameter(key);

                    if (pointStr == null || pointStr.isEmpty()) {
                        continue;
                    }
                    try {

	                    int point = Integer.parseInt(pointStr);
	
	                    if (point < 0 || point > 100) {
	                        errorStudentNos.add(studentNo);
	                    }else {
	                        Test test = new Test();
	                        test.setStudentNo(studentNo);
	                        test.setSubjectCd(subjectCd);
	                        test.setNo(no);
	                        test.setPoint(point);
	                        test.setSchool(teacher.getSchool());
	                        test.setClassNum(f2);
	                        saveList.add(test);
	                    }
                   }catch (NumberFormatException e) {
                       errorStudentNos.add(studentNo);
                   }

                }
            }
            if (!errorStudentNos.isEmpty()) {
                request.setAttribute("errorStudentNos", errorStudentNos);
                
                List<Test> tests = updateDao.filter(teacher.getSchool(), entYear, f2, subjectCd, no);
                request.setAttribute("tests", tests);
                return "test_regist.jsp";
            }

            for (Test saveTest : saveList) {
                updateDao.upsertPoint(saveTest);
            }
            
            return "test_regist_done.jsp";
        }

        return "test_regist.jsp";
    }
}

//}
//
