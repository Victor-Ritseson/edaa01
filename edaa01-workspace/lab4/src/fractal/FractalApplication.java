package fractal;

import mountain.*;


import koch.*;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(300);
		fractals[0] = new RecMountain(new Point(50,450), new Point(240,120), new Point(450,470), 30.00);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
