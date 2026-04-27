package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable {

    // 入学年度
    private int entYear;

    // 学生番号
    private String studentNo;

    // 学生名
    private String studentName;

    // クラス番号
    private String classNum;

    // 点数マップ
    private Map<String, Integer> points;


    // ゲッター・セッター
    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Map<String, Integer> getPoints() {
        return points;
    
    }

    public void setPoints(Map<String, Integer> points) {
        this.points = points;
    }


    public Integer getPoint(String key) {
        return points.get(key);
    }

    public void putPoint(String key, Integer value) {
        points.put(key, value);
    }

}