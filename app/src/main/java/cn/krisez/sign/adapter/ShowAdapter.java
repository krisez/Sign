package cn.krisez.sign.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.krisez.sign.R;
import cn.krisez.sign.bean.ShowStuState;
import cn.krisez.sign.bean.Students;
import cn.krisez.sign.bean.sign.Course;

/**
 * Created by Krisez on 2018-02-18.
 * 展示学生情况表->SignShowActivity
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.MViewHolder> {
    private List<ShowStuState> mStateList;
    private LayoutInflater layoutInflater;

    public ShowAdapter(Context context, List<ShowStuState> mStateList) {
        this.mStateList = mStateList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    private OnItemClickListener onItemClickListener;//定义

    //获取方法类
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.sign_show_item, parent, false);
        return new MViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MViewHolder holder, int position) {
        ShowStuState showStuState = mStateList.get(position);
        holder.mTextView1.setText(showStuState.getXh());
        holder.mTextView2.setText(showStuState.getName());
        if (showStuState.isState())
            holder.state.setImageResource(R.drawable.ic_choosen);

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
        return mStateList.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView1; // xh
        private TextView mTextView2; // name
        private ImageView state; // state

        MViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.sign_show_item_xh);
            mTextView2 = itemView.findViewById(R.id.sign_show_item_name);
            state = itemView.findViewById(R.id.sign_show_item_state);
        }
    }
}
