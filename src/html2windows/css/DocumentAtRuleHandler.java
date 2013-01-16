package html2windows.css;

import html2windows.dom.Document;

public class DocumentAtRuleHandler implements AtRuleHandler {

    @Override
    public void handle(String value, Document document) {
        CSSRuleSet ruleSet = new CSSParser().parseRuleSet(value);

        String widthStr = ruleSet.getProperty("width");
        String heightStr = ruleSet.getProperty("height");
        int width = parseInt(widthStr);
        int height = parseInt(heightStr);

        document.setSize(width, height);
    }

    private int parseInt(String str) {
        try {
            return Integer.valueOf(str);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
}
