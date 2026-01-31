import java.util.*;
import java.io.*;
public class ScoundrelProject{
	
	public int terminalWidth = 120;//my terminal is set up to be 120 characters long. I assume this is default for most computers, there's an option to change it.
	public int terminalHeight =30;//I use this once lmao
	
	private int deckType=0;
	private int maxHP =20;
	private Card[] deck;
	private boolean usingSeed = false;
	private int seed;

	private boolean IS_DEBUGGING = false;
	
	File highscore = new File("highscores.txt");
	
	void main()
	{
		IO.println("test");
		clearScreen();
		titleScreen();
		mainMenu();
	}
	
	public void titleScreen()
	{
		clearScreen();
		printCentered("SCOUNDREL");
		printCentered("A game project by Doran Loescher");
		for(int i=0;i<terminalHeight-4;i++)
		{
			IO.println();
		}
		printCentered("Press enter to continue");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();// Halts the code until enter is pressed. This totally wont cause any problems later and is definitely the best way to do this.
	}
	
	private void printCentered(String str)
	{
		for (int i=0;i<terminalWidth/2-(str.length()/2);i++)
		{
			IO.print(" ");
		}
		IO.println(str);
	}
	
	private void mainMenu()
	{
		Scanner scan = new Scanner(System.in);
		int choice;
		do{
			choice = 0;
			clearScreen();
			printCentered("Scoundrel");
			IO.println("1- Start Game");
			IO.println("2- Options");
			IO.println("3- Highscores");
			IO.println("4- Extras");
			IO.println("0- Exit program");
			IO.println();
			
			try{
				choice = scan.nextInt();
			}
			catch (Exception e)
			{
				scan.nextLine();
				clearScreen();
				printCentered("Invalid menu option or other error");
				printCentered("Press enter to continue.");
				choice = -1;
				//scan.nextLine();
			}
			scan.nextLine();//empties the line from the scanner since nextInt keeps it there.
			
			if (choice !=0)//processes the menu options using switch cases
			{
				switch (choice)
				{
					case 1:
						//IO.println("test1 success");
						//scan.nextLine();
						if (usingSeed)
							startGame(seed);
						else
							startGame();
						break;
					case 2:
						//IO.println("test2 success");
						//scan.nextLine();
						optionsMenu();
						break;
					case 3:
						showHighscores();
						break;
					case 4:
						extras();
						break;
				}
				
			}
		}while (choice !=0);
	}
	
