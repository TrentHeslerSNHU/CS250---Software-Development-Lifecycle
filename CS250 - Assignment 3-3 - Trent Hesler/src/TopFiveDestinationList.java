// CS250-T5475 Assignment 3-3: Developing Basic ListView Control
// Trent Hesler
// 05/21/2023
// Comment: I probably went a little overboard with the changes for this particular assignment.
//          I made some pretty significant changes to the codebase, compared to the expectations
//          the instructor probably had for this assignment, but I was just really enjoying
//          myself with this! Hope that's okay!
//
// !! CHANGES MADE ARE INDICATED BY COMMENT LINES BEGINNING WITH "CHANGELOG" TO ALLOW FOR EASE OF REVIEW !!
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();


        //CHANGELOG: Updated top 5 destinations; Changed function calls to reflect changes made to TopDestinationListFrame and
        //           Destination (formerly TextAndIcon) classes; Added/updated destination items' text,
        //           description, attribution, and image values.
        
        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestination("Tokyo", "Visit Tokyo Tower and enjoy 5-star accommodations in Japan's capital city!", "https://commons.wikimedia.org/wiki/File:Neon-street-in-Tokyo-Japan.jpg", new ImageIcon(getClass().getResource("/resources/Tokyo.jpg")));
        addDestination("Taipei", "Take a food tour and sample the best dishes Taiwan's Zhongshan district has to offer!", "https://commons.wikimedia.org/wiki/File:20200809_Taipei,_Taiwan_Skyline.jpg",new ImageIcon(getClass().getResource("/resources/Taipei.jpg")));
        addDestination("Hong Kong", "Enjoy a luxury cruise around Kowloon Bay and snorkeling at Sai Kung Beach!", "https://commons.wikimedia.org/wiki/File:Hong_Kong_Island_Panorama_(2).jpg", new ImageIcon(getClass().getResource("/resources/Hong_Kong.jpg")));
        addDestination("Seoul", "Meet K-Pop stars and enjoy the vibrant night life of this top destination!", "https://commons.wikimedia.org/wiki/File:Seoul_(175734251).jpeg", new ImageIcon(getClass().getResource("/resources/Seoul.jpg")));
        addDestination("Pyongyang", "Like a working vacation? Spend a week in one of the DPRK's finest labor camps!", "https://commons.wikimedia.org/wiki/File:Ryugyong_Hotel_01.JPG", new ImageIcon(getClass().getResource("/resources/Pyongyang.jpg")));
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        
        //CHANGELOG: Added a label for the student name.
        JLabel studentName = new JLabel("Trent Hesler - CS250-T5475");
        getContentPane().add(studentName, BorderLayout.NORTH);

        //CHANGELOG: Changed this line to reflect name change for DestinationListCellRenderer class (formerly
        //           TextAndIconListCellRenderer class).
        DestinationListCellRenderer renderer = new DestinationListCellRenderer(2);

        list.setCellRenderer(renderer);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    //CHANGELOG: Changed function name and parameters to reflect changes to Destination (formerly TextAndIcon)
    //           class; changed "tai" variable name to "dest" to reflect change to the variable's class.
    private void addDestination(String text, String description, String attribution, Icon icon) {
        Destination dest = new Destination(text, description, attribution, icon);
        listModel.addElement(dest);
    }
}


//CHANGELOG: Added member variables for destination description and image attribution, with corresponding setters and getters;
//           Changed class name to reflect these changes.
class Destination {
    private String text;
    private String description;
    private String attribution;
    private Icon icon;

    //CHANGELOG: Updated constructor to incorporate class name change and added member variables
    public Destination(String text, String description, String attribution, Icon icon) {
        this.text = text;
        this.description = description;
        this.attribution = attribution;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }
    
    //CHANGELOG: Added getter for new description member variable.
    public String getDescription() {
    	return description;
    }
    
    //CHANGELOG: Added getter for new attribution member variable.
    public String getAttribution() {
    	return attribution;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    //CHANGELOG: Added setter for new description member variable.
    public void setDescription(String description) {
    	this.description = description;
    }
    
    //CHANGELOG: Added setter for new attribution member variable.
    public void setAttribution(String attribution) {
    	this.attribution = attribution;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


//CHANGELOG: Changed class name to reflect/accommodate changes to TextDescriptionAttribution (formerly TextAndIcon) class;
//           Modified getListCellRendererComponent function to accommodate changes to TextDescriptionAttribution
//           (formerly TextAndIcon) class.
class DestinationListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public DestinationListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public DestinationListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public DestinationListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
    	//CHANGELOG: Changed variable type and name to reflect changes made to Destination (formerly TextAndIcon) class.
    	Destination dest = (Destination) value;

        //CHANGELOG: Updated comments to reflect changes to Destination (TextAndIcon) class;
        //           Added HTML formatting to text component to enable multi-line text and improve aesthetics; Changed the way 
        //           text is assigned to each list cell to allow for more dynamic list generation (in case client wishes
        //           change number of list elements, for instance).
        // Sets text, description, attribution and image on 'this' JLabel.
        setText("<html><p>"+(index+1)+". <u>"+dest.getText()+"</u></p><p>"+dest.getDescription()+"</p><p></p><p><i>Image credit: "+dest.getAttribution()+"</i></p></html>");
        setIcon(dest.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}