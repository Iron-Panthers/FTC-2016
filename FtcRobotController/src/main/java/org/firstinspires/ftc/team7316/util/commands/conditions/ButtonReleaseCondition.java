package org.firstinspires.ftc.team7316.util.commands.conditions;

import org.firstinspires.ftc.team7316.util.input.ButtonWrapper;

/**
 * Created by andrew on 11/19/16.
 */
public class ButtonReleaseCondition implements Conditional {

    private ButtonWrapper button;

    public ButtonReleaseCondition(ButtonWrapper button) {
        this.button = button;
    }
    
    @Override
    public boolean shouldRemove() {
        return !this.button.isPressed();
    }
}
