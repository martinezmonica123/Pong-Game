import processing.core.PApplet;

/**
 * Ball class creates balls of predetermined size to be used 
 * in Pong class.
 * 
 * @author Monica Martinez
 * @version April 30, 2014
 *
 */
class Ball {

	//Position of Ball
	float xBallPos;
	float yBallPos;
	
	//Size of Ball
	float wBallSize;
	float hBallSize;
	
	//Speed of Ball
	float xBallSpeed;
	float yBallSpeed;

	PApplet canvas;
	
	/**
	 * Creates a ball object with predetermined values.
	 * 
	 * @param canvas
	 */
	public Ball( PApplet canvas ) {
		this.canvas = canvas;
		
		xBallPos = canvas.width/2; 
		yBallPos = canvas.width/4;
		
		wBallSize = 50;
		hBallSize = 50;
		
		xBallSpeed = canvas.random((float) 1.5, 3 );
		yBallSpeed = -canvas.random((float) 1.5, 3);
	}

	
	/**
	 * Method changes the position of the ball based 
	 * on it's position and speed(the increment).
	 * 
	 */
	public void move() {
		
			//increments ball position
			xBallPos += xBallSpeed;
			yBallPos += yBallSpeed;

			//if ball is moving "east"
			if (xBallPos > canvas.width) {
				xBallPos = canvas.width;
				xBallSpeed *= -1;
			}
			//if ball is moving "west"
			if (xBallPos < 0) {
				xBallPos = 0;
				xBallSpeed *= -1;
			}
			//if ball is moving "north"
			if (yBallPos > canvas.height) {
				yBallPos = canvas.height;
				yBallSpeed *= -1;
			}
			//if ball is moving "south"
			if (yBallPos < 0) {
				yBallPos = 0;
				yBallSpeed *= -1;
			}
			//draws ball
			this.canvas.ellipse(xBallPos, yBallPos, wBallSize, hBallSize);
		}
	}
	

