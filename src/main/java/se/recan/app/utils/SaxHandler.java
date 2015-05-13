package se.recan.app.utils;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Anders Recksén (recan)
 */
public class SaxHandler extends DefaultHandler {

    private static final Logger LOGGER = Logger.getLogger("SaxHandler");
    
    public SaxHandler(String xmlFile) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

//        parser.parse(new File(xmlFile), this);
    }
    
    @Override
    public void startElement(String uri, String localName,
        String qualifiedName, Attributes attributes) {
        
//        if(qualifiedName.equals("person")) {
//            LOGGER.debug("Ny persondata");
//        }
//        LOGGER.debug(qualifiedName + " " + attributes.getValue("name"));
    }
    
    @Override
    public void characters(char characters[], int start, int length)
        throws SAXException {
        
//        String characterData = (new String(characters, start, length)).trim();
    }
    
    @Override
    public void endElement(String uri, String localName, String qualifiedName)
        throws SAXException {
        
//        LOGGER.debug("2:" + qualifiedName);
//        if(qualifiedName.equals("extra")) {
//            list.add(order);
//        }
    }    
}
