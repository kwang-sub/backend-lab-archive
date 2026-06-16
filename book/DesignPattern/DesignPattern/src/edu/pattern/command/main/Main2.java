package edu.pattern.command.main;

import edu.pattern.command.RemoteControl;
import edu.pattern.command.light.Light;
import edu.pattern.command.light.LightOffCommand;
import edu.pattern.command.light.LightOnCommand;

public class Main2 {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");

        LightOffCommand livingLightOffCommand = new LightOffCommand(livingRoomLight);
        LightOnCommand livingLightOnCommand = new LightOnCommand(livingRoomLight);

        LightOffCommand kitchenLightOffCommand = new LightOffCommand(kitchenLight);
        LightOnCommand kitchenLightOnCommand = new LightOnCommand(kitchenLight);

        remoteControl.setCommand(0, livingLightOnCommand, livingLightOffCommand);
        remoteControl.setCommand(1, kitchenLightOnCommand, kitchenLightOffCommand);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);

        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);

    }
}
