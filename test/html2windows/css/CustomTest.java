package html2windows.dom;
import java.io.File;

import html2windows.css.FontProperty;
import html2windows.css.DimensionProperty;
import html2windows.css.CustomLayoutManager;
import html2windows.css.Style;
import html2windows.dom.ElementInter;
import html2windows.dom.Document;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class CustomTest { 

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void test() throws Exception {
        JFrame frame = new JFrame("Button Frame");
        JPanel customPanel = new JPanel( new CustomLayoutManager());

        DimensionProperty dimension = new DimensionProperty();

        String tagName = "h1";
        ElementInter fontNode = new ElementInter(tagName);
        Style Type = new Style(fontNode);
        Graphics g = null;

        Type.setProperty("width","100");
        Type.setProperty("height","100");
        Type.setProperty("background-color","orange");
        dimension.paint(Type, fontNode, g);
        
        JButton btn = new JButton("OK");
        JButton btn1 = new JButton("OK");
        customPanel.add(btn);
        customPanel.add(btn1);
        customPanel.add(dimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360,200);
        frame.setLocationRelativeTo(null);
        frame.add(customPanel);
        frame.setVisible(true);
    }

}
