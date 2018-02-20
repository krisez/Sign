package cn.krisez.sign.model.sign_model;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.krisez.sign.App;
import cn.krisez.sign.SignStart;
import cn.krisez.sign.bean.ShowStuState;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.bean.sign.CourseBelong;
import cn.krisez.sign.persenter.sign_presenter.ShowPresenter;
import cn.krisez.sign.persenter.sign_presenter.listener.ShowListener;
import cn.krisez.sign.persenter.sign_presenter.listener.SignListener;
import cn.krisez.sign.persenter.sign_presenter.listener.StartListener;
import cn.krisez.sign.utils.SharedPreferenceUtil;
import cn.krisez.sign.utils.Time;

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
            //根据学生id查找课程，course是Students表的列
            BmobQuery<Course> bmobQuery = new BmobQuery<>();
            Students students = new Students();
            students.setObjectId(SharedPreferenceUtil.getStudent().getObjectId());
            bmobQuery.addWhereRelatedTo("courses", new BmobPointer(students));
            bmobQuery.findObjects(new FindListener<Course>() {
                @Override
                public void done(List<Course> list, BmobException e) {
                    if (e == null) {
                        listener.success(list, "");
                    } else listener.failed(e.getMessage());
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
    public void start(String _id, final StartListener listener) {
        //存储课程
        final Course course = new Course();
        course.setObjectId(_id);
        int a = (int) (Math.random() * 10000);
        final String yzm = a < 1000 ? "0" + a : "" + a;
        final SignStart signStart = new SignStart();
        signStart.setCourse(course);
        signStart.setYzm(yzm);
        signStart.setSign(true);
        signStart.save(new SaveListener<String>() {
            @Override
            public void done(final String s, BmobException e) {
                if (e == null) {
                    //查找学生
                    BmobQuery<Students> query = new BmobQuery<>();
                    query.addWhereRelatedTo("students", new BmobPointer(course));
                    query.findObjects(new FindListener<Students>() {
                        @Override
                        public void done(List<Students> list, BmobException e) {
                            List<BmobObject> showStuStates = new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {
                                showStuStates.add(new ShowStuState(list.get(i).getXh(), list.get(i).getName(), false));
                            }
                            new BmobBatch().insertBatch(showStuStates).doBatch(new QueryListListener<BatchResult>() {
                                @Override
                                public void done(List<BatchResult> list, BmobException e) {
                                    if(e==null){
                                        final BmobRelation relation = new BmobRelation();
                                        for(int j=0;j<list.size();j++){
                                            BatchResult result = list.get(j);
                                            ShowStuState showStuState = new ShowStuState();
                                            BmobException ex =result.getError();
                                            if(ex == null){
                                                showStuState.setObjectId(result.getObjectId());
                                                relation.add(showStuState);
                                            }else{
                                                listener.failed(e.getMessage());
                                            }
                                        }
                                        signStart.setStuState(relation);
                                        signStart.update(new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                listener.success(yzm, s);
                                            }
                                        });
                                    }else{
                                        Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                                    }
                                }
                            });
                        }
                    });
                } else listener.failed(e.getMessage());
            }
        });
    }

    @Override
    public void show(String _id, final ShowListener listener) {
        BmobQuery<ShowStuState> query = new BmobQuery<>();
        final SignStart signStart = new SignStart();
        signStart.setObjectId(_id);
        query.addWhereRelatedTo("stuState", new BmobPointer(signStart));
        query.findObjects(new FindListener<ShowStuState>() {
            @Override
            public void done(List<ShowStuState> list, BmobException e) {
                if (e == null) {
                    listener.success(list);
                } else listener.failed(e.getMessage());
            }
        });
    }

    @Override
    public void changePosState(String stuId, final int pos, final ShowListener listener) {
        BmobQuery<ShowStuState> query = new BmobQuery<>();
        query.getObject(stuId, new QueryListener<ShowStuState>() {
            @Override
            public void done(ShowStuState showStuState, BmobException e) {
                showStuState.setState(true);
                showStuState.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            listener.change(pos);
                        }else listener.failed(e.getMessage());
                    }
                });
            }
        });
    }


    @Override
    public void end(SignListener listener) {

    }

    @Override
    public void sign(final String yzm, String courseId, final SignListener listener) {
        //往前推了2分钟  查询五分钟之前   以后的数据
        //例如当前时间  2018-02-16 04:30:21--->>  2018-02-016 04:25:21   ->start
        String start = Time.getTime();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //查询修改状态
        BmobQuery<SignStart> ss = new BmobQuery<>();
        ss.addWhereEqualTo("mCourse", courseId);
        ss.addWhereGreaterThan("createdAt", new BmobDate(date));
        final Date finalDate = date;
        ss.findObjects(new FindListener<SignStart>() {
            @Override
            public void done(List<SignStart> list, BmobException e) {
                if (e == null) {
                    if (yzm.equals(list.get(0).getYzm())) {
                        BmobQuery<ShowStuState> sss = new BmobQuery<>();
                        sss.addWhereGreaterThan("createdAt", new BmobDate(finalDate));
                        sss.addWhereEqualTo("xh", SharedPreferenceUtil.getStudent().getXh());
                        sss.findObjects(new FindListener<ShowStuState>() {
                            @Override
                            public void done(List<ShowStuState> list, BmobException e) {
                                if (e == null) {
                                    if (list.isEmpty()) listener.failed("无记录");
                                    else {
                                        ShowStuState showStuState = new ShowStuState();
                                        showStuState.setObjectId(list.get(0).getObjectId());
                                        showStuState.setState(true);
                                        showStuState.update(new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if (e == null) {
                                                    listener.success(null, "");
                                                } else listener.failed(e.getMessage());
                                            }
                                        });
                                        //listener.success(null,list.get(0).getObjectId());
                                    }
                                } else listener.failed(e.getMessage());
                            }
                        });
                    } else listener.failed("验证码错误");
                } else listener.failed(e.getMessage());
            }
        });

    }

}
