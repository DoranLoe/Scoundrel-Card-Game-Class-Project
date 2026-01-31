public class Card{
	private int suite;// might make this an enum instead of an int
	private int value;

	public Card()
	{
		this(-1,-1);
	}
	public Card(int su,int val)
	{
		suite= su;
		value=val;
	}
	

	public String getSuite()
	{
		String rtn ="";
		
		switch(suite)
		{
			case 0:
				rtn = "Spades";
				break;
			case 1:
				rtn = "Clubs";
				break;
			case 2:
				rtn = "Diamonds";
				break;
			case 3:
				rtn = "Hearts";
				break;
		}
		if (rtn.length() ==0)
			rtn = "Stars";
		return rtn;
	}//temp
	
	public int getValue()
	{
		return value;
	}//temp
	
	public String getName()
	{
		String rtn ="";
		switch(value)
		{
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				rtn = ""+value;
				break;
			case 11:
				rtn= "Jack";
				break;
			case 12:
				rtn= "Queen";
				break;
			case 13:
				rtn= "King";
				break;
			case 14:
				rtn= "Ace";
				break;
		}
		if (rtn.length()==0)
			rtn="Majesty";
		return rtn;
	}//temp
	
	
	
	public String toString()
	{
		return (getName()+" of "+getSuite());
	}
}
