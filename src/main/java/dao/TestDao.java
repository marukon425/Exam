package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Test;

public class TestDao extends Dao {
	private String baseSql = 
			"select s.ent_year, s.class_num, s.no as student_no, s.name, " + 
		    "  t.point, t.subject_cd, t.no " +                         
		    "from student s left join test t on s.no = t.student_no " +  
		    "  and t.subject_cd = ?  and t.no = ? and t.school_cd = ? " +
		    "where s.school_cd = ? and s.ent_year = ? and s.class_num = ? " + 
		    "order by s.no asc"; 

	
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {

	    List<Test> list = new ArrayList<>();

	    while (rSet.next()) {

	        Test test = new Test();

            test.setEntYear(rSet.getInt("ent_year"));
            test.setClassNum(rSet.getString("class_num"));
            test.setStudentNo(rSet.getString("student_no"));
            test.setStudentName(rSet.getString("name"));
            test.setSubjectCd(rSet.getString("subject_cd"));
            test.setNo(rSet.getInt("no"));

            // 点数の処理：NULLの場合は -1 をセットして未入力状態とする
            if (rSet.getObject("point") != null) {
                test.setPoint(rSet.getInt("point"));
            } else {
                test.setPoint(-1); 
            }

            test.setSchool(school);
            list.add(test);
        }
        return list;
    }
	
	public List<Test> filter(School school, int entYear, String classNum, String subjectCd, int no) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文のソート
        try {
        	statement = connection.prepareStatement(baseSql);
        	statement.setString(1, subjectCd); // t.subject_cd
        	statement.setInt(2, no);           // t.no
        	statement.setString(3, school.getCd()); // t.school_cd
        	statement.setString(4, school.getCd()); // s.school_cd
        	statement.setInt(5, entYear);      // s.ent_year
        	statement.setString(6, classNum);  // s.class_num
            // プライベートステートメントを実行
            rSet = statement.executeQuery();
            
            // リストへの格納処理を実行
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }

            }
            
            
            
            
        }
        
        
        

        return list;
    }

	public boolean upsertPoint(Test test) throws Exception {
	    String sql = "MERGE INTO test " +
	                 "(student_no, subject_cd, school_cd, no, point, class_num) " +
	                 "KEY (student_no, subject_cd, school_cd, no) " +
	                 "VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setString(1, test.getStudentNo());
	        statement.setString(2, test.getSubjectCd());
	        statement.setString(3, test.getSchool().getCd());
	        statement.setInt(4, test.getNo());
	        statement.setInt(5, test.getPoint());
	        // ここでBeanにセットした値がDBに飛びます
	        statement.setString(6, test.getClassNum()); 

	        return statement.executeUpdate() > 0;
	    }
	}
    public boolean updatePoint(Test test) throws Exception {

        String sql =
            "update test set point=? " +
            "where student_no=? " +
            "and subject_cd=? " +
            "and school_cd=? " +
            "and no=?";

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, test.getPoint());
            statement.setString(2, test.getStudentNo());
            statement.setString(3, test.getSubjectCd());
            statement.setString(4, test.getSchool().getCd());
            statement.setInt(5, test.getNo());

            return statement.executeUpdate() > 0;

        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) {}
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) {}
            }
        }
    }
}
