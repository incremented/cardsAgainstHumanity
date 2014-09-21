package init;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cards.AnswerCard;

public class AnswerCardsParser implements Callable<List<AnswerCard>>{

	List<AnswerCard> answerCards;
	
	File answerCardsFile;
	
	DocumentBuilder db;
	Document doc;
	
	
	
	public AnswerCardsParser() {
		
		this.answerCards = new ArrayList<>();
		
        try {
        	
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			this.db = dbf.newDocumentBuilder();
			
			this.answerCardsFile = new File("resources/AnswerCards.xml");
			
		
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public List<AnswerCard> call() throws FileNotFoundException {

		 if (this.answerCardsFile.exists()) {
             
			try {
				Document doc = db.parse(this.answerCardsFile);
				Element root = doc.getDocumentElement();
				
				NodeList answerCardsNodes = root.getElementsByTagName("Card");
				
				for(int i=0; i < answerCardsNodes.getLength(); i++){
					
					Node node = answerCardsNodes.item(i);
					
					if(node.getNodeType() == Node.ELEMENT_NODE){
						
						Element cardElement = (Element) node;
						
						AnswerCard answerCard = new AnswerCard(Integer.parseInt(cardElement.getAttribute("id")));
						answerCard.setDescription(cardElement.getAttribute("description"));
						
						this.answerCards.add(answerCard);
					}
					
				}
				
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 }else{
			 throw new FileNotFoundException();
		 }
		return this.answerCards;
	}
	
}