	private void optionsMenu()
	{
		boolean done = false;
		while(!done)
		{
			clearScreen();
			printCentered("Scoundrel");
			printCentered("options");
			IO.println("1- Change deck");
			IO.println("2- Change Max HP");
			IO.println("3- Change Random Seed");
			IO.println("4- Change Terminal width");
			IO.println("0- Back");
			IO.println();
			
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			scan = new Scanner(line);
			if (scan.hasNextInt())
			{
				switch(scan.nextInt())//working on the options menu!
				{
					case 1:
						optionMenuDeck();
						break;
					case 2:
						optionMenuHP();
						break;
					case 3:
						optionMenuSeed();
						break;
					case 4:
						optionMenuTerm();
						break;
					case 0:
						done = true;
				}
			}
		}
	}
	private void optionMenuDeck()
	{
		boolean done = false;
		while (!done)
		{
			clearScreen();
			printCentered("Scoundrel");
			printCentered("available decks");
			IO.println("1- Standard deck\n\tYour standard scoundrel deck. all Clubs and Spades, only number Hearts and Diamonds");
			IO.println();
			IO.println("2- Hoard deck\n\tFor when you want to fight the world. More monsters, but they're weaker.");
			IO.println();
			IO.println("3- Dragon deck\n\tFor whne you want to fight the big guys. less monsters, but they're all faces.");
			IO.println();
			IO.println("4- Berzerker deck\n\tYour standard scoundrel deck, but all the Potions are Swords. No healing, only war.");
			IO.println();
			IO.println("5- Pacifist deck\n\tYour standard scoundrel deck, but all Swords are Potions. Why attack when you can heal?");
			IO.println();
			IO.println("6- Max Power deck\n\t All monsters are aces. All swords and potions are 10s.");
			IO.println();
			IO.println("0- Back");
			IO.println();
			
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			scan = new Scanner(line);
			if (scan.hasNextInt())
			{
				switch(scan.nextInt())
				{
					case 0: done = true;
						break;
					case 1: 
						deckType =0;
						clearScreen();
						printCentered("Deck Type changed!");			//I copy and pasted a lot here. I probably could have split it into it's own tiny method.
						IO.println("Press enter to continue");
						scan = new Scanner(System.in);
						scan.nextLine();
						done = true;
						break;
					case 2: 
						deckType =1;
						clearScreen();
						printCentered("Deck Type changed!");
						IO.println("Press enter to continue");
						scan = new Scanner(System.in);
						scan.nextLine();
						done = true;
						break;
					case 3: 
						deckType =2;
						clearScreen();
						printCentered("Deck Type changed!");
						IO.println("Press enter to continue");
						scan = new Scanner(System.in);
						scan.nextLine();
						done = true;
						break;
					case 4: 
						deckType =3;
						clearScreen();
						printCentered("Deck Type changed!");
						IO.println("Press enter to continue");
						scan = new Scanner(System.in);
						scan.nextLine();
						done = true;
						break;
					case 5: 
						deckType =4;
						clearScreen();
						printCentered("Deck Type changed!");
						IO.println("Press enter to continue");
						scan = new Scanner(System.in);
						scan.nextLine();
						done = true;
						break;
					case 6: 
						deckType =5;
						clearScreen();
						printCentered("Deck Type changed!");
						IO.println("Press enter to continue");
						scan = new Scanner(System.in);
						scan.nextLine();
						done = true;
						break;
				}
			}
		}
		
	}
	private void optionMenuHP()
	{
		boolean done = false;
		while (!done)
		{
			clearScreen();
			printCentered("Scoundrel");
			printCentered("Change Max HP");
			IO.println("Max HP can be a value between 1 and 50");
			IO.println("Anything outside these bounds will become the closest valid value.");
			IO.println("Default is 20");
			IO.println();
			IO.println("How much hp do you want?");
			
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			scan = new Scanner(line);
			
			if (scan.hasNextInt())
			{
				int newHP = scan.nextInt();
				if (newHP <=1)
					maxHP = 1;
				else if (newHP>=50)
					maxHP = 50;
				else
					maxHP=newHP;
				done = true;
			}
		}
	}
	private void optionMenuSeed()
	{
		boolean done = false;
		while(!done)
		{
			clearScreen();
			printCentered("Scoundrel");
			printCentered("Change Seed");
			IO.println("Don't like randomness?");
			IO.println("Enter any interger to use as a seed!");
			IO.println("Except zero. enter 0 for no seed.");
			IO.println();
			
			Scanner scan = new Scanner (System.in);
			String line = scan.nextLine();
			scan = new Scanner(line);
			
			if (scan.hasNextInt())
			{
				int newSeed = scan.nextInt();
				if (newSeed == 0)
					usingSeed = false;
				else
				{
					usingSeed = true;
					seed = newSeed;
				}
				done = true;
			}
		}
	}
	private void optionMenuTerm()
	{
		boolean done = false;
		while (!done)
		{
			clearScreen();
			printCentered("Scoundrel");
			printCentered("Change Terminal size");
			IO.println("By default this program assumes your terminal is 120x30.");
			IO.println("Please enter new terminal width.");
			IO.println("(enter 0 to not change width)");
			IO.println();
			
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			scan = new Scanner(line);
			if (scan.hasNextInt())
			{
				int newTermWidth = scan.nextInt();
				if (newTermWidth == 0)
					break;
				else
					terminalWidth = newTermWidth;
					done = true;
			}
		}
		
	}
	
