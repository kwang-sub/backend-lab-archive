package edu.pattern.command.main;

import edu.pattern.command.Command;
import edu.pattern.command.RemoteControl;
import edu.pattern.command.light.Light;
import edu.pattern.command.light.LightOffCommand;
import edu.pattern.command.light.LightOnCommand;
import edu.pattern.command.macro.MacroOffCommand;
import edu.pattern.command.macro.MacroOnCommand;

public class Main4 {
    public static void main(String[] args) {
        RemoteControl control = new RemoteControl();
        Light light1 = new Light("방");
        Light light2 = new Light("거실");

        LightOnCommand lightOnCommand1 = new LightOnCommand(light1);
        LightOnCommand lightOnCommand2 = new LightOnCommand(light2);

        MacroOnCommand onCommand = new MacroOnCommand(new Command[]{lightOnCommand1, lightOnCommand2});

        LightOffCommand lightOffCommand1 = new LightOffCommand(light1);
        LightOffCommand lightOffCommand2 = new LightOffCommand(light2);

        MacroOffCommand offCommand = new MacroOffCommand(new Command[] {lightOffCommand1, lightOffCommand2});
        control.setCommand(0, onCommand, offCommand);
        control.onButtonWasPushed(0);
        control.undoButtonWasPushed();
    }
}
