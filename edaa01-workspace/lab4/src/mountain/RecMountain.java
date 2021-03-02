package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class RecMountain extends Fractal{
	private Point a;
	private Point b;
	private Point c;
	private double dev;
	private HashMap<Side, Point> sides = new HashMap();
	
	public RecMountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
	}
	/**
	 * Returns the title.
	 * @return the title
	 */
    @Override
	public String getTitle() {
		return "Bergfraktal";
	}
	
    /** Draws the MountainFractal.  
	 * @param turtle the turtle graphic object
	 */
    @Override
	public void draw(TurtleGraphics turtle) {
    	fractalLine(turtle, order ,a, b, c, dev);
	}

	/* 
	 * Reursive method: Draws a recursive line of the triangle. 
	 */
	private void fractalLine(TurtleGraphics turtle, int order , Point a, Point b, Point c, double dev) {
		if(order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
			
		} else {
			Point ab = newSide(a, b, dev);
			Point ac = newSide(a, c, dev);
			Point bc = newSide(b, c, dev);
			
					
			fractalLine(turtle,order -1, a, ab, ac, dev/2);
			fractalLine(turtle,order -1, ab, b, bc, dev/2);
			fractalLine(turtle,order -1, ac, bc, c, dev/2);
			fractalLine(turtle,order -1, bc, ac, ab, dev/2);
			
		}
		
	}
	
	private Point newSide(Point a, Point b, double dev) {
		Side newSide = new Side(a,b);
		if(sides.containsKey(newSide)) {
			Point mid = sides.get(newSide);
			sides.remove(newSide);
			return mid;
		}
		else {
			int random = (int) new RandomUtilities().randFunc(dev);
			Point newPoint = new Point((a.getX() + b.getX())/2, ((a.getY() + b.getY())/2) + random);
			sides.put(newSide, newPoint);
			
			return (newPoint);
		}
	}
	
	
	

}
