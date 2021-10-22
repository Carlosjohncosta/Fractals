public class comNum {
	
    double real, imaginary;
    comNum(double x, double y) { //constructor for x and y coords (real and imaginary respectively)
        real = x;
        imaginary = y;
    }
    
    void Pow(int power, boolean abs) { //calculates power of current comNum.
        for (int i = 1; i < power; i++) { //loops until power is less than one (for powers higher than 2);
            double real = this.real;
            double imaginary = this.imaginary;
            this.real = Math.pow(real, 2) - Math.pow(imaginary, 2);
            if (!abs) { //changes calc depending on if real or absolute value is needed.
                this.imaginary = 2 * real * imaginary;
            } else {
                this.imaginary = -2 * Math.abs(real) * Math.abs(imaginary);
            }
        }
    }

    void Add(comNum addNum) { //adds current comNum to a comNum parsed in.
        this.real += addNum.real;
        this.imaginary += addNum.imaginary;
    }
    
    double absVal() { //calculates absolute value of point.
    	double a = Math.pow(this.real, 2);
        a += Math.pow(this.imaginary, 2);
        return Math.sqrt(a);
    }
    
    static double translate(String axis, double coord) { //translates pixel coord into complex num (static so it can be used outside of any instance)
        if (axis == "X") {
            return Fractal.xOffset + (coord - Fractal.width / 2) / ((Fractal.width * Fractal.zoom) / 4);
        } else if (axis == "Y") {
            return Fractal.yOffset + (coord - Fractal.height / 2) / -((Fractal.height * Fractal.zoom) / 2);
        } 
        return 0;
    }
}

