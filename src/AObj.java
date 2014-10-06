import javalib.worldimages.WorldImage;
import java.util.*;

public abstract class AObj {
    // The location of this Object
    CartPt loc;
    // The name of the image file for this Lily
    String name;
    

    Random random = new Random();

    AObj(CartPt loc, String name) {
        this.loc = loc;
        this.name = name;
    }

    // Moves an Object to the left or right
    public abstract AObj driveLeft();

    public abstract AObj driveRight();

   

    // Produces an image of all Objects at this location
    public abstract WorldImage objImage();

    

}
