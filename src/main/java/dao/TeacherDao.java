package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Teacher;


public class TeacherDao extends Dao {
    public Teacher search(String login, String password)
            throws Exception {
    		Teacher customer = null;

            Connection con = getConnection();

            PreparedStatement st;
            st = con.prepareStatement(
                "select * from TEACHER  where ID=? and PASSWORD=?");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                customer = new Teacher();
                customer.setId(rs.getString("ID"));
                customer.setPassword(rs.getString("PASSWORD"));
                customer.setName(rs.getString("NAME"));
                //customer.setName(rs.getString("NAME"));
                // school が別テーブルならここではセットしない
            }


            st.close();
            con.close();
            return customer;
        }

}
