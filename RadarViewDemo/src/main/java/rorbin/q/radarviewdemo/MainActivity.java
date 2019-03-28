package rorbin.q.radarviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rorbin.q.radarview.RadarData;
import rorbin.q.radarview.RadarView;

public class MainActivity extends AppCompatActivity {
    private RadarView mRadarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mRadarView = (RadarView) findViewById(R.id.radarView);

        mRadarView.setEmptyHint("无数据");

        List<String> vertexText = new ArrayList<>();
        Collections.addAll(vertexText, "词汇", "语法", "流利度", "发音", "听力理解");
        mRadarView.setVertexText(vertexText);

        List<Integer> res = new ArrayList<>();
        Collections.addAll(res, android.R.drawable.ic_input_add, android.R.drawable.ic_input_add,
                android.R.drawable.ic_input_add, android.R.drawable.ic_input_add, android.R.drawable.ic_input_add);
        mRadarView.setVertexIconResid(res);
        mRadarView.setVertexIconSize(0);

        List<Float> values2 = new ArrayList<>();
        Collections.addAll(values2, 17f, 1f, 4f, 2f, 8f);
        RadarData data2 = new RadarData(values2);
        data2.setValueTextEnable(true);
        data2.setVauleTextColor(Color.WHITE);
        data2.setValueTextSize(dp2px(10));
        data2.setLineWidth(dp2px(1));
        mRadarView.addData(data2);
        mRadarView.setMaxValue(30);
        mRadarView.setRotationEnable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toggle_rotation:
                mRadarView.setRotationEnable(!mRadarView.isRotationEnable());
                break;
            case R.id.anime_value:
                mRadarView.animeValue(2000);
                break;
            case R.id.change_layer:
                int randomInt = new Random().nextInt(6) + 1;
                mRadarView.setLayer(randomInt);
                break;
            case R.id.change_web_mode:
                if (mRadarView.getWebMode() == RadarView.WEB_MODE_POLYGON) {
                    mRadarView.setWebMode(RadarView.WEB_MODE_CIRCLE);
                } else {
                    mRadarView.setWebMode(RadarView.WEB_MODE_POLYGON);
                }
                break;
            case R.id.toggle_line_enable:
                mRadarView.setRadarLineEnable(!mRadarView.isRadarLineEnable());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private float dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }
}