	private void showHighscores()
	{
		Scanner wait = new Scanner(System.in);
		boolean done = false;
		while(!done)
		{
			clearScreen();
			printCentered("Scoundrel");
			printCentered("Highscores!");
			try
			{
				//IO.println("Not ready yet");
				Scanner scan = new Scanner(highscore);
				
				String part = "Name:";
				String nextPart = "DeckType:";
				String layout = part;
				for (int i =0; i<terminalWidth/4-part.length()-nextPart.length()/2-1;i++)
					layout= layout+" ";
				part = nextPart;
				nextPart = "Max HP:";
				layout = layout+part;
				for (int i =0; i<terminalWidth/4-part.length()/2-nextPart.length()/2-1;i++)
					layout= layout+" ";
				part = nextPart;
				nextPart = "Seeded:";
				layout = layout+part;
				for (int i =0; i<terminalWidth/4-part.length()/2-nextPart.length()/2-1;i++)
					layout= layout+" ";
				part = nextPart;
				nextPart = "Score:";
				layout = layout+part;
				for (int i =0; i<terminalWidth/4-part.length()/2-nextPart.length();i++)
					layout= layout+" ";
				layout = layout+nextPart;
				IO.println(layout);
				
				while (scan.hasNextLine())
				{
					String line = scan.nextLine();
					int deckint = line.charAt(0)-'0';
					int hpint = line.charAt(1)-'a'+1;
					int seeded = line.charAt(2)-'0';
					String name = line.substring(3,line.lastIndexOf("\"")+1);
					String score = line.substring(line.lastIndexOf("\"")+1,line.length());
					
					layout = name;
					for (int i =0; i<terminalWidth/4-name.length()-2;i++)
						layout= layout+" ";
					layout = layout+deckint;
					for (int i =0; i<terminalWidth/4-2;i++)
						layout= layout+" ";
					layout = layout+hpint;
					String seedboolean="False";
					if (seeded!=0)
						seedboolean = "True";
					for (int i =0; i<terminalWidth/4-seedboolean.length()/2-1;i++)
						layout= layout+" ";
					layout = layout+seedboolean;
					for (int i =0; i<terminalWidth/4-seedboolean.length()/2-score.length();i++)
						layout= layout+" ";
					layout = layout+score;
					IO.println(layout);
				}
				
				done = true;
			}catch(FileNotFoundException fnfe)
			{
				IO.println("Could not find Highscore file.");
				IO.println("cannot show highscores");
				done = true;
			}
		}
		IO.println("\nPress enter to continue.");
		wait.nextLine();
	}
	
	private void extras()
	{
		boolean done = false;
		while(!done)
		{
			clearScreen();
			printCentered("Extras");
			IO.println();
			
			IO.println("There are several things listed in the project requirements that I did not need for this game.");
			IO.println("I will breifly go over them here.");
			IO.println();
			IO.println("1- ^");
			IO.println("2- Interfaces, Inheritence, and polymorphism.");
			IO.println("3- Enum types");
			IO.println("0- back");
			IO.println();
			
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			scan = new Scanner(line);
			if (scan.hasNextInt())
			{
				boolean done2 = false;
				switch(scan.nextInt())
				{
					case 1:
						while (!done2)
						{
							clearScreen();
							printCentered("Extras");
							printCentered("^");
							IO.println();
							
							IO.println("In the java programing language '^' Is the XOR (exclusive or) operator.");
							IO.println("It'll compare the binary form of two numbers.");
							IO.println("For each digit place in both numbers, if the digits are the same then that digit place in the resulting number will be \na one, If it is different it'll be a zero.");
							IO.println("Reasonably, I didn't find a place to use this in my project. I simply didn't need it.");
							IO.println();
							
							IO.println("Press enter to continue");
							
							scan = new Scanner(System.in);
							scan.nextLine();
							done2 = true;
						}
						break;
					case 2:
						while (!done2)
						{
							clearScreen();
							printCentered("Extras");
							printCentered("Interfaces, Inheritence, and polymorphism.");
							IO.println();
							printCentered("Interfaces");
							IO.println();
							
							IO.println("Interfaces are a type of java file that serves as blue print for a class.");
							IO.println("Interfaces contain a set of methods that must be included and filled out in whatever file implements the interface.");
							IO.println("This allows for polymorphism between the child classes.");
							IO.println();
							
							IO.println("Press enter to continue");
							scan = new Scanner(System.in);
							scan.nextLine();
							
							clearScreen();
							printCentered("Extras");
							printCentered("Interfaces, Inheritence, and polymorphism.");
							IO.println();
							printCentered("Inheritence");
							IO.println();
							
							IO.println("In java there are multiple methods of inheritence.");
							IO.println("In this class we covered mainly Extends and Implements.");
							IO.println();
							IO.println("Extends makes the class into a subclass of the class it extends.");
							IO.println("This grants it use of public and protected methods and variables. A class can only extend one other class.");
							IO.println();
							IO.println("Implements makes the class use the interface it implements.");
							IO.println("This requires it to use the methods in the interface file. A class can implement multiple interfaces.");
							IO.println();
							IO.println("Both allow for polymorphism.");
							IO.println();
							
							IO.println("Press enter to continue");
							scan.nextLine();
							
							clearScreen();
							printCentered("Extras");
							printCentered("Interfaces, Inheritence, and polymorphism.");
							IO.println();
							printCentered("Polymorphism");
							IO.println();
							
							IO.println("The main use of the different methods of inheritence is Polymorphism.");
							IO.println("A class that uses inheritence can be treated as the class or interface it inherents in other classes.");
							IO.println("This allows a piece of code to be more variable in what it does as it would be able to the \"same\" method \nin different classes.");
							IO.println();
							
							IO.println("I didn't end up using Interfaces, Inheritence, or Polymoorphism.");
							IO.println("I only used 2 class files for this project, so there wasn't really a place to use them.");
							IO.println("I could have probably've split up this ScoundrelProject class into multiple classes... but I didn't.");
							IO.println("Instead it's over 1000 lines long. oops.");
							IO.println();
							
							IO.println("Press enter to continue");
							scan.nextLine();
							done2 = true;
						}
						break;
					case 3:
						while (!done2)
						{
							clearScreen();
							printCentered("Extras");
							printCentered("Enum Types");
							IO.println();
							
							IO.println("Enumerated Types are a data type that can only be a limited number of predecided values.");
							IO.println("Enum Types can be used with switch case.");
							IO.println();
							
							IO.println("I could have used enum types to store the suits of the playing cards.");
							IO.println("I didn't because I had already made the Card class before I learned about them and changing them to use \nEnum types wouldn't be too useful for my project");
							IO.println("even if it would be clearer for the case that someone else uses my code. Reusability was not a big focus for my project.");
							IO.println();
							
							IO.println("Press enter to continue");
							
							scan = new Scanner(System.in);
							scan.nextLine();
							done2 = true;
						}
						break;
					case 0:
						done = true;
				}
			}
		}
	}
	
