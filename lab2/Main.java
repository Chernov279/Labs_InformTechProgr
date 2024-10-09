package lab2;

public class Main {
    public static void main(String[] args) {
        Sword sword = new Sword();
        Bow bow = new Bow();
        MagicWand wand = new MagicWand();

        sword.showInfo();
        sword.attack();

        bow.showInfo();
        bow.attack();

        wand.showInfo();
        wand.attack();

        System.out.println("Total weapons created: " + Weapon.getWeaponCount());
    }
}
