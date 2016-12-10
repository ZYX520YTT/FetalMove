package fetalmove.neusoft.com.fetalmove.activity;
/**
 * Created by 张宇翔 on 2016/12/10 18:46.
 * Email：1124751755@qq.com
 * 功能：
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import fetalmove.neusoft.com.fetalmove.HistoryAdapter;
import fetalmove.neusoft.com.fetalmove.R;
import fetalmove.neusoft.com.fetalmove.bean.CountInfo;
import fetalmove.neusoft.com.fetalmove.db.dao.CountDao;

public class HirstoryActivity extends AppCompatActivity {
    @ViewInject(R.id.ib_back)
    private ImageButton ib_back;
    @ViewInject(R.id.re_view)
    private RecyclerView re_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirstory);
        ViewUtils.inject(this);
        Init();

    }
    private void Init(){
        //查询出历史数据
        CountDao countDao=new CountDao(this);
        List<CountInfo> countInfos= countDao.finall();



        re_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        re_view.setAdapter(new HistoryAdapter(this,countInfos));
        re_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));//添加分割线
        re_view.setItemAnimator(null);


        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
