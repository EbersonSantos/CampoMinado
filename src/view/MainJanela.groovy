/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view

import control.Controlador
import gui.JanelaControl
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException

/**
 *
 * @author Eberson
 */
class MainJanela {
    public static void main(String[] args) {
          
        Controlador app = new Controlador();
		
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run(){
                    new JanelaControl(app);
                }
            });		

    }
        
}

