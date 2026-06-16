package edu.pattern.iterator;


import java.util.Iterator;

public class DinerMenu implements Menu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("BLT1", "통밀 베이컨", false, 3400);
        addItem("BLT2", "통밀 아보카도", true, 3000);
        addItem("BLT3", "통밀 바나나", true, 3200);
        addItem("BLT4", "통밀 치킨", false, 3300);

    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("메뉴가 꽉 찼습니다.");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

   public Iterator createIterator() {
        return new DinerMenuIterator(menuItems);
   }
}
