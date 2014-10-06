import javalib.worldimages.WorldImage;
import java.util.*;

public abstract class AObj {
    // The location of this Object
    CartPt loc;
    // The name of the image file for this Lily
    String name;
    // An ArrayList of this AObj
    ArrayList<AObj> Objects;

    Random random = new Random();

    AObj(CartPt loc, String name) {
        this.loc = loc;
        this.name = name;
    }

    // Moves an Object to the left or right
    public abstract AObj driveLeft();

    public abstract AObj driveRight();

    // Determines if the Object is a Car, Log, or Lily
    public abstract boolean isCar();

    public abstract boolean isLogs();

    public abstract boolean isLilies();

    // Produces an image of all Objects at this location
    public abstract WorldImage objImage();

    // Is the Object in the world?
    public abstract boolean outOfBounds(int HEIGHT, int WIDTH);

}
