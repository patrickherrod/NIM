package nim;

/*
 * Class the signifies the cpu player
 */
public class Computer {

	public Computer() {
		
	}
	
	/*
	 * Method that computes the number of sticks the cpu would like to take.
	 * @param sticks - the number of sticks in the pile
	 */
	public static int takeTurn(int sticks) {
		int holder = 10;
		if(sticks < 4) {		//if there are 3 or less sticks in the pile, then take that many number of sticks
			holder = sticks;
		}
		else {
			while(!(holder <= 3)) {				//if more the than three sticks, randomly pick 1-3
				holder = (int)(Math.random()*3);
			}
		}
		return holder;
	}
}
