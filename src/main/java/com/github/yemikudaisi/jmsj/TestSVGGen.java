package com.github.yemikudaisi.jmsj;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;

import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;

import org.w3c.dom.DOMImplementation;

public class TestSVGGen {

  public void paint(Graphics2D g2d) {
    g2d.setPaint(Color.red);
    g2d.fill(new Rectangle(10, 10, 100, 100));
  }

  public static void main(String[] args) throws IOException {

    // Get a DOMImplementation.
    DOMImplementation domImpl =
      GenericDOMImplementation.getDOMImplementation();

    // Create an instance of org.w3c.dom.Document.
    String svgNS = "http://www.w3.org/2000/svg";
    MilitarySymbol milSym = MilitarySymbolFactory.createSymbol("10-0-3-10-0-0-16-120300-00-00");
    Document document = SvgFactory.createSymbolSvg(milSym);

    // Create an instance of the SVG Generator.
    SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

    // Ask the test to render into the SVG Graphics2D implementation.
    //TestSVGGen test = new TestSVGGen();
    //test.paint(svgGenerator);

    // Finally, stream out SVG to the standard output using
    // UTF-8 encoding.
    boolean useCSS = true; // we want to use CSS style attributes
    Writer out = new OutputStreamWriter(System.out, "UTF-8");
    svgGenerator.stream(out, useCSS);
  }
}