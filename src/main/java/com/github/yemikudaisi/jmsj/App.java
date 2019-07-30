package com.github.yemikudaisi.jmsj;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;

import com.github.yemikudaisi.jmsj.symbology.BrigadeBelowEchelonAmplifier;
import com.github.yemikudaisi.jmsj.symbology.EntityModifierHeirarchy;
import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.StandardEntityOnes;
import com.github.yemikudaisi.jmsj.symbology.StandardEntityTwos;
import com.github.yemikudaisi.jmsj.symbology.Status;
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
    	milSym.setHqTFDummy(HQTFDummy.TaskForce);    	
    	milSym.setSymbolSet(SymbolSets.Air);
    	milSym.setStatus(Status.PresentDamaged);
    	showSymbol(milSym);
    	EntityModifierHeirarchy h = MilitarySymbolFactory.getEnityModifierHeirarchyForSymbolSet(milSym.getSymbolSet());
    	System.out.println(h);        
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
