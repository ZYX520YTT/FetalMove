package fetalmove.neusoft.com.fetalmove;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fetalmove.neusoft.com.fetalmove.bean.CountInfo;

/**
 * Created by 张宇翔 on 2016/12/10 19:58.
 * Email：1124751755@qq.com
 * 功能：
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    public Context context;
    public List<CountInfo> countInfos;

    public HistoryAdapter(Context context, List<CountInfo> countInfos){
        this.context=context;
        this.countInfos=countInfos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.
                from(context).inflate(R.layout.item_hirstory,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CountInfo countInfo=countInfos.get(position);
        holder.tvDate.setText(countInfo.getDate());
        holder.tvTime.setText(countInfo.getTime());
        holder.tvCount.setText(countInfo.getCount()+"次");
    }

    @Override
    public int getItemCount() {
        return countInfos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tvDate;
        public TextView tvTime;
        public TextView tvCount;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDate= (TextView) itemView.findViewById(R.id.tvDate);
            tvTime= (TextView) itemView.findViewById(R.id.tvTime);
            tvCount= (TextView) itemView.findViewById(R.id.tvCount);
        }
    }
}
