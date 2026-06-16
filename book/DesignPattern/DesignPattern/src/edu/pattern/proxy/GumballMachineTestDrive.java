package edu.pattern.proxy;

import edu.pattern.proxy.sample.GumballMachine;

public class GumballMachineTestDrive {
    public static void main(String[] args) {
        int count = 112;
        String location = "Ausein";

        GumballMachine gumballMachine = new GumballMachine(location, count);

        GumballMonitor monitor = new GumballMonitor(gumballMachine);

        monitor.report();
    }
}
