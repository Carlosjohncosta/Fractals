let xOffset = -1.5;
let yOffset = 0;
let zoom = 20;
let maxItt = 50;

function setup() {
	createCanvas(windowWidth, windowHeight);
	var width = windowWidth;
	var height = windowHeight;
    colorMode(HSB, 100);
}

function draw() {
    for (let y = 0; y < height; y++){
		for (let x = 0; x < width; x++){
            let pos = math.complex(xOffset + (x  - width / 2) / (width / (8 / zoom)), 
            yOffset + (y - height / 2) / -(height / (4 / zoom)));
            let pointVal = mandelbrotCalc(pos)
            let hue = 100 * (pointVal / maxItt);
            let c = color(hue, 100, 100);
            if (pointVal < maxItt) {
                stroke(c)
                point(x, y);
            } else {
                stroke(0);
                point(x, y);
            }
        }
    }
    noLoop();
}

function mandelbrotCalc(pos) {
    let temp = math.complex(0, 0);
    for (let i = 0; i < maxItt; i++) {
        temp = math.add(pos, math.multiply(temp, temp));
        if (checkDist(temp) >= 2) {
            return i;
        }
    }
    return maxItt;
}

let checkDist = val => {
    let a = val.re * val.re;
    let b = val.im * val.im;
    return Math.sqrt(a + b);
}