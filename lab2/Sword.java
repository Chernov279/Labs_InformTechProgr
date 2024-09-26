package lab2;

class Sword extends Weapon {
    private double bladeLength;

    public Sword() {
        this("Standard Sword", 10, 1, 1.2);
    }

    public Sword(String name, int damage, int strength, double bladeLength) {
        super(name, damage, strength);
        this.bladeLength = bladeLength;
    }

    @Override
    public void attack() {
        if (getStrength() > 0){
        setStrength(getStrength() - 1);
        System.out.println(getName() + " is slashing with " + this.getDamage() * getBladeLength() + " damage and strength is " + getStrength() + "!");
    }else{
        System.out.println(getName() + " with " + this.getDamage() * getBladeLength() + " is broken!");
        }
    }

    public double getBladeLength() {
        return bladeLength;
    }

    public void setBladeLength(double bladeLength) {
        this.bladeLength = bladeLength;
    }
}
