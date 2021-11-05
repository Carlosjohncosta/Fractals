import processing.core.*; //imports processing core.

public class Langton extends PApplet{
	
	public static void main(String[] args) {
		PApplet.main("Langton"); //boilerplate shit.
	}
	//static ints used for use in Ants class.
	static int height = 100; //height of grid.
	static int length = 100; //length of grid.
	static int[][] grid = new int[length][height]; //2D array storing each cell.
	int cellSize = 3; //size of each cell (in pixels).
	int centX = (int)(height/2); //Centre X coord.
	int centY = (int)(length/2); //Centre Y coord.
	boolean running = true;
	
	Ant[] ants = new Ant[] //Array of ant objects. (x position, y position, direction).
	{
		//new Ant(centX, centY, 2),
		new Ant(centX - 7, centY, 0),
		new Ant(centX + 7, centY + 1, 2),
	};
	
	public void settings() { //initialises size of screen depending on size of cell.
		size(length * cellSize, height * cellSize);
	}
	
	public void setup() { //Only used for frame rate.
		frameRate(100000);
	}
	
	public void keyPressed() {
		if (running) {
			running = false;
		}  else {
			running = true;
		}
	}
	
	public void draw() { //Draw loop.
		drawScreen(); //Separate function as draw() is just a loop and I may want to use other functions.
	}
	
	void drawScreen() {
		if (running) {
			background(255);
			for (int y = 0; y < height; y++) { //nested array to display each cell in grid.
				for (int x = 0; x < length; x++) {
					if (grid[x][y] == 1) { //Checks if position in grid = 1.
						fill(0);
						square(x * cellSize, y* cellSize,  cellSize);
					}
				}
			}
			fill(255, 0, 0);
			for (int i = 0; i < ants.length; i++) { //Loops through all Ant objects in ants array.
				square(ants[i].xPos * cellSize, ants[i].yPos * cellSize, cellSize);
				ants[i].nextPos();
			}
			}
	}

}
