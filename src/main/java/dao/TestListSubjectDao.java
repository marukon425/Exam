package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Test;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    // SQL文
    private String baseSql =
        "select st.ent_year, st.no as student_no, st.name as student_name, " +
        "st.class_num, t.no as test_no, t.point " +
        "from student st " +
        "join test t on st.no = t.student_no and t.subject_cd = ? " +
        "where st.school_cd = ? " +
        "and st.ent_year = ? " +
        "and st.class_num = ? " +
        "order by st.no asc, t.no asc";

    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        List<TestListSubject> list = new ArrayList<>();

        try {
            while (rSet.next()) {

                String studentNo = rSet.getString("student_no");

                Integer testNo  = (Integer) rSet.getObject("test_no");
                Integer point   = (Integer) rSet.getObject("point");

                TestListSubject existing = null;
                for (TestListSubject t : list) {
                    if (t.getStudentNo().equals(studentNo)) {
                        existing = t;
                        break;
                    }
                }

                if (existing == null) {
                    TestListSubject tls = new TestListSubject();
                    tls.setEntYear(rSet.getInt("ent_year"));
                    tls.setStudentNo(studentNo);
                    tls.setStudentName(rSet.getString("student_name"));
                    tls.setClassNum(rSet.getString("class_num"));
                    tls.setPoints(new HashMap<>());

                    if (testNo != null && point != null) {
                        tls.putPoint(String.valueOf(testNo), point);
                    }

                    list.add(tls);
                } else {
                    if (testNo != null && point != null) {
                        existing.putPoint(String.valueOf(testNo), point);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<TestListSubject> filter(
            int entYear,
            String classNum,
            Subject subject,
            School school
    ) throws Exception {

        List<TestListSubject> list;

        try (
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(baseSql)
        ) {
            statement.setString(1, subject.getCd());
            statement.setString(2, school.getCd());
            statement.setInt(3, entYear);
            statement.setString(4, classNum);

            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet);
            }
        }

        return list;
    }

    public List<TestListSubject> filterForSubjectList(
            int entYear,
            String classNum,
            Subject subject,
            School school
    ) throws Exception {

        List<TestListSubject> list = filter(entYear, classNum, subject, school);

        for (TestListSubject tls : list) {
            if (tls.getPoints() == null) {
                tls.setPoints(new HashMap<>());
            }

            // 1回・2回を必ず用意（既存値は壊さない）
            tls.getPoints().putIfAbsent("1", null);
            tls.getPoints().putIfAbsent("2", null);
        }

        return list;
    }
    public List<Test> nofilter(String studentNo, String subjectCd) throws Exception {
        List<Test> list = new ArrayList<>();
        // SQL: studentテーブルを結合して年度とクラスを取得
        String sql = "select t.*, st.ent_year, st.class_num, c.name as subject_name " +
                     "from test t " +
                     "join student st on t.student_no = st.no " +
                     "join subject c on t.subject_cd = c.cd " +
                     "where t.student_no=? and t.subject_cd=?";

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, studentNo);
            st.setString(2, subjectCd);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    test.setStudentNo(rs.getString("student_no"));
                    test.setSubjectCd(rs.getString("subject_cd"));
                    test.setSubjectName(rs.getString("subject_name"));
                    test.setNo(rs.getInt("no"));
                    test.setPoint(rs.getInt("point"));
   
                    test.setEntYear(rs.getInt("ent_year"));
                    test.setClassNum(rs.getString("class_num"));
                    
                    list.add(test);
                }
            }
        }
        return list;
    }
    
    public List<Test> noselect(String studentNo, String subjectCd, int no) throws Exception {
        List<Test> list = new ArrayList<>();
        // SQL: studentテーブルを結合して年度とクラスを取得
        String sql = "select t.*, st.ent_year, st.class_num, c.name as subject_name " +
                     "from test t " +
                     "join student st on t.student_no = st.no " +
                     "join subject c on t.subject_cd = c.cd " +
                     "where t.student_no=? and t.subject_cd=? and t.no=?";

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, studentNo);
            st.setString(2, subjectCd);
            st.setInt(3, no);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Test test = new Test();
                    test.setStudentNo(rs.getString("student_no"));
                    test.setSubjectCd(rs.getString("subject_cd"));
                    test.setSubjectName(rs.getString("subject_name"));
                    test.setNo(rs.getInt("no"));
                    test.setPoint(rs.getInt("point"));
   
                    test.setEntYear(rs.getInt("ent_year"));
                    test.setClassNum(rs.getString("class_num"));
                    
                    list.add(test);
                }
            }
        }
        return list;
    }
    
    public List<Test>selectfilter(String studentNo, String subjectCd) throws Exception{
    	 List<Test> list = new ArrayList<>();
    	 String sql = "select t.*, s.ent_year, s.class_num, s.name as student_name, c.name as subject_name " +
                 "from test t " +
                 "join student s on t.student_no = s.no " +
                 "join subject c on t.subject_cd = c.cd " +
                 "where t.student_no = ?  and t.subject_cd = ? "+
                 "order by t.no asc";
    	 try (Connection conn = getConnection();
                PreparedStatement st = conn.prepareStatement(sql)) {
                st.setString(1, studentNo);
                st.setString(2,subjectCd);
                
                
                SchoolDao schoolDao = new SchoolDao();
                
                try (ResultSet rSet = st.executeQuery()) {
                    while (rSet.next()) {
                        Test test = new Test();
                        test.setStudentNo(rSet.getString("student_no"));
                    	test.setStudentName(rSet.getString("student_name"));
                    	test.setSubjectCd(rSet.getString("subject_cd"));
                    	test.setSubjectName(rSet.getString("subject_name"));
                    	test.setNo(rSet.getInt("no"));
                    	test.setPoint(rSet.getInt("point"));
                    	test.setEntYear(rSet.getInt("ent_year"));
                        test.setClassNum(rSet.getString("class_num"));

                        test.setSchool(schoolDao.get(rSet.getString("school_cd")));
                        
                        list.add(test);
                    }
                }
            }
         return list;
    }
    
    
    
    public boolean delete(String student_No, String subject_Cd, int no) throws Exception {
	    String sql = "delete from test where student_no=? and subject_cd=? and no=?";
	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	    	 statement.setString(1, student_No);
	         statement.setString(2, subject_Cd);
	         statement.setInt(3, no);
	        
	        int count = statement.executeUpdate();
	        
	        return count > 0;
	    }
	}
}