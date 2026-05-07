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
}