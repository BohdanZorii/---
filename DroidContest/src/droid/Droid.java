package droid;

import java.util.Arrays;
import java.util.Random;


public abstract class Droid {
    private final String name;
    private final int damage;
    private final int criticalDamageChance;
    private int armor;
    private int health, power, level;


    public Droid(String name, int level) {
        this.name = name;
        this.level=level;
        this.damage=5*level;
        this.criticalDamageChance =17;
        this.armor=8*level;
    }

     class Status{

        private Effect effects[];
        private int arrId;

        public static boolean isStunned;
        public static boolean powerIsBlocked;


        private Status(){
            effects=new Effect[1];
        }

        public void addEffect(Effect effect){
          if(isFull()){
              extendArray();
          }

          effects[arrId++]=effect;
        }
        public void deleteEffect(Effect effect){
            for (int i = 0; i < effects.length; i++) {
                if(effects[i]!=effect)   continue;

                if(i == effects.length-1) {
                    effects[i]=null;
                    break;
                }

                effects[i]=effects[i+1];
            }


        }

        private boolean isFull(){
            for (Effect effect:effects) {
                if(effect!=null)
                    return false;
            }
            return true;
        }
        private void extendArray(){
            effects = Arrays.copyOf(effects, effects.length+1);
        }

        private abstract class Effect {
            protected int duration;
            protected int intensity;

            public Effect(){}
            public Effect(int duration, int intensity) {
                this.duration = duration;
                this.intensity=intensity;
            }

            public int getIntensity() {
                return intensity;
            }
            public void setIntensity(int intensity) {
                this.intensity = intensity;
            }
            public int getDuration() {
                return duration;
            }
            public void setDuration(int duration) {
                this.duration = duration;
            }

            public abstract boolean affect();
        }


        private class SelfRepairing extends Effect {

            public SelfRepairing(int duration, int intensity) {
                super(duration, intensity);
            }

            public boolean affect(){
                if(duration>0){
                    health+=intensity;
                    System.out.println(name + " has just healed "+intensity+" hp.");
                    duration--;
                    return true;
                }else {
                    System.out.println(name + " : Self-repairing ended.");
                    return false;
                }

            }
        }

        private class Degeneration extends Effect {

            public Degeneration(int duration, int intensity) {
                super(duration, intensity);
            }

            public boolean affect(){
                if(duration>0){
                    health-=intensity;
                    System.out.println(name + " has just lost "+intensity+" hp due to degeneration.");
                    duration--;
                    return true;
                }else {
                    System.out.println(name + " : Degeneration ended.");
                    return false;
                }

            }
        }

        private class PowerGain extends Effect {

            public PowerGain(int duration, int intensity) {
                super(duration, intensity);
            }

            public boolean affect(){
                if(duration>0){
                    health-=intensity;
                    System.out.println(name + " has just gain "+intensity+" additional points of power.");
                    duration--;
                    return true;
                }else {
                    System.out.println(name + " : Power gain ended.");
                    return false;
                }

            }
        }

        private class PowerBlock extends Effect{


            public PowerBlock(int duration) {
                super.duration=duration;
                powerIsBlocked=true;
            }

            public boolean affect(){
                if(duration>0){
                    duration--;
                    return true;
                }else {
                    powerIsBlocked=false;
                    System.out.println(name + " : Power block ended.");
                    return false;
                }

            }

        }

        private class ArmorBoost extends Effect{

            public ArmorBoost(int duration, int intensity){
                super(duration, intensity);
                armor+=intensity;
            }

            public boolean affect(){
                if(duration>0){
                    duration--;
                    return true;
                }else {
                    armor-=intensity;
                    System.out.println(name + " : Armor boost ended.");
                    return false;
                }
            }

        }

        private class ArmorBreak extends Effect{
            public ArmorBreak(int duration, int intensity){
                super(duration, intensity);
                armor-=intensity;
            }

            public boolean affect(){
                if(duration>0){
                    duration--;
                    return true;
                }else {
                    armor+=intensity;
                    System.out.println(name + " : Armor break ended.");
                    return false;
                }
            }
        }

        private class Stun extends Effect {


            public Stun(int duration, int intensity) {
                super(duration, intensity);
            }


            public boolean affect(){
                if(duration>0){
                    Random chance = new Random(100);
                    if(intensity >= chance.nextInt()) {
                        isStunned = true;
                        System.out.println(name + " is not with us(stunned).");
                    }else{
                        isStunned = false;
                        System.out.println(name + " manages to fight back despite a stun.");
                    }

                    duration--;
                    return true;
                }else {
                    isStunned=false;
                    System.out.println(name + " : Stun ended.");
                    return false;
                }

            }
        }

    }



    public int Hit(){
        if(!Status.powerIsBlocked)
            power+=10;

        Random chance = new Random(100);
        if(criticalDamageChance >= chance.nextInt()){
            return damage*2;
        }else {
            return damage;
        }
    }

    public int getHit(int damage) {

        int actualDamage = (int)(damage*((100-armor)/100.0));
        health -= actualDamage;

        if (health < 0) {
            health = 0;
        }

        if(!Status.powerIsBlocked)
            power+=10;

        return actualDamage;
    }


    public abstract int useAbility();

    public int getDamage() {
        return damage;
    }
    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return health > 0;
    }



    @Override
    public String toString() {
        return name + "[ " + health+" hp, " + power + "% charged]";
    }


}
