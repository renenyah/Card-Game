/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES. Nyah Rene and Peace Cyebukayire */

/**
 * This class represents the table where the game is being played.
 *
 * It implements the Table interface and is designed to work with Card and
 * CardPlayer objects.
 *
 * <p>
 * Each table instance must keep track of the cards that players place on the table
 * during the game. The number of places available has a fixed size (<code>NUMBER_OF_PLACES</code>),
 * so we use a regular Java array to represent a CardTable's places field.
 * Each entry in this places array contains
 * the cards that were added to that place, which is a more dynamic structure (we don't know
 * in advance how many cards will be added to this place!).
 * <p>
 * Therefore, each place
 * entry in this array will reference an ArrayList of Card objects.
 * <p>
 * Here is how to declare the array of ArrayLists field <code>places</code>:
 *
 * <p>
 * <code>
 * 		private ArrayList&lt;Card&gt;[] places = new ArrayList[NUMBER_OF_PLACES];
 * </code>
 * <p>
 *
 * Note that the Field Summary section below will only show you public fields,
 * but you must declare the required field places described above, which is private.
 * You are also free to create additional fields in your class implementation, if deemed necessary.
 *
 */

// according to the class documentation described here:
// https://www.cs.emory.edu/~nelsay2/cs171_s23/a2/doc/cs171a2/CardTable.html

import java.util.ArrayList;

public class CardTable extends Object implements Table<Card,CardPlayer> { 
 
	private ArrayList<Card>[] places = new ArrayList[NUMBER_OF_PLACES];
	private int currentPlace; 
	private int previousPlace;

	// places to new ArrayLists of Card objects
	public CardTable(){
		for(int i = 0; i < 4; i ++){
			places[i] = new ArrayList<Card>() ;
		}
		currentPlace = 0; 
	}

	public void addCardToPlace(Card card){
		places[currentPlace].add(card);  
		// Add card to table to the current stack 
		if(this.currentPlace==3){
			this.currentPlace=0;

		}else{
			this.currentPlace++;
		}
	}
	
	//getting the identities of the first card on each stack
	public int[] getPlaces(){
		int[] identities = new int[4];

		for(int i=0; i<identities.length; i++){
			//if the stack is not empty
			if(!(places[i].isEmpty())){
				//the first card on a stack 
				int lastCardindex = places[i].size()-1;
				/*get the first card on a stack and find it's identity
				then assign it to a position in the array */
				identities[i]= places[i].get(lastCardindex).identifier;
			}else{
				identities[i]= -1;
			}
		}	
		return identities;
	}
			
	public void checkPlaces(CardPlayer player){
		// Store previous place
		//if current place is 0 which is the first the previous place will represent going back to place 3 
		//if we didnt have this that means that it would be out of bounds
		if(currentPlace == 0){
			previousPlace = 3;
		}else{
			previousPlace = currentPlace - 1;
		}
	
		//store the identities of the first card on top of each stack
		int [] topCard = this.getPlaces();
		
		//playedCard represents getting the last card that was placed down on the current stack 
		Card playedCard = places[previousPlace].get(places[previousPlace].size()-1);
			for(int i=0; i<topCard.length; i++){
				//we do this because the remainder gets us the rank since the orginal equation was suit*100+rank
				//allCardRanks represents the rank of the first card on each stack
				int cardRank = topCard[i] %100; //only comparing one card at a time not all
				
				/*if current card that we're at is not empty AND its not the current place AND
				* the ranks of the playedCard and the rest of the cards are the same  then it'll
				* do the following
				*/
				if(topCard[i] != -1 && i !=previousPlace && playedCard.getRank() == cardRank ){ 
					// Add both cards to bank
					player.bank.add(places[i].get(places[i].size()-1));
					player.bank.add(playedCard);
	
					//in the current stack, get the first card's identity. 
					int cardOnTableMatch = places[i].get(places[i].size()-1).identifier;
					//initilizing the identity of the first card in the current place we're in which is the same place the playedCard is at
					int playedCardOnTableMatch = places[previousPlace].get(places[previousPlace].size()-1).identifier; 
					System.out.println("Matched ranks: " + cardOnTableMatch + " (on table) and " + playedCardOnTableMatch + " (" + player.name +"'s card)" );

					//updating the points
					int scoreTracker = player.getPoints();
					player.setPoints(scoreTracker + 1);

					// System.out.println(places[previousPlace].get(places[previousPlace].size()-1).identifier); //prints out the removed playedCard
					// Remove both cards from table
					places[previousPlace].remove(places[previousPlace].size()-1); // removed the playedCard off the table
					
					// System.out.println(places[i].get(places[i].size()-1).identifier); //printing out the matched card that was already on the table
					places[i].remove(places[i].size()-1); //removed the matching card 

				}
			}
		}
	}