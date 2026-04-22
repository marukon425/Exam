package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
	private String baseSql = "select * from subject where school_cd=?";
	
	public Subject get(String cd) throws Exception {
		// 科目インスタンスの初期化
		Subject subject = new Subject();
		 // データベースへのコネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        
        try {
        	// プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from subject where cd=?");
            // プリペアードステートメントに科目コードをバインド
            statement.setString(1, cd);
            // プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();
            
            // 学校Daoを初期化
            SchoolDao schoolDao = new SchoolDao();
            
            if (rSet.next()) {
            	// リザルトセットが存在する場合
                // 科目インスタンスに検索結果をセット
            	subject.setCd(rSet.getString("cd"));
            	subject.setName(rSet.getString("name"));
            	// 学校フィールドには学校コードで検索した学校インスタンスをセット
                subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
            } else {
            	// リザルトセットが存在しない場合
                // 科目インスタンスにnullをセット
            	subject = null;
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

        return subject;
    }
	
	private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {
		//リスト初期化
		List<Subject> list = new ArrayList<>();
		
		try {
			//リザルトセットを全件走査
			while (rSet.next()) {
				// 科目インスタンスを初期化
				Subject subject = new Subject();
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(school);
				//リストに追加
				list.add(subject);
			}
		}catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return list;
	}
	
	public List<Subject> filter(School school, String cd) throws Exception {
		//リストを初期化
		List<Subject> list = new ArrayList<>();
		// コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文のソート
        String order = " order by cd asc";
        
        try {
        	// プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
       
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
	
	
	public boolean subject_insert(School school, String cd, String name) throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    int count = 0;

	    String sql = "insert into subject (school_cd, cd, name) values (?, ?, ?)";
	        
	    try {
	        statement = connection.prepareStatement(sql);
	        // パラメータのセット
	        statement.setString(1, school.getCd());
	        statement.setString(2, cd);
	        statement.setString(3, name);
    	
            // プリペアードステートメントを実行
            count = statement.executeUpdate();
            
           
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

        return count<0;
	}
		
	
	public boolean save(Subject subject) throws Exception {
	    // 1. 変数の宣言
	    String sql;
	    int count = 0;
	
	    // 2. データベース操作 (try-with-resources を使用して自動的に close する)
	    try (Connection connection = getConnection()) {
	        // 科目が存在するか確認
	        Subject old = get(subject.getCd());
	
	        if (old == null) {
	            // 存在しない場合は INSERT
	            sql = "insert into subject(cd, name, school_cd) values(?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, subject.getCd());
	                statement.setString(2, subject.getName());
	                statement.setString(3, subject.getSchool().getCd());
	                // SQLを実行し、更新件数を取得
	                count = statement.executeUpdate();
	            }
	        } else {
	            // 存在する場合は UPDATE
	            sql = "update subject set cd=?, name=?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            	statement.setString(1, subject.getCd());
	                statement.setString(2, subject.getName());
	                // SQLを実行し、更新件数を取得
	                count = statement.executeUpdate();
	            }
	        }
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