	public void startGame()
	{
		Random rand = new Random();
		startGame(rand.nextInt());
	}
	public void startGame(int seed)
	{
		//clearScreen();
		
		Scanner scan = new Scanner(System.in);
		deck = constructDeck();//why was I sending this decktype? it can just see that.
		if (deck.length==0)//should be impossible once im done.
		{
			clearScreen();
			printCentered("An Issue has occured creating the deck.");
			printCentered("Please change decktype in options or restart program.");
			printCentered("Press enter to continue");
			scan.nextLine();
			return;
		}
		if (IS_DEBUGGING)
		{
			clearScreen();
			IO.println("testing Deck Construction.");
			IO.println("deck length: "+deck.length+" ");
			IO.println("deck toString: "+deck.toString()); // deepToString doesn't work here... because that's an array method dummy.
			IO.println("deck array toString: "+Arrays.toString(deck));
			IO.println("Enter number from 0 to 42");
			int test = scan.nextInt();
			scan.nextLine();
			IO.println("Card toString: "+deck[test].toString());
			IO.print("card print: ");
			IO.println(deck[test]);
			IO.println("press enter to continue");
			scan.nextLine();
			clearScreen();
		}
		shuffle(seed);
		
		int dT = 4; //decktracker
		int hp= maxHP;
		Card[] room = new Card[4];
		Card Sword = null;
		int sDur = -1;
		room[0]=deck[0];
		room[1]=deck[1];
		room[2]=deck[2];
		room[3]=deck[3];
		//room[3]=null; test Hmmm i think that's fine.  could be better, but it's fine
		boolean over = false;//see when the game needs to end
		boolean finished = false;// see if the game ended naturally.
		boolean cantSkip = false;
		
		if (IS_DEBUGGING)
			{
				IO.print("end testing? True or False: ");
				over = scan.nextBoolean(); 
				scan.nextLine();
			}
		
		while(!over)
		{
			
			clearScreen();
			printCentered("Scoundrel");
			printCentered(Arrays.toString(room));//displays room info
			IO.println();
			if (Sword != null)
			{
				IO.println ("Current Sword: "+Sword);
				IO.print ("Sword duribility: ");
				if (sDur==-1){IO.println("full");}
				else {IO.println(sDur);}
			}
			else
			{
				IO.println();
				IO.println();
			}
			IO.println();
			printCentered("HP left: "+ hp);
			
			String choice = scan.nextLine();
			if (choice.length() == 0)
				choice = "stopcrash ";
			int a = processAction(choice);
			Scanner numGet = new Scanner(choice);
			numGet.next();											//will empty it if just a string, will prepare for nextInt() if not.
			
			switch (a)													//this is where the action actually gets processed.
			{
				case 0:													//unknown command
				clearScreen();
				printCentered("Type \"help\" to see a list of available actions!");
				scan.nextLine();
				break;
				
				case 1:													//help
				helpScreen();
				break;
				
				case 2:													//quit
				break;
				
				case 3:													//skip
				if (!cantSkip)
				{
					deck = Arrays.copyOf(deck,deck.length+4);
					deck[deck.length-4] = room[0];
					deck[deck.length-3] = room[1];
					deck[deck.length-2] = room[2];
					deck[deck.length-1] = room[3];
					room[0] = deck[dT];
					room[1] = deck[dT+1];
					room[2] = deck[dT+2];
					room[3] = deck[dT+3];
					dT+=4;
					cantSkip = true;
				}
				else
				{
					clearScreen();
					printCentered("You can only skip if a room is full and you didn't skip the previous room.");
					printCentered("press Enter to continue");
					scan.nextLine();
				}
				break;
				
				case 4:													//take
				if (numGet.hasNextInt())
				{
					int rN = numGet.nextInt();
					if (rN<1 || rN>4)
						explainFormating();
					else if(room[rN-1]!=null && room[rN-1].getSuite().equals("Diamonds"))	//take a sword
					{
						Sword = room[rN-1];
						sDur = -1;
						room[rN-1]=null;
						cantSkip=true;
					}
					else if(room[rN-1]!=null && room[rN-1].getSuite().equals("Hearts"))		//take a potion
					{
						hp += room[rN-1].getValue();
						room[rN-1] = null;
						cantSkip=true;
						if (hp>maxHP) hp=maxHP;
					}
				}
				else
				{
					explainFormating();
					//if (IS_DEBUGGING){IO.println(numGet.nextLine());scan.nextLine();}
				}
				break;
				
				
				case 5:													//attack
				if (Sword !=null && numGet.hasNextInt())
				{
					int rN = numGet.nextInt();
					if (rN<1 || rN>4)
					{
						explainFormating();
						break;
					}
					else if (room[rN-1]!=null)
					{
						if ((sDur == -1 || sDur >= room[rN-1].getValue()) && (room[rN-1].getSuite().equals("Clubs") ||room[rN-1].getSuite().equals("Spades")))
						{
							int pain = room[rN-1].getValue()-Sword.getValue();
							if (pain <0) pain = 0;
							hp-=pain;
							sDur = room[rN-1].getValue();
							room[rN-1]=null;
							cantSkip=true;
							break;
						}
						else 
							break;
					}
					numGet = new Scanner (""+rN);
				}
				case 6:													//face
				if (numGet.hasNextInt())
				{
					int rN = numGet.nextInt();
					if (rN<1 || rN>4)
						explainFormating();
					else if(room[rN-1]!=null && (room[rN-1].getSuite().equals("Clubs") ||room[rN-1].getSuite().equals("Spades")))
					{
						hp-= room[rN-1].getValue();
						room[rN-1] = null;
						cantSkip=true;
					}
				}
				else
					explainFormating();
				break;
				
				case 7:
				Scanner wait = new Scanner(System.in);
				clearScreen();
				printCentered("Scoundrel");
				IO.println();
				printCentered("snooPING AS usual I see!");
				IS_DEBUGGING = !IS_DEBUGGING;
				wait.nextLine();
				
			}
			if (IS_DEBUGGING)
			{
				if (choice.equals("givesword"))
					Sword = new Card(-1,0);
				if (choice.equals("takesword"))
					Sword = null;
				if (choice.equals("hurt"))
				{
					IO.println ("how much?");
					Scanner hurtScan = new Scanner(System.in);
					int hurt = hurtScan.nextInt();
					hp-=hurt;
				}
				if (choice.equals("heal"))
				{
					IO.println ("how much?");
					Scanner hurtScan = new Scanner(System.in);
					int hurt = hurtScan.nextInt();
					hp+=hurt;
				}
			}
			
			
			//checking for endstates
			if (hp<=0 || a==2)
			{
				over = true;
				finished = hp<=0;
			}
			
			
			int emptyCount = 0;
			
			for (Card c:room)
			{
				if (c==null)
				{
					cantSkip=true;
					emptyCount++;
				}
			}
			
			if (emptyCount>=room.length-1)//see if room needs refilled
			{
				if (deck.length-(dT)>room.length-1)//see if it can be refilled
				{
					for (int i=0;i<room.length;i++)//refill it
					{
						if (room[i]==null)
						{
							room[i]=deck[dT];
							dT++;
							cantSkip = false;
						}
					}
				}
				else // can't refill room, end game
				{
					over = true;
					finished = true;
				}
			}
				
		}//end of game loop 

		updateHighscore(endGame(dT, hp, finished));						//does the gameover screen and then sends the score to the code that checks if the HS needs updated
		
	}//end of startGame()
	
	
	
