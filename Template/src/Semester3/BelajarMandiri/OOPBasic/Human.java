package Semester3.BelajarMandiri.OOPBasic;

public class Human {
    String name;
    int age;
    int height;
    String eyeColor;

    public Human(){
        
    }

    public void speak(){
        System.out.println("hello, my name is " + name);
        System.out.println("I am "+ age + " years old");
        System.out.println("I am "+ height+ " cm tall");
        System.out.print("My eye color is " + eyeColor);
    }
    public void eat(){
        System.out.println("eating...");
    }
    public void walk(){
        System.out.println("walking...");
    }
    public void work(){
        System.out.println("working...");
    }
}
