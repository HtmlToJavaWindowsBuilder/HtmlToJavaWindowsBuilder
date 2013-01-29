package html2windows.css;

import html2windows.dom.Document;

public interface AtRuleHandler {
    public void handle(String value, Document document);
}
