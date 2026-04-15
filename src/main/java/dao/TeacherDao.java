package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;


public class TeacherDao extends Dao {
    public Teacher search(String login, String password)
            throws Exception {
    		Teacher teacher = null;
    		School school = null;

            Connection con = getConnection();

            PreparedStatement st;
            st = con.prepareStatement(
                "select * from TEACHER  where ID=? and PASSWORD=?");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
            	teacher = new Teacher();
            	school = new School();
            	school.setCd(rs.getString("SCHOOL_CD"));
            	teacher.setId(rs.getString("ID"));
            	teacher.setPassword(rs.getString("PASSWORD"));
            	teacher.setName(rs.getString("NAME"));
                teacher.setSchool(school);
                
                //customer.setName(rs.getString("NAME"));
                // school が別テーブルならここではセットしない
            }


            st.close();
            con.close();
            return teacher;
        }

}
