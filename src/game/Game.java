package game;

import queue.Queue;

public class Game {
	
	private Queue<Card> spoils = new Queue<Card>();
	public static int round = 1;
	
	public static void main(String[] args)
	{
		Game newGame = new Game();
		Deck d = new Deck();
		d.shuffle();
		Deck frontHalf = new Deck(d, 0, 26);
		Deck backHalf = new Deck(d, 26, 52);
		Player player1 = new Player(frontHalf);
		Player player2 = new Player(backHalf);
		Card player1Value = null;
		Card player2Value = null;
	    while((player1.getSize() != 0 && player2.getSize() != 0))
	    {  
	    	System.out.println();
	 	    System.out.println("Round " + round);
	 	    newGame.getSize(player1, player2);
			newGame.showDecks(player1, player2);
	 	   	if(player1.getSize() != 0)
	 	   		player1Value = player1.removeCard();
			if(player2.getSize() != 0)
				player2Value = player2.removeCard();
	    	newGame.War(player1, player2, player1Value, player2Value);
	    	round++;
	    	if(round > 25000)
	    		break;
	    }
	    newGame.showDecks(player1, player2);
  	    newGame.Winner(player1, player2);
	}
	
	public void War(Player player1, Player player2, Card player1Value, Card player2Value)
	{
		System.out.println("War: " + player1Value.toString() + " " + player2Value.toString());
		Card Winner = getWinner(player1Value, player2Value);
		if(Winner == null)
		{
			spoils(player1, player2, player1Value, player2Value);
		}
		if(Winner == player1Value)
		{
			System.out.println("Player 1 wins.");
			player1.addCard(player1Value);
			player1.addCard(player2Value);
			while(!spoils.isEmpty())
				player1.addCard(spoils.dequeue());
		}
		else if(Winner == player2Value)
		{
			System.out.println("Player 2 wins.");
			player2.addCard(player1Value);
			player2.addCard(player2Value);
			while(!spoils.isEmpty())
				player2.addCard(spoils.dequeue());
		}
	}
	
	public void spoils(Player player1, Player player2, Card player1Value, Card player2Value) {
			spoils.enqueue(player1Value);
			spoils.enqueue(player2Value);
			for(int i = 0; i < 3; i++)
			{
				Card player1Spoils = null;
				Card player2Spoils = null;
				if(player1.getSize() != 1 && player1.getSize() != 0)
				{
					player1Spoils = player1.removeCard();
					spoils.enqueue(player1Spoils);
				}
				if(player2.getSize() != 1 && player2.getSize() != 0)
				{
					player2Spoils = player2.removeCard();
					spoils.enqueue(player2Spoils);
				}
			}
			System.out.print("Spoils:");
			spoils.showQueue();
			getSize(player1, player2);
			showDecks(player1, player2);
		   	if(player1.getSize() == 0)
		   	{
	 	   		player2Value = player2.removeCard();
	 	   		War(player1, player2, player1Value, player2Value);
		   	}
		   	else if(player2.getSize() == 0)
			{
				player1Value = player1.removeCard();
				War(player1, player2, player1Value, player2Value);
			}
		   	else
		   	{
		   		player1Value = player1.removeCard();
		   		player2Value = player2.removeCard();
		   		War(player1, player2, player1Value, player2Value);		
		   	}
	}
	
	public Card getWinner(Card player1Value, Card player2Value)
	{
		if(player1Value.getIntValue() > player2Value.getIntValue())
			return player1Value;
		else if(player1Value.getIntValue() < player2Value.getIntValue())
			return player2Value;
		else
			return null;
	}
	
	public void showDecks(Player player1, Player player2)
	{
		System.out.print("Player 1 Deck: ");
	  	player1.showDeck();
  	    System.out.println();
  		System.out.print("Player 2 Deck: ");
  	    player2.showDeck();
  	    System.out.println();
	}
	
	public void Winner(Player player1, Player player2)
	{
		System.out.println();
	    if(player1.getSize() == 0)
	    	System.out.println("Player 2 wins War!");
	    else if(player2.getSize() == 0)
	    	System.out.println("Player 1 wins War!");
	    else
	    	System.out.println("Tie!");
	}
	 	
	public void getSize(Player player1, Player player2)
	{
		System.out.println();
		System.out.println("Player 1 Deck Size: " + player1.getSize());
		System.out.println("Player 2 Deck Size: " + player2.getSize());
	}
}

/*Sample application: The card game war
-------------------------------------

The game is played by shuffling and dealing the deck to two players.
Each player plays a card. Higher card (by value, or *rank*, suits are
irrelevant) wins, player takes both cards.

In case of tie, declare "war", dealing three face down as spoils of war.
Then play again. Winner takes all 10 cards (or more, in case of repeated
ties).

Repeat until one player has no cards left.

How could we model this in Java using queues?

A Queue<Integer> for each player's deck.

A Queue<Integer> for the current cards "in play", to be awarded to one
player or the other.

Start by shuffling an array of ints, containing values 1--13 each 4
times. (Aside: Fisher-Yates shuffle.)

Enqueue half for each player.

Play by looping:

    dequeue from each player (if empty: other player won, break loop)
    if winner:
      queue both to winner's deck
      dequeue all from spoils deck, queue to winner's deck
    else:
      queue both to spoils queue
      dequeue three from each, queue to spoils queue*/
