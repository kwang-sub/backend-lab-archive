package edu.pattern.complex;

import edu.pattern.complex.composite.Flock;
import edu.pattern.complex.factory.AbstractDuckFactory;
import edu.pattern.complex.factory.CountingDuckFactory;
import edu.pattern.complex.factory.QuackCounter;
import edu.pattern.complex.goose.Goose;
import edu.pattern.complex.goose.GooseAdapter;
import edu.pattern.complex.observer.Quackologist;

public class DuckSimulator2 {

    public static void main(String[] args) {
        DuckSimulator2 simulator = new DuckSimulator2();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();

        simulator.simulate(duckFactory);
    }

    private void simulate(AbstractDuckFactory duckFactory) {

        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();

        Goose goose = new Goose();
        Quackable gooseDuck = new GooseAdapter(goose);

        System.out.println("\n오리 시뮬레이션 게임");

        Flock flockOfDucks = new Flock();

        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);

        Flock flockOfMallards = new Flock();

        Quackable mallardDuck1 = duckFactory.createMallardDuck();
        Quackable mallardDuck2 = duckFactory.createMallardDuck();
        Quackable mallardDuck3 = duckFactory.createMallardDuck();
        Quackable mallardDuck4 = duckFactory.createMallardDuck();

        flockOfMallards.add(mallardDuck1);
        flockOfMallards.add(mallardDuck2);
        flockOfMallards.add(mallardDuck3);
        flockOfMallards.add(mallardDuck4);

        flockOfDucks.add(flockOfMallards);

        System.out.println("\n오리 시뮬레이션 게임(+옵저버)");
        Quackologist quackologist = new Quackologist();
        flockOfDucks.registerObserver(quackologist);

        simulate(flockOfDucks);
        System.out.println("오리 소리횟수 : " + QuackCounter.numberOfQuacks);
    }

    private void simulate(Flock flockOfDucks) {
        flockOfDucks.quack();
    }
}
