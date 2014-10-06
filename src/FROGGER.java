import tester.*;

import java.util.*;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import javalib.colors.*;
import java.awt.Color;

public class FROGGER extends World {

    // FrogAlive that the player will control
    FrogAlive frogAlive = new FrogAlive(new CartPt(25, 375), "frogger.jpg", 0);

    // Individual Instances of each Object
    ArrayList<Car> carLane1 = new ArrayList<Car>();
    ArrayList<Car> carLane2 = new ArrayList<Car>();
    ArrayList<Log> logList1 = new ArrayList<Log>();
    ArrayList<Log> logList2 = new ArrayList<Log>();

    // Constants:
    Color black = new Color(0, 0, 0);
    Color green = new Color(0, 100, 0);
    Color gray = new Color(125);
    /*
     * Color WATER = new Color(30, 60, 200); Color YELLOW = new Color(255, 255,
     * 0);
     */

    int width = 400;
    int height = 400;

    FROGGER(FrogAlive frogAlive, ArrayList<Car> carLane1,
            ArrayList<Car> carLane2, ArrayList<Log> logList1,
            ArrayList<Log> logList2) {
        this.frogAlive = frogAlive;
        this.carLane1 = carLane1;
        this.carLane2 = carLane2;
        this.logList1 = logList1;
        this.logList2 = logList2;
    }

    WorldImage background = new RectangleImage(new Posn(width / 2, height / 2),
            width, height, black).overlayImages(new RectangleImage(new Posn(
            200, 225), 400, 50, green).overlayImages(new RectangleImage(
            new Posn(200, 300), 400, 100, Color.gray)
            .overlayImages(new RectangleImage(new Posn(200, 150), 400, 100,
                    Color.blue))));

    // Returns World. After 1 tick, all Objects move
    // while the FrogAlive does not.
    public World onTick() {

        if (this.frogAlive.loc.y < 125) {

            return new FROGGER(new FrogAlive(new CartPt(25, 375),
                    this.frogAlive.name, this.frogAlive.score + 1),
                    this.carLane1, this.carLane2, this.logList1,
                    this.logList2);
        }

        if (this.frogAlive.carCollided(carLane1)
                || this.frogAlive.carCollided(carLane2)) {
            return new FROGGER(new FrogAlive(new CartPt(25, 375),
                    this.frogAlive.name, this.frogAlive.score,
                    this.frogAlive.lives - 1), this.carLane1,
                    this.carLane2,
                    this.logList1, this.logList2);
        }

        return new FROGGER(this.frogOnLog(this.frogAlive.wrapper()),
                this.catchLeftCars(this.moveLeftCars(this.carLane1)),
                this.catchRightCars(this.moveRightCars(this.carLane2)),
                this.catchRightLogs(this.moveLeftLogs(this.logList1)),
                this.catchLeftLogs(this.moveRightLogs(this.logList2)));

    }

