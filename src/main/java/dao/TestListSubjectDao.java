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
		"st.class_num, t.num, t.point " +
		"from student st " +
		"left join test t on st.no = t.student_no and t.subject_cd = ? " +
		"where st.school_cd = ? " +
		"and st.ent_year = ? " +
		"and st.class_num = ? " +
		"and st.is_attend = true " +
		"order by st.no asc, t.num asc";

	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		// リスト初期化
		List<TestListSubject> list = new ArrayList<>();

		try {
			while (rSet.next()) {
				// 学生番号を取得
				String studentNo = rSet.getString("student_no");

				// 同じ学生が既にリストに存在するか確認
				TestListSubject existing = null;
				for (TestListSubject t : list) {
					if (t.getStudentNo().equals(studentNo)) {
						existing = t;
						break;
					}
				}

				if (existing == null) {
					TestListSubject testListSubject = new TestListSubject();
					testListSubject.setEntYear(rSet.getInt("ent_year"));
					testListSubject.setStudentNo(studentNo);
					testListSubject.setStudentName(rSet.getString("student_name"));
					testListSubject.setClassNum(rSet.getString("class_num"));
					testListSubject.setPoints(new HashMap<>());
					int point = rSet.getInt("point");
					if (!rSet.wasNull()) {
						testListSubject.putPoint(String.valueOf(rSet.getInt("num")), point);
					}
					list.add(testListSubject);
				} else {
					// 既存の学生の場合 → 点数のみ追加
					int point = rSet.getInt("point");
					if (!rSet.wasNull()) {
						existing.putPoint(String.valueOf(rSet.getInt("num")), point);
					}
				}
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 入学年度・クラス・科目をもとに成績一覧を取得
	public List<TestListSubject> filter(int entYear, String classNum,
			Subject subject, School school) throws Exception {
		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		try {
			statement = connection.prepareStatement(baseSql);
			statement.setString(1, subject.getCd());
			statement.setString(2, school.getCd());
			statement.setInt(3, entYear);
			statement.setString(4, classNum);
			rSet = statement.executeQuery();
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