	private int endGame(int dT, int hp, boolean f)						//post-game end screen, will show score and return score
	{
		Scanner wait = new Scanner(System.in);
		clearScreen();
		printCentered("Scoundrel");
		int score = 0;
		if(!f ||hp<=0)
		{
			printCentered("Game Over!");
			
			for (;dT<deck.length;dT++)
			{
				if (deck[dT].getSuite().equals("Clubs") || deck[dT].getSuite().equals("Spades"))
				{
					score -= deck[dT].getValue();
					if (IS_DEBUGGING)
						IO.print(deck[dT].toString()+score+"\n");
				}
			}
			printCentered("Score: "+score);
		}
		else
		{
			printCentered("You Win!");
			score = hp;
			printCentered("Score: "+hp);
		}
		if (IS_DEBUGGING)
			IO.println("dT"+dT+"hp"+hp+"fin"+f+"score"+score);
		IO.println("\nPress enter to continue");
		wait.nextLine();
		return score;
	}
	
	private void updateHighscore(int score)								//gets sent a score and checks if it's a highscore. If it is, prompts for a name and updates it.
	{
		if(IS_DEBUGGING)
			return;//if you're cheating you shouldn't get a highscore.
			
		Scanner wait = new Scanner (System.in);
		Scanner scan = new Scanner(System.in);
		String recon ="";//reconstruct since i will have to if the highscore changes I think
		if (!highscore.canRead())
		{
			IO.println("Highscore file could not be found. can't save score.");
			wait.nextLine();
		}
		else
		{
			try
			{
				scan = new Scanner(highscore);
			}catch (FileNotFoundException fnfe)
			{
				IO.println("Highscore file could not be found. can't save score.");
				wait.nextLine();
			}
			boolean scoreplacefound = false;
			while(scan.hasNextLine())
			{
				String line = scan.nextLine();
				int seedCompare = usingSeed ? 1 : 0;															//A singular ternary operator becuse you cant compare booleans to ints for some reason.
				if (line.charAt(0)-'0'==deckType && line.charAt(1)-'a'+1==maxHP && line.charAt(2)-'0'==seedCompare)
				{
					scoreplacefound = true;
					Scanner hScore = new Scanner(line.substring(line.lastIndexOf("\"")+1,line.length()));// lastIndexOf found at https://www.w3schools.com/java/java_ref_string.asp, but was probably also in my notes
					int hScoreInt = hScore.nextInt();
					if (hScoreInt <score)
					{
						//construct highscore string and place in file before skipping the rest of this loop.
						recon = recon + newHighscore(score)+"\n";
						continue;
					}
				}
				else if (!scoreplacefound && (line.charAt(0)-'0'>deckType || (line.charAt(0)-'0'==deckType && line.charAt(1)-'a'+1>maxHP) || (line.charAt(0)-'0'==deckType && line.charAt(1)-'a'+1==maxHP && line.charAt(2)-'0'> seedCompare)))
				{
					scoreplacefound = true;
					recon = recon + newHighscore(score)+"\n";
					//construct highscore string and place in file before continuing as normal
				}
				recon = recon+line+"\n";
			}
			if (!scoreplacefound)
			{
				recon = recon + newHighscore(score)+"\n";
			} 
			//we be here now gotta actually update the file now. recon has what it needs to be.
			try{
				PrintStream update = new PrintStream (highscore);
				update.print(recon);
			}catch (FileNotFoundException fnfe)
			{
				IO.println("Highscore file could not be found. can't save score.");
				wait.nextLine();
			}
		}
	}
	
