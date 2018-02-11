package cn.krisez.sign.model.sign_model;

import android.util.Log;

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
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.bean.sign.CourseBelong;
import cn.krisez.sign.persenter.sign_presenter.SignListener;
import cn.krisez.sign.utils.SharedPreferenceUtil;

/**
 * Created by Krisez on 2018-02-11.
 */

public class SignModel implements ISignModel {

    /**
     * 老师1对多 1->course
     * 学生多对多 n->course
     *
     * @param listener
     */
    @Override
    public void getCourse(final SignListener listener) {
        if (App.getUser().getType().equals("2")) {
            BmobQuery<CourseBelong> query = new BmobQuery<>();
            query.addWhereEqualTo("mUser", App.getUser());
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
                    } else {
                        listener.failed(e.getMessage());
                    }
                }
            });
        } else {
            BmobQuery<Course> bmobQuery = new BmobQuery<>();
            Students students = new Students();
            students.setObjectId("33a72b3e59");
            bmobQuery.addWhereRelatedTo("courses", new BmobPointer(students));
            bmobQuery.findObjects(new FindListener<Course>() {
                @Override
                public void done(List<Course> list, BmobException e) {
                    if (e == null){
                        listener.success(list,"");
                    }else listener.failed(e.getMessage());
                }
            });
        }
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
                            if (e == null)
                                listener.success(null, cs);
                            else listener.failed(e.getMessage());
                        }
                    });
                    listener.success(null, s);
                } else listener.failed(e.getMessage());
            }
        });
    }

    @Override
    public void search(String _id, final SignListener listener) {
        BmobQuery<Course> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId", _id);
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                listener.success(list, "");
            }
        });

    }

    @Override
    public void join(final String _id, final SignListener listener) {
        Course course = new Course();
        course.setObjectId(_id);

        Students students = new Students();
        students.setObjectId(SharedPreferenceUtil.getStudent().getObjectId());
        //学生关联
        BmobRelation relation1 = new BmobRelation();
        relation1.add(course);
        students.setCourses(relation1);
        students.update();
        //课程关联
        BmobRelation relation2 = new BmobRelation();
        relation2.add(SharedPreferenceUtil.getStudent());
        course.setStudents(relation2);
        course.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                listener.success(null, _id);
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
