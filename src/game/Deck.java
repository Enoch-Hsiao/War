package game;

public class Deck
{
	private Card[] myDeck;
	public static final int NUMCARDS = 52;
	private int size;
	public Deck()
	{
		myDeck = new Card[NUMCARDS];
		for(int i = 0; i < myDeck.length; i++)
			myDeck[i] = new Card(i);
	}
	
	public Deck(Deck d, int start, int end)
	{
		myDeck = new Card[NUMCARDS];
		int j = 0;
		for(int i = start; i < end; i++)
		{
			myDeck[j] = d.getCard(i);
			j++;
		}
		size = end - start;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public Card getCard(int i)
	{
		return myDeck[i];
	}
	
	public String toString()
	{
		String builder = "";
		
		int count = 0;
		for(Card card : myDeck)
		{
			builder += cardString(card) + "\t";
			count++;
			if(count % 13 == 0)
				builder += "\n";
		}
		
		return builder;
	}
	
	public String cardString(Card card)
	{
		char suit = card.getSuit();
		String value = card.getStringValue();
		
		return value + suit;
	}
	
	private void swap(Card[] ar, int i, int j)
	{
		Card temp = ar[i];
		ar[i] = ar[j];
		ar[j] = temp;
	}
	
	public void shuffle()
	{
		for(int i = 0; i < myDeck.length; i++)
		    swap(myDeck, i , (int)(Math.random() * myDeck.length));
	}
	
	public static void main(String[] args)
	{
		Deck d = new Deck();
		System.out.println(d);
		System.out.println();
		d.shuffle();
		System.out.println(d);
	}
}

