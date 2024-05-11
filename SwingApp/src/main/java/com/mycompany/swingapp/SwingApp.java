/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.swingapp;

import Wizard.UpdateJSONWizard;
import javax.swing.*;

/**
 *
 * @author Fahad.Akhter
 */
public class SwingApp {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SwingUtilities.invokeLater(()->{
            UpdateJSONWizard wizard = new UpdateJSONWizard();
            wizard.setVisible(true);
        });
    }
}
