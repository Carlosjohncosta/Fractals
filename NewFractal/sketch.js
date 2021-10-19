let xOffset = 0;
let yOffset = 0;
let zoom = 1.5;
let maxItt = 1000;
let nextFrame = true;
let showJulia = false;
let calcAbs = false;
let width;
let height;
let juliaVal = {
    real: 0,
    imaginary: -0.8
};

function setup() {
    width = windowWidth / 1.5;
    height = windowHeight / 1.5;
	createCanvas(width, height);
    background(200);
    colorMode(HSB, 100);
}

function draw() {
    if (nextFrame) { 
    for (let y = 0; y < height; y++){
		for (let x = 0; x < width; x++){
            let pos = comNumGen(translate("X", x), translate("Y", y));
            let pointVal = mandelbrotCalc(pos);
            let colorLoop = pointVal;
            let colorNum = maxItt / 30;
            while (colorLoop > maxItt / colorNum) {
                colorLoop -= colorNum
            }
            let hue = 100 * (colorLoop / (maxItt / colorNum));
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
    nextFrame = false;
    }
}

function mouseClicked() {
    xOffset = translate("X", mouseX);
    yOffset = translate("Y", mouseY);
    zoom *= 20;
    nextFrame = true;
}

function keyPressed() {
    zoom /= 20;
    nextFrame = true;
}


function mandelbrotCalc(pos) {
    let temp = comNumGen(0, 0);
    showJulia ? temp = pos : 0;
    for (let i = 0; i < maxItt; i++) {
        temp = comPow(temp, 2, calcAbs);
        showJulia ? temp = comAdd(juliaVal, temp) : temp = comAdd(pos, temp);
        if (checkDist(temp) >= 2) {
            return i;
        }
    }
    return maxItt;
}

let checkDist = val => {
    let a = Math.pow(val.real, 2);
    let b = Math.pow(val.imaginary, 2);
    return Math.sqrt(a + b);
}

function comPow(comNum, power, abs = false) {
    let square;
    if (abs == false) {
        square = comNumGen(Math.pow(comNum.real, 2) - Math.pow(comNum.imaginary, 2),
        2 * (comNum.real * comNum.imaginary));
    } else {
        square = comNumGen(Math.pow(comNum.real, 2) - Math.pow(comNum.imaginary, 2),
        2 * Math.abs(comNum.real) * -Math.abs(comNum.imaginary));
    }
    if (power > 1) {
        return comPow(square, power - 1);
    } else {
        return comNum;
    };
}

let comAdd = (a, b) => comNumGen(a.real + b.real, a.imaginary + b.imaginary);

let comNumGen = (real, imaginary) => {
    return {
        real, 
        imaginary
    }
};

let translate = (axis, coord) => {
    if (axis == "X") {
        return xOffset + (coord  - width / 2) / (width / (8 / zoom));
    } else if (axis == "Y") {
        return yOffset + (coord - height / 2) / - (height / (4 / zoom));
    }
}