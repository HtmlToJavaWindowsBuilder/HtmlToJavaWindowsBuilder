package html2windows.css;
import java.io.File;

import html2windows.css.CustomLayoutManager;
import html2windows.css.CSSPainter;
import html2windows.css.DimensionProperty;
import html2windows.css.Style;
import html2windows.dom.ElementInter;
import html2windows.dom.Element;
import html2windows.dom.Document;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;

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
public class DimensionTest { 

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    @Test
    public void testPainterWithBackgroundColor() throws Exception {
        JFrame frame = new JFrame("Button Frame");
        JPanel customPanel = new JPanel( new CustomLayoutManager());
        Document document = new Document();

        String tagName = "h1";
        ElementInter elementInter = new ElementInter(tagName);
        elementInter.setOwnerDocument(document);

        Element elementNode = elementInter;
        //elementNode.setPreferredSize(new Dimension(100, 100));
        //Element elementNode = document.createElement(tagName);
        Style style = elementNode.getStyle();
        style.setProperty("font-size","12");
        style.setProperty("background-color","orange");
        style.setProperty("font-weight","bold");
        style.setProperty("font-style","italic");
        style.setProperty("font-variant","small-caps");

        document.setPainter(new FontProperty("111"));

        JButton btn = new JButton("OK");
        JButton btn1 = new JButton("OK");

        customPanel.add(btn);
        customPanel.add(document);
        customPanel.add(btn1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360,200);
        frame.setLocationRelativeTo(null);
        frame.add(customPanel);
        frame.setVisible(true);
    }
}
