package edu.pattern.composite;

public class MenuTestDrive {
    public static void main(String[] args) {
//        최상위 모든 메뉴를 가지는 allMenus
        MenuComponent allMenus = new Menu("전체 메뉴", "전체 메뉴");

//       시간대에 따른 메뉴를 가지는 복합객체들
        MenuComponent pancakeHouseMenu = new Menu("팬케이크 하우스 메뉴", "아침 메뉴");
        MenuComponent cafeMenu = new Menu("카페 메뉴", "점심 메뉴");
        MenuComponent dinerMenu = new Menu("객체마을 식당 메뉴", "저녁 메뉴");

        allMenus.add(pancakeHouseMenu);
        allMenus.add(cafeMenu);
        allMenus.add(dinerMenu);


        pancakeHouseMenu.add(new MenuItem("팬케이크", "달콤한 팬케익", false, 3300));
        dinerMenu.add(new MenuItem("파스타", "마리나라 소스 스파게티", true, 3300));

//      저녁메뉴에 복합객체인 디저트메뉴를 추가한다.
        MenuComponent dessertMenu = new Menu("디저트 메뉴", "디저트 메뉴입니다");
        dinerMenu.add(dessertMenu);

        dessertMenu.add(new MenuItem("애플파이", "바삭한 애플파이", true, 1500));

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();
    }
}
