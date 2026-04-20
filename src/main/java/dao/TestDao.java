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
	private String baseSql = "select * from test where school_cd = ?";
	
	public Test get(String no) throws Exception {
		// 成績インスタンスを初期化
		Test test = new Test();
		// データのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
        PreparedStatement statement = null;
        
        try {
        	// プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from test where no=?");
            // プリペアードステートメントに学生番号をバインド
            statement.setString(1, no);
            // プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();
            // 学校Daoを初期化
            SchoolDao schoolDao = new SchoolDao();
            
            if (rSet.next()) {
            	// リザルトセットが存在する場合
                test.setEntYear(rSet.getInt("ent_year"));
            	test.setStudentNo(rSet.getString("studentNo"));
            	test.setStudentName(rSet.getString("studentName"));
            	test.setSubjectCd(rSet.getString("subjectCd"));
            	test.setSubjectName(rSet.getString("subjectName"));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));
                // 学校フィールドには学校コードで検索した学校インスタンスをセット
                test.setSchool(schoolDao.get(rSet.getString("school_cd")));
            }else {
                // リザルトセットが存在しない場合
                // 学生インスタンスにnullをセット
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
	
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        // リスト初期化
        List<Test> list = new ArrayList<>();

        try {
            // リザルトセットを全件走査
            while (rSet.next()) {
                // 成績インスタンスを初期化
                Test test = new Test();
                test.setEntYear(rSet.getInt("ent_Year"));
            	test.setStudentNo(rSet.getString("studentNo"));
            	test.setStudentName(rSet.getString("studentName"));
            	test.setSubjectCd(rSet.getString("subjectCd"));
            	test.setSubjectName(rSet.getString("subjectName"));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_Num"));
                test.setSchool(school);
                // リストに追加
                list.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return list;

    }
	
	public List<Test> filter(School school, int entYear, String classNum, String subjectName, int no) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and ent_year=? and class_num=? and subjectName=? and no=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            // プリペアードステートメントにクラス番号をバインド
            statement.setString(3, classNum);
            // プリペアードステートメントに科目名をバインド
            statement.setString(4, subjectName);
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
	
	public List<Test> filter(School school, int entYear, String classNum, String subjectName) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and ent_year=? and class_num=? and subjectName=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            // プリペアードステートメントにクラス番号をバインド
            statement.setString(3, classNum);
            // プリペアードステートメントに科目名をバインド
            statement.setString(4, subjectName);
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
	
	
	
	public List<Test> filter(School school, int entYear, String classNum) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and ent_year=? and class_num=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            // プリペアードステートメントにクラス番号をバインド
            statement.setString(3, classNum);
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
	
	public List<Test> filter(School school, int entYear) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and ent_year=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
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
	
	public List<Test> filter(School school, int entYear, String classNum, int no) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and ent_year=? and class_num=? and no=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            // プリペアードステートメントにクラス番号をバインド
            statement.setString(3, classNum);
            // プリペアードステートメントにテスト回数をバインド
            statement.setInt(4, no);
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
	
	public List<Test> filter2(School school, int entYear, String subjectName, int no) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and ent_year=? and subjectName=? and no=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに入学年度をバインド
            statement.setInt(2, entYear);
            // プリペアードステートメントに科目名をバインド
            statement.setString(3, subjectName);
            // プリペアードステートメントにテスト回数をバインド
            statement.setInt(4, no);
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
	
	public List<Test> filter2(School school, String classNum, String subjectName, int no) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and ent_year=? and subjectName=? and no=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
         // プリペアードステートメントにクラス番号をバインド
            statement.setString(2, classNum);
            // プリペアードステートメントに科目名をバインド
            statement.setString(3, subjectName);
            // プリペアードステートメントにテスト回数をバインド
            statement.setInt(4, no);
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
	
	public List<Test> filter2(School school, String classNum, String subjectName) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and classNumr=? and subjectName=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに科目名をバインド
            statement.setString(2, classNum);
            // プリペアードステートメントにテスト回数をバインド
            statement.setString(3, subjectName);
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
	
	public List<Test> filter2(School school,  String subjectName, int no) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and subjectName=? and no=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに科目名をバインド
            statement.setString(2, subjectName);
            // プリペアードステートメントにテスト回数をバインド
            statement.setInt(3, no);
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
	
	public List<Test> filter2(School school, String classNum) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and classNum=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに科目名をバインド
            statement.setString(2, classNum);
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
	
	public List<Test> filter3(School school, String subjectName) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and subjectName=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントに科目名をバインド
            statement.setString(2, subjectName);
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
	
	public List<Test> filter3(School school, int no) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件
        String condition = " and no=?";
        // SQL文のソート
        String order = " order by ent_year asc, studentCd asc";
        try {
        	 // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + condition + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントにテスト回数をバインド
            statement.setInt(2, no);
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
