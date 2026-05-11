package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestListStudentDao extends Dao {

	// SQL文
	private String baseSql =
		"select t.student_no, t.subject_cd, s.name as subject_name, t.no, t.point " +
		"from test t " +
		"join subject s on t.subject_cd = s.cd " +
		"where t.student_no = ? " +
		"order by t.subject_cd asc, t.no asc";

	public Test get(String student_No, String subject_Cd, int no) throws Exception {
		Test test = new Test();
		 // データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		
		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(
					"select t.student_no, s.name as student_name, t.subject_cd,  c.name as subject_name, t.no, t.point, t.school_cd" +
					" from test t join student s on t.student_no = s.no"+
					" join subject c on t.subject_cd = c.cd"+
					" where t.student_no=? and t.subject_cd=? and t.no=?");
			
			statement.setString(1, student_No);
			statement.setString(2, subject_Cd);
			statement.setInt(3, no);
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();
			
			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();
			
			if (rSet.next()) {

				test.setStudentNo(rSet.getString("student_no"));
				test.setStudentName(rSet.getString("student_name"));
				test.setSubjectCd(rSet.getString("subject_cd"));
				test.setSubjectName(rSet.getString("subject_name"));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));

				test.setSchool(schoolDao.get(rSet.getString("school_cd")));
			} else {
				// リザルトセットが存在しない場合
				// 科目インスタンスにnullをセット
				test = null;
			}
		}catch (Exception e) {
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
		return test;
	}
	private List<Test> postFilter(ResultSet rSet) throws Exception {
		// リスト初期化
		List<Test> list = new ArrayList<>();
		try {
			//全件取得
			while (rSet.next()) {
				// 成績インスタンスを初期化
				Test test = new Test();
				test.setStudentNo(rSet.getString("student_no"));
				test.setSubjectCd(rSet.getString("subject_cd"));
				test.setSubjectName(rSet.getString("subject_name"));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				// リストに追加
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 学生番号をもとに成績一覧を取得
	public List<Test> filter(String StudentNo) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;

		try {
			//SQL文をセット
			statement = connection.prepareStatement(baseSql);
			//学生番号セット
			statement.setString(1, StudentNo);
			// sqlを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
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
	
	public boolean save(Test test) throws Exception {
	    // 1. 変数の宣言
	    int count = 0;
	
	    // 2. データベース操作 (try-with-resources を使用して自動的に close する)
	    try (Connection connection = getConnection()) {

	    } catch (Exception e) {
	        // エラーが発生した場合はそのまま投げる
	        throw e;
	    }
	
	    // --- ここから画像の内容 ---
	    // tryブロックの外で、最終的な count を判定して戻り値を返す
	    if (count > 0) {
	        // 実行件数が1件以上ある場合
	        return true;
	    } else {
	        // 実行件数が0件の場合
	        return false;
	    }
	    
	
	}
}
