import javalib.worldimages.Posn;

public class CartPt extends Posn {

    CartPt(int x, int y) {
        super(x, y);
    }

    // produce a point moved by the given distance from this point
    CartPt moveBy(int dx, int dy) {
        return new CartPt(this.x + dx, this.y + dy);
    }

    // Compute the distance from this point to the given one
    double distTo(CartPt that) {
        return Math.sqrt((this.x - that.x) * (this.x - that.x)
                + (this.y - that.y) * (this.y - that.y));
    }
}
