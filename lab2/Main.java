package lab2;

public class Main {
    public static void main(String[] args) {
        // Создание объектов
        Sword sword = new Sword();
        Bow bow = new Bow();
        MagicWand wand = new MagicWand();

        // Вывод информации об оружии и атака
        sword.showInfo();
        sword.attack();

        bow.showInfo();
        bow.attack();

        wand.showInfo();
        wand.attack();

        // Счетчик созданных объектов
        System.out.println("Total weapons created: " + Weapon.getWeaponCount());
    }
}
