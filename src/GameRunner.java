import javalib.worldcanvas.WorldCanvas;
import javalib.worldimages.WorldImage;

public class GameRunner {

    // Using the written examples
    ExamplesFROGGER eFROG = new ExamplesFROGGER();

    // Canvas
    WorldCanvas c1 = new WorldCanvas(400, 800);
    WorldCanvas c2 = new WorldCanvas(400, 800);

    // Image generation of examples
    WorldImage frogImage = this.eFROG.frog.frogImage();
    WorldImage carImage = this.eFROG.lane1Car1.objImage();
    WorldImage logImage = this.eFROG.lane1Log1.objImage();

    // Make world image
    WorldImage worldImage = this.eFROG.myWorld.makeImage();

    // Show the player and the examples of Objects on the canvas
    /*
     * boolean makeDrawing1 = c1.show() && c1.drawImage(this.frogImage) &&
     * c1.drawImage(this.carImage) && c1.drawImage(this.logImage);
     * 
     * // Show the world boolean makeDrawing2 = c2.show() &&
     * c2.drawImage(this.worldImage);
     */

    // Run the game
    boolean run() {
        eFROG.init();
        return this.eFROG.myWorld.bigBang(400, 400, .1);
    }

    // Main method to run FROGGER
    public static void main(String[] argv) {
        GameRunner frogger = new GameRunner();
        frogger.run();
    }
}
