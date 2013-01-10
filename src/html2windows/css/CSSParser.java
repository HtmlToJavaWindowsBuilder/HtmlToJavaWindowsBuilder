package html2windows.css;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import html2windows.dom.Document;
import html2windows.dom.Element;

public class CSSParser {
    public static void main(String arg[]) {
        String cssString = "";

        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(
                            "C:/Users/mutant/git/HtmlToJavaWindowsBuilder2/src/html2windows/css/testFile.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                cssString += line;
                cssString += "\n";
            }

            System.out.println(cssString);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        CSSParserClass CSSParser = new CSSParserClass();
        CSSParser.parser(cssString, new Document());
    }
}

class CSSParserClass {
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
     * Input cssString and document(the root of tree) Call this to start
     * CSSParser, and it will parse cssString, and add style to each element
     * 
     * @return the elements which are inserted ruleSet, if parsing didn't work,
     *         return null
     */
    public ArrayList<Element> parser(String cssString, Document document) {
        this.cssString = cssString;
        this.pos = 0;
        this.document = document;
        this.ruleSet = new CSSRuleSet(priority);

        return parseStyleSheet();
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
     * [ CDO | CDC | S | statement ]*;
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
        System.out.println("parseSpace");
    }

    /**
     * [ CDO | CDC | S | statement ]*;
     * 
     * @return the elements which are inserted ruleSet, if parsing didn't work,
     *         return null
     */
    private ArrayList<Element> parseStyleSheet() {
        System.out.println("parseStyleSheet");
        while (isNotEnd()) {
            parseSpace();
            return parseStatement();
        }

        return null;
    }

    /**
     * ruleset | at-rule;
     * 
     * @return the elements which are inserted ruleSet, if parsing didn't work,
     *         return null
     */
    private ArrayList<Element> parseStatement() {
        System.out.println("parseStatement");
        if (isNotEnd()) {
            if (getChar() == '@') {
                pos++;
                return parseAtRule();
            }
            else
                return parseRuleSet();
        }

        return null;
    }

    /**
     * ATKEYWORD S* any* [ block | ';' S* ];
     * 
     * @return the elements which are inserted ruleSet, if parsing didn't work,
     *         return null
     */
    private ArrayList<Element> parseAtRule() {
        System.out.println("parseAtRule");
        if (isNotEnd()) {
            parseIdent();
            parseSpace();
            parseAny();
            if (isNotEnd()) {
                if (getChar() == '{') {
                    pos++;
                    parseBlock();
                }
                else if (getChar() == ';') {
                    pos++;
                    parseSpace();
                }
            }
        }

        return null;
    }

