package edu.pattern.command.stereo;

import edu.pattern.command.Command;

public class StereoOffCommand implements Command {
    Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }


    @Override
    public void undo() {

    }
}
