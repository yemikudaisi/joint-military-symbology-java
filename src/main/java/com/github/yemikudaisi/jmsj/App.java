package com.github.yemikudaisi.jmsj;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;

import com.github.yemikudaisi.jmsj.symbology.BrigadeBelowEchelonAmplifier;
import com.github.yemikudaisi.jmsj.symbology.Entity;
import com.github.yemikudaisi.jmsj.symbology.EntityModifierHeirarchy;
import com.github.yemikudaisi.jmsj.symbology.EntitySubType;
import com.github.yemikudaisi.jmsj.symbology.EntityType;
import com.github.yemikudaisi.jmsj.symbology.HQTFDummy;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.Modifier;
import com.github.yemikudaisi.jmsj.symbology.NotApplicableAmplifier;
import com.github.yemikudaisi.jmsj.symbology.StandardEntityOnes;
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
    	milSym.setStandardEntityOne(StandardEntityOnes.Reality);
    	milSym.setStandardEntityTwo(StandardEntityTwos.Friend);
    	milSym.setSymbolSet(SymbolSets.LandUnits);
    	milSym.setStatus(Status.Present);
    	milSym.setStatusAmplifierMode(StatusAmplifierModes.Default);
    	milSym.setHqTFDummy(HQTFDummy.NotApplicable);
    	milSym.setAmplifier(BrigadeBelowEchelonAmplifier.Battalion);
    	milSym.setEntity(new Entity("11","130000"));
    	milSym.setEntityType(new EntityType("01","110300"));
    	milSym.setEntitySubType(new EntitySubType("00","010096"));
    	//milSym.setSectorOneModifer(new Modifier("",""));
    	//milSym.setSectorTwoModifer("");

    	EntityModifierHeirarchy h = MilitarySymbolFactory.getEnityModifierHeirarchyForSymbolSet(milSym.getSymbolSet());
    	//System.out.println(h);
    	
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
