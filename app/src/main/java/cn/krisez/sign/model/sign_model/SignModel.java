package cn.krisez.sign.model.sign_model;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.krisez.sign.App;
import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.bean.sign.CourseBelong;
import cn.krisez.sign.persenter.sign_presenter.SignListener;

/**
 * Created by Krisez on 2018-02-11.
 */

public class SignModel implements ISignModel {
    @Override
    public void getCourse(final SignListener listener) {
        BmobQuery<CourseBelong> query = new BmobQuery<>();
        query.addWhereEqualTo("mUser",App.getUser());
        query.include("mCourse");
        query.findObjects(new FindListener<CourseBelong>() {
            @Override
            public void done(List<CourseBelong> list, BmobException e) {
                if (e == null) {
                    List<Course> courses = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        courses.add(list.get(i).getCourse());
                    }
                    listener.success(courses, null);
                } else{
                    listener.failed(e.getMessage());
                }
            }
        });
    }

    @Override
    public void create(String courseName, String teacher, String classCode, final SignListener listener) {
        final Course course = new Course();
        course.setCan_join(true);
        course.setCode(classCode);
        course.setCourseName(courseName);
        course.setTeacher(teacher);
        course.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    final String cs = s;
                    CourseBelong cb = new CourseBelong();
                    cb.setCourse(course);
                    cb.setUser(App.getUser());
                    cb.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null)
                            listener.success(null,cs);
                            else listener.failed(e.getMessage());
                        }
                    });
                    listener.success(null, s);
                } else listener.failed(e.getMessage());
            }
        });
    }

    @Override
    public void join(final String _id, final SignListener listener) {
        Course course = new Course();
        course.setObjectId(_id);
        BmobRelation relation = new BmobRelation();
        relation.add(App.getUser());
        course.setStudents(relation);
        course.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                listener.success(null,_id);
            }
        });
    }

    @Override
    public void start(SignListener listener) {

    }

    @Override
    public void end(SignListener listener) {

    }

    @Override
    public void sign(SignListener listener) {

    }

}
