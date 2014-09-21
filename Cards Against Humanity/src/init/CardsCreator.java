package init;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
	}
	
	
	

	public List<HostCard> getHostCards(){ return this.hostCards;}
	public List<AnswerCard> getAnswerCards(){ return this.answerCards;}
	
}
