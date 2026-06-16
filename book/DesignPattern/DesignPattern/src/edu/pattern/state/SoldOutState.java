package edu.pattern.state;

public class SoldOutState implements State {
    GumballMachine gumballMachine;
    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("솔드 아웃입니다.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("솔드아웃입니다.");
    }

    @Override
    public void turnCrank() {
        System.out.println("솔드아웃입니다.");
    }

    @Override
    public void dispense() {
        System.out.println("알맹이를 내보낼 수 없습니다.");
    }

    @Override
    public void refill(int num) {
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }
}
