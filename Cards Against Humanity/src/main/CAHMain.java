package main;

import init.CardsCreator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cards.AnswerCard;
import cards.HostCard;



public class CAHMain{
	
	
	List<HostCard> hostCards;
	List<AnswerCard> answerCards;
	
	public static void main(String[] args) {
		
		new CAHMain();
	}
	
	
	public CAHMain(){
		
		
		ExecutorService threadPool = Executors.newFixedThreadPool(1);
		
		CardsCreator cardCrator = new CardsCreator();
		threadPool.submit(cardCrator);	
		
		try {
			threadPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.hostCards = cardCrator.getHostCards();
		this.answerCards = cardCrator.getAnswerCards();
		
		for(HostCard h : hostCards){
			
			System.out.println(h.toString());
		}
		
		for(AnswerCard a : answerCards){
			
			System.out.println(a.toString());
		}
		
	}
	
}