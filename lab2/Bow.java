package lab2;

class Bow extends Weapon {
    private int arrowSpeed;

    public Bow() {
        this("Standard Bow", 20, 1, 10);
    }

    public Bow(String name, int damage, int strength, int arrowSpeed) {
        super(name, damage, strength);
        this.arrowSpeed = arrowSpeed;
    }

    @Override
    public void attack() {
        if (getStrength() > 0){
            setStrength(getStrength() - 1);
            System.out.println(getName() + " is shooting an arrow with " + getDamage() + " damage at speed " + arrowSpeed + " m/s!");
        }else{
            System.out.println(getName() + " with " + this.getDamage() + "damage is broken!");
        }
    }


    public int getArrowSpeed() {
        return arrowSpeed;
    }

    public void setArrowSpeed(int arrowSpeed) {
        this.arrowSpeed = arrowSpeed;
    }
}