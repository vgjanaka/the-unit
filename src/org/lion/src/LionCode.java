/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lion.src;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Janaka Pathma Kumara
 */
public class LionCode {

    
    
    
    public static void OpenLink(String path) {
        try {
            java.awt.Desktop.getDesktop().browse(new URI(path));
        } catch (Exception ex) {
            Logger.getLogger(LionCode.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static void OpenFile(String url) {
        try {
            File htmlFile = new File(url);
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (Exception ex) {
            Logger.getLogger(LionCode.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * If possible this method opens the default browser to the specified web
     * page. If not it notifies the user of webpage's url so that they may
     * access it manually.
     *     
* @param url - this can be in the form of a web address
     * (http://www.mywebsite.com) or a path to an html file or SVG image file
     * e.t.c
     */
    public static void openInBrowser(String url) {
        try {
            URI uri = new URL(url).toURI();
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(uri);
            }
        } catch (Exception e) {
            /*
             *  I know this is bad practice 
             *  but we don't want to do anything clever for a specific error
             */
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);

            // Copy URL to the clipboard so the user can paste it into their browser
            StringSelection stringSelection = new StringSelection(url);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
            // Notify the user of the failure
//            WindowTools.informationWindow("This program just tried to open a webpage." + "\n"
//                    + "The URL has been copied to your clipboard, simply paste into your browser to access.",
//                    "Webpage: " + url);
        }
    }
}
