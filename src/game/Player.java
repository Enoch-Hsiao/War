package game;

import queue.Queue;

public class Player {
	
	private Queue<Card> deck = new Queue<Card>();
	private int size;
	
	public Player(Deck halfDeck){
		for(int i = 0;  i < 26; i++)
		{
			deck.enqueue(halfDeck.getCard(i));
		}
		size = 26;
	}
	
	public void showDeck()
	{
		deck.showQueue();
	}
	
	public Card removeCard()
	{
		Card removed = deck.dequeue();
		size--;
		return removed;
	}
	
	public void addCard(Card card)
	{
		size++;
		deck.enqueue(card);
	}
	
	public int getSize()
	{
		return size;
	}
	
	public Queue<Card> getDeck()
	{
		return deck;
	}	
}
