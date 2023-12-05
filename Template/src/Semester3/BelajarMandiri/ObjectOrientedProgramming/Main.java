package Semester3.BelajarMandiri.ObjectOrientedProgramming;

public class Main {
    public static void main(String[] args){
        // apapun yang ada di dalam sini akan di jalankan 
        // System.out.println("Hello!");

        // int a = 0;
        // if(a==0){
        //     System.out.println("a is zero!");
        // }
       
        //Panggil class PEN
        // Pen p = new Pen();
        // System.out.println(p.color);
        // System.out.println(p.point);
        // System.out.println(p.type);

        // System.out.println(Pen.clicked);
        // Pen.click();
        // System.out.println(Pen.clicked);

        //Panggil class HEADPHONE
        Headphone h = new Headphone();
        System.out.println(h.charge); // ini kita ngeprint attributnya
        System.out.println(h.color);
        System.out.println(h.controls);
        Headphone.powerOn();
        System.out.println(Headphone.power);
        Headphone.volumeUp(); // ini untuk ngerun method dari class headphone
        Headphone.volumeUp();
        Headphone.volumeUp();
        Headphone.volumeUp();
        System.out.println(Headphone.volume); // kalau ini untuk nampilin hasilnya

    }
    
}
