package com.github.yemikudaisi.jmsj;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;

import com.github.yemikudaisi.jmsj.symbology.BrigadeBelowEchelonAmplifier;
import com.github.yemikudaisi.jmsj.symbology.EntityModifierHeirarchy;
import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.StandardEntityTwos;
import com.github.yemikudaisi.jmsj.symbology.Status;
import com.github.yemikudaisi.jmsj.symbology.StatusAmplifierModes;
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
    	milSym.setStandardEntityTwo(StandardEntityTwos.AssumedFriend);
    	milSym.setAmplifier(BrigadeBelowEchelonAmplifier.Company);
    	milSym.setHqTFDummy(HQTFDummy.Headquarters);
    	milSym.setStatusAmplifierMode(StatusAmplifierModes.Alternate);
    	//milSym.setSymbolSet(SymbolSets.Air);
    	milSym.setSymbolSet(SymbolSets.LandUnits);
    	milSym.setStatus(Status.PresentDestroyed);
    	EntityModifierHeirarchy h = MilitarySymbolFactory.getEnityModifierHeirarchyForSymbolSet(milSym.getSymbolSet());
    	System.out.println(h);

    	System.out.println("SIDC: "+milSym);
    	showSymbol(milSym);
    }
    
    public static void showSymbol(MilitarySymbol milSym) {
        JSVGCanvas c = new JSVGCanvas();
        SVGDocument d = SvgFactory.createSymbolSvg(milSym);
        c.setSVGDocument(d);
        
        JFrame f = new JFrame("svg");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(c);
        f.setVisible(true);
    }
}
