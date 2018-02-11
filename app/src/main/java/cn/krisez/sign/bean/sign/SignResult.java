package cn.krisez.sign.bean.sign;

import cn.bmob.v3.BmobObject;
import cn.krisez.sign.bean.Students;

/**
 * Created by Krisez on 2018-02-11.
 */

public class SignResult extends BmobObject {
    private Students student;
    private boolean checked;

    public SignResult() {
    }

    public SignResult(Students student, boolean checked) {
        this.student = student;
        this.checked = checked;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
