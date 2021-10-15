let grid = []; //grid of all pixels on screen
let itteration = 0; //used for coloring
let zoom = 1.5; //zoom of the fractal
let xOffset = 0; //real plain offset
let yOffset = 0; //sets Y value 
let colorLoop = 0; //loop ror changing colors depending on itteration
let colour; 


function setup() {
	createCanvas(windowWidth, windowHeight);
	var width = windowWidth;
	var height = windowHeight;
	pixelDensity(1);
	gridArrayGen();
}


function draw() {
	update();
	itteration += 5;
	colorLoop > 2 ? colorLoop = 0 : 0;
	for (let y = 0; y < height; y++){
		for (let x = 0; x < width; x++){
			if (grid[x][y].inSet == true){
				set(x, y, 0);
			} else {
				if (grid[x][y].changed == false){
					switch (colorLoop){
						case 0:
							colour = color(0, 0, 255 - itteration);
							break;
						case 1:
							colour = color(0, 255 - itteration, 0);
							break;
						case 2:
							colour = color(255 - itteration, 0, 0);
							break;
					}
					set(x, y, colour);
					grid[x][y].changed = true;
				}
			}
		}
	}
	colorLoop ++;
	console.log(colorLoop)
	updatePixels(); //test
}


let gridArrayGen = () => {
	let arr = [];
	for (let x = 0; x < width; x++){
		for (let y = 0; y < height; y++){
			arr.push(gridProperties(x, y));
		}
		grid.push(arr);
		arr = [];
	}
}

let gridProperties = (x, y) => {
	return {
		_real: xOffset + (x  - width / 2) / (width / (8 / zoom)),
		_imaginary: yOffset + (y - height / 2) / -(height / (4 / zoom)),
		_prev: {
			//real: xOffset + (x  - width / 2) / (width / (8 / zoom)), //set values to zero if not using julia set
			//imaginary: yOffset + (y - height / 2) / -(height / (4 / zoom)), 
			real: 0, //set values to zero if not using julia set
			imaginary: 0,
		},
		changed: false,
		_inSet: true,
		get value() {
			return {
				real: this._real,
				imaginary: this._imaginary
			}
		},
		get inSet(){
			return this._inSet
		},
		itterate(){
			if (this.inSet == true){
				//this._prev = shipCalc(this._prev, this.value);
				this._prev = mandelbrotCalc(this._prev, this.value);
				//this._prev = miscCalc(this._prev);
				this._inSet = (checkDivergance(this._prev));
			}	
		}
	}
}


function checkDivergance(number){
	let distance = Math.sqrt(Math.pow(number.imaginary, 2) + Math.pow(number.real, 2));
	if (distance > 2){
		return false;
	} else {
		return true;
	}
}


let update = () => {
	for (let y = 0; y < height; y++){
		for (let x = 0; x < width; x++){
			grid[x][y].itterate();
		}
	}
}


function mandelbrotCalc(prev, current){
	let squarePrev = [Math.pow(prev.real, 2) - Math.pow(prev.imaginary, 2), 2 * (prev.real * prev.imaginary)]
	let realNums = squarePrev[0] + current.real;
	let imaginaryNums = squarePrev[1] + current.imaginary;
	return {
		real: realNums,
		imaginary: imaginaryNums
	}	
}


function shipCalc(prev, current){
	let squarePrev = [Math.pow(prev.real, 2) - Math.pow(prev.imaginary, 2), 2 * Math.abs(prev.real) * Math.abs(prev.imaginary) * -1];
	let realNums = (squarePrev[0] + current.real);
	let imaginaryNums = squarePrev[1] + current.imaginary;
	return {
		real: realNums,
		imaginary: imaginaryNums
	}	
}


function miscCalc(prev, current){ 
	let squarePrev = [Math.pow(prev.real, 2) - Math.pow(prev.imaginary, 2), 2 * (prev.real * prev.imaginary)]
	let realNums = squarePrev[0] - 0.3;
	let imaginaryNums = squarePrev[1] - 0.7;
	return {
		real: realNums,
		imaginary: imaginaryNums 	
	}	
}