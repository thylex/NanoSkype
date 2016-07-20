/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.nanoskype.frontend.app;

import org.thylex.nanoskype.frontend.gui.Settings;

/**
 *
 * @author Henrik
 */
public class NanoSkypeApp {

    private String osName;
    private String userHome;
    private String configFilePath;
    private Settings settings;

    public NanoSkypeApp() {
        initilize();
    }

    private void initilize() {
        //Look for settings file
        //If not found, create default file and open GUI
        osName = System.getProperty("os.name");
        userHome = System.getProperty("user.home");

        System.out.println(osName);
        System.out.println(userHome);

        //Prep class environment and properties
        if (osName.contains("Windows")) {
            System.out.println("Yup, its win");
            configFilePath = userHome.concat("\\Documents\\NanoSkype.xml");
            System.out.println(configFilePath);
        } else {
            System.out.println("Unsupported OS, exiting.");
            System.exit(1);
        }

        //Prep application environment
        settings = new Settings(NanoSkypeApp.this);
        settings.setVisible(true);

    }

    public String getConfigFilePath() {
        return configFilePath;
    }

}
