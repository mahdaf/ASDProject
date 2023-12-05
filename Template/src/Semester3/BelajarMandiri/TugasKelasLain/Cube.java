package Semester3.BelajarMandiri.TugasKelasLain;

public class Cube implements GeometricObject{
    private String objectName;
    private double side;

    public Cube(String objectName, double side){
        this.objectName = objectName;
        this.side = side;
    }
    @Override
    public double getSurfaceObject() {
        // TODO Auto-generated method stub
        return 6*(this.side*this.side);
    }
    @Override
    public double getVolume() {
        // TODO Auto-generated method stub
        return this.side*this.side*this.side;
    }
    public String toString(){
        return "The Object is " + this.objectName + "[side="+this.side+"]";
    }    
    public double getSide(){
        return this.side;
    }
    public String getObjectName(){
        return this.objectName;
    }
    

}
