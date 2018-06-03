package utfpr.itsone.app;

import utfpr.itsone.view.Index;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Index();
            }
        });
    }
}
