package com.github.yemikudaisi.jmsj;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;

import javax.swing.JFrame;

import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.StandardEntityOnes;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MilitarySymbol milSym = new MilitarySymbol();
        milSym.setStandardEntityOne(StandardEntityOnes.Exercise);
        milSym.setHqTFDummy(HQTFDummy.FientDummyHeadquarters);
        //String frameFile = JmsmlFileSystem.getFrameFileName(milSym);
        String ampFile = JmsmlFileSystem.getAmplifierFileName(milSym);
        //String hqtfdFile = JmsmlFileSystem.getHqTfFileName(milSym);
        JSVGCanvas c = new JSVGCanvas();
        String file = App.class.getClassLoader().getResource(ampFile).getFile();
        String uri = new File(file).toURI().toString();
		//c.setURI(uri);
        SVGDocument d = buildSymbolSvg(milSym);
        c.setSVGDocument(d);
        

        // A frame for the canvas to live in
        JFrame f = new JFrame("svg");
        f.setSize(600, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the canvas to the frame
        f.getContentPane().add(c);

        // Show the frame
        f.setVisible(true);
    }
    
    private static SVGDocument buildSymbolSvg(MilitarySymbol milSym) {
    	try {
    		String parser = XMLResourceDescriptor.getXMLParserClassName();
    	    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        	Document frameDocument = f.createDocument(JmsmlFileSystem.getFrameFileName(milSym));
        	Document hqtfdDocument = f.createDocument(JmsmlFileSystem.getHqTfFileName(milSym));
        	Document amplifierDocument = f.createDocument(JmsmlFileSystem.getAmplifierFileName(milSym));
        	
        	DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();

        	// Create a new document
        	String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        	Document doc = impl.createDocument(svgNS, "svg", null);
        
        	// Get the root element (the 'svg' element).
        	Element svgRoot = doc.getDocumentElement();
        	
        	//TODO: Figure out the height anf width of the tallest and widest
        	
        	// Set the width and height attributes on the root 'svg' element.
        	svgRoot.setAttributeNS(null, "width", "1000");
        	svgRoot.setAttributeNS(null, "height", "1000");
        	svgRoot.appendChild(frameDocument);
        	svgRoot.appendChild(hqtfdDocument);
        	svgRoot.appendChild(amplifierDocument);
        	
        	return (SVGDocument) doc;
    	}catch(IOException e) {
    		
    	}
    	return null;
    }
    
    
    
    private void some() {
    	// A canvas to show "circles.svg"
    	JSVGCanvas c = new JSVGCanvas();
    	String uri = new File("jmsml/svg/MIL_STD_2525D_Symbols/Frames/0_000_0.svg").toURI().toString();
    	c.setURI(uri);
    }
    
    private void createDoc() {
    	DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();

    	// Create a new document
    	String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    	Document doc = impl.createDocument(svgNS, "svg", null);

    	// Get the root element (the 'svg' element).
    	Element svgRoot = doc.getDocumentElement();

    	// Set the width and height attributes on the root 'svg' element.
    	svgRoot.setAttributeNS(null, "width", "400");
    	svgRoot.setAttributeNS(null, "height", "450");

    	// Create the rectangle.
    	Element rectangle = doc.createElementNS(svgNS, "rect");
    	rectangle.setAttributeNS(null, "x", "10");
    	rectangle.setAttributeNS(null, "y", "20");
    	rectangle.setAttributeNS(null, "width", "100");
    	rectangle.setAttributeNS(null, "height", "50");
    	rectangle.setAttributeNS(null, "fill", "red");

    	// Attach the rectangle to the root 'svg' element.
    	svgRoot.appendChild(rectangle);
    }
    
    private void createDocFromXml() {
    	try {
    	    String parser = XMLResourceDescriptor.getXMLParserClassName();
    	    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);

    	    // Construct a document from XML in a string
    	    StringReader r = new StringReader("");
    	    Document doc1 = f.createDocument(r.toString());

    	    // Construct a document from XML at a URI
    	    String uri = "http://xmlgraphics.apache.org/batik/demo/barChart.svg";
    	    Document doc2 = f.createDocument(uri);
    	} catch (IOException ioe) {
    	    // ...
    	}
    }
}
