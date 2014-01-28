package nim;

import java.util.Scanner;

/*
 * Artificial Intelligence - Project 1
 * Patrick Herrod
 * August 30, 2012
 */

public class Game {
	public static int numSticks;		//variable to hold the number of sticks left in the pile
	public static int sticksTaken;		//variable to store how many stick picked up from a single turn
	public static int turn = 0;			//variable to determine whose turn it is
	public static String input;			//string to store user inputs
	public static Scanner sc = new Scanner(System.in);
	
	public Game() {
	}

	/*
	 * Main method the runs the basic game functions.
	 */
	public static void main(String[] args) {
		String newGame = "o";			//variable used to determine to start a new game or not
		System.out.println();
		System.out.println("\tWelcome to NIM.\n" +
				"In this version there are 10 sticks.\n" +
				"Each player has the option of grabbing 1, 2, or 3 sticks.\n" +
				"It will be the computer's turn to start.\n" +
				"Press enter to start a new game, and press enter to input a character or integer. ");
		sc.nextLine();		//wait for use to press enter
		do {
			newGame = "o";
			numSticks = 10;		//initialize the pile of sticks
			drawSticks();		//display sticks
			System.out.println();
			/*String confirm = "o";  Functionality for later that allows the user select who will go first	
			System.out.print("Would you like to go first? Y/N ");
			confirm = sc.next();
			while(!(confirm.equals("Y") || confirm.equals("y") || confirm.equals("N") || confirm.equals("n"))) {
				System.out.print("Please enter 'Y' or 'N'. ");
				confirm = sc.next();
			}
			System.out.println();
			if(confirm.equals("Y") || confirm.equals("y"))
				turn++;  */
			while(numSticks != 0){		//while sticks are still in the pile
				if(turn == 0) {			//cpu's turn
					sticksTaken = Computer.takeTurn(numSticks);
					if(sticksTaken == 1)		//different output based on 1 or multiple sticks
						System.out.println("The computer took " + sticksTaken + " stick.");
					else
						System.out.println("The computer took " + sticksTaken + " sticks.");
					numSticks -= sticksTaken;	//remove sticks from pile
					sticksTaken = 0;			//reset stick counter for each move
					drawSticks();				//redraw the pile of sticks
					System.out.println();
					turn++;						//signal user's turn
				}
				else {		//user's turn
					userTurn();					
					numSticks -= sticksTaken;	
					sticksTaken = 0;		
					drawSticks();
					System.out.println();
					turn--;						//signal cpu's turn
				}
			}
			//Once sticks are gone, the last player to take a turn wins
			if(turn == 0)	//user wins
				System.out.println("Congratulations you won!");
			else			//cpu wins
				System.out.println("I'm sorry you lost and the computer won.");
			//Prompt to ask for a new game
			while(!(newGame.equals("Y") || newGame.equals("y") || newGame.equals("N") || newGame.equals("n"))) {  //Keep looping if the user doesn't enter one of the four char values
				System.out.println();
				System.out.print("Would you like to play another game? Y/N ");
				newGame = sc.next();
			}
		} while(!(newGame.equals("N") || newGame.equals("n")));  	//If user decides not to play another game (inputs an 'n'/'N')
		System.out.println();
		System.out.println("Thanks for playing.");
	}
	
	/*
	 * Method that displays the remaining sticks in the console
	 */
	public static void drawSticks() {
		String sticks = "";
		for(int i = 0; i < numSticks; i++) {		//create a string that has the appropriate number of sticks "| " 			
			sticks = sticks + "| ";
		}
		System.out.println();
		System.out.print(sticks + "(" + numSticks + " sticks)" );		//print the sticks string and a digit to represent the number of remaining sticks
		System.out.println();
	}
	
	/*
	 * Method that allows the user to interact and input a desired number of sticks to grab
	 */
	public static void userTurn() {
		String confirm = "n";		//confimation character, used for yes or no prompt
		int holder = 0;				//variable for the desired number of sticks to be taken
		int flag = 0;				//flag that allows a different prompt for sticks after the first pass of the loop
		System.out.print("You're turn! sticks: ");
		while(!(confirm.equals("Y") || confirm.equals("y"))) {		//while the user hasn't confirmed their selection
			flag++;
			if(!(confirm.equals("N") || confirm.equals("n"))){		//if the does not enter a 'y' and does not enter an 'n'
				System.out.println();
				System.out.println("Anything other than a 'Y' is interpretted as 'N'.");
			}
			if(flag > 1){					//prompt for the number of sticks after the first pass through the loop
				System.out.println();
				System.out.print("sticks: ");
			}
			input = sc.next();
			while(!(input.equals("1") || input.equals("2") || input.equals("3"))){	//if the user does not enter a 1,2 or 3
				System.out.println();
				System.out.print("You can only input a 1, 2 or 3. ");
				input = sc.next();			//grab the next input
			}
			holder = Integer.parseInt(input);  //once the input is a 1,2 or 3, parse it and place it in holder
			while (numSticks < holder) {		//if the user wants to take more than the available number of sticks
				if (numSticks == 1)				//selection for the proper error message
					System.out.println("There is only 1 left to take.");
				else
					System.out.println("There are only " + numSticks + " left to take.");
				System.out.println();
				System.out.print("Please enter another number. ");
				input = sc.next();
				holder = Integer.parseInt(input);
			}
			System.out.println();
			System.out.print("Are you sure you want to that many sticks: " + input + " ? Y/N ");  	//prompt for the user to confirm their move
			confirm = sc.next();
			
		}
		sticksTaken = holder;		//passing the number of sticks to the main game method, through the global variable
	}
}
