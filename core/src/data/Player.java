package data;

import managers.FoodManager;

public class Player extends Snake {

    @Override
    public void death(FoodManager foodManager) {
        System.out.println("\nYour score : " + this.point +
                "\nYour size : " + this.tail.size());
        System.exit(0);
    }

}
