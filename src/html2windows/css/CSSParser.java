package html2windows.css;

import java.util.regex.Pattern;

import html2windows.dom.Document;

public class CSSParser {
    String cssString;
    int pos;

    public void parse(String cssString, Document document) {
        this.cssString = cssString;
        this.pos = 0;
        parseStyleSheet();
    }

    // done
    private void parseSpace() {
        char ch = 0;

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

    private boolean isNotEnd() {
        return (pos < cssString.length());
    }

    // done
    private char getChar() {
        // css syntax is case-insensitive
        return Character.toLowerCase(cssString.charAt(pos));
    }

    // done
    private void parseStyleSheet() {
        while (isNotEnd()) {
            parseSpace();
            parseStatement();
        }
    }

    // done
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

    // done
    private void parseAtRule() {
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
    }

    private void parseBlock() {
        if (isNotEnd()) {
            parseSpace();
            while (isNotEnd() && getChar() != '}') {
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
                else {
                    parseAny();
                }
            }
            if (isNotEnd() && getChar() == '}') {
                pos++;
                parseSpace();
            }
        }
    }

    /**
     * 
     */
    private void parseRuleSet() {
        if (isNotEnd()) {
            if (Pattern.compile("(.*) {").matcher(cssString.substring(pos))
                    .find()) {
                // parseSelector?;
                // FIXIT
                parseSelector();
                if (isNotEnd() && getChar() == '{') {
                    pos++;
                    parseSpace();
                    if (isNotEnd()) {
                        // declaration?;
                        // FIXIT
                        parseDeclaration();
                        while (isNotEnd() && getChar() == ';') {
                            pos++;
                            parseSpace();
                            // declaration?;
                            // FIXIT
                            parseDeclaration();
                        }
                        if (isNotEnd() && getChar() == '}') {
                            pos++;
                            parseSpace();
                        }
                    }
                }
            }
        }
    }

    // done
    private void parseSelector() {
        while (isNotEnd()) {
            parseAny();
        }
    }

    // done
    private void parseDeclaration() {
        if (isNotEnd()) {
            parseProperty();
            parseSpace();
            if (isNotEnd()) {
                if (getChar() == ':') {
                    pos++;
                    parseSpace();
                    parseValue();
                }
            }
        }
    }

    private void parseProperty() {
        if (isNotEnd()) {
            parseIdent();
        }
    }

    // done
    private void parseValue() {
        if (isNotEnd()) {
            do {
                if (getChar() == '{') {
                    pos++;
                    parseBlock();
                }
                else if (getChar() == '@') {
                    pos++;
                    parseIdent();
                    parseSpace();
                }
                else {
                    parseAny();
                }
            }
            while (isNotEnd());
        }
    }

    private void parseAny() {
        if (isNotEnd()) {
            parseIdent();

        }
    }

    // done
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

    // done
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

    // done
    private void parseCDC() {
        if (isNotEnd()) {
            if (isNotEnd() && getChar() == '-') {
                pos++;
            }
        }
    }

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
                buffer += ch;

            }
            if (isNmstart(ch)) {
                buffer += parseNmstart();
            }
            while (isNmchar(ch)) {
                buffer += ch;
                pos++;
                if (isNotEnd())
                    ch = getChar();
            }
        }

        return buffer;
    }

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

    private boolean isNonascii(char ch) {
        return false;
    }

    private boolean isEscape(char ch) {
        if (ch == 92) {
            pos++;
            ch = getChar();
            // unicode in escape
            if (isHex(ch)) {
                try {
                    int hexCh = parseUnicode();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
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
        while (ch == 95 || (ch >= 97 && ch <= 122) || (ch >= 48 && ch <= 57)
                || isNonascii(ch) || isEscape(ch)) {
            pos++;
            ch = getChar();
            isTrue = true;
        }

        return isTrue;
    }

    private boolean isHex(char ch) {
        return ((ch >= 48 && ch <= 57) || (ch >= 97 && ch <= 102));
    }

    private void parseName() {
        if (isNotEnd()) {
            do {
                parseNmchar();
            }
            while (isNotEnd());
        }
    }

    private String parseNmstart() {
        char ch;
        String buffer = "";

        if (isNotEnd()) {
            ch = getChar();
            // [_a-z] || nonascii
            if (ch == '_' || (ch >= 97 && ch <= 122)) {
                buffer += ch;
            }
            // escape
            else if (ch == '\\') {
                pos++;
                parseEscape();
            }
            else {
                parseNonascii();
            }
        }

        return buffer;
    }

    // [^\0-\237]
    private void parseNonascii() {
        char ch;

        if (isNotEnd()) {
            ch = getChar();
            if (ch < 0 || ch > 159) {
                pos++;
            }
            else {
                System.out.println("error in parseNonascii()");
            }
        }
        else {
            System.out.println("end in parseNonascii()");
        }
    }

    private int parseUnicode() throws Exception {
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

                return Integer.parseInt(buffer, 16);
            }
        }
        else {
            System.out.println("end in parseUnicode()");
        }
        throw new Exception();
    }

    /*
     * {unicode}|\\[^\n\r\f0-9a-f] but \\ is used to identify whether it goes to
     * parseEscape, so is not considered in this function()
     */
    private String parseEscape() {
        char ch = 0;
        String buffer = "";

        if (isNotEnd()) {
            ch = getChar();
            // unicode in escape
            if (isHex(ch)) {
                try {
                    int hexInt = parseUnicode();
                    char hexCh = (char) hexInt;
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ch = getChar();
                if (ch == '\r') {
                    pos++;
                    ch = getChar();
                    if (ch == '\n') {

                    }
                }
                // space
                else if (ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t'
                        || ch == '\f') {

                }
            }
            // else in escape
            else if (!(ch == '\n' || ch == '\r' || ch == '\f' || isHex(ch))) {
                
            }
            else {
                System.out.println("error in parseEscape()");
            }
        }
        else {
            System.out.println("end in parseEscape()");
        }

        return buffer;
    }

    // [_a-z0-9-]|{nonascii}|{escape}
    private void parseNmchar() {
        char ch;

        if (isNotEnd()) {
            ch = getChar();
            if (ch == '_' || ch >= 97 && ch <= 122 || ch >= 48 && ch <= 57
                    || ch == '-') {
                pos++;
            }
            else if (ch == '\\') {
                pos++;
                parseEscape();
            }
            else {
                parseNonascii();
            }
        }
        else {
            System.out.println("end in parseNmchar()");
        }
    }

    // [0-9]+|[0-9]*\.[0-9]+
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

    // {string1}|{string2}
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

    // \"([^\n\r\f\\"]|\\{nl}|{escape})*\"
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

    // \'([^\n\r\f\\']|\\{nl}|{escape})*\'
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

    // \n|\r\n|\r|\f
    private void parseNl() {
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

    // [ \t\r\n\f]*
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