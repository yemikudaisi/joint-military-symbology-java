package com.github.yemikudaisi.jmsj;

import java.io.File;
import java.io.IOException;

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
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class MilitarySymbolSvgFactory {
	public static SVGDocument createSvg(MilitarySymbol milSym) {
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
        	
        	if(milSym.getSymbolSet() != SymbolSets.MeteorologicalAtmospheric ||
        			milSym.getSymbolSet() != SymbolSets.MeteorologicalOceanographic ||
        			milSym.getSymbolSet() != SymbolSets.MeteorologicalSpace ||
        			milSym.getSymbolSet() != SymbolSets.Cyberspace) {
        		try {
        			
        		}catch(NullPointerException e) {
        			System.out.println(e.getMessage());	
        		}
        	}
        	
        	if(milSym.getHqTFDummy() != HQTFDummy.NotApplicable) {
        		String hqtfdFile = App.class.getClassLoader().getResource(ResourceManager.getHqTfFileName(milSym)).getFile();
            	Document hqtfdDocument = f.createDocument(new File(hqtfdFile).toURI().toString());
            	Node n = doc.importNode(hqtfdDocument.getDocumentElement().getElementsByTagName("g").item(0), true);
            	svgRoot.appendChild(n);
        	}
        	
        	if(!(milSym.getAmplifier() instanceof NotApplicableAmplifier)) {
        		String ampFile = App.class.getClassLoader().getResource(ResourceManager.getAmplifierFileName(milSym)).getFile();
            	Document amplifierDocument = f.createDocument(new File(ampFile).toURI().toString());
            	Node n = doc.importNode(amplifierDocument.getDocumentElement().getElementsByTagName("g").item(0), true);
            	svgRoot.appendChild(n);
        	}
        	
        	return (SVGDocument) doc;
    	}catch(IOException e) {
    		System.out.println(e.getMessage());
    	}
    	return null;
	}
}
