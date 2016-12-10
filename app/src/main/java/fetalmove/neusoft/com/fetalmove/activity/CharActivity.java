package fetalmove.neusoft.com.fetalmove.activity;

/**
 * Created by 张宇翔 on 2016/12/10 18:46.
 * Email：1124751755@qq.com
 * 功能：
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.lidroid.xutils.view.annotation.ViewInject;

import fetalmove.neusoft.com.fetalmove.R;

public class CharActivity extends AppCompatActivity {

    @ViewInject(R.id.ib_back)
    private ImageButton ib_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);

        Init();
    }
    private void Init(){
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
