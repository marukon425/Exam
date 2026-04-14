package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {
    public ClassNum get(String class_num, School school) throws Exception {
        // クラス番号をクラス情報にセット
        ClassNum classNum = new ClassNum();
        // DBへのコネクションを取得
        Connection connection = getConnection();
        // プリペアードステートメントをセット
        PreparedStatement statement = null;
        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from class_num where class_num = ? and school_cd = ?");
            // パラメータをセット
            statement.setString(1, classNum);
            statement.setString(2, school.getCd());
            // SQL文を実行
            ResultSet rs = statement.executeQuery();

            SchoolDao schoolDao = new SchoolDao();
            if (rs.next()) {
                // クラス番号が存在する場合は各値をセット
                classNum.setClassNum(rs.getString("class_num"));
                classNum.setSchool(schoolDao.getSchool(rs.getString("school_cd")));
            } else {
                // クラス番号が存在しない場合はnullをセット
                classNum = null;
            }
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
        return classNum;
    }

    public List<String> filter(School school) throws Exception {
        // リストを初期化
        List<String> list = new ArrayList<>();
        // データベースのコネクションを設定
        Connection connection = getConnection();
        // プリペアドステートメント
        PreparedStatement statement = null;

        try {
            // プリペアドステートメントにSQL文をセット
            statement = connection.prepareStatement("select class_num from class_num where school_cd=? order by class_num");
            // プリペアドステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // クエリを実行
            ResultSet rSet = statement.executeQuery();

            // 結果セットを処理
            while (rSet.next()) {
                // リストにクラス番号を追加
                list.add(rSet.getString("class_num"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアドステートメントを閉じる
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

    public boolean save(ClassNum classNum) throws Exception {
    }

    public boolean save(ClassNum classNum, String newClassNum) throws Exception {
    }
}