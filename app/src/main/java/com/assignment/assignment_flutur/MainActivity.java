package com.assignment.assignment_flutur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView mSlotImage1, mSlotImage2, mSlotImage3;
    private SlotWheel mSlotWheel1, mSlotWheel2, mSlotWheel3;
    private Button mButton;
    private boolean mIsStarted;

    public static final Random random = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (random.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlotImage1 = findViewById(R.id.slot_image_1);
        mSlotImage2 = findViewById(R.id.slot_image_2);
        mSlotImage3 = findViewById(R.id.slot_image_3);
        mButton = findViewById(R.id.buttonPlayStop);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsStarted){
                    mSlotWheel1.stopSlotWheel();
                    mSlotWheel2.stopSlotWheel();
                    mSlotWheel3.stopSlotWheel();

                    if (mSlotWheel1.mCurrentIndex == mSlotWheel2.mCurrentIndex
                            && mSlotWheel2.mCurrentIndex == mSlotWheel3.mCurrentIndex){
                        Toast.makeText(MainActivity.this, "You hit the jackpot!!!",
                                Toast.LENGTH_LONG).show();
                    }else if (mSlotWheel1.mCurrentIndex == mSlotWheel2.mCurrentIndex
                            || mSlotWheel2.mCurrentIndex == mSlotWheel3.mCurrentIndex
                            || mSlotWheel1.mCurrentIndex == mSlotWheel3.mCurrentIndex){
                        Toast.makeText(MainActivity.this, "You almost hit the jackpot!!!",
                                Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Sorry :(\n you didn't hit the jackpot,\n" +
                                        " better luck next time.", Toast.LENGTH_LONG).show();
                    }

                    mButton.setText("PLAY");
                    mIsStarted = false;
                }else{
                    mSlotWheel1 = new SlotWheel(new SlotWheel.SlotWheelListener(){
                        @Override
                        public void nextImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mSlotImage1.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(0, 200));

                    mSlotWheel1.start();

                    mSlotWheel2 = new SlotWheel(new SlotWheel.SlotWheelListener(){
                        @Override
                        public void nextImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mSlotImage2.setImageResource(img);
                                }
                            });
                        }

                    }, 200, randomLong(150, 400));

                    mSlotWheel2.start();

                    mSlotWheel3 = new SlotWheel(new SlotWheel.SlotWheelListener() {
                        @Override
                        public void nextImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mSlotImage3.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(150, 400));

                    mSlotWheel3.start();

                    mButton.setText("STOP");
                    mIsStarted = true;
                }
            }
        });
    }
}
