import javalib.worldimages.FromFileImage;
import javalib.worldimages.WorldImage;
import java.util.*;

public class Car extends AObj {
    boolean isCar = true;
    boolean isLog = false;
    boolean isLily = false;
    

    Car(CartPt loc, String name) {
        super(loc, name);
    }

    

    // Two methods that move this Car either left/right by 5
    public Car driveLeft() {
        return new Car(this.loc.moveBy(-5, 0), this.name);
    }

    public Car driveRight() {
        return new Car(this.loc.moveBy(5, 0), this.name);
    }

    // Produces an image of the Car at this location
    public WorldImage objImage() {
        return new FromFileImage(this.loc, this.name);
    }

   

}
