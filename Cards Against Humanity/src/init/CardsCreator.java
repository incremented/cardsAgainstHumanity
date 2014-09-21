package init;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cards.AnswerCard;
import cards.HostCard;

public class CardsCreator implements Runnable{

	ExecutorService threadPool;
	
	List<HostCard> hostCards;
	List<AnswerCard> answerCards;
	
	public CardsCreator(){
		
		threadPool = Executors.newFixedThreadPool(2);
		
	}
	
	
	@Override
	public void run() {

		HostCardsParser hp = new HostCardsParser();
		AnswerCardsParser ap = new AnswerCardsParser();
		
		try {
			this.hostCards = hp.call();
			this.answerCards = ap.call();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		Future<List<HostCard>> hostCardThread =  threadPool.submit(new HostCardsParser());
		 
		Future<List<AnswerCard>> answerCardThread = threadPool.submit(new AnswerCardsParser());
		
		try {
			this.hostCards = hostCardThread.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.answerCards = answerCardThread.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	
	

	public List<HostCard> getHostCards(){ return this.hostCards;}
	public List<AnswerCard> getAnswerCards(){ return this.answerCards;}
	
}
