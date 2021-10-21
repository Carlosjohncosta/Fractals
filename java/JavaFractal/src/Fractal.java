import processing.core.*;
public class Fractal extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Fractal");
    }
    
    int maxItt = 1000;
    double xOffset = 0;
    double yOffset = 0;
    double zoom = 0.8; 
    int power = 2;
    boolean abs = false;
    boolean showJulia = false;
    comNum juliaVal = new comNum(-0.7269, 0.1889);
    int width;
    int height;

    public void settings() {
        width = (int)(displayWidth / 2);
        height = (int)(width / 2);
        size(width, height);
        //fullScreen();
    }

    public void setup() {
        colorMode(HSB, 100);
        pixelDensity(1);
    }

    public void draw() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                comNum point = new comNum(translate("X", x), translate("Y", y));
                int pointVal = fracCalc(point);
                int colorLoop = pointVal;
                float colorNum = maxItt / 30;
                while (colorLoop > maxItt / colorNum) {
                    colorLoop -= colorNum;
                }
                float hue = 100 * (colorLoop / (maxItt / colorNum));
                if (pointVal < maxItt) {
                    set(x, y, color(hue, 100, 100));
                } else {
                    set(x, y, color(0, 0, 0));
                }
            }
        }
        noLoop();
    }

    public void mousePressed() {
        if (mouseButton == LEFT) {
            xOffset = translate("X", mouseX);
            yOffset = translate("Y", mouseY);
            zoom *= 20;
            loop();
        } else if (mouseButton == RIGHT) {
            xOffset = translate("X", mouseX);
            yOffset = translate("Y", mouseY);
            zoom /= 20;
            loop();
        }
    }

    public void keyPressed() {
    	String s = "s";
        if (keyCode == BACKSPACE) {
        	xOffset = 0;
            yOffset = 0;
            zoom = 0.8;
            loop();
        }
        if (key == s.charAt(0)) {
            saveFrame((xOffset) + " " + (yOffset) + "i " + Math.floor(zoom) + "x.png");
        }
    }

    int fracCalc(comNum point) {
        comNum prev = new comNum(0, 0);
        if (showJulia) {
            prev = point;
        }
        for (int i = 0; i < maxItt; i++) {
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
        return maxItt;
    }


    double translate(String axis, double coord) {
        if (axis == "X") {
            return xOffset + (coord - width / 2) / ((width * zoom) / 4);
        } else if (axis == "Y") {
            return yOffset + (coord - height / 2) / -((height * zoom) / 2);
        } else {
            return 0;
        }
    }
}