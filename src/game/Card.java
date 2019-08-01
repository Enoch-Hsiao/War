package game;

public class Card {
	
	private char[] suits = {'\u2665', '\u2666', '\u2663', '\u2660'};
	private String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	private char suit;
	private String stringValue;
	private int intValue;
	public Card(int i) {
		// TODO Auto-generated constructor stub	
		suit = suits[i / 13];
		stringValue = values[i % 13];
		intValue = i%13;
	}
	public String toString()
	{
		return stringValue + suit;
	}
	public char getSuit()
	{
		return suit;
	}
	public String getStringValue()
	{
		return stringValue;
	}
	public int getIntValue()
	{
		return intValue;
	}


}
