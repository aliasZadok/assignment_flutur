package com.assignment.assignment_flutur;

import java.lang.Thread;

public class SlotWheel extends Thread {

    private static int[] imageReferences = {R.drawable.einstein, R.drawable.albert_camus,
            R.drawable.issac_newton, R.drawable.alexander_pope, R.drawable.hannibal_barca,
            R.drawable.jean_paul_sartre};
    public int mCurrentIndex;
    private SlotWheelListener mSlotWheelListener;
    private long mFrameDuration;
    private long mStartIn;
    private boolean mIsStarted;

    public SlotWheel(SlotWheelListener slotWheelListener, long frameDuration, long startIn) {
        this.mSlotWheelListener = slotWheelListener;
        this.mFrameDuration = frameDuration;
        this.mStartIn = startIn;
        mCurrentIndex = 0;
        mIsStarted = true;
    }

    public void nextImage() {
        mCurrentIndex++;

        if (mCurrentIndex == imageReferences.length) {
            mCurrentIndex = 0;
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(mStartIn);
        } catch (InterruptedException ie) {

        }
        while (mIsStarted) {
            try {
                Thread.sleep(mFrameDuration);
            } catch (InterruptedException ie) {
            }
            nextImage();
            if (mSlotWheelListener != null) {
                mSlotWheelListener.nextImage(imageReferences[mCurrentIndex]);
            }
        }
    }

    public void stopSlotWheel() {
        mIsStarted = false;
    }

    interface SlotWheelListener {
        void nextImage(int img);
    }
}
