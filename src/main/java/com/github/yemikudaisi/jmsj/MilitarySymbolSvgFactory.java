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

import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;

public class MilitarySymbolSvgFactory {
	public static SVGDocument createSvg(MilitarySymbol milSym) {
		try {
    		String parser = XMLResourceDescriptor.getXMLParserClassName();
    	    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
    	    String frameFile = App.class.getClassLoader().getResource(JmsmlFileSystem.getFrameFileName(milSym)).getFile();
    	    String hqtfdFile = App.class.getClassLoader().getResource(JmsmlFileSystem.getHqTfFileName(milSym)).getFile();
    	    String ampFile = App.class.getClassLoader().getResource(JmsmlFileSystem.getAmplifierFileName(milSym)).getFile();
        	Document frameDocument = f.createDocument(new File(frameFile).toURI().toString());
        	Document hqtfdDocument = f.createDocument(new File(hqtfdFile).toURI().toString());
        	Document amplifierDocument = f.createDocument(new File(ampFile).toURI().toString());
        	
        	DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        	String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        	Document doc = impl.createDocument(svgNS, "svg", null);
        	Element svgRoot = doc.getDocumentElement();
        	
        	//TODO: Figure out the height anf width of the tallest and widest

        	svgRoot.setAttributeNS(null, "width", "1000");
        	svgRoot.setAttributeNS(null, "height", "1000");
        	Node n = doc.importNode(frameDocument.getDocumentElement().getElementsByTagName("g").item(0), true);
        	svgRoot.appendChild(n);
        	n = doc.importNode(hqtfdDocument.getDocumentElement().getElementsByTagName("g").item(0), true);
        	svgRoot.appendChild(n);
        	n = doc.importNode(amplifierDocument.getDocumentElement().getElementsByTagName("g").item(0), true);
        	svgRoot.appendChild(n);
        	
        	return (SVGDocument) doc;
    	}catch(IOException e) {
    		System.out.println(e.getMessage());
    	}
    	return null;
	}
}
