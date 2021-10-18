let xOffset = 0;
let yOffset = 0;
let zoom = 1;
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
            let pos = {
                real: xOffset + (x  - width / 2) / (width / (8 / zoom)),
                imaginary: yOffset + (y - height / 2) / - (height / (4 / zoom))
            };
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
    let temp = {
        real: 0,
        imaginary: 0
    };
    for (let i = 0; i < maxItt; i++) {
        temp = comPow(temp, 2);
        temp = comAdd(pos, temp);
        if (checkDist(temp) >= 2) {
            return i;
        }
    }
    return maxItt;
}

let checkDist = val => {
    let a = val.real * val.real;
    let b = val.imaginary * val.imaginary;
    return Math.sqrt(a + b);
}

let comPow = (comNum, power) => {
    let square = { 
        real: Math.pow(comNum.real, 2) - Math.pow(comNum.imaginary, 2), 
        imaginary: 2 * (comNum.real * comNum.imaginary)
    };
    if(power > 1) {
        return comPow(square, power - 1);
    } else {
        return comNum;
    };
}

let comAdd = (a, b) => {
    return {
        real: a.real + b.real,
        imaginary: a.imaginary + b.imaginary
    }
}