package html2windows.css;

import html2windows.css.level1.CSS1SelectorMatcher;
import html2windows.dom.Document;
import html2windows.dom.Element;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Use for add style to each element
 * 
 * @author mutant
 */
public class CSSParser {
    // the input String of CSS, to parse whether syntax is correct or not, then
    // add style to each element
    String cssString;
    // the position of character of cssString
    int pos;
    Document document;
    // author normal priority is 3
    private static final int priority = 3;
    public CSSRuleSet ruleSet;

    /**
     * Call this to start CSSParser, and it will parse cssString, 
     * and add style to each element
     * 
     * @param cssString the cssRuleSet, used to add style to each element
     * @param document  the root of tree which has been built
     */
    
    public void parse(String cssString, Document document) {
        this.cssString = cssString;
        this.pos = 0;
        this.document = document;

        this.ruleSet = new CSSRuleSet(priority);

        parseStyleSheet();
    }
    
    public CSSRuleSet parseRuleSet(String str){
        ruleSet = new CSSRuleSet(priority);
        cssString = str;
        pos = 0;
        
        if (isNotEnd() && getChar() == '{') {
            pos++;
            parseSpace();
            if (isNotEnd()) {
                parseDeclaration();
                while (isNotEnd() && getChar() == ';') {
                    pos++;
                    parseSpace();
                    parseDeclaration();
                }
                if (isNotEnd() && getChar() == '}') {
                    pos++;
                    parseSpace();
                }
            }
        }
        
        return ruleSet;
    }
    
    /**
     * @return true if cssString is end, false otherwise;
     */
    private boolean isNotEnd() {
        return (pos < cssString.length());
    }

    /**
     * @return the position character of cssString
     */
    private char getChar() {
        // css syntax is case-insensitive
        return Character.toLowerCase(cssString.charAt(pos));
    }

    /**
     * Rule: [ CDO | CDC | S | statement ]*;
     */
    private void parseSpace() {
        char ch;

        if (isNotEnd()) {
            ch = getChar();
            while (ch == '\t' || ch == '\r' || ch == '\n' || ch == '\f'
                    || ch == ' ') {
                pos++;
                if (isNotEnd()) {
                    ch = getChar();
                }
                else
                    break;
            }
        }
    }

    /**
     * Rule: [ CDO | CDC | S | statement ]*;
     */
    private void parseStyleSheet() {
        while (isNotEnd()) {
            parseSpace();
            parseStatement();
        }
    }

    /**
     * Rule: ruleset | at-rule;
     */
    private void parseStatement() {
        if (isNotEnd()) {
            if (getChar() == '@') {
                pos++;
                parseAtRule();
            }
            else
                parseRuleSet();
        }
    }

    /**
     * Rule: ATKEYWORD S* any* [ block | ';' S* ];
     */
    private void parseAtRule() {
        int prePos;
        int nextPos;
        AtRuleHandler handler;
        
        if (isNotEnd()) {
            String ident = parseIdent();
            handler = document.getAtRuleHandler(ident);
            parseSpace();
            if (isNotEnd()) {
                prePos = pos;
                if (getChar() == '{') {
                    pos++;
                    while(isNotEnd() && getChar() != '}'){
                        pos++;
                    }
                }
                else if (getChar() == ';') {
                    pos++;
                    parseSpace();
                }
                nextPos = pos++;
                if(handler != null){
                    handler.handle(cssString.substring(prePos, nextPos ), document);
                }
            }
        }
    }

    /**
     * Rule: '{' S* [ any | block | ATKEYWORD S* | ';' S* ]* '}' S*;
     * 
     * @return  the part of content without '{' '}' ';'
     */
    private String parseBlock() {
        String buffer = "";
        char ch;

        if (isNotEnd()) {
            parseSpace();
            ch = getChar();
            while (isNotEnd() && ch != '}') {
                // block
                if (ch == '{') {
                    pos++;
                    buffer += parseBlock();
                }
                // ATKEYWORD
                else if (ch == '@') {
                    pos++;
                    buffer += parseIdent();
                }
                // ';'
                else if (ch == ';') {
                    pos++;
                    parseSpace();
                }
                // any
                else {
                    parseAny();
                }
            }

            if (isNotEnd() && ch == '}') {
                pos++;
                parseSpace();
            }
        }

        return buffer;
    }

