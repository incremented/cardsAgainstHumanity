package cards;

public abstract class Card {

	
	private int id;
	
	String description;
	
	
	public Card(int id){
		
		this.id = id;
	}
	
	
	
	public void setDescription(String s){ this.description = s;}
	
	
	public int getID(){ return this.id;}
	public String getDescription(){ return this.description;}
}
