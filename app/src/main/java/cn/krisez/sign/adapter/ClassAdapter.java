package cn.krisez.sign.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.krisez.sign.R;
import cn.krisez.sign.bean.Seat.Seats;

/**
 * Created by Krisez on 2018-01-15.
 */

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.SeatsHolder> {

    private List<Seats> mSeats;
    private LayoutInflater layoutInflater;

    public ClassAdapter(Context context, List<Seats> mSeats) {
        this.mSeats = mSeats;
        this.layoutInflater = LayoutInflater.from(context);
    }

    private OnItemClickListener onItemClickListener;//定义

    //获取方法类
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public SeatsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.seat_item, parent, false);
        return new SeatsHolder(v);
    }

    @Override
    public void onBindViewHolder(final SeatsHolder holder, int position) {
        Seats seat = mSeats.get(position);
        if (seat.getType().equals("gd")) {
            holder.itemView.setBackgroundColor(Color.BLACK);
        } else holder.name.setText(seat.getBelong().getName());

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    int layoutPos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, layoutPos);
                    return;
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
        return mSeats.size();
    }

    class SeatsHolder extends RecyclerView.ViewHolder {
        private TextView name;

        SeatsHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.s_name);
        }
    }
}