    /**
     * Get seletorName and add ruleset to element.
     * 
     * Rule: selector? '{' S* declaration? [ ';' S* declaration? ]* '}' S*; 
     * 
     * @return  the elements which are inserted ruleSet, if parsing didn't work,
     *          return null
     */
    private void parseRuleSet() {
        ArrayList<Element> elements = null;
        String selector = "";

        if (isNotEnd()) {
            selector = "";
            ruleSet = new CSSRuleSet(priority);
            if (Pattern.compile("(. *?)\\{").matcher(cssString.substring(pos))
                    .find()) {
                selector += parseSelector();
                System.err.println(selector);

                if (isNotEnd() && getChar() == '{') {
                    pos++;
                    parseSpace();
                    if (isNotEnd()) {
                        parseDeclaration();
                        while (isNotEnd() && getChar() == ';') {
                            pos++;
                            parseSpace();
                            parseDeclaration();
                        }
                        if (isNotEnd() && getChar() == '}') {
                            pos++;
                            parseSpace();
                            // put ruleset into element
                            elements = new CSS1SelectorMatcher().getElementBySelector(selector, document);
                            for(Element e: elements){
                                e.getStyle().addCSSRuleSet(ruleSet);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Rule: any+; but character can't be '{'
     * 
     * @return  string of selector
     */
    private String parseSelector() {
        String selector = "";
        char ch;

        if (isNotEnd()) {
            do {
                ch = getChar();
                if (ch == '{') {
                    selector = selector.trim();
                    break;
                }
                else {
                    selector += parseAny();
                }
            }
            while (isNotEnd());
        }

        return selector;
    }

    /**
     * Rule: property S* ':' S* value;
     */
    private void parseDeclaration() {
        String property = "";
        String value = "";

        if (isNotEnd()) {
            property = parseProperty();
            parseSpace();
            if (isNotEnd()) {
                if (getChar() == ':') {
                    pos++;
                    parseSpace();
                    value = parseValue();
                }
            }
        }

        ruleSet.setProperty(property, value);
    }

    /**
     * Rule: IDENT;
     */
    private String parseProperty() {
        if (isNotEnd()) {
            return parseIdent();
        }

        return null;
    }

    /**
     * Rule: [ any | block | ATKEYWORD S* ]+;
     */
    private String parseValue() {
        String buffer = "";

        if (isNotEnd()) {
            do {
                // block
                if (getChar() == '{') {
                    pos++;
                    buffer += parseBlock();
                }
                // ATKEYWORD
                else if (getChar() == '@') {
                    pos++;
                    buffer += parseIdent();
                    parseSpace();
                }
                // any
                else {
                    buffer += parseAny();
                }
            }
            while (isNotEnd() && getChar() != ';');
        }

        return buffer;
    }

    /**
     * Rule: [ IDENT | NUMBER | PERCENTAGE | DIMENSION | STRING | DELIM | URI | HASH |
     * UNICODE-RANGE | INCLUDES | DASHMATCH | ':' | FUNCTION S* [any|unused]*
     * ')' | '(' S* [any|unused]* ')' | '[' S* [any|unused]* ']' ] S*;
     * 
     * @return character;
     */
    private char parseAny() {
        char ch = getChar();
        pos++;

        return ch;
    }

    /**
     * Rule: block | ATKEYWORD S* | ';' S* | CDO S* | CDC S*;
     */
    private void parseUnused() {
        if (getChar() == '{') {
            pos++;
            parseBlock();
        }
        else if (getChar() == '@') {
            pos++;
            parseIdent();
        }
        else if (getChar() == ';') {
            pos++;
            parseSpace();
        }
        else if (getChar() == '<') {
            pos++;
            parseCDO();
            parseSpace();
        }
        else if (getChar() == '-') {
            pos++;
            parseCDC();
            parseSpace();
        }
    }

    /**
     * Rule: <!--
     */
    private void parseCDO() {
        if (isNotEnd()) {
            if (isNotEnd() && getChar() == '!') {
                pos++;
                if (isNotEnd() && getChar() == '-') {
                    pos++;
                    if (isNotEnd() && getChar() == '-') {
                        pos++;
                    }
                }
            }
        }
    }

    /**
     * Rule: -->
     */
    private void parseCDC() {
        if (isNotEnd()) {
            if (isNotEnd() && getChar() == '-') {
                pos++;
            }
        }
    }

    /**
     * Rule: {ident}
     * 
     * @return  the parsing string
     */
    private String parseIdent() {
        char ch = 0;
        String buffer = "";

        if (isNotEnd()) {
            ch = getChar();
            if (ch == '-') {
                buffer += ch;
                pos++;
                if (isNotEnd())
                    ch = getChar();
            }

            if (isNmstart(ch)) {
                buffer += parseNmstart();
            }

            String temp;
            while ((temp = parseNmchar()) != "" && isNotEnd()) {
                buffer += temp;
            }

        }

        return buffer;
    }

    /**
     * Rule: [_a-z]|{nonascii}|{escape}
     * 
     * @param   input character
     * @return  true if ch is Nmstart, false otherwise;
     */
    private boolean isNmstart(char ch) {
        // [_a-z] || nonascii
        if (ch == 95 || (ch >= 97 && ch <= 122) || isNonascii(ch)) {
            return true;
        }
        // escape
        else if (isEscape(ch)) {
            return true;
        }

        return false;
    }

    /**
     * @param   input character
     * @return  true if ch is Nonascii, false otherwise;
     */
    private boolean isNonascii(char ch) {
        if (ch < 0 || ch > 159) {
            return true;
        }
        return false;
    }

    /**
     * @param   input character
     * @return  true if ch is Escape, false otherwise;
     */
    private boolean isEscape(char ch) {
        if (ch == 92) {
            pos++;
            ch = getChar();
            // unicode in escape
            if (isHex(ch)) {
                try {
                    // FIX IT
                    // int hexCh = parseUnicode();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                ch = getChar();
                if (ch == '\r') {
                    pos++;
                    ch = getChar();
                    if (ch == '\n') {
                        return true;
                    }
                }
                // space
                else if (ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t'
                        || ch == '\f') {
                    return true;
                }
            }
            // else in escape
            if (!(ch == '\n' || ch == '\r' || ch == '\f' || isHex(ch))) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param   input character
     * @return  true if ch is Nmchar, false otherwise;
     */
    private boolean isNmchar(char ch) {
        boolean isTrue;

        isTrue = false;
        if (ch == 95 || (ch >= 97 && ch <= 122) || (ch >= 48 && ch <= 57)
                || isNonascii(ch) || isEscape(ch)) {
            ch = getChar();
            isTrue = true;
        }

        return isTrue;
    }

    /**
     * @param   input character
     * @return  true if ch is Hex, false otherwise;
     */
    private boolean isHex(char ch) {
        return ((ch >= 48 && ch <= 57) || (ch >= 97 && ch <= 102));
    }

    /**
     * Rule: {nmchar}+
     */
    private void parseName() {
        if (isNotEnd()) {
            do {
                parseNmchar();
            }
            while (isNotEnd());
        }
    }

    /**
     * Rule: [_a-z]|{nonascii}|{escape}
     * 
     * @return  the Nmstart string
     */
    private String parseNmstart() {
        char ch;
        String buffer = "";

        if (isNotEnd()) {
            ch = getChar();
            // [_a-z]
            if (ch == '_' || (ch >= 97 && ch <= 122)) {
                buffer += ch;
                pos++;
            }
            // escape
            else if (ch == '\\') {
                buffer += ch;
                pos++;
                buffer += parseEscape();
            }
            // nonascii
            else {
                buffer += parseNonascii();
            }
        }

        return buffer;
    }

    /**
     * Rule: [_a-z0-9-]|{nonascii}|{escape}
     * 
     * @return  the Nmchar string
     */
    private String parseNmchar() {
        String buffer = "";
        char ch;

        if (isNotEnd()) {
            ch = getChar();
            // [_a-z0-9-]
            if (ch == '_' || ch >= 97 && ch <= 122 || ch >= 48 && ch <= 57
                    || ch == '-') {
                buffer += ch;
                pos++;
            }
            else if (ch == '\\') {
                buffer += ch;
                pos++;
                parseEscape();
            }
            else if (ch < 0 || ch > 159) {
                parseNonascii();
            }
        }

        return buffer;
    }

    /**
     * Rule: [^\0-\237]
     * @return  the Nonascii string
     */
    private String parseNonascii() {
        char ch;
        String buffer = "";

        if (isNotEnd()) {
            ch = getChar();
            if (ch < 0 || ch > 159) {
                buffer += ch;
                pos++;
            }
        }

        return buffer;
    }

    /**
     * Rule: \\[0-9a-f]{1,6}(\r\n|[ \n\r\t\f])?
     * 
     * @return  the integer which is parsed from input hex
     * @throws  Exception
     */
    private String parseUnicode() throws Exception {
        char ch;
        String buffer = "";

        if (isNotEnd()) {
            ch = getChar();
            if (isHex(ch)) {
                int times = 0;
                do {
                    ch = getChar();
                    if (!isHex(ch))
                        break;
                    buffer += ch;
                    pos++;
                    times++;
                }
                while (times <= 6 && isNotEnd());

                int hexInt = Integer.parseInt(buffer, 16);
                char hexCh = (char) hexInt;
                buffer += hexCh;
            }

            ch = getChar();
            if (ch == '\r') {
                buffer += ch;
                pos++;
                ch = getChar();
                if (ch == '\n') {
                    buffer += ch;
                    pos++;
                }
            }
            // space
            else if (ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t'
                    || ch == '\f') {
                buffer += ch;
                pos++;
            }
        }

        return buffer;
    }

    /**
     * Rule: {unicode}|\\[^\n\r\f0-9a-f] 
     * but \\ is used to identify whether it goes to
     * parseEscape, so is not considered in this function()
     * 
     * @return  the escape string
     */
    private String parseEscape() {
        char ch = 0;
        String buffer = "";

        if (isNotEnd()) {
            ch = getChar();
            /*
             * unicode in escape if (isHex(ch)) { try { buffer +=
             * parseUnicode(); } catch (Exception e) { e.printStackTrace(); } }
             */
            // else in escape
            if (!(ch == '\n' || ch == '\r' || ch == '\f' || isHex(ch))) {
                buffer += ch;
                pos++;
            }
        }

        return buffer;
    }

    /**
     * Rule: [0-9]+|[0-9]*\.[0-9]+
     */
    private void parseNum() {
        char ch;

        if (isNotEnd()) {
            ch = getChar();
            if (ch == '.') {
                do {
                    int times = 0;
                    ch = getChar();
                    if (ch >= 48 && ch <= 57) {
                        times++;
                        pos++;
                    }
                    else {
                        if (times == 0) {
                            break;
                        }
                    }
                }
                while (isNotEnd());
            }
            else {
                int times = 0;
                do {
                    ch = getChar();
                    if (ch >= 48 && ch <= 57) {
                        times++;
                        pos++;
                    }
                    else {
                        if (times == 0) {
                            break;
                        }
                        else {
                            pos++;
                            break;
                        }
                    }
                }
                while (isNotEnd());

                ch = getChar();
                if (ch == '.') {
                    do {
                        times = 0;
                        ch = getChar();
                        if (ch >= 48 && ch <= 57) {
                            times++;
                            pos++;
                        }
                        else {
                            if (times == 0) {
                                break;
                            }
                        }
                    }
                    while (isNotEnd());
                }
            }
        }
    }

    /**
     * Rule: {string1}|{string2}
     */
    private void parseString() {
        char ch;

        if (isNotEnd()) {
            ch = getChar();
            if (ch == '"') {
                pos++;
                parseString1();
            }
            else if (ch == '\'') {
                pos++;
                parseString2();
            }
        }
    }

    /**
     * Rule: \"([^\n\r\f\\"]|\\{nl}|{escape})*\"
     */
    private void parseString1() {
        char ch;

        if (isNotEnd()) {
            ch = getChar();
            while (isNotEnd()) {
                if (!(ch == '\n' || ch == '\r' || ch == '\f' || ch == '\"')) {
                    pos++;
                    ch = getChar();

                }
                else if (ch == '\\') {
                    pos++;
                    parseNl();
                }
                // end is \"
                else if (ch == '\"') {
                    pos++;
                    break;
                }
                else {
                    parseEscape();
                }
            }
        }
    }

    /**
     * Rule: \'([^\n\r\f\\']|\\{nl}|{escape})*\'
     */
    private void parseString2() {
        char ch;

        if (isNotEnd()) {
            while (isNotEnd()) {
                ch = getChar();
                if (!(ch == '\n' || ch == '\r' || ch == '\f' || ch == '\"')) {
                    pos++;
                    ch = getChar();

                }
                else if (ch == '\\') {
                    pos++;
                    parseNl();
                }
                // end is \"
                else if (ch == '\'') {
                    pos++;
                    break;
                }
                else {
                    parseEscape();
                }
            }
        }
    }

    /**
     * Rule: \n|\r\n|\r|\f
     */
    private void parseNl() {
        char ch;

        if (isNotEnd()) {
            ch = getChar();
            if (ch == '\r') {
                pos++;
                if (isNotEnd()) {
                    ch = getChar();
                }
            }
        }
    }

    /**
     * Rule: [ \t\r\n\f]*
     */
    private void parseW() {
        char ch;

        while (isNotEnd()) {
            ch = getChar();
            if (ch == 32 || ch == '\t' || ch == '\r' || ch == '\n'
                    || ch == '\f') {
                pos++;
            }
        }
    }
}
