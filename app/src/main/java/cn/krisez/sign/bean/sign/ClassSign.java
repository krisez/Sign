package cn.krisez.sign.bean.sign;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by Krisez on 2018-02-11.
 */

public class ClassSign extends BmobObject {
    private Course mCourse;
    private boolean signed;//false 不能
    private List<SignResult> mResults;

    public ClassSign() {
    }

    public ClassSign(Course course, boolean signed, List<SignResult> results) {
        mCourse = course;
        this.signed = signed;
        mResults = results;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course course) {
        mCourse = course;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public List<SignResult> getResults() {
        return mResults;
    }

    public void setResults(List<SignResult> results) {
        mResults = results;
    }
}
