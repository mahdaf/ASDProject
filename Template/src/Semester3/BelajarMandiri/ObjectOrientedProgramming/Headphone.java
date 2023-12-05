package Semester3.BelajarMandiri.ObjectOrientedProgramming;

public class Headphone {
    String charge = "Micro USB";
    String[] controls = {"power", "volume", "skip", "play/pause"};
    String color = "Red/black";

    static boolean power = false;
    static int volume=0;

    public static void powerOn(){
        power = true;
    }
    public static void powerOff(){
        power = false;
    }
    public static void volumeUp(){
        volume++;
    }
    public static void volumeDown(){
        volume--;
    }

}
