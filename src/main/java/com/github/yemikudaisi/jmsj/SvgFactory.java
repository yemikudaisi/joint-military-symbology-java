package com.github.yemikudaisi.jmsj;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.NotApplicableAmplifier;
import com.github.yemikudaisi.jmsj.symbology.Status;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class SvgFactory {
	static Logger logger = Logger.getLogger(SvgFactory.class.getName());
	
	public static SVGDocument createSymbolSvg(MilitarySymbol milSym) {
			//TODO: Check of amplifiers and HQTFDummy has been set
    		String parser = XMLResourceDescriptor.getXMLParserClassName();
    	    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        	
        	DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        	String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        	Document newSvgDocument = impl.createDocument(svgNS, "svg", null);
        	Element svgRoot = newSvgDocument.getDocumentElement();
        	
        	//TODO: Figure out the height and width of the tallest and widest

        	svgRoot.setAttributeNS(null, "width", "1000");
        	//svgRoot.setAttributeNS(null, "height", "1000");
        	
        	//TODO:METOC and Cyberspace has no frame
        	//FIXME: Hard-coded, make more flexible
        	
        	// Symbol Set Frame (Including projected Status)
        	if(milSym.getSymbolSet() != SymbolSets.MeteorologicalAtmospheric &&
        			milSym.getSymbolSet() != SymbolSets.MeteorologicalOceanographic &&
        			milSym.getSymbolSet() != SymbolSets.MeteorologicalSpace &&
        			milSym.getSymbolSet() != SymbolSets.Cyberspace) {
        		try {
        			String file = App.class.getClassLoader().getResource(ResourceManager.getFrameSvgResourcePath(milSym)).getFile();
        			Document document = f.createDocument(new File(file).toURI().toString());
        			appendDocument(newSvgDocument, svgRoot, document);
        			
        		}catch(Exception e) {
        			logger.log(Level.WARNING, "Unable to create frame SVG for "+
        		milSym.getSymbolSet().getDescription()+
        		", "+ResourceManager.getFrameSvgResourcePath(milSym)
        		+".");
        		}
        	}
        	
        	// HQTFDummy assembly
        	if(milSym.getHqTFDummy() != HQTFDummy.NotApplicable) {
        		try {
	        		String file = App.class.getClassLoader().getResource(ResourceManager.getHqTfDummySvgResourcePath(milSym)).getFile();
	        		Document document = f.createDocument(new File(file).toURI().toString());
	            	NodeList nL = document.getDocumentElement().getChildNodes();
	            	appendDocument(newSvgDocument, svgRoot, document);
	        	}catch(Exception e) {
	    			logger.log(Level.WARNING, "Unable to create HQ TF Dummy SVG for "+ milSym.getSymbolSet()+
	    					", "+milSym.getHqTFDummy()+
	    					", "+ResourceManager.getHqTfDummySvgResourcePath(milSym)+
	    					".");
	    		}
        	}
        	
        	// Amplifiers, SIDC position 
        	// Only caters for status damage and broken, frame affecting statuses handled in frame assemble above
        	if(!(milSym.getAmplifier() instanceof NotApplicableAmplifier) && 
        			milSym.getSymbolSet() != SymbolSets.MeteorologicalAtmospheric&&  // No amplifier for this sym sets
        			milSym.getSymbolSet() != SymbolSets.MeteorologicalOceanographic&& // No amplifier for this sym sets
        			milSym.getSymbolSet() != SymbolSets.MeteorologicalSpace ) { // No amplifier for this sym sets
	    		try {
	    			String file = App.class.getClassLoader().getResource(ResourceManager.getAmplifierSvgResourcePath(milSym)).getFile();
	    			Document document = f.createDocument(new File(file).toURI().toString());
	    			appendDocument(newSvgDocument, svgRoot, document);
	        	}catch(Exception e) {
	    			logger.log(Level.WARNING, "Unable to create Amplifier for "+
			        	milSym.getSymbolSet()+
			        	", "+milSym.getAmplifier()+
			        	", "+ResourceManager.getAmplifierSvgResourcePath(milSym)
			        	+".");
	    		}
        		
        	}
        	
        	// Status\Operational Condition Amplifier
        	// Only caters for status damage and broken, frame affecting statuses handled in frame assembled above
        	if(milSym.getStatus() == Status.PresentDamaged || milSym.getStatus() == Status.PresentDestroyed) {
        		try {
        			String file = App.class.getClassLoader().getResource(ResourceManager.getStatusSvgResourcePath(milSym)).getFile();
        			Document document = f.createDocument(new File(file).toURI().toString());
        			appendDocument(newSvgDocument, svgRoot, document);
	        	}catch(Exception e) {
	    			logger.log(Level.WARNING, "Unable to create Status for "+
	        	milSym.getSymbolSet()+
	        	", "+milSym.getStatus()+
	        	", "+ResourceManager.getStatusSvgResourcePath(milSym)
	        	+".");
	    		}
        	}        	

        	
        	// Entities assembly, SIDC position 11-16
        	if(true) {
	    		try {
	    			String path = ResourceManager.getEntitySvgResourcePath(milSym);
	    			String file = App.class.getClassLoader().getResource(path).getFile();
	            	Document document = f.createDocument(new File(file).toURI().toString());
	            	appendDocument(newSvgDocument, svgRoot, document);
	        	}catch(Exception e) {
	    			logger.log(Level.WARNING, "Unable to create Entity icons for "+
			        	milSym.getSymbolSet()+
			        	", "+milSym.getAmplifier()+
			        	", "+ResourceManager.getAmplifierSvgResourcePath(milSym)
			        	+".");
	    		}
        		
        	}
        	
        	return (SVGDocument) newSvgDocument;
	}
	
	private static void appendDocument(Document parent, Node root, Document child) {
    	//Node n = doc.importNode(document.getDocumentElement().getElementsByTagName("g").item(0), true);
    	//NodeList nL = document.getDocumentElement().getElementsByTagName("g");
		NodeList nL = child.getDocumentElement().getChildNodes();
    	for(int i =0; i<nL.getLength(); i++) {
    		Node n = nL.item(i);
    		
    		if(n.getNodeType() == Node.ELEMENT_NODE ) {
    			Element e = (Element)n;
        		String display = e.getAttribute("display");
        		//Node p = n.get
        		if(!display.equalsIgnoreCase("none")) {
        			Node t = parent.importNode(n, true);
	            	root.appendChild(t);
        		}	
    		}
    	}
	}
	
}
