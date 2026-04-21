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
	
//	public Test get(String no) throws Exception {
//		// 成績インスタンスを初期化
//		Test test = new Test();
//		// データのコネクションを確立
//		Connection connection = getConnection();
//		// プリペアードステートメント
//        PreparedStatement statement = null;
//        
//        try {
//        	// プリペアードステートメントにSQL文をセット
//            statement = connection.prepareStatement("select * from test where no=?");
//            // プリペアードステートメントに学生番号をバインド
//            statement.setString(1, no);
//            // プリペアードステートメントを実行
//            ResultSet rSet = statement.executeQuery();
//            // 学校Daoを初期化
//            SchoolDao schoolDao = new SchoolDao();
//            
//            if (rSet.next()) {
//            	// リザルトセットが存在する場合
//                test.setEntYear(rSet.getInt("ent_year"));
//            	test.setStudentNo(rSet.getString("studentNo"));
//            	test.setStudentName(rSet.getString("studentName"));
//            	test.setSubjectCd(rSet.getString("subjectCd"));
//            	test.setSubjectName(rSet.getString("subjectName"));
//                test.setNo(rSet.getInt("no"));
//                test.setPoint(rSet.getInt("point"));
//                test.setClassNum(rSet.getString("class_num"));
//                // 学校フィールドには学校コードで検索した学校インスタンスをセット
//                test.setSchool(schoolDao.get(rSet.getString("school_cd")));
//            }else {
//                // リザルトセットが存在しない場合
//                // 学生インスタンスにnullをセット
//                test = null;
//            }
//        }catch (Exception e) {
//            throw e;
//        } finally {
//            // プリペアードステートメントを閉じる
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException sqle) {
//                    throw sqle;
//                }
//            }
//            // コネクションを閉じる
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException sqle) {
//                    throw sqle;
//                }
//            }
//        }
//
//        return test;
//	}
	
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
	
	
	
}
