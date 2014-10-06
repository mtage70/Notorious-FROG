import java.awt.Color;

import java.util.ArrayList;

import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;
import tester.Tester;

public class ExamplesFROGGER {
    // Constants:
    int width = 200;
    int height = 200;

    // An instance of FrogAlive
    FrogAlive frog = new FrogAlive(new CartPt(25, 375), "frogger.jpg", 0);

    // An instance of each type of data
    Car lane1Car1 = new Car(new CartPt(25, 325), "car.jpg");
    Car lane1Car2 = new Car(new CartPt(175, 325), "car.jpg");
    Car lane1Car3 = new Car(new CartPt(325, 325), "car.jpg");

    Car lane2Car1 = new Car(new CartPt(25, 275), "car.jpg");
    Car lane2Car2 = new Car(new CartPt(175, 275), "car.jpg");
    Car lane2Car3 = new Car(new CartPt(325, 275), "car.jpg");

    Log lane1Log1 = new Log(new CartPt(0, 175), "Log.png", 5);
    Log lane1Log2 = new Log(new CartPt(200, 175), "Log.png", 5);

    Log lane2Log1 = new Log(new CartPt(100, 125), "Log.png", -5);
    Log lane2Log2 = new Log(new CartPt(300, 125), "Log.png", -5);

    // A sample of an ArrayList<AObj> of each type of Object
    ArrayList<Car> cars1 = new ArrayList<Car>();
    ArrayList<Car> cars2 = new ArrayList<Car>();

    ArrayList<Log> logs1 = new ArrayList<Log>();
    ArrayList<Log> logs2 = new ArrayList<Log>();

    // Initialize the ArrayLists and concatenate them into the cars ArrayList
    void init() {
        cars1.add(lane1Car1);
        cars1.add(lane1Car2);
        cars1.add(lane1Car3);

        cars2.add(lane2Car1);
        cars2.add(lane2Car2);
        cars2.add(lane2Car3);

        logs1.add(lane1Log1);
        logs1.add(lane1Log2);

        logs2.add(lane2Log1);
        logs2.add(lane2Log2);

    }
    
    public void testInit(Tester t) {
        init();
        t.checkExpect(cars1.get(0), lane1Car1);
    }

    // An instance of a frogGER world
    FROGGER myWorld = new FROGGER(frog, cars1, cars2, logs1, logs2);
}
