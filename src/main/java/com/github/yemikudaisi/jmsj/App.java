package com.github.yemikudaisi.jmsj;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;

import com.github.yemikudaisi.jmsj.symbology.SymbolSetEntityModifierTree;
import com.github.yemikudaisi.jmsj.symbology.SymbolSets;
import com.github.yemikudaisi.jmsj.symbology.MilitarySymbol;
import com.github.yemikudaisi.jmsj.symbology.StatusAmplifierModes;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// Tested against symbols generated from https://sidc.milsymb.net/#/APP6
    	MilitarySymbolFactory.setStatusAmplifierMode(StatusAmplifierModes.Default);
    	//MilitarySymbol milSym = MilitarySymbolFactory.createSymbol("10-0-0-20-2-4-61-120501-03-01");
    	MilitarySymbol milSym = MilitarySymbolFactory.createSymbol("10-0-3-10-0-0-16-120300-00-00");
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
    	//SymbolSetEntityModifierTree h = MilitarySymbolFactory.createSymbolSetEntityModifierTree(SymbolSets.LandUnits);
    	//System.out.println(h);
    	
    	//System.out.println("SIDC: "+milSym);
    	showSymbol(milSym);
    }
    
    public static void showSymbol(MilitarySymbol milSym) {
        JFrame f = new JFrame("SIDC: "+milSym);
        
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
        panel.setBackground(Color.DARK_GRAY);
        
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
