/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui

import javax.swing.JFrame
import javax.swing.JPanel
import model.Tabuleiro
import control.Controlador
import javax.swing.BorderFactory
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import java.awt.Image
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel

class TelaCampoMinado extends JPanel{
    
    private JanelaControl control
    private JButton[][] grid
        
    TelaCampoMinado(JanelaControl control){
        this.control = control
        def size = control.app.getTamanho()
        this.setLayout(new GridLayout(size,size))
	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.createComponentes()
    }
    
    private void createComponentes(){                
        
        def size = control.app.getTamanho()
        grid = new JButton[size][size]
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){      
                def botao = new JButton(" ") //creates new button
                botao.setBackground(Color.YELLOW)
                botao.setName("${x+1}-${y+1}")
                botao.setActionCommand("jogar")
                botao.setFont(new Font("Arial", Font.PLAIN, 14))
                botao.addActionListener(control)
                grid[x][y] = botao
                this.add(grid[x][y])
            }
        }
    }
    
    public void setTextButton(int linha, int coluna, String text){
        switch(text){
            case "-1":
                this.setIcon(linha,coluna)
                break
            case "1":
            case "2":               
            case "3":                               
                grid[linha][coluna].setText(text)
                grid[linha][coluna].setForeground(Color.BLUE)
                break
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
                grid[linha][coluna].setText(text)
                grid[linha][coluna].setForeground(Color.RED)
                break
        }
        grid[linha][coluna].setBackground(null)
        
    }
    
    public void setIcon(int linha, int coluna){ //mudar a imagem de um botao pra bomba na matriz, receber argumentos -1    
        def icon = new ImageIcon("bomba.png")
        grid[linha][coluna].setIcon(icon)
        grid[linha][coluna].setText("")
    }
    
    public int getLinhaButton(JButton b){  //usando pattern do groovy
        def p = ~/-/
        String s = b.getName()
        String[] result = p.split(s)        
        return Integer.parseInt(result[0])
    }
    
    public int getColunaButton(JButton b){  //usando pattern do groovy
        def p = ~/-/
        String s = b.getName()
        String[] result = p.split(s)
        return Integer.parseInt(result[1])
    }
    
    public void enabledButton(int linha, int coluna, String text){ //desativar um botao e mudar seu texto
        grid[linha][coluna].setEnabled(false)
        this.setTextButton(linha, coluna, text)
    }
}

