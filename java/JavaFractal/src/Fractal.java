import processing.core.*; //imports processing into project.

public class Fractal extends PApplet {
    public static void main(String[] args) { //initialises applet.
        PApplet.main("Fractal");
    }

    
    int maxIt = 1000; //maximum number of iterations.
    static double xOffset = 0; //X offset (real plain)
    static double yOffset = 0; //Y offset (imaginary plain)
    static double zoom = 0.8; //Zoom of fractal. 
    int power = 2; //power used in fractal (use 2 for mandelbbrot and burning ship)
    boolean abs = false; //if true, power will be calculated as an absolute value.
    boolean showJulia = false; //Shows julia set for given complex num bellow.
    comNum juliaVal = new comNum(0, -0.8);
    static int width; //width of screen
    static int height; //height of screen
    //static variables are used for comNum.translate.
    
    public void settings() { //initialises screen (settings is used as setup does not allow variable assignment for size.)
        width = (int)(displayWidth / 2);
        height = (int)(width / 2);
        size(width, height);
        //fullScreen();
    }

    public void setup() { //changes color to HSB mode.
        colorMode(HSB, 100);
        pixelDensity(1);
    }

    public void draw() { //draw loop, updates every cycle.
        for (int y = 0; y < height; y++) { //nested loop to check each pixel on screen.
            for (int x = 0; x < width; x++) {
                comNum point = new comNum(comNum.translate("X", x), 
                comNum.translate("Y", y)); //Creates new comNum, with x and y translated from pixel pos to complex num.
                
                int pointVal = fracCalc(point); //returns num of iterations until escape from abs value 2.
                int colorLoop = pointVal; //bellow is used to calculate color of pixel.
                float colorNum = maxIt / 30;
                while (colorLoop > maxIt / colorNum) {
                    colorLoop -= colorNum;
                }
                float hue = 100 * (colorLoop / (maxIt / colorNum));
                if (pointVal < maxIt) { 
                    set(x, y, color(hue, 100, 100)); //set to color if not in set.
                } else {
                    set(x, y, color(0, 0, 0)); //set to black if is in set.
                }
            }
        }
        noLoop(); //stops loop until loop() is called.
    }

    public void mousePressed() { //used to zoom in, translates pos of mouse and increases/decreases zoom.
    	xOffset = comNum.translate("X", mouseX);
        yOffset = comNum.translate("Y", mouseY);
        
        if (mouseButton == LEFT) {
            zoom *= 20;
        } else if (mouseButton == RIGHT) {
            zoom /= 20;
        }
        
        loop();
    }

    public void keyPressed() { //backspace resets values, s saves image.

        if (keyCode == BACKSPACE) {
        	reset();
        }
        if (key == 's' ) {
            saveFrame((xOffset) + " " + (yOffset) + "i " + Math.floor(zoom) + "x.png");
        }
        if(key == 'a') {
        	if (abs == true) {
        		abs = false;
        	} else {
        		abs = true;
        	}
        	reset();
        }
        if (key == 'j') {
        	if (showJulia == true) {
        		showJulia = false;
        	} else {
        		showJulia = true;
        	}
        	reset();
        }
    }

    int fracCalc(comNum point) { //algorithm to check divergence from abs val 2.
        comNum prev = new comNum(0, 0);
        if (showJulia) {
            prev = point;
        }
        for (int i = 0; i < maxIt; i++) {
            prev.Pow(power, abs);
            if (showJulia) {
                prev.Add(juliaVal);
            } else {
                prev.Add(point);
            }
            if (prev.absVal() >= 2) {
                return i;
            }
        }
        return maxIt;
    }
    
    void reset() {
    	xOffset = 0;
        yOffset = 0;
        zoom = 0.8;
        loop();
    }
} 