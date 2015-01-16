import processing.core.PApplet;

/**
 * Implements the Ball class to create a game of Pong. 
 * If the paddle is able to catch the ball a multiple of 10 times, 
 * the number of balls on the screen increases (max of 5.)
 * Attempts to change the balls direction if two or in the same 
 * position/collision course.
 * 
 * @author Monica Martinez
 * @version April 30, 2014
 *
 */
public class Pong extends PApplet {

	// dimensions of the canvas
	int xMax = 700;
	int yMax = 500;

	// Properties of Paddle
	float xPaddleSize = 200;
	float yPaddleSize = 5;
	float xPaddleIncr = 6;
	float xPaddlePos = xMax / 2 - xPaddleSize / 2;
	float yPaddlePos = yMax - yPaddleSize;

	// Game Setting
	boolean gameOn = true;
	int score = 0;

	// Ball Instances
	Ball[] balls = new Ball[5];

	int numBalls = 1;
	int ballCounter = 0;

	/* (non-Javadoc)
	 * @see processing.core.PApplet#setup()
	 */
	public void setup() {
		size(xMax, yMax);
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new Ball(this);
		}
	}

	/* (non-Javadoc)
	 * @see processing.core.PApplet#draw()
	 */
	public void draw() {

		background(135,206,235);
		stroke(255, 255, 255);
		fill(255, 255, 255);
		rect(0, 0, 5, yMax);
		rect(xMax - 5, 0, 5, yMax);
		rect(0, 0, xMax, 5);

		//creates Paddle
		rect(xPaddlePos, yPaddlePos, xPaddleSize, yPaddleSize);

		//Keeps track of user-controlled paddle movement
		if (keyPressed && key == CODED) {
			if (keyCode == LEFT)
				xPaddlePos = xPaddlePos - xPaddleIncr;
			else if (keyCode == RIGHT)
				xPaddlePos = xPaddlePos + xPaddleIncr;
			if (xPaddlePos < 0)
				xPaddlePos = 0;
			else if (xPaddlePos + xPaddleSize > xMax)
				xPaddlePos = xMax - xPaddleSize;
		}

		if (gameOn) {
			//Moves the number of required balls
			for (int i = 0; i < numBalls; i++) {
				balls[i].move();
				
//DOES NOT WORK!!! ... Checks for "collision" and changes the two balls speed if there is a collision			
				if (numBalls >= 2) {
					for (int j = 0; j < numBalls; j++) {
						for (int k = 1; k < numBalls; k++) {							
							//checks to see if two ball objects are in the exact same position	
							//Use distance formula
							if (Math.sqrt((Math.pow((balls[i].xBallPos - balls[k].xBallPos),2 )) 
									+ (Math.pow((balls[i].yBallPos - balls[k].yBallPos),2 ))) <= 50) {
								//changes the direction/position of the balls
								balls[i].xBallSpeed *= -1;

							}
						}
					}	
				}
				
				
				//if the ball is moving "south"
				if (balls[i].yBallPos >= yMax - yPaddleSize) {
					// check if the rectangle is there
					if (balls[i].xBallPos >= xPaddlePos && balls[i].xBallPos <= xPaddlePos + xPaddleSize) {
						balls[i].yBallSpeed = -balls[i].yBallSpeed;
						//updates score if ball caught
						score++;
						ballCounter++;
						
						//if number of times ball caught = multiple of 10 increase ball count
						if (ballCounter % 10 == 0) {
							numBalls++;
						}
					} else 
						gameOn = false;
				}
			}
		} else {
			//Display if game is over
			textAlign(CENTER);
			textSize(26);
			text("Your Score: "+ score + "\n\n\n\n" +"The Ball Is Lost!\n" + "Please Try Again Soon.", xMax/2, yMax/5);
		}
	}
}





