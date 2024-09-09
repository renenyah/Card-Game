/*
 * This class represents a card player.
 * For each card player instance, we keep track of how many points
 * they earned in the game so far, as well as whether it is their turn or not.
 * Additionally, their hand and bank of cards are stored in two
 * separate ArrayLists of Card objects.
 *
 */ import java.util.ArrayList;


public class CardPlayer extends GeneralPlayer<Card>{ 

	private int points; 
	private boolean turn;
	private String nameP; //added this to store the names of the players
	private ArrayList<Card> hand = new ArrayList<Card>(); 
	public ArrayList<Card> bank = new ArrayList<Card>(); //the player's bank of cards (keeping track of the pairs)

	//constructors
	public CardPlayer(){
		super();
	}
	public CardPlayer(String name){ 
		super(name);
	}
	public String getName(){ 
		return this.nameP;
	}

	//methods
	public int getPoints(){ 
		return points;
	}
	public void setPoints(int points ){ 
		this.points = points;
		//continue to add points when player gets a matching pair 
	}
	public boolean isTurn(){ 
		return turn; 
	}
	public void setTurn(boolean whosTurn){ 
		this.turn = whosTurn;
	}
	public void addToHand(Card card){ 
		this.hand.add(card);
	}
	public ArrayList<Card> getHand(){
		return hand;
	}
	public String handToString(){
		String message= "";
		String matches = "";
		for(int i=0; i<hand.size(); i++){
			//this is the identity of all the matches
			matches += hand.get(i).identifier + " ";
		}
       	return message +=  name + " hand has " + hand.size() + " cards: " +  matches;
     
   	}
   	public String bankToString(){
   		String message= "";
		String matches = "";
		for(int i=0; i<bank.size(); i++){
			//this is the identity of all the matches
			matches += bank.get(i).identifier + " ";
		}
       	return message +=  name + " bank has " + bank.size() + " cards: " +  matches;
   	}
   	public Card play(){ 
		//when a player plays it'll remove card from hand
   		if(hand.size() > 0){ 
   			Card currentPlay = hand.remove(0); //0 means the current index, so this removes the top card
			this.turn = false; 
   			return currentPlay; 
   		} else{ 
   			return null; 
   		}
   	}
}












