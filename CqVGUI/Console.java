// Package Declaration
package CqVGUI;

// Imported Packages
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import CustomFonts.*;
import java.io.File;
import java.io.IOException;

public class Console extends JFrame {
    private static final boolean isHeadless = java.awt.GraphicsEnvironment.isHeadless();
    private static Console instance;
    private JTextPane textPane;
    private static boolean isVisible;
    private static StyledDocument doc;
    private static Style defaultStyle, warningStyle, errorStyle;
    static int lineNum = 0;
    static final String WARN_COLOR = "#FFC900";
    static final String ERROR_COLOR = "#D40000";
    private static boolean inverted = false;
    static final int INIT_WIDTH = 700;
    static final int INIT_HEIGHT = 650;

    // Singleton to ensure one instance
    public static Console getInstance() {
        if (isHeadless) {
            return null;
        }
        if (instance == null) {
            instance = new Console("Console");
        }
        return instance;
    }

    private Console(String title) {
        super(title);
        if (isHeadless) return;
        // ...existing GUI initialization code...
        setSize(INIT_WIDTH, INIT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(true);
        if (inverted) {
            getContentPane().setBackground(Color.WHITE);
        } else {
            getContentPane().setBackground(Color.BLACK);
        }

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(Color.BLACK);
        textPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        doc = textPane.getStyledDocument();
        defaultStyle = textPane.addStyle("default", null);
        if (inverted) {
            StyleConstants.setForeground(defaultStyle, Color.BLACK);
        } else {
            StyleConstants.setForeground(defaultStyle, Color.WHITE);
        }

        warningStyle = textPane.addStyle("warning", null);
        StyleConstants.setForeground(warningStyle, Color.decode("#FFC900"));

        errorStyle = textPane.addStyle("error", null);
        StyleConstants.setForeground(errorStyle, Color.decode("#D40000"));

        JScrollPane scrollPane = new JScrollPane(textPane);
        add(scrollPane, BorderLayout.CENTER);

        // Set the location of the frame to the upper right corner
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - getWidth();
        int y = 0;
        setLocation(x, y);

        JMenuBar menuBar = new JMenuBar();
        JMenu options = new JMenu("Options");
        JMenuItem about = new JMenuItem("About");
        JMenuItem clear = new JMenuItem("Clear");
        JMenuItem smartInvert = new JMenuItem("Smart Invert");
        JMenuItem returnToDefault = new JMenuItem("Return to Default");
        options.add(about);
        options.add(clear);
        options.add(smartInvert);
        options.add(returnToDefault);
        options.setFont(Inter.getInterBoldFont(15));
        setMenuItemFont(options, Inter.getInterRegularFont(10));
        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "CqV GUI Console\nVersion 1.1\nBuild 2\nCreated by Uddi.java", "About", JOptionPane.INFORMATION_MESSAGE);
        });

        smartInvert.addActionListener(e -> {
            inverted = true;
            textPane.updateUI();
        });

        returnToDefault.addActionListener(e -> {
            inverted = false;
            textPane.updateUI();
        });

        menuBar.add(options);
        setJMenuBar(menuBar);
        clear.addActionListener(e -> {
            int result = JOptionPane.showOptionDialog(null, "Are you sure you want to clear the console?", "Clear Console",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Yes", "No" }, "No");
            if (result == JOptionPane.YES_OPTION) {
                textPane.setText("");
            }
        });

        setVisible(false);
    }

    public static void print(Object text) {
        if (isHeadless) {
            System.out.print(text);
            return;
        }
        try {
            getInstance().appendText(String.valueOf(text), defaultStyle);
            System.out.print("GUI Console PRINT: " + text);
        } catch (Exception e) {
            Console.errprintln("Error: " + e.getMessage());
            Console.errprintln("Error Stack Trace: " + e.getStackTrace());
        }
    }

    public static void warnprint(Object text) {
        if (isHeadless) {
            System.out.print(text);
            return;
        }
        getInstance().appendText(String.valueOf(text), warningStyle);
        System.out.print("GUI Console WARN: " + text);
    }

    public static void errprint(Object text) {
        if (isHeadless) {
            System.err.print(text);
            return;
        }
        getInstance().appendText(String.valueOf(text), errorStyle);
        System.err.print("GUI Console ERROR: " + text);
    }
    
    public static void println(Object text) {
        if (isHeadless) {
            System.out.println(text);
            return;
        }
        try {
            getInstance().appendText(String.valueOf(text) + "\n", defaultStyle);
            System.out.println("GUI Console PRINTLN: " + text);
        } catch (Exception e) {
            Console.errprintln("Error: " + e.getMessage());
            Console.errprintln("Error Stack Trace: " + e.getStackTrace());
        }
    }

    public static void warnprintln(Object text) {
        if (isHeadless) {
            System.out.println(text);
            return;
        }
        getInstance().appendText(String.valueOf(text) + "\n", warningStyle);
        System.out.println("GUI Console WARNLN: " + text);
    }
    
    public static void errprintln(Object text) {
        if (isHeadless) {
            System.err.println(text);
            return;
        }
        getInstance().appendText(String.valueOf(text) + "\n", errorStyle);
        System.err.println("GUI Console ERRORLN: " + text);
    }

    public static void printf(String format, Object... args) {
        if (isHeadless) {
            System.out.print(String.format(format, args));
            return;
        }
        try {
            String formatted = String.format(format, args);
            getInstance().appendText(formatted, defaultStyle);
            System.out.print("GUI Console PRINTF: " + formatted);
        } catch (Exception e) {
            Console.errprintln("Error: " + e.getMessage());
            for (StackTraceElement ste : e.getStackTrace()) {
                Console.errprintln("    at " + ste);
            }
        }
    }

    public static void warnprintf(String format, Object... args) {
        if (isHeadless) {
            System.out.print(String.format(format, args));
            return;
        }
        try {
            String formatted = String.format(format, args);
            getInstance().appendText(formatted, warningStyle);
            System.out.print("GUI Console WARNF: " + formatted);
        } catch (Exception e) {
            Console.errprintln("Error: " + e.getMessage());
            for (StackTraceElement ste : e.getStackTrace()) {
                Console.errprintln("    at " + ste);
            }
        }
    }

    public static void errprintf(String format, Object... args) {
        if (isHeadless) {
            System.err.print(String.format(format, args));
            return;
        }
        try {
            String formatted = String.format(format, args);
            getInstance().appendText(formatted, errorStyle);
            System.err.print("GUI Console ERRORF: " + formatted);
        } catch (Exception e) {
            Console.errprintln("Error: " + e.getMessage());
            for (StackTraceElement ste : e.getStackTrace()) {
                Console.errprintln("    at " + ste);
            }
        }
    }

    public static void spacer() {
        if (isHeadless) {
            System.out.println();
            return;
        }
        getInstance().appendText("\n", defaultStyle);
    }

    private void appendText(String text, Style style) {
        if (isHeadless) return;
        SwingUtilities.invokeLater(() -> {
            try {
                doc.insertString(doc.getLength(), text, style);
                textPane.setCaretPosition(doc.getLength());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }

    public static void toggleVisibility() {
        if (isHeadless) return;
        Console console = getInstance(); // Ensure the instance exists
        isVisible = !isVisible;
        console.setVisible(isVisible);
    }

    public static void setMenuFont(JMenuBar menuBar, Font font) {
        menuBar.setFont(font); // Set font for the menu bar itself
        for (MenuElement menuElement : menuBar.getSubElements()) {
            if (menuElement.getComponent() instanceof JMenu) {
                JMenu menu = (JMenu) menuElement.getComponent();
                menu.setFont(font); // Set font for JMenu
                setMenuItemFont(menu, font); // Recursively set font for all items in the menu
            }
        }
    }
    
    public static void setMenuItemFont(JMenu menu, Font font) {
        for (MenuElement menuElement : menu.getSubElements()) {
            if (menuElement.getComponent() instanceof JMenuItem) {
                JMenuItem menuItem = (JMenuItem) menuElement.getComponent();
                menuItem.setFont(font); // Set font for each JMenuItem
            }
        }
    }

    public static void setMenuBarItemFont(JMenuBar menuBar, Font font) {
        for (MenuElement menuElement : menuBar.getSubElements()) {
            if (menuElement.getComponent() instanceof JMenuItem) {
                JMenuItem menuItem = (JMenuItem) menuElement.getComponent();
                menuItem.setFont(font); // Set font for each JMenuItem directly in the menu bar
            }
        }
    }
}
