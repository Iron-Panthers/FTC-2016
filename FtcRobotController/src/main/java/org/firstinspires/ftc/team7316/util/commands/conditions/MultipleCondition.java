package org.firstinspires.ftc.team7316.util.commands.conditions;

/**
 * Created by andrew on 11/19/16.
 */
public class MultipleCondition implements Conditional {

    private Conditional[] conditions;
    private boolean useAnd;

    public MultipleCondition(boolean needsAll, Conditional... conditions) {
        this.conditions = conditions;
        this.useAnd = needsAll;
    }

    @Override
    public boolean state() {
        for ( Conditional condition : this.conditions) {
            if (useAnd) {
                if (!condition.state()) {
                    return false;
                }
            } else {
                if (condition.state()) {
                    return true;
                }
            }
        }
        if (useAnd) {
            return true;
        } else {
            return false;
        }
    }
}
