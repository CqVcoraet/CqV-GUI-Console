// Package Custom Fonts
package CustomFonts;

// Imported Packages
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.*;

/*
 * If package declaration fails, run the following commands in the terminal for mac user only:
 *  find . -name ".DS_Store" -delete
 *  rm -rf __MACOSX
 * 
 * This is because DS Store is a hidden file on mac on every file and because Java uses a strict compiler, it confuses it therefore causing
 * errors when declaring the package.
 */

public class Inter {
    // Custom Fonts
    static Font[] inter = new Font[4];

    // Default font sizes
    static int thinSize = 20;
    static int regularSize = 20;
    static int boldSize = 30;
    static int extraBoldSize = 45;

    // Getters for Inter font styles with customizable size
    public static Font getInterThinFont(int fontSize) {
        if (fontSize <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid font size", "Font Size Error", JOptionPane.ERROR_MESSAGE);
            fontSize = thinSize;
        }
        String fontPath = fontSize > 23 ? "Inter/static/Inter_24pt-Thin.ttf" : "Inter/static/Inter_18pt-Thin.ttf";
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading Inter Thin Font: " + e.getMessage());
            return new Font("Arial", Font.PLAIN, fontSize);
        }
    }

    public static Font getInterRegularFont(int fontSize) {
        if (fontSize <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid font size", "Font Size Error", JOptionPane.ERROR_MESSAGE);
            fontSize = regularSize;
        }
        String fontPath = fontSize > 23 ? "Inter/static/Inter_24pt-Regular.ttf" : "Inter/static/Inter_18pt-Regular.ttf";
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading Inter Regular Font: " + e.getMessage());
            return new Font("Arial", Font.PLAIN, fontSize);
        }
    }

    public static Font getInterBoldFont(int fontSize) {
        if (fontSize <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid font size", "Font Size Error", JOptionPane.ERROR_MESSAGE);
            fontSize = boldSize;
        }
        String fontPath = fontSize > 23 ? "Inter/static/Inter_24pt-Bold.ttf" : "Inter/static/Inter_18pt-Bold.ttf";
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.BOLD, fontSize);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading Inter Bold Font: " + e.getMessage());
            return new Font("Arial", Font.BOLD, fontSize);
        }
    }

    public static Font getInterSemiBoldFont(int fontSize) {
        if (fontSize <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid font size", "Font Size Error", JOptionPane.ERROR_MESSAGE);
            fontSize = boldSize;
        }
        String fontPath = fontSize > 23 ? "Inter/static/Inter_24pt-SemiBold.ttf" : "Inter/static/Inter_18pt-SemiBold.ttf";
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.BOLD, fontSize);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading Inter SemiBold Font: " + e.getMessage());
            return new Font("Arial", Font.BOLD, fontSize);
        }
    }

    public static Font getInterExtraBoldFont(int fontSize) {
        if (fontSize <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid font size", "Font Size Error", JOptionPane.ERROR_MESSAGE);
            fontSize = extraBoldSize;
        }
        String fontPath = fontSize > 23 ? "Inter/static/Inter_24pt-ExtraBold.ttf" : "Inter/static/Inter_18pt-ExtraBold.ttf";
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.BOLD, fontSize);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading Inter ExtraBold Font: " + e.getMessage());
            return new Font("Arial", Font.BOLD, fontSize);
        }
    }
}
