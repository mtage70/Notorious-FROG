import javalib.worldimages.FromFileImage;
import javalib.worldimages.WorldImage;
import java.util.*;

public class Log extends AObj {
    boolean isCar = false;
    boolean isLog = true;
    boolean isLily = false;
    int speed;

   
    

    Log(CartPt loc, String name, int speed) {
        super(loc, name);
        this.speed = speed;
    }

    // Determines if the Object is a Car, Logs, or Lilies.
    public boolean isCar() {
        return this.isCar;
    }

    public boolean isLogs() {
        return this.isLog;
    }

    public boolean isLilies() {
        return this.isLily;
    }

    // Two methods that move this Log either left/right by 5
    public Log driveLeft() {
        return new Log(this.loc.moveBy(speed, 0), this.name, this.speed);
    }

    public Log driveRight() {
        return new Log(this.loc.moveBy(speed, 0), this.name, this.speed);
    }

    // Produces an image of the Log at this location
    public WorldImage objImage() {
        return new FromFileImage(this.loc, this.name);
    }

    // Is the Log out of the world?
    public boolean outOfBounds(int w, int h) {
        return (this.loc.x >= w || this.loc.x <= w);
    }

}