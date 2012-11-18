package html2windows.css;

import java.awt.LayoutManager;

public interface CSSLayoutProvider {
    public LayoutManager getLayout(Style style);
}
