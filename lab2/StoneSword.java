package lab2;

class StoneSword extends Sword{
    public Sword stoneSword = new Sword("Stone Sword", 20, 40, 5.0);


    public void protection(){
        System.out.println("Защита");
    }

    public int getAttackDamage(){
        return (int)(this.getDamage() * this.getBladeLength());
    }
}