	private String newHighscore(int score)								//constructs a string to put in the highscore file and shows the new Highscore screen
	{
		clearScreen();
		String rtn = ""+deckType+"";
		short hpnum = (short)maxHP;
		hpnum+='a';
		hpnum--;
		char hpdigit =(char)hpnum;
		rtn=rtn+hpdigit;
		if (usingSeed)
			rtn = rtn+1;
		else
			rtn = rtn+0;
		rtn = rtn+"\"";
		
		printCentered("Scoundrel");
		printCentered("New Highscore!");
		IO.println();
		IO.println();
		IO.println("Please enter your name! (nothing after a space will be saved)");
		Scanner scan = new Scanner(System.in);
		String name = "";
		do
		{
			name =scan.next();
			rtn = rtn + name;
		} while (name.equals(""));
		rtn = rtn +"\""+score;
		return (rtn);
	}
	
	
	private int processAction(String a) 								//doesn't actually do the action, just turns the input into a number for easier use
	{
		/*
		if (IS_DEBUGGING)
		{
			Scanner wait = new Scanner (System.in);
			clearScreen();
			IO.print(a);
			IO.print(a.substring(0,3));
			wait.nextLine();
		} */
		if (a == null)
			throw new IllegalArgumentException("Expected a string, got null.");
		if (a.equalsIgnoreCase("help"))
			return 1;
		if (a.equalsIgnoreCase("quit"))
			return 2;
		if (a.equalsIgnoreCase("Skip"))
			return 3;
		if (a.length()>4 && a.substring(0,4).equalsIgnoreCase("take"))
			return 4;
		if (a.length()>6 && a.substring(0,6).equalsIgnoreCase("attack"))
			return 5;
		if (a.length()>4 && a.substring(0,4).equalsIgnoreCase("face"))
			return 6;
		if (a.equalsIgnoreCase("pingas"))
			return 7;
		return 0;
	}
	private void helpScreen()
	{
		clearScreen();
		Scanner wait = new Scanner(System.in);
		printCentered("HELP SCREEN");
		IO.println();
		printCentered("Room Layout:");
		printCentered("[1,2,3,4]");
		IO.println();
		printCentered("Available Actions:");
		IO.println("\"help\": Shows this screen. Hello!");
		IO.println();
		IO.println("\"face #\": Take damage equal to the strength of enemy at space # and discards the enemy. \n\tDoes nothing if # isn't an enemy.");
		IO.println();
		IO.println("\"attack #\": If sword durribility is more than the strength of enemy at space #: Take damage equal to the strength of \n\tenemy at space # minus the strength of your sword. Enemy strength becomes sword durribility.");
		IO.println("\tDoes nothing if sword duribility is lower than enemy strength or space # isn't an enemy. \n\tFaces the enemy if no sword.");
		IO.println();
		IO.println("\"take #\": Takes the item at space #. equips it if it's a sword, heals you if it's a potion. \n\tDoes nothing if it's an enemy.");
		IO.println();
		IO.println("\"skip\": skips the room and puts all it's cards it at the bottom of the deck. can't be done twice in a row.");
		IO.println();
		wait.nextLine();
	}
	private void explainFormating()
	{
		clearScreen();
		Scanner wait = new Scanner(System.in);
		printCentered("INPUT FORMATING HELP");
		IO.println();
		printCentered("The formating of the Face, Take, and Attack commands is [command] a space and then [number]");
		printCentered("The number corresponds to which space in the room you wish to act upon using this layout:");
		printCentered("[1,2,3,4]");
		IO.println();
		printCentered("Press Enter to continue");
		wait.nextLine();
	}
	
	
	private Card[] constructDeck() 
	{
		if (deckType <0 || deckType >5)
			throw new IllegalArgumentException("currently only deckType 0 allowed");
		Card[] rtn = new Card[0];
		int i = 0;
		switch(deckType)
		{
			case 0://default deck
				rtn = new Card[44];//26 monster cards 18 sword and potion cards
				for (int s =0; s<4;s++)
				{
					switch (s)
					{
						case 0:
						case 1:
							for (int v = 2; v<15;v++)//high aces
							{
								rtn[i]= new Card(s,v);
								i++;
							}
							break;
						case 2:
						case 3:
							for (int v = 2; v<11;v++)//no ace or faces
							{
								rtn[i]= new  Card(s,v);
								i++;
							}
							break;
					}
				}
				break;
				
			case 1://hoard deck
				rtn = new Card[52];//34 monster cards 18 sword and potion cards
				for (int s =0; s<4;s++)
				{
					switch (s)
					{
						case 0:
						case 1:
							for (int v = 2; v<15;v++)//high aces
							{
								if (v<=10)
								{
									rtn[i]= new Card(s,v);
									i++;
								}
								else
								{
									rtn[i] = new Card (s,v/2+2);
									i++;
									rtn[i] = new Card (s,v/2-3);
									i++;
								}
							}
							break;
						case 2:
						case 3:
							for (int v = 2; v<11;v++)//no ace or faces
							{
								rtn[i]= new  Card(s,v);
								i++;
							}
							break;
					}
				}
				break;
				
			case 2://dragon deck
				rtn = new Card[36];//18 monster cards 18 sword and potion cards
				for (int s =0; s<4;s++)
				{
					switch (s)
					{
						case 0:
						case 1:
							for (int v = 11; v<15;v++)//high aces
							{
								rtn[i]= new Card(s,v);
								i++;
								rtn[i]= new Card(s,v);
								i++;
							}
							rtn[i]= new Card(s,15);
							i++;
							break;
						case 2:
						case 3:
							for (int v = 2; v<11;v++)//no ace or faces
							{
								rtn[i]= new  Card(s,v);
								i++;
							}
							break;
					}
				}
				break;
			
			case 3://berserker deck
				rtn = new Card[44];//26 monster cards 18 sword cards
				for (int s =0; s<3;s++)
				{
					switch (s)
					{
						case 0:
						case 1:
							for (int v = 2; v<15;v++)//high aces
							{
								rtn[i]= new Card(s,v);
								i++;
							}
							break;
						case 2:
							for (int v = 2; v<11;v++)
							{
								rtn[i]= new  Card(s,v);
								i++;
								rtn[i]= new  Card(s,v);
								i++;
							}
							break;
					}
				}
				break;
			
			case 4: //pacifist deck
				rtn = new Card[44];//26 monster cards 18 potion cards
				for (int s =0; s<4;s++)
				{
					switch (s)
					{
						case 0:
						case 1:
							for (int v = 2; v<15;v++)//high aces
							{
								rtn[i]= new Card(s,v);
								i++;
							}
							break;
						case 2:
							break;
						case 3:
							for (int v = 2; v<11;v++)//no ace or faces
							{
								rtn[i]= new  Card(s,v);
								i++;
								rtn[i]= new  Card(s,v);
								i++;
							}
							break;
					}
				}
				break;
			
			
			case 5://max deck
				rtn = new Card[44];//26 monster cards 18 sword and potion cards
				for (int s =0; s<4;s++)
				{
					switch (s)
					{
						case 0:
						case 1:
							for (int v = 2; v<14;v++)
							{
								rtn[i]= new Card(s,14);//only aces
								i++;
							}
							rtn[i]= new Card(s,15);//one majesty
							i++;
							break;
						case 2:
						case 3:
							for (int v = 2; v<11;v++)
							{
								rtn[i]= new  Card(s,10);//only 10s
								i++;
							}
							break;
					}
				}
				break;
			
			
		}
		return rtn;
	}
	
	
	private void shuffle (int seed)//shuffles current deck.
	{
		Card temp;
		Random rand = new Random(seed);
		for (int i=0; i<deck.length;i++)
		{
			temp = deck[i];
			int temp2 = rand.nextInt(deck.length-1);
			deck[i] = deck[temp2];
			deck[temp2]=temp;
		}
	}
	private void shuffle()
	{
		Random rand = new Random();
		shuffle(rand.nextInt());
	}
	
	
	
	
	public static void clearScreen() { //from the terminal directory moved here for easier use and the fact that that directory will need to be deleted.
		try{//windows
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch(Exception e){//mac and linux
			try{
				String term = System.getenv("TERM"); // https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getenv-java.lang.String-
				if(term != null && !term.equals("dumb")){
					new ProcessBuilder("clear").inheritIO().start().waitFor();
				}
			}catch(Exception e2){}
		}
	}
}
