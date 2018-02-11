package cn.krisez.sign.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import cn.krisez.sign.R;
import cn.krisez.sign.bean.Seat.Seats;
import cn.krisez.sign.bean.sign.Course;
import cn.krisez.sign.bean.sign.CourseBelong;

/**
 * Created by Krisez on 2018-02-11.
 */

public class SignAdapter extends RecyclerView.Adapter<SignAdapter.MyViewHolder> {

    private List<Course> mCourses;
    private LayoutInflater layoutInflater;

    public SignAdapter(Context context, List<Course> mCourses) {
        this.mCourses = mCourses;
        this.layoutInflater = LayoutInflater.from(context);
    }

    private OnItemClickListener onItemClickListener;//定义

    //获取方法类
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.sign_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Course course = mCourses.get(position);
        holder.id.setText(course.getObjectId());
        holder.course.setText(course.getCourseName());
        holder.code.setText(course.getCode());
        holder.teacher.setText(course.getTeacher());
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, layoutPos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, layoutPos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView id,course,code,teacher;

        MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.sign_item_id);
            course = itemView.findViewById(R.id.sign_item_course);
            code = itemView.findViewById(R.id.sign_item_classcode);
            teacher = itemView.findViewById(R.id.sign_item_teacher);
        }
    }
}
