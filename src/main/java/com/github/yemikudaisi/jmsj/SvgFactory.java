package com.github.yemikudaisi.jmsj;

import java.awt.Graphics2D;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.Status;
import com.github.yemikudaisi.jmsj.symbology.StatusAmplifierModes;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

public class SvgFactory {
	static Logger logger = Logger.getLogger(SvgFactory.class.getName());
	
	public static SVGDocument createSymbolSvg(MilitarySymbol milSym) {
		return createSymbolSvg(milSym,612, 792);
	}
	
	public static SVGDocument createSymbolSvg(MilitarySymbol milSym, int percentage) {
		return createSymbolSvg(milSym,(double)(612*percentage)/100, (double)(792*percentage)/100);
	}
	
	public static SVGDocument createSymbolSvg(MilitarySymbol milSym, double width, double height) {
			//TODO: Check of amplifiers and HQTFDummy has been set
    		String parser = XMLResourceDescriptor.getXMLParserClassName();
    	    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        	
        	DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        	String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        	Document newSvgDocument = impl.createDocument(svgNS, "svg", null);
        	Element svgRoot = newSvgDocument.getDocumentElement();
        	
        	//TODO:METOC and Cyberspace has no frame
        	//FIXME: Hard-coded, make more flexible
        	
        	//TODO: Use land unit of land cicilian, give option for using civilian frame
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
        	
        	// Echelon/Equipment mobility/ Naval towed array amplifiers, SIDC position
        			
        	if(MilitarySymbolFactory.isAmplifierApplicable(milSym) && 
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
        	
        	// Frame affecting statuses are handled in frame assembly above
        	if(milSym.getStatusAmplifierMode() == StatusAmplifierModes.Alternate || 		// If the alternate status symbol 
        			(milSym.getStatusAmplifierMode() == StatusAmplifierModes.Default && 	// Status amplifier mode is default and status is damaged or destroyed
        				(milSym.getStatusAmplifier() == Status.PresentDamaged || 					// damaged or 
        				milSym.getStatusAmplifier() == Status.PresentDestroyed))) { 					// destroyed
        		try {
        			String file = App.class.getClassLoader().getResource(ResourceManager.getStatusSvgResourcePath(milSym)).getFile();
        			Document document = f.createDocument(new File(file).toURI().toString());
        			// FIXME: Alternate status amplifier covering echelon (e.g Naval towed arrays)
        			appendDocument(newSvgDocument, svgRoot, document);
	        	}catch(Exception e) {
	    			logger.log(Level.WARNING, "Unable to create Status for "+
	        	milSym.getSymbolSet()+
	        	", "+milSym.getStatusAmplifier()+
	        	", "+ResourceManager.getStatusSvgResourcePath(milSym)
	        	+".");
	    		}
        	}        	
        	
        	// Entities assembly, SIDC position 11-16
        	if(true) {
    			List<String> paths = ResourceManager.getEntitySvgResourcePath(milSym);
	    		for(String path:paths) {
	    			try {
		    			String file = App.class.getClassLoader().getResource(path).getFile();
		            	Document document = f.createDocument(new File(file).toURI().toString());
		            	appendDocument(newSvgDocument, svgRoot, document);
		        	}catch(Exception e) {
		    			logger.log(Level.WARNING, "Unable to create Entity icons for "+
				        	milSym.getSymbolSet()+
				        	", "+milSym.getAmplifier()+
				        	", "+ResourceManager.getAmplifierSvgResourcePath(milSym)
				        	+".");
		    			continue;
		    		}
	    		}
        		
        	}
        	
        	// if both modifiers were assigned
        	if(true) {
    			List<String> paths = ResourceManager.getModifiersSvgResourcePath(milSym);
	    		for(String path:paths) {
	    			try {
		    			String file = App.class.getClassLoader().getResource(path).getFile();
		            	Document document = f.createDocument(new File(file).toURI().toString());
		            	appendDocument(newSvgDocument, svgRoot, document);
		        	}catch(Exception e) {
		    			logger.log(Level.WARNING, "Unable to get modifier symbols for "+
				        	milSym.getSymbolSet()+
				        	", "+path
				        	+".");
		    			continue;
		    		}
	    		}
        		
        	}
        	
        	//svgRoot.setAttributeNS(null, "width", width+"px");
        	//svgRoot.setAttributeNS(null, "height", height+"px");
        	svgRoot.setAttributeNS(null, "width", "500px");
        	svgRoot.setAttributeNS(null, "height", "700px");
        	return (SVGDocument) newSvgDocument;
	}
	
	private static void compareSize(int maxHeight, int maxWidth, Document document) {
		int h = Integer.parseInt(document.getDocumentElement().getAttribute("height").replace("px", ""));
		int w = Integer.parseInt(document.getDocumentElement().getAttribute("width").replace("px", ""));
		maxHeight = Math.max(maxHeight, h);
		maxWidth = Math.max(maxWidth, w);
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
	
	public static JSVGCanvas createCanvas(MilitarySymbol milSym) {
		JSVGCanvas canvas = new JSVGCanvas();
        SVGDocument d = SvgFactory.createSymbolSvg(milSym);
        canvas.setSVGDocument(d);
        return canvas;
	}
	
	public static void test(MilitarySymbol milSym, Graphics2D g2d) {
		SVGDocument d = SvgFactory.createSymbolSvg(milSym);
		UserAgent userAgent = new UserAgentAdapter();
		DocumentLoader loader = new DocumentLoader(userAgent);
		BridgeContext ctx = new BridgeContext(userAgent, loader);
		ctx.setDynamicState(BridgeContext.DYNAMIC);
		GVTBuilder builder = new GVTBuilder();
		GraphicsNode rootGN = builder.build(ctx, d);
		rootGN.paint(g2d);
	}
	
}
