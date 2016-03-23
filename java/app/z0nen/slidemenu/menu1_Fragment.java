package app.z0nen.slidemenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.sule.gaugelibrary.GaugeView;

/**
 * Created by britremel 2016
 */
public class menu1_Fragment extends Fragment {
    private View rootview;
    String tankLevelFromLogIn;
    String readingTimestamp;    //takes value from database

    private TextView gaugeValue;
    private TextView timestamp; //shows reading from database on app

    private GaugeView mGaugeView;
    //private final Random RAND = new Random();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu1_layout, container, false);


       // String tankUpdate = null;
        //String readingTime =null;
        mGaugeView = (GaugeView) rootview.findViewById(R.id.gauge_view);
                //getView().findViewById(R.id.gauge_view);
        mTimer.start();

        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();

        gaugeValue = (TextView)rootview.findViewById(R.id.textViewGaugeLevel);
        timestamp = (TextView)rootview.findViewById(R.id.textViewUpdateDateTime);

        tankLevelFromLogIn = extras.getString("tankUpdate");
        readingTimestamp = extras.getString("readingTime");
        float number = Float.parseFloat(tankLevelFromLogIn);

        tankLevelFromLogIn = String.format("%.1f",number);

        gaugeValue.setText(tankLevelFromLogIn);
        timestamp.setText(readingTimestamp);

        return rootview;
    }

    private final CountDownTimer mTimer = new CountDownTimer(30000, 1000) {

        @Override
        public void onTick(final long millisUntilFinished) {
            float tankLevel = Float.parseFloat(tankLevelFromLogIn);
           // float tankLevel = 55;
            mGaugeView.setTargetValue(tankLevel);
        }

        @Override
        public void onFinish() {}
    };
}
