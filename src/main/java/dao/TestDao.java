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
	private String baseSql = "select t.*, s.ent_year, s.class_num, s.name from test t " +
		    "join student s on t.student_no = s.no " +
		    "where t.school_cd=? and s.ent_year=? and s.class_num=? and t.subject_cd=? and t.no=?";
	

	
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
	        test.setPoint(rSet.getInt("point"));

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
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            // プリペアードステートメントにクラス番号をバインド
            statement.setString(3, classNum);
            // プリペアードステートメントに科目名をバインド
            statement.setString(4, subjectCd);
            // プリペアードステートメントにテスト回数をバインド
            statement.setInt(5, no);
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
	
	
	/**
     * 成績登録
     */
    public boolean save(Test test) throws Exception {

        String checkSql =
            "select count(*) from test " +
            "where student_no=? and subject_cd=? and school_cd=? and no=?";

        String insertSql =
            "insert into test(student_no, subject_cd, school_cd, no, point, class_num) " +
            "values(?, ?, ?, ?, ?, ?)";

        String updateSql =
            "update test set point=? " +
            "where student_no=? and subject_cd=? and school_cd=? and no=?";

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            // --- 既存データ確認 ---
            statement = connection.prepareStatement(checkSql);
            statement.setString(1, test.getStudentNo());
            statement.setString(2, test.getSubjectCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());

            rSet = statement.executeQuery();
            rSet.next();
            boolean exists = rSet.getInt(1) > 0;

            statement.close();

            // --- INSERT / UPDATE ---
            if (exists) {
                statement = connection.prepareStatement(updateSql);
                statement.setInt(1, test.getPoint());
                statement.setString(2, test.getStudentNo());
                statement.setString(3, test.getSubjectCd());
                statement.setString(4, test.getSchool().getCd());
                statement.setInt(5, test.getNo());
            } else {
                statement = connection.prepareStatement(insertSql);
                statement.setString(1, test.getStudentNo());
                statement.setString(2, test.getSubjectCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClassNum());
            }

            int count = statement.executeUpdate();
            return count > 0;

        } catch (Exception e) {
            throw e;
        } finally {
            if (rSet != null) {
                try { rSet.close(); } catch (SQLException e) {}
            }
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) {}
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) {}
            }
        }
    }

}
