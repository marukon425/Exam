package bean;

import java.io.Serializable;

public class Teacher extends User implements Serializable {

    /**
     * 教員ID:String
     */
    private String id;

    /**
     * パスワード:String
     */
    private String password;

    /**
     * 教員名:String
     */
    private String name;

    /**
     * 所属校:School
     */
    private School school;

    /**
     * ゲッター・セッター
     */
    public String getId() {
        return id;
    }

    public School getSchool() {
        return school;
    }
    
    public void setSchool(School school) {
    	this.school = school;
    }
    public void setId(String id) {
        this.id = id;
    }
}