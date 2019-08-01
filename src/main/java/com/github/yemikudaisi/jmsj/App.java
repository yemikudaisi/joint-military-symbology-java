package com.github.yemikudaisi.jmsj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    	//MilitarySymbol milSym = new MilitarySymbol();
    	// "10 03 10 0 0 31 130395 03 04"
    	// "10 03 35 0 0 00 110102 08 00"
    	MilitarySymbol milSym = MilitarySymbolFactory.createSymbol("10 03 35 0 0 00 110102 08 09");
    	/**milSym.setStandardEntityOne(StandardEntityOnes.Reality);
    	milSym.setStandardEntityTwo(StandardEntityTwos.Friend);
    	milSym.setSymbolSet(SymbolSets.LandUnits);
    	milSym.setStatus(Status.Present);
    	milSym.setStatusAmplifierMode(StatusAmplifierModes.Default);
    	milSym.setHqTFDummy(HQTFDummy.NotApplicable);
    	milSym.setAmplifier(BrigadeBelowEchelonAmplifier.Battalion);
    	milSym.setEntity(new Entity("11","130000"));
    	milSym.setEntityType(new EntityType("01","110300"));
    	milSym.setEntitySubType(new EntitySubType("00","010096"));
    	milSym.setSectorOneModifier(new Modifier("03","03"));
    	milSym.setSectorTwoModifier(new Modifier("04","04"));
    	*/

    	EntityModifierHeirarchy h = MilitarySymbolFactory.getEnityModifierHeirarchyForSymbolSet(milSym.getSymbolSet());
    	//System.out.println(h);
    	
    	System.out.println("SIDC: "+milSym);
    	showSymbol(milSym);
    }
    
    public static void showSymbol(MilitarySymbol milSym) {
        JFrame f = new JFrame("svg");
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JSVGCanvas canvas = new JSVGCanvas();
        SVGDocument d = SvgFactory.createSymbolSvg(milSym);
        canvas.setSVGDocument(d);
        canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        panel.add(canvas,c);
        
        JTextField sidcTextField = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.weightx = 0.6;
        c.anchor = GridBagConstraints.PAGE_END;
        panel.add(sidcTextField,c);
        
        JButton showSymbolButton = new JButton("Show symbol");
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.weightx = 0.4;
        panel.add(showSymbolButton,c);        
        
        f.setSize(612, 792);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(canvas);
        f.setVisible(true);
    }
}
