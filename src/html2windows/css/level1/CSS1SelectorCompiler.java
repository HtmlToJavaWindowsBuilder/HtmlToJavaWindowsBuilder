package html2windows.css.level1;

import java.lang.Character;

class CSS1SelectorCompiler{
	Selector lastSelector;

	String selector;
	int position;

	public Selector compile(String selector){
		if (selector == null) {
			return null;
		}

		selector.trim();

		if ("".equals(selector)) {
			return null;
		}


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

	private void push(Selector selector){
		selector.setPrev(lastSelector);
		lastSelector = selector;
	}

	private String getIdent(){
		String ident = "";
		for (int ch = getChar(); isIdentChar(ch); ch = getChar()){
			ident += toChar(ch);
		}
		backChar();
		return ident;
	}
	
	private boolean isKeyChar(int ch){
		if (ch == '*' || ch == '#' || ch == '.' || isIdentChar(ch))
			return true;
		else
			return false;
	}

	private boolean isIdentChar(int ch){
		if (ch == '_' || ch == '-' ||  (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
			return true;
		}
		else
			return false;
	}

	private int getChar(){
		try{
			int ch = selector.charAt(position++);
			return ch;
		}
		catch(IndexOutOfBoundsException ex){
			return -1;
		}
	}
	
	private void backChar(){
		position--;
	}

	private String toChar(int ch){
		return Character.toString((char) ch);
	}
}

