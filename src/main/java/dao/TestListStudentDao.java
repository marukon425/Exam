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
		"select t.subject_cd, s.name as subject_name, t.no, t.point " +
		"from test t " +
		"join subject s on t.subject_cd = s.cd " +
		"where t.student_no = ? " +
		"order by t.subject_cd asc, t.no asc";

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
}