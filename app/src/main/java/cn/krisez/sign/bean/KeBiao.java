package cn.krisez.sign.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Krisez on 2018-01-27.
 */

public class KeBiao{
    private String textView;
    private String lessonId;

    public KeBiao(String lessonId, String textView) {
        this.lessonId = lessonId;
        this.textView = textView;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }
}
