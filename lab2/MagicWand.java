package lab2;

class MagicWand extends Weapon {
    private int manaCost;

    public MagicWand() {
        this("Basic Wand", 30, 1, 20);
    }

    public MagicWand(String name, int damage, int strength, int manaCost) {
        super(name, damage, strength);
        this.manaCost = manaCost;
    }

    @Override
    public void attack() {
        System.out.println(getName() + " casts a spell with " + getDamage() + " damage, using " + manaCost + " mana!");
    }

    // Геттер и сеттер для затрат маны
    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
}

