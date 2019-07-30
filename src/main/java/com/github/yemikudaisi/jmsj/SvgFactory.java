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
import org.w3c.dom.svg.SVGDocument;

import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.NotApplicableAmplifier;
import com.github.yemikudaisi.jmsj.symbology.Status;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class SvgFactory {
	static Logger logger = Logger.getLogger(SvgFactory.class.getName());
	
	public static SVGDocument createSymbolSvg(MilitarySymbol milSym) {
		try {
			//TODO: Check of amplifiers and HQTFDummy has been set
    		String parser = XMLResourceDescriptor.getXMLParserClassName();
    	    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        	
        	DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        	String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        	Document doc = impl.createDocument(svgNS, "svg", null);
        	Element svgRoot = doc.getDocumentElement();
        	
        	//TODO: Figure out the height and width of the tallest and widest

        	svgRoot.setAttributeNS(null, "width", "1000");
        	svgRoot.setAttributeNS(null, "height", "1000");
        	
        	//TODO:METOC and Cyberspace has no frame
        	// FIXME: Hard-coded, make more flexible
        	
        	// Symbol Set Frame (Including projected Status)
        	if(milSym.getSymbolSet() != SymbolSets.MeteorologicalAtmospheric ||
        			milSym.getSymbolSet() != SymbolSets.MeteorologicalOceanographic ||
        			milSym.getSymbolSet() != SymbolSets.MeteorologicalSpace ||
        			milSym.getSymbolSet() != SymbolSets.Cyberspace) {
        		try {
        			String frameFile = App.class.getClassLoader().getResource(ResourceManager.getFrameSvgResourcePath(milSym)).getFile();
                	Document frameDocument = f.createDocument(new File(frameFile).toURI().toString());
                	Node n = doc.importNode(frameDocument.getDocumentElement().getElementsByTagName("g").item(0), true);
                	svgRoot.appendChild(n);
        			
        		}catch(NullPointerException e) {
        			logger.log(Level.WARNING, "Unable to create frame SVG for "+
        		milSym.getSymbolSet().getDescription()+
        		", "+ResourceManager.getFrameSvgResourcePath(milSym)
        		+".");
        		}
        	}
        	
        	// HQTFDummy assembly
        	if(milSym.getHqTFDummy() != HQTFDummy.NotApplicable) {
        		try {
	        		String hqtfdFile = App.class.getClassLoader().getResource(ResourceManager.getHqTfDummySvgResourcePath(milSym)).getFile();
	            	Document hqtfdDocument = f.createDocument(new File(hqtfdFile).toURI().toString());
	            	Node n = doc.importNode(hqtfdDocument.getDocumentElement().getElementsByTagName("g").item(0), true);
	            	svgRoot.appendChild(n);
	        	}catch(NullPointerException e) {
	    			logger.log(Level.WARNING, "Unable to create HQ TF Dummy SVG for "+ milSym.getSymbolSet()+
	    					", "+milSym.getHqTFDummy()+
	    					", "+ResourceManager.getHqTfDummySvgResourcePath(milSym)+
	    					".");
	    		}
        	}
        	
        	// Amplifiers assembly
        	// Only caters for status damage and broken, frame affecting statuses handled in frame assemble above
        	if(!(milSym.getAmplifier() instanceof NotApplicableAmplifier)) {
        		try {
        			String file = App.class.getClassLoader().getResource(ResourceManager.getAmplifierSvgResourcePath(milSym)).getFile();
                	Document amplifierDocument = f.createDocument(new File(file).toURI().toString());
                	Node n = doc.importNode(amplifierDocument.getDocumentElement().getElementsByTagName("g").item(0), true);
                	svgRoot.appendChild(n);
	        	}catch(NullPointerException e) {
	    			logger.log(Level.WARNING, "Unable to create Amplifier for "+
	        	milSym.getSymbolSet()+
	        	", "+milSym.getHqTFDummy()+
	        	", "+ResourceManager.getAmplifierSvgResourcePath(milSym)
	        	+".");
	    		}
        		
        	}        	
        	
        	// Status\Operational Condition Amplifier
        	if(milSym.getStatus() == Status.PresentDamaged || milSym.getStatus() == Status.PresentDestroyed) {
        		try {
        			String file = App.class.getClassLoader().getResource(ResourceManager.getStatusSvgResourcePath(milSym)).getFile();
                	Document document = f.createDocument(new File(file).toURI().toString());
                	Node n = doc.importNode(document.getDocumentElement().getElementsByTagName("g").item(0), true);
                	svgRoot.appendChild(n);
	        	}catch(NullPointerException e) {
	    			logger.log(Level.WARNING, "Unable to create Status for "+
	        	milSym.getSymbolSet()+
	        	", "+milSym.getStatus()+
	        	", "+ResourceManager.getStatusSvgResourcePath(milSym)
	        	+".");
	    		}
        	}
        	
        	return (SVGDocument) doc;
    	}catch(IOException e) {
    		System.out.println(e.getMessage());
    	}
    	return null;
	}
}
