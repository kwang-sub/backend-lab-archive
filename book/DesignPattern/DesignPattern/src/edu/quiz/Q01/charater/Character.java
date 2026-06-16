package edu.quiz.Q01.charater;

import edu.quiz.Q01.weapon.WeaponBehavior;

public abstract class Character {
    WeaponBehavior weapon;

    public void setWeapon(WeaponBehavior weapon) {
        this.weapon = weapon;
    }

    public abstract void fight();
}
