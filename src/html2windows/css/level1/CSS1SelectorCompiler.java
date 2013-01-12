package html2windows.css.level1;

class CSS1SelectorCompiler{
	Selector lastSelector;
	Selector headSelector;

	String selector;
	int position;

	public Selector compile(String selector){
		lastSelector = new DescendantSelector();	// Dummy Object
		headSelector = lastSelector;

		this.selector = selector;
		position = 0;
		
		int ch = selector.charAt(position);
		for(position = 0; position < selector.length(); position++){
			ch = getChar();

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
				while (getChar() != ' ');
				backChar();
				push(new DescendantSelector());
			}
			else if (isIdentChar(ch)){
				push(new TypeSelector(ch + getIdent()));
			}
		}

		return lastSelector.prev();
	}

	private void push(Selector selector){
		headSelector.setPrev(selector);
		headSelector = selector;
	}

	private String getIdent(){
		String ident = "";
		for (int ch = getChar(); isIdentChar(ch); ch = getChar()){
			ident += ch;
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
		if (ch == '_' || ch == '-' ||  (ch >= 'a' && ch <= 'z') || (ch >= '0' || ch <= '9')) {
			return true;
		}
		else
			return false;
	}

	private int getChar(){
		try{
			return selector.charAt(position++);
		}
		catch(IndexOutOfBoundsException ex){
			return -1;
		}
	}
	
	private void backChar(){
		position--;
	}
}

