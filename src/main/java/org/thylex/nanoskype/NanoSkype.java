/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.nanoskype;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.thylex.nanoskype.frontend.app.NanoSkypeApp;

/**
 *
 * @author Henrik
 */
public class NanoSkype {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // Set cross-platform Java L&F (also called "Metal")
            //UIManager.setLookAndFeel(
            //        UIManager.getCrossPlatformLookAndFeelClassName());
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        NanoSkypeApp app = new NanoSkypeApp();

    }

}