    /**
     * '{' S* [ any | block | ATKEYWORD S* | ';' S* ]* '}' S*;
     * 
     * @return the part of content without '{' '}' ';'
     */
    private String parseBlock() {
        System.out.println("parseBlock");
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
     * selector? '{' S* declaration? [ ';' S* declaration? ]* '}' S*; get
     * seletorName and put ruleset into element.
     * 
     * @return the elements which are inserted ruleSet, if parsing didn't work,
     *         return null
     */
    private ArrayList<Element> parseRuleSet() {
        System.out.println("parseRuleSet");
        String selector = "";

        while (isNotEnd()) {
            if (Pattern.compile("(. *?)\\{").matcher(cssString.substring(pos))
                    .find()) {
                selector += parseSelector();

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
                            ArrayList<Element> elements = getElementBySelector(selector);
                            /*Iterator<Element> iterator = elements.iterator();
                            while (iterator.hasNext()) {
                                Element e = (Element) iterator.next();
                                e.style().addCSSRuleSet(ruleSet);
                            }*/
                            
                            System.out.println(selector);
                            System.out.println("height: " + ruleSet.getProperty("height"));
                            System.out.println("width: " + ruleSet.getProperty("width"));
                            System.out.println("color: " + ruleSet.getProperty("color"));
                            System.out.println("padding: " + ruleSet.getProperty("padding"));
                            //return elements;
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * any+; but character can't be '{'
     * 
     * @return string of selector
     */
    private String parseSelector() {
        System.out.println("parseSelector");
        String selector = "";
        char ch;

        if (isNotEnd()) {
            do {
                ch = getChar();
                if (ch == '{') {
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
     * property S* ':' S* value;
     */
    private void parseDeclaration() {
        System.out.println("parseDeclaration");
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
     * IDENT;
     */
    private String parseProperty() {
        System.out.println("parseProperty");
        if (isNotEnd()) {
            return parseIdent();
        }

        return null;
    }

    /**
     * [ any | block | ATKEYWORD S* ]+;
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

        System.out.println("parseValue: " + buffer);
        return buffer;
    }

    /**
     * [ IDENT | NUMBER | PERCENTAGE | DIMENSION | STRING | DELIM | URI | HASH |
     * UNICODE-RANGE | INCLUDES | DASHMATCH | ':' | FUNCTION S* [any|unused]*
     * ')' | '(' S* [any|unused]* ')' | '[' S* [any|unused]* ']' ] S*;
     * 
     * @return character;
     */
    private char parseAny() {
        char ch = getChar();
        System.out.println("parseAny: " + ch);
        pos++;

        return ch;
    }

    /**
     * block | ATKEYWORD S* | ';' S* | CDO S* | CDC S*;
     */
    private void parseUnused() {
        System.out.println("parseUnused");
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
     * <!--
     */
    private void parseCDO() {
        System.out.println("parseCDO");
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
     * -->
     */
    private void parseCDC() {
        System.out.println("parseCDC");
        if (isNotEnd()) {
            if (isNotEnd() && getChar() == '-') {
                pos++;
            }
        }
    }

    /**
     * {ident}
     * 
     * @return the parsing string
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
            else {
                System.out.println("ident no '-'");
            }

            if (isNmstart(ch)) {
                buffer += parseNmstart();
            }

            String temp;
            while ((temp = parseNmchar()) != "" && isNotEnd()) {
                buffer += temp;
            }

        }

        System.out.println("parseIdent: " + buffer);
        return buffer;
    }

    /**
     * [_a-z]|{nonascii}|{escape}
     * 
     * @param ch
     *            ,
     * @return
     */
    private boolean isNmstart(char ch) {
        System.out.println("isNmstart");
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
     * @param ch
     *            : input testing charater
     * @return true if the syntax is Nonascii, false otherwise;
     */
    private boolean isNonascii(char ch) {
        System.out.println("isNonascii");
        if (ch < 0 || ch > 159) {
            return true;
        }
        return false;
    }

    /**
     * @param ch
     *            : input testing charater
     * @return true if the syntax is Escape, false otherwise;
     */
    private boolean isEscape(char ch) {
        System.out.println("isEscape");
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

    private boolean isNmchar(char ch) {
        boolean isTrue;

        isTrue = false;
        if (ch == 95 || (ch >= 97 && ch <= 122) || (ch >= 48 && ch <= 57)
                || isNonascii(ch) || isEscape(ch)) {
            ch = getChar();
            isTrue = true;
        }

        System.out.println("isNmchar: " + isTrue);
        return isTrue;
    }

    /**
     * @param ch
     *            : input the testing character;
     * @return true if character is Hex, false otherwise;
     */
    private boolean isHex(char ch) {
        System.out.println("isHex");
        return ((ch >= 48 && ch <= 57) || (ch >= 97 && ch <= 102));
    }

    /**
     * {nmchar}+
     */
    private void parseName() {
        System.out.println("parseName");
        if (isNotEnd()) {
            do {
                parseNmchar();
            }
            while (isNotEnd());
        }
    }

    /**
     * [_a-z]|{nonascii}|{escape}
     * 
     * @return the testing string
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

        System.out.println("parseNmstart: " + buffer);
        return buffer;
    }

    /**
     * [_a-z0-9-]|{nonascii}|{escape}
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

        System.out.println("parseNmchar: " + buffer);
        return buffer;
    }

    /**
     * [^\0-\237]
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

        System.out.println("parseNonascii: " + buffer);
        return buffer;
    }

    /**
     * \\[0-9a-f]{1,6}(\r\n|[ \n\r\t\f])?
     * 
     * @return the integer which is parsed from input hex
     * @throws Exception
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
        else {
            System.out.println("end in parseUnicode()");
        }

        System.out.println("parseUnicode: " + buffer);
        return buffer;
    }

    /**
     * {unicode}|\\[^\n\r\f0-9a-f] but \\ is used to identify whether it goes to
     * parseEscape, so is not considered in this function()
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
            else {
                System.out.println("error in parseEscape()");
            }
        }
        else {
            System.out.println("end in parseEscape()");
        }

        System.out.println("parseEscape: " + buffer);
        return buffer;
    }

    /**
     * [0-9]+|[0-9]*\.[0-9]+
     */
    private void parseNum() {
        System.out.println("parseNum");
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
                            System.out.println("error in parseNum");
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
                            System.out.println("error in parseNum");
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
                                System.out.println("error in parseNum");
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
     * {string1}|{string2}
     */
    private void parseString() {
        System.out.println("parseString");
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
     * \"([^\n\r\f\\"]|\\{nl}|{escape})*\"
     */
    private void parseString1() {
        System.out.println("parseString1");
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
     * \'([^\n\r\f\\']|\\{nl}|{escape})*\'
     */
    private void parseString2() {
        System.out.println("parseString2");
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
     * \n|\r\n|\r|\f
     */
    private void parseNl() {
        System.out.println("parseN1");
        char ch;

        if (isNotEnd()) {
            ch = getChar();
            if (ch == '\n' || ch == '\f') {
                System.out.println("in parseNl isEnd(end of \n || \f)");
            }
            else if (ch == '\r') {
                pos++;
                if (isNotEnd()) {
                    ch = getChar();
                    if (ch == '\n') {
                        System.out.println("in parseNl isEnd(end of \r\n)");
                    }
                }
                else {
                    System.out.println("in parseNl isEnd(end of \r)");
                }
            }
        }
    }

    /**
     * [ \t\r\n\f]*
     */
    private void parseW() {
        System.out.println("parseW");
        char ch;

        while (isNotEnd()) {
            ch = getChar();
            if (ch == 32 || ch == '\t' || ch == '\r' || ch == '\n'
                    || ch == '\f') {
                pos++;
            }
        }
    }

    private ArrayList<Element> getElementBySelector(String selector) {
        CSS2SelectorMatcher matcher = new CSS2SelectorMatcher();

        System.out.println("getElementBySelector");
        return matcher.getElementBySelector(selector, document);
    }
}
