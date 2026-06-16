package edu.pattern.iterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CafeMenu implements Menu {


    Map<String, MenuItem> menuItems = new HashMap<String, MenuItem>();

    public CafeMenu() {
        addItem("베지버거", "통밀빵, 상추, 토마토", true, 3300);
        addItem("불고기버거", "통밀빵, 상추, 토마토, 불고기", true, 3400);
        addItem("새우버거", "통밀빵, 상추, 토마토, 새우", true, 3500);
        addItem("치즈버거", "통밀빵, 상추, 토마토, 치즈", true, 3300);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.put(name, menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItems.values().iterator();
    }
}
