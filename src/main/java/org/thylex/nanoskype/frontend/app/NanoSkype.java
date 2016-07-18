/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.nanoskype.frontend.app;

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

        //Look for settings file
        //If not found, create default file and open GUI
        String osName = System.getProperty("os.name");
        String userHome = System.getProperty("user.home");
        
        System.out.println(osName);
        System.out.println(userHome);

        if (osName.contains("Windows")) {
            System.out.println("Yup, its win");
        }
    }
    
}
