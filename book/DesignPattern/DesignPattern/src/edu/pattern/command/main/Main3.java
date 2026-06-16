package edu.pattern.command.main;

import edu.pattern.command.RemoteControl;
import edu.pattern.command.light.Light;
import edu.pattern.command.light.LightOffCommand;
import edu.pattern.command.light.LightOnCommand;

public class Main3 {
    public static void main(String[] args) {
        RemoteControl control = new RemoteControl();
        Light livingRoomLight = new Light("Living Room");
        LightOnCommand lightOnCommand = new LightOnCommand(livingRoomLight);
        LightOffCommand lightOffCommand = new LightOffCommand(livingRoomLight);

        control.setCommand(0, lightOnCommand, lightOffCommand);

        control.onButtonWasPushed(0);
        System.out.println("켜기 취소");
        control.undoButtonWasPushed();
    }
}
