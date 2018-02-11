package cn.krisez.sign.bean.sign;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Krisez on 2018-02-11.
 */

public class Course extends BmobObject {
    private String courseName;
    private String teacher;
    private String code;
    private BmobRelation students;
    private boolean can_join;//false 不能

    public Course() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isCan_join() {
        return can_join;
    }

    public void setCan_join(boolean can_join) {
        this.can_join = can_join;
    }

    public BmobRelation getStudents() {
        return students;
    }

    public void setStudents(BmobRelation students) {
        this.students = students;
    }
}
