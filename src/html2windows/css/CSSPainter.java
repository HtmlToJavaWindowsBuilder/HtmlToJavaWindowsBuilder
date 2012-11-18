package html2windows.css;

import java.awt.Graphics;

import html2windows.dom.Element;

public interface CSSPainter {
    public void paint(Style style, Element element, Graphics g);
}
