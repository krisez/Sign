package cn.krisez.sign.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import cn.krisez.sign.R;
import cn.krisez.sign.bean.KeBiao;
import cn.krisez.sign.ui.seat_ui.SeatActivity;
import cn.krisez.sign.utils.Utils;

/**
 * Created by Krisez on 2018-01-27.
 */

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder> {
    private List<KeBiao> mDatas;
    private LayoutInflater layoutInflater;
    private Context mContex;

    public TableAdapter(Context context, List<KeBiao> datas){

        this.mDatas = datas;
        this.mContex = context;
        layoutInflater = LayoutInflater.from(context);
    }

    //定义一个接口，传入两个点击方法
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private OnItemClickListener onItemClickListener;//定义

    //获取方法类
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.table_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final KeBiao keBiao = mDatas.get(position);
        final String s = keBiao.getTextView();
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.width = Utils.dip2px(mContex,60);
        if(position>=16&&position<=23 ||position>=40 &&position<=47){
            lp.height = Utils.dip2px(mContex,20);
        }
        if(keBiao.getTextView().length()>10){
            holder.tv.setText(Utils.getLessonName(s));
            holder.addr.setText(Utils.getLessonAddr(s));
        }else holder.tv.setText(keBiao.getTextView());
        if(keBiao.getTextView().length()>25)
            holder.itemView.setBackgroundColor(Color.parseColor(Utils.RandomColor(position)));
        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,layoutPos);
                    if (!Objects.equals(mDatas.get(position).getTextView(), "") && Objects.equals(keBiao.getLessonId(), " ")){
                        new AlertDialog.Builder(mContex).setTitle("详情")
                                .setMessage(mDatas.get(position).getTextView())
                                .setPositiveButton("座位安排", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(mContex, SeatActivity.class);
                                        intent.putExtra("teacher",Utils.getLessonTeacher(s));
                                        intent.putExtra("class",Utils.getLessonAddr(s));
                                        mContex.startActivity(intent);
                                    }
                                })
                                .show();
                    }
                    else Toast.makeText(mContex, "没课哦~", Toast.LENGTH_SHORT).show();

                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView,layoutPos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        TextView addr;
        MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.table_name);
            addr = itemView.findViewById(R.id.table_addr);
        }
    }
}
