package lab2;

abstract class Weapon {
    private String name;
    private int damage;
    private int strength;

    private static int weaponCount = 0;

    public Weapon() {
        this("Unnamed Weapon", 0, 1);
    }

    public Weapon(String name, int damage, int strength) {
        this.name = name;
        this.damage = damage;
        this.strength = strength;
        weaponCount++;
    }

    public abstract void attack();

    public void showInfo() {
        System.out.println("Name: " + name + ", Damage: " + damage + ", Weight: " + strength);
    }

    public String getName() {
        return name;
    }
    public int getDamage() {
        return damage;
    }
    public int getStrength() {
        return strength;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }


    public static int getWeaponCount() {
        return weaponCount;
    }
}
