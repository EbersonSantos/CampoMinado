/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui

import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import javax.swing.Box
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JPanel

class Janela extends JFrame{
    
    private JanelaControl control
    private GridLayout cardLayout
    private JLabel status;
    private JPanel cards
    private TelaCampoMinado tcm
    private TelaInformacoes ti
    
    public Janela(JanelaControl control) {
        this.control = control
        
        this.createComponentes()
        this.createTela()
        
        this.setTitle("CAMPO MINADO")
        //this.setSize(800,600)
        this.setLocationRelativeTo(null)
        this.setDefaultCloseOperation(EXIT_ON_CLOSE)
        this.setVisible(true)
        this.setExtendedState(JFrame.MAXIMIZED_BOTH)
        this.setMinimumSize(new Dimension(1300, 900));
      
    }
    
    private void createTela(){
        this.cardLayout = new GridLayout()
        this.cards = new JPanel(cardLayout)
	this.getContentPane().add(cards, BorderLayout.CENTER)
        
        this.ti = new TelaInformacoes(control)
	this.cards.add(ti, "ti")
        
        this.tcm = new TelaCampoMinado(control)
	this.cards.add(tcm, "tcm")
        
        //this.ti = new TelaInformacoes()
	//this.cards.add(ti, "ti")
    }
    
    public TelaCampoMinado getTcm() {
	return tcm
    }
    
    public TelaInformacoes getTi(){
        return ti
    }
    
    public void resetJanela(){
        this.remove(cards)
        this.createTela()
        this.setVisible(true)
    }
    
    private void createComponentes() {
        JMenuBar barra = new JMenuBar()
        this.getContentPane().add(barra, BorderLayout.NORTH)
		
        JMenu menu
		
        JMenuItem item
		
        menu = new JMenu("Novo Jogo")
        barra.add(menu)
		
        item = new JMenuItem("Fácil")
        item.setActionCommand("facil")
        item.addActionListener(control)
        menu.add(item)

        item = new JMenuItem("Médio")
        item.setActionCommand("medio")
        item.addActionListener(control)
        menu.add(item)

        item = new JMenuItem("Difícil")
        item.setActionCommand("dificil")
        item.addActionListener(control)
        menu.add(item)
        
        menu = new JMenu("Som")
        barra.add(menu)
        
        item = new JMenuItem("Ativar/Desativar");
        item.setActionCommand("ativar");
        item.addActionListener(control);
        menu.add(item);
		
        menu = new JMenu("Sair")
        barra.add(menu)
        
	item = new JMenuItem("Sair");
        item.setActionCommand("sair");
        item.addActionListener(control);
        menu.add(item);	
        
        item = new JMenuItem("Sobre");
        item.setActionCommand("sobre");
        item.addActionListener(control);
        menu.add(item);
        
        
        this.status = new JLabel("")
        status.setFont(new Font("Arial", Font.PLAIN, 15))
        this.getContentPane().add(status, BorderLayout.SOUTH)
    }
    
    public void setStatus(String msg) {
        this.status.setText(msg);
    }
}

