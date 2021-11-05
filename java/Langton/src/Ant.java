 public class Ant {
	int xPos; //x position of ant.
	int yPos; //y position of ant.
	int direction; //direction of ant.
	Ant(int x, int y, int dir) { //constructor.
		this.xPos = x;
		this.yPos = y;
		this.direction = dir;
	}
	
	void nextPos() { //Calculates new position of ant.
		//All variables bellow are temporary versions of current values. 
		int currGridVal = Langton.grid[this.xPos][this.yPos];
		int tempX = this.xPos;
		int tempY = this.yPos;
		if (currGridVal == 0) { //I can't be arsed to explain, it just works lmao.
			switch (this.direction) {
				case 0:
					this.yPos ++;
					this.direction = 3;
					break;
				case 1:
					this.xPos ++;
					this.direction = 0;
					break;
				case 2:
					this.yPos --;
					this.direction = 1;
					break;
				case 3:
					this.xPos --;
					this.direction = 2;
					break;
			}
			Langton.grid[tempX][tempY] = 1;
		} else {
			switch (this.direction) {
				case 0:
					this.yPos --;
					this.direction = 1;
					break;
				case 1:
					this.xPos --;
					this.direction = 2;
					break;
				case 2:
					this.yPos ++;
					this.direction = 3;
					break;
				case 3:
					this.xPos ++;
					this.direction = 0;
					break;
		}
		Langton.grid[tempX][tempY] = 0;
		}
	}
}