    public ArrayList<Log> moveLeftLogs(ArrayList<Log> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).driveLeft());
        }
        return list;
    }

    public ArrayList<Log> moveRightLogs(ArrayList<Log> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).driveRight());
        }
        return list;
    }

    // move frog based on the log
    public FrogAlive frogOnLog(FrogAlive frog) {
        if (onLog(frog, logList1)) {
            return new FrogAlive(new CartPt((frog.loc.x + currentLog(frog,
                    logList1).speed), frog.loc.y), frog.name, frog.score,
                    frog.lives);

        } else if (onLog(frog, logList2)) {
            return new FrogAlive(new CartPt((frog.loc.x + currentLog(frog,
                    logList2).speed), frog.loc.y), frog.name, frog.score,
                    frog.lives);

        } else
            return frog;
    }

    // returns the log that the frog is on
    public Log currentLog(FrogAlive frog, ArrayList<Log> logList) {
        for (int i = 0; i < logList.size(); i++) {
            if (frog.loc.y == logList.get(i).loc.y
                    && (Math.abs(frog.loc.x - logList.get(i).loc.x) < 50)) {
                return logList.get(i);
            }
        }
        return null;
    }

    // returns a boolean value seeing if the frog is on a log
    public boolean onLog(FrogAlive frog, ArrayList<Log> logList) {
        for (int i = 0; i < logList.size(); i++) {
            if (frog.loc.y == logList.get(i).loc.y
                    && (Math.abs(frog.loc.x - logList.get(i).loc.x) < 50)) {
                return true;
            }

        }
        return false;

    }

    // if a car is too far left this method puts it on the right
    public ArrayList<Car> catchLeftCars(ArrayList<Car> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).loc.x < 0) {
                list.get(i).loc = new CartPt(425, list.get(i).loc.y);
            }
        }
        return list;
    }

    // if a car is too far right this method puts it on the left
    public ArrayList<Car> catchRightCars(ArrayList<Car> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).loc.x > 400) {
                list.get(i).loc = new CartPt(-25, list.get(i).loc.y);
            }
        }
        return list;
    }

    // if a log is too far left this method puts it on the right
    public ArrayList<Log> catchLeftLogs(ArrayList<Log> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).loc.x < -50) {
                list.get(i).loc = new CartPt(450, list.get(i).loc.y);
            }
        }
        return list;
    }

    // if a log is too far right this method puts it on the left
    public ArrayList<Log> catchRightLogs(ArrayList<Log> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).loc.x > 450) {
                list.get(i).loc = new CartPt(-50, list.get(i).loc.y);
            }
        }
        return list;
    }

    // Returns a FROGGER in response to a key event. The player can move up,
    // down, left, or right (wrapping on sides). If the player lands on the
    // giant lily on the other side, score goes up 1+ and player is returned
    // to spawn.

    public World onKeyEvent(String ke) {
        if (ke.equals("left"))
            return new FROGGER(this.frogAlive.moveLeft(), this.carLane1,
                    this.carLane2, this.logList1, this.logList2);
        else if (ke.equals("right"))
            return new FROGGER(this.frogAlive.moveRight(), this.carLane1,
                    this.carLane2, this.logList1, this.logList2);
        else if (ke.equals("up"))
            return new FROGGER(this.frogAlive.moveUp(), this.carLane1,
                    this.carLane2, this.logList1, this.logList2);
        else if (ke.equals("down"))
            return new FROGGER(this.frogAlive.moveDown(), this.carLane1,
                    this.carLane2, this.logList1, this.logList2);
        else
            return this;
    }

    // move all cars to the left
    public ArrayList<Car> moveLeftCars(ArrayList<Car> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).driveLeft());
        }
        return list;
    }

    // move all cars to the right
    public ArrayList<Car> moveRightCars(ArrayList<Car> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).driveRight());
        }
        return list;
    }

    // Returns a new world image that is this RedvsBlue world.
    public WorldImage makeImage() {
        return allObjImage()
                .overlayImages(this.frogAlive.frogImage())
                .overlayImages(
                        new TextImage(new Posn(width - 50, 25), "Score:"
                                + this.frogAlive.score, Color.red))
                .overlayImages(
                        new TextImage(new Posn(width - 100, 25), "Lives:"
                                + this.frogAlive.lives, Color.red));
    }

    // Helper method that puts all car and log images onto one image
    public WorldImage allObjImage() {
        for (Car obj : this.carLane1) {
            this.background = this.background.overlayImages(obj.objImage());
        }
        for (Car obj : this.carLane2) {
            this.background = this.background.overlayImages(obj.objImage());
        }
        for (Log obj : this.logList1) {
            this.background = this.background.overlayImages(obj.objImage());
        }
        for (Log obj : this.logList2) {
            this.background = this.background.overlayImages(obj.objImage());
        }

        return this.background;
    }

    // Returns the last world image if the player collects a bluePill.
    public WorldImage lastImage(String s) {
        return this.makeImage().overlayImages(
                new TextImage(new Posn(width / 4, height / 4), s, Color.red));
    }

    // After each tick, if the player has crossed the road 5 times, if true,
    // game over.
    // NO LIVES COUNTER ONLY SCORE
    public WorldEnd worldEnds() {
        if (this.frogAlive.score == 5)
            return new WorldEnd(true, this.makeImage().overlayImages(
                    new TextImage(new Posn(width / 4, 50), "You win!",
                            Color.red)));
        else if (this.frogAlive.lives == 0) {
            return new WorldEnd(true, this.makeImage().overlayImages(
                    new TextImage(new Posn(width / 4, 50), "You lose!",
                            Color.red)));
        } else if (((this.frogAlive.loc.y == 175) || 
                this.frogAlive.loc.y == 125)
                && (!onLog(this.frogAlive, this.logList1))
                && (!onLog(this.frogAlive, this.logList2))) {
            return new WorldEnd(true, this.makeImage().overlayImages(
                    new TextImage(new Posn(width / 4, 50), "You lose!",
                            Color.red)));

        } else
            return (new WorldEnd(false, this.makeImage()));
    }

    // initiate the game world
    public FROGGER initWorld() {
        return new FROGGER(this.frogAlive, this.carLane1, this.carLane2,
                this.logList1, this.logList2);
    }
}
