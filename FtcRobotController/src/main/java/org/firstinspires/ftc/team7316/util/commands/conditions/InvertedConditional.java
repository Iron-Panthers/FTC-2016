package org.firstinspires.ftc.team7316.util.commands.conditions;

/**
 * Created by andrew on 11/29/16.
 */
public class InvertedConditional implements Conditional {

    private Conditional conditional;

    public InvertedConditional(Conditional conditional) {
        this.conditional = conditional;
    }

    @Override
    public boolean shouldRemove() {
        return !conditional.shouldRemove();
    }
}
