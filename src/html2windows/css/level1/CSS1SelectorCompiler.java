package html2windows.css.level1;

import java.lang.Character;

/**
 * Compiler for CSS selector level 1.
 * 
 * The compile css selector string to a chain of class Selector 
 * in reverse order.
 * For example :
 * 	A string 
 * 		'#some-node .some-class sometype' 
 * 	will be compiled to 
 * 		sometype -> ' ' -> .some-class -> ' ' -> #some-node
 *
 * @author Southernbear
 */
class CSS1SelectorCompiler{
	/**
	 * The last selector in string and the head of the return chain.
	 */
	Selector lastSelector;

	/**
	 * Handled selector string.
	 */
	String selector;

	/**
	 * Current processing position of selector string.
	 */
	int position;

	/**
	 * Constructor.
	 *
	 * @param selector Selector string to be compiled.
	 * @return A Chain of class Selector in reverse order from selector
	 *         string.
	 */
	public Selector compile(String selector){
		if (selector == null) {
			return null;
		}

		selector.trim();

		lastSelector = null;

		this.selector = selector;
		position = 0;

		while (position < selector.length()) {
			int ch = getChar();

			if (ch == '#') {
				String id = getIdent();
				push(new IDSelector(id));
			}
			else if (ch == '.') {
				String className = getIdent();
				push(new ClassSelector(className));
			}
			else if (ch == ' ') {
				// Skip space
				while (getChar() == ' ');
				backChar();
				push(new DescendantSelector());
			}
			else if (isIdentChar(ch)){
				String type = toChar(ch) + getIdent();
				push(new TypeSelector(type));
			}
			else {
				return null;
			}
		}

		return lastSelector;
	}

	/**
	 * Append a selector to chain.
	 *
	 * @param selector Selector to be pushed.
	 */
	private void push(Selector selector){
		selector.setPrev(lastSelector);
		lastSelector = selector;
	}
	
	/**
	 * Extract a identity string from selector string at current position 
	 * and set position and set the position right behind the identify string.
	 *
	 * @return A identity extracted from selector string at current position.
	 */
	private String getIdent(){
		String ident = "";
		for (int ch = getChar(); isIdentChar(ch); ch = getChar()){
			ident += toChar(ch);
		}
		backChar();
		return ident;
	}
	
	/**
	 * Check whether is a leading character of some recognizable token.
	 *
	 * @param ch Character to be cheched.
	 * @return Whether ch is a leading character of some recoginzable token.
	 */
	private boolean isKeyChar(int ch){
		if (ch == '*' || ch == '#' || ch == '.' || isIdentChar(ch))
			return true;
		else
			return false;
	}

	/**
	 * Check whether is a valid character in a identity string.
	 *
	 * @param ch  Character to be checkd.
	 * @return Whether ch is a valid character in a identity string.
	 */
	private boolean isIdentChar(int ch){
		if (ch == '_' || ch == '-' ||  (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
			return true;
		}
		else
			return false;
	}

	/**
	 * Get character from selector string at current position and move 
	 * to next position.
	 *
	 * @return Get character from selector string at current position. 
	 */
	private int getChar(){
		try{
			int ch = selector.charAt(position++);
			return ch;
		}
		catch(IndexOutOfBoundsException ex){
			return -1;
		}
	}
	
	/**
	 * Move current position back.
	 */
	private void backChar(){
		position--;
	}

	/**
	 * Convert integer code to single character string.
	 *
	 * @param ch Integer of character code.
	 * @return Single character coorsponding to ch.
	 */
	private String toChar(int ch){
		return Character.toString((char) ch);
	}
}

