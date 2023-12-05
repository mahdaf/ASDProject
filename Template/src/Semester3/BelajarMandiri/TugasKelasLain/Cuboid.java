package Semester3.BelajarMandiri.TugasKelasLain;
public class Cuboid implements GeometricObject{
    private double panjang;
    private double lebar;
    private double tinggi;
    private String objectName;

    public Cuboid(String objectName, double panjang, double lebar, double tinggi){
        this.objectName = objectName;
        this.panjang = panjang;
        this.lebar = lebar;
        this.tinggi = tinggi;
    }

    @Override
    public double getVolume() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getSurfaceObject() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String toString(){
        return "The Object is " + this.objectName + "[side="+this.panjang+","+this.lebar+","+this.tinggi+"]";
    }

    public double getPanjang(){
        return this.panjang;
    }
    public double getLebar(){
        return this.lebar;
    }
    public double getTinggi(){
        return this.tinggi;
    }
    public String getObjectName(){
        return this.objectName;
    }
}