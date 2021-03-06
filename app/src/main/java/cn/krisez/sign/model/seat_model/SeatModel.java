package cn.krisez.sign.model.seat_model;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.krisez.sign.App;
import cn.krisez.sign.bean.ClassRoom;
import cn.krisez.sign.bean.ClassSeats;
import cn.krisez.sign.bean.Seat.A_Seat;
import cn.krisez.sign.bean.Seat.Seats;
import cn.krisez.sign.persenter.class_presenter.ClassListener;
import cn.krisez.sign.utils.SharedPreferenceUtil;
import cn.krisez.sign.utils.Time;

/**
 * Created by Krisez on 2018-01-25.
 */

public class SeatModel implements ISeatModel {
    @Override
    public void updateSeat(final int pos, final ClassListener listener) {
        ClassSeats classSeats = new ClassSeats();
        classSeats.setObjectId(SharedPreferenceUtil.getClassId());
        BmobQuery<ClassSeats> query = new BmobQuery<>();
        query.getObject(SharedPreferenceUtil.getClassId(), new QueryListener<ClassSeats>() {
            @Override
            public void done(ClassSeats classSeats, BmobException e) {
                final List<Seats> seats = classSeats.getSeats();
                seats.get(pos).setBelong(SharedPreferenceUtil.getStudent());
                classSeats.setSeats(seats);
                classSeats.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        listener.success(seats);
                    }
                });
            }
        });

    }

    @Override
    public void reset(final String teacher, final String lesson, final ClassListener listener) {
        //往前推了2分钟  查询五分钟之前   以后的数据
        //例如当前时间  2018-02-16 04:30:21--->>  2018-02-16 01:30:21   ->start
        String start = Time.getSeatTime();
        @SuppressLint("SimpleDateFormat")
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //查询修改状态
        BmobQuery<ClassRoom> query = new BmobQuery<>();
        query.addWhereEqualTo("number", lesson);
        query.addWhereEqualTo("teacher", teacher);
        query.addWhereEqualTo("date", Time.getDate());
        query.addWhereGreaterThan("createdAt", new BmobDate(date));
        query.include("mClassSeats");
        query.findObjects(new FindListener<ClassRoom>() {
            @Override
            public void done(List<ClassRoom> list, BmobException e) {
                if (e == null && list.size() > 0) {
                    SharedPreferenceUtil.setClassId(list.get(0).getClassSeats().getObjectId());
                    listener.success(list.get(0).getClassSeats().getSeats());
                } else if (list.size() == 0) {
                    if (App.getUser().getType().equals("2")) {
                        final List<Seats> seats = new ArrayList<>();
                        BmobQuery<A_Seat> aSeatBmobQuery = new BmobQuery<>();
                        aSeatBmobQuery.setLimit(143);
                        aSeatBmobQuery.findObjects(new FindListener<A_Seat>() {
                            @Override
                            public void done(List<A_Seat> list, BmobException e) {
                                final ClassSeats classSeats = new ClassSeats();
                                seats.addAll(list);
                                classSeats.setType("A");
                                classSeats.setSeats(seats);
                                classSeats.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        SharedPreferenceUtil.setClassId(s);
                                        ClassRoom classRoom = new ClassRoom();
                                        classRoom.setNumber(lesson);
                                        classRoom.setDate(Time.getDate());
                                        classRoom.setTeacher(teacher);
                                        classRoom.setClassSeats(classSeats);
                                        classRoom.save(new SaveListener<String>() {
                                            @Override
                                            public void done(String s, BmobException e) {
                                                if (e == null) {
                                                    listener.success(seats);
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    } else {
                        listener.failed();
                    }
                } else {
                    e.printStackTrace();
                    listener.failed();
                }
            }
        });

        /*final List<Seats> seats = new ArrayList<>();
        BmobQuery<ClassSeats> query = new BmobQuery<>();
        query.findObjects(new FindListener<ClassSeats>() {
            @Override
            public void done(List<ClassSeats> list, BmobException e) {
                if (e == null) {
                    seats.addAll(list.get(0).getSeats());
                    listener.success(seats);
                } else listener.failed();
            }
        });*/
    }
}

