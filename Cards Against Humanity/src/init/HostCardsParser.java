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

import cards.HostCard;

public class HostCardsParser implements Callable<List<HostCard>>{

	List<HostCard> hostCards;
	
	File hostCardsFile;
	
	DocumentBuilder db;
	Document doc;
	
	
	
	public HostCardsParser() {
		
		this.hostCards = new ArrayList<>();
		
        try {
        	
        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			this.db = dbf.newDocumentBuilder();
			
			this.hostCardsFile = new File("resources/HostCards.xml");
			
		
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public List<HostCard> call() throws FileNotFoundException {

		 if (this.hostCardsFile.exists()) {
             
			try {
				Document doc = db.parse(this.hostCardsFile);
				Element root = doc.getDocumentElement();
				
				NodeList hostCardsNodes = root.getElementsByTagName("Card");
				
				for(int i=0; i < hostCardsNodes.getLength(); i++){
					
					Node node = hostCardsNodes.item(i);
					
					if(node.getNodeType() == Node.ELEMENT_NODE){
						
						Element cardElement = (Element) node;
						
						HostCard hostCard = new HostCard(Integer.parseInt(cardElement.getAttribute("id")));
						hostCard.setDescription(cardElement.getAttribute("description"));
						
						this.hostCards.add(hostCard);
					}
					
				}
				
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 }else{
			 throw new FileNotFoundException();
		 }
		return hostCards;
	}
	
}
