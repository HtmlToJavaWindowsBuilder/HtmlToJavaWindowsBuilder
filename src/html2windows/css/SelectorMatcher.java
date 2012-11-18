package html2windows.css;

import java.util.ArrayList;

import html2windows.dom.Document;
import html2windows.dom.Element;

public interface SelectorMatcher {
    public ArrayList<Element> getElementBySelector(String selector, Document document);
}
