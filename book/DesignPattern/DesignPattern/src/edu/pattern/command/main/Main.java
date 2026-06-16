package edu.pattern.command.main;

import edu.pattern.command.garagedoor.GarageDoor;
import edu.pattern.command.garagedoor.GarageDoorOpenCommand;
import edu.pattern.command.light.Light;
import edu.pattern.command.light.LightOnCommand;
import edu.pattern.command.SimpleRemoteControl;

public class Main {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();

        Light light = new Light("거실");
        LightOnCommand lightOnCommand = new LightOnCommand(light);

        remote.setSlot(lightOnCommand);
        remote.buttonWasPressed();

        GarageDoor garageDoor = new GarageDoor();
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);

        remote.setSlot(garageDoorOpenCommand);
        remote.buttonWasPressed();
    }
}
