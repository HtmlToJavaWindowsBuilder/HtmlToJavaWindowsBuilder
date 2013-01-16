package html2windows.css;

import html2windows.dom.Element;
import java.awt.*;

/**
 * layout manager control all css position and layout
 *
 * @author bee040811
 */
public class CustomLayoutManager implements LayoutManager {

    /**
     * vgap mean that preferredHeight control factor
     */
    private int vgap;

    /**
     * minWidth mean that minimum width
     */
    private int minWidth = 0;

    /**
     * minHeight mean that minimum height 
     */
    private int minHeight = 0;

    /**
     * preferredWidth mean that preferred width
     */
    private int preferredWidth = 0;

    /**
     * preferredHeight mean that preferred height 
     */
    private int preferredHeight = 0;

    /**
     * sizeUnknown mean that before know or not setting size
     */
    private boolean sizeUnknown = true;

    /**
     * Required by LayoutManager. 
     *
     * @param name
     * @param comp
     *
     * @return   
     */
    public void addLayoutComponent(String name, Component comp) {
    }

    /**
     * Required by LayoutManager. 
     *
     * @param comp
     *
     */
    public void removeLayoutComponent(Component comp) {
    }

    /**
     * This is called when the panel is first displayed,
     * and every time its size changes,
     * Note: You CAN'T assume preferredLayoutSize or
     * minimumLayoutSize will be called -- in the case
     * of applets, at least, they probably won't be.
     *
     * @param parent    Container
     *
     */
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int maxWidth = parent.getWidth()
            - (insets.left + insets.right);
        int maxHeight = parent.getHeight()
            - (insets.top + insets.bottom);
        int nComps = parent.getComponentCount();
        int previousWidth = 0, previousHeight = 0;
        int x = 0, y = insets.top;
        int rowh = 0, start = 0;
        int xFudge = 0, yFudge = 0;
        boolean oneColumn = false;

        // Go through the components' sizes, if neither
        // preferredLayoutSize nor minimumLayoutSize has
        // been called.
        if (sizeUnknown) {
            setSizes(parent);
        }

        if (maxWidth <= minWidth) {
            oneColumn = true;
        }

        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            if (c instanceof Element) {
                Element cElement = (Element) c;
                Style style = cElement.getStyle();
                int width = getWidth(style);
                int height = getHeight(style);
                int top = getTop(style);
                int left = getLeft(style);
                String floating = getFloat(style);
                String position = getPosition(style);
                Dimension d = new Dimension(width, height);

                if( position == "absolute" ) {
                    c.setBounds(left, top, d.width, d.height);
                
                    previousWidth = d.width;
                }
                else {
                    y += previousHeight + top;
                    x += left;
                    c.setBounds(x, y, d.width, d.height);

                    previousWidth = d.width;
                    previousHeight = d.height;
                }
            }
            else if (c.isVisible()) {
                Dimension d = c.getPreferredSize();

                y += previousHeight;
                c.setBounds(x, y, d.width, d.height);

                previousWidth = d.width;
                previousHeight = d.height;
            }
        }
    }

    /**
     * set container size
     *
     * @param parent
     *
     */
    private void setSizes(Container parent) {
        int nComps = parent.getComponentCount();
        Dimension d = null;

        //Reset preferred/minimum width and height.
        preferredWidth = 0;
        preferredHeight = 0;
        minWidth = 0;
        minHeight = 0;

        for (int i = 0; i < nComps; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                d = c.getPreferredSize();
                if (i > 0) {
                    preferredWidth += d.width/2;
                    preferredHeight += vgap;
                } else {
                    preferredWidth = d.width;
                }
                preferredHeight += d.height;

                minWidth = Math.max(c.getMinimumSize().width,
                        minWidth);
                minHeight = preferredHeight;
            }
        }
    }

    /**
     * Required by LayoutManager. 
     *
     * @param parent
     *
     * @return   
     */
    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        int nComps = parent.getComponentCount();

        setSizes(parent);

        //Always add the container's insets!
        Insets insets = parent.getInsets();
        dim.width = preferredWidth
            + insets.left + insets.right;
        dim.height = preferredHeight
            + insets.top + insets.bottom;

        sizeUnknown = false;

        return dim;

    }

    /**
     * Required by LayoutManager. 
     *
     * @param parent
     *
     * @return   
     */
    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        int nComps = parent.getComponentCount();

        //Always add the container's insets!
        Insets insets = parent.getInsets();
        dim.width = minWidth
            + insets.left + insets.right;
        dim.height = minHeight
            + insets.top + insets.bottom;
        sizeUnknown = false;

        return dim;

    }

    /**
     * Function will get Width property
     *
     * @param style     mean Element style
     *
     * @return width    mean that width value  
     */
    private int getWidth(Style style) {
        int width = 100;
        int value = getPxNumber(style.getProperty("width"));
        if(value != 0) {
            return value;
        }
        return width;
    }

    /**
     * Function will get float property
     *
     * @param style     mean Element style
     *
     * @return floating mean that floating type
     */
    private String getFloat(Style style) {
        String floating = "none";
        if(style.getProperty("float") != null) {
            return style.getProperty("float");
        }
        return floating;
    }

    /**
     * Function will get height property 
     *
     * @param style     mean Element style
     *
     * @return height   mean that height value
     */
    private int getHeight(Style style) {
        int height = 100;
        int value = getPxNumber(style.getProperty("height"));
        if(value != 0) {
            return value;
        }
        return height;
    }

    /**
     * Function will get top property
     *
     * @param style      mean Element style
     *
     * @return top       mean that top value  
     */
    private int getTop(Style style) {
        int top = 0;
        int value = getPxNumber(style.getProperty("top"));
        if(value != 0) {
            return value;
        }
        return top;
    }

    /**
     * function will get left property
     *
     * @param style      mean element style
     *
     * @return left      mean that left value  
     */
    private int getLeft(Style style) {
        int left = 0;
        int value = getPxNumber(style.getProperty("left"));
        if(value != 0) {
            return value;
        }
        return left;
    }
    
    /**
     * covert number px into number
     *
     * @param numString
     *
     * @return value mean that number
     */
    private int getPxNumber(String numString) {
        if( numString != null) {
            if( numString.matches("[0-9]+") ) {
                return Integer.parseInt(numString);
            }
            else if(numString.matches("[0-9]+px")) {
                numString = numString.replaceAll("([0-9]+)px","$1");          
                return Integer.parseInt(numString);
            }
        }
        return 0;
    }

    /**
     * function will get padding-left property
     *
     * @param style      mean element style
     *
     * @return paddingLeft mean that padding-left value 
     */
    private int getPaddingLeft(Style style) {
        int paddingLeft = 0;
        int value = getPxNumber(style.getProperty("padding-left"));
        if(value != 0) {
            return value;
        }
        return paddingLeft;
    }

    /**
     * function will get margin-top property
     *
     * @param style      mean element style
     *
     * @return marginTop mean that margin-top value  
     */
    private int getMarginTop(Style style) {
        int marginTop= 0;
        if(style.getProperty("margin-top") != null) {
            return Integer.parseInt(style.getProperty("margin-top"));
        }
        return marginTop;
    }

    /**
     * function will get position property
     *
     * @param style      mean element style
     *
     * @return position mean that absolute or relative  
     */
    private String getPosition(Style style) {
        String position= "relative";
        if(style.getProperty("position") != null) {
            return style.getProperty("position");
        }
        return position;
    }
}
