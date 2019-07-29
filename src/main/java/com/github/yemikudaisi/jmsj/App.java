package com.github.yemikudaisi.jmsj;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;

import com.github.yemikudaisi.jmsj.symbology.BrigadeBelowEchelonAmplifier;
import com.github.yemikudaisi.jmsj.symbology.EntityModifierHeirarchy;
import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.StandardEntityOnes;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	MilitarySymbol milSym = new MilitarySymbol();
    	//milSym.setAmplifier(BrigadeBelowEchelonAmplifier.Company);
    	//milSym.setHqTFDummy(HQTFDummy.TaskForce);
    	//showSymbol(milSym);
    	
    	milSym.setSymbolSet(SymbolSets.Air);
    	showSymbol(milSym);
    	EntityModifierHeirarchy h = MilitarySymbolFactory.build(milSym.getSymbolSet());
    	System.out.println(h);
        
    }
    
    public static void showSymbol(MilitarySymbol milSym) {
        //milSym.setStandardEntityOne(StandardEntityOnes.Simulation);
        //milSym.setHqTFDummy(HQTFDummy.FientDummyHeadquarters);
        //milSym.setAmplifier(BrigadeBelowEchelonAmplifier.Battalion);

        JSVGCanvas c = new JSVGCanvas();
        SVGDocument d = MilitarySymbolSvgFactory.createSvg(milSym);
        c.setSVGDocument(d);
        
        JFrame f = new JFrame("svg");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(c);
        f.setVisible(true);
    }
}
