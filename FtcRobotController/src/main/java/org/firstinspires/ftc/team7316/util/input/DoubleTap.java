package org.firstinspires.ftc.team7316.util.input;

import android.widget.Button;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Listenable;

/**
 * Created by Maxim on 1/31/2017.
 */
public class DoubleTap extends Listenable implements ButtonListener {

    private ElapsedTime timer;
    private double delay;
    private boolean state, tappedOnce;

    public DoubleTap(double delay) {
        timer = new ElapsedTime();
        this.delay = delay;
        this.state = false;
    }

    @Override
    public void init() {

    }

    @Override
    protected void subLoop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

    @Override
    public boolean state() {
        if (state) {
            state = false;
            return true;
        }
        return false;
    }

    @Override
    public void onPressed() {
        if (tappedOnce && delay > timer.seconds()) {
            state = true;
        }
    }

    @Override
    public void onReleased() {
        tappedOnce = true;
        timer.reset();
    }

}
