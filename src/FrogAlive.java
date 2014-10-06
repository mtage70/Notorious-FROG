import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javalib.worldimages.FromFileImage;
import javalib.worldimages.WorldImage;

public class FrogAlive {
    // The location of this Car
    CartPt loc;
    // The name of the image file for this Car
    String name;
    // The score of the player
    int score = 0;
    int lives = 3;

    // Instance of Random
    Random random = new Random();

    FrogAlive(CartPt loc, String name, int score) {
        this.loc = loc;
        this.name = name;
        this.score = score;
    }

    FrogAlive(CartPt loc, String name, int score, int lives) {
        this.loc = loc;
        this.name = name;
        this.score = score;
        this.lives = lives;
    }

    // Produce a new ship 3 pixels to the left from this ship
    FrogAlive moveLeft() {
        return new FrogAlive(this.loc.moveBy(-50, 0), 
                this.name, this.score,
                this.lives);
    }

    // Produce a new ship 3 pixels to the right from this ship
    FrogAlive moveRight() {
        return new FrogAlive(this.loc.moveBy(50, 0), 
                this.name, this.score,
                this.lives);
    }

    // Moves the ship up 10 pixels
    FrogAlive moveUp() {
        return new FrogAlive(this.loc.moveBy(0, -50),
                this.name, this.score,
                this.lives);
    }

    // Moves the ship down 10 pixels
    FrogAlive moveDown() {
        return new FrogAlive(this.loc.moveBy(0, 50),
                this.name, this.score,
                this.lives);
    }

    // If the player is on a log/lily, move at Object's speed
    FrogAlive driveLeft() {
        return new FrogAlive(this.loc.moveBy(-5, 0),
                this.name, this.score,
                this.lives);
    }

    FrogAlive driveRight() {
        return new FrogAlive(this.loc.moveBy(5, 0),
                this.name, this.score,
                this.lives);
    }

    // Determines if the FrogAlive has collided with a car in the list
    boolean carCollided(ArrayList<Car> list) {

        Rectangle frog = new Rectangle(this.loc.x - 25,
                this.loc.y - 25, 50, 50);
        Rectangle car;

        for (int i = 0; i < list.size(); i += 1) {
            car = new Rectangle(list.get(i).loc.x - 25, list.get(i).loc.y - 25,
                    50, 50);

            if (frog.intersects(car)) {
                return true;

            }
        }

        return false;
    }

    // If the FrogAlive has crossed out of bounds, either above/below the
    // screen or over the sides.
    public FrogAlive wrapper() {
        if (this.loc.x < 0) {
            return new FrogAlive(new CartPt(25, this.loc.y), this.name,
                    this.score, this.lives);
        } else if (this.loc.x > 400) {
            return new FrogAlive(new CartPt(375, this.loc.y), this.name,
                    this.score, this.lives);
        } else if (this.loc.y > 400) {
            return new FrogAlive(new CartPt(this.loc.x, 375), this.name,
                    this.score, this.lives);
        } else if (this.loc.y < 0) {
            this.score += 1;
            return new FrogAlive(new CartPt(25, 375), this.name, this.score,
                    this.lives);
        } else {
            return this;
        }
    }

    // Produces an image of the FrogAlive at this location
    public WorldImage frogImage() {
        return new FromFileImage(this.loc, this.name);
    }
}
