public class comNum {
	
    double real, imaginary;
    comNum(double x, double y) {
        real = x;
        imaginary = y;
    }

    void Pow(int power, boolean abs) {
        for (int i = 1; i < power; i++) {
            double real = this.real;
            double imaginary = this.imaginary;
            this.real = Math.pow(real, 2) - Math.pow(imaginary, 2);
            if (!abs) {
                this.imaginary = 2 * (real * imaginary);
            } else {
                this.imaginary = 2 * Math.abs(real) * -Math.abs(imaginary);
            }
        }
    }

    void Add(comNum addNum) {
        this.real += addNum.real;
        this.imaginary += addNum.imaginary;
    }
    
    double absVal() {
    	double a = Math.pow(this.real, 2);
        a += Math.pow(this.imaginary, 2);
        return Math.sqrt(a);
    }
}
