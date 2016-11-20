package org.firstinspires.ftc.team7316.util.commands.conditions;

import org.firstinspires.ftc.team7316.util.hardware.CatapultWrapper;

/**
 * Created by Maxim on 11/18/2016.
 */
public class CatapultPositionConditional implements Conditional {

    private CatapultWrapper catapultWrapper;
    private boolean wantArmedState;

    public CatapultPositionConditional(CatapultWrapper catapultWrapper, boolean wantArmedState) {
        this.catapultWrapper = catapultWrapper;
        this.wantArmedState = wantArmedState;
    }

    @Override
    public boolean shouldRemove() {
        if (wantArmedState) {
            return catapultWrapper.isPrimed;
        } else {
            return !catapultWrapper.isPrimed;
        }
    }

}
