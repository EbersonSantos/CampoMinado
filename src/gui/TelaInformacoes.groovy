/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui

import java.awt.BorderLayout
import java.awt.Font
import java.awt.Color
import java.awt.FlowLayout
import javax.swing.JPanel
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.SwingConstants

class TelaInformacoes extends JPanel{
    
    private JanelaControl control
   // private JPanel sul
    private JPanel norte
    private JLabel imagem
    private JLabel linha
    private JLabel coluna
    private JLabel colunaJogada
    private JLabel linhaJogada
    private JLabel jogadaMaq
    
    
    
    public TelaInformacoes(JanelaControl control){
        this.control = control
        this.setLayout(new BorderLayout())
       
	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10))
        this.createComponentes()
    }
    
    private void createComponentes(){
        norte = new JPanel()
        norte.setLayout(new FlowLayout())
        
        jogadaMaq = new JLabel("JOGADA DA MAQUINA  -  ",SwingConstants.CENTER)
        this.setFont(new Font("Arial", Font.PLAIN, 50))
        norte.add(jogadaMaq)
        
        linha = new JLabel("LINHA: ",SwingConstants.CENTER)
        this.setFont(new Font("Arial", Font.PLAIN, 50))
        norte.add(linha)
        
        linhaJogada = new JLabel("  ",SwingConstants.CENTER)
        this.setFont(new Font("Arial", Font.PLAIN, 50))
        norte.add(linhaJogada)
        
        coluna = new JLabel("COLUNA:",SwingConstants.CENTER)
        this.setFont(new Font("Arial", Font.PLAIN, 50))
        norte.add(coluna)
        
        colunaJogada = new JLabel("  ",SwingConstants.CENTER)
        this.setFont(new Font("Arial", Font.PLAIN, 50))
        norte.add(colunaJogada)
        
        this.add(norte, BorderLayout.NORTH)
        
        def icon = new ImageIcon("normal.gif")
        imagem = new JLabel(icon)
        this.add(imagem, BorderLayout.CENTER)
        
    }
    
    public void jogadorGanhou(){
        this.remove(imagem)
        def icon = new ImageIcon("vitoria.gif")
        imagem = new JLabel(icon)
        this.add(imagem, BorderLayout.CENTER)
    }
    
    public void jogadorPerdeu(){
        this.remove(imagem)
        def icon = new ImageIcon("perda.gif")
        imagem = new JLabel(icon)
        this.add(imagem, BorderLayout.CENTER)
    }
    
    public void houveEmpate(){
        this.remove(imagem)
        def icon = new ImageIcon("empate.gif")
        imagem = new JLabel(icon)
        this.add(imagem, BorderLayout.CENTER)
    }
    
    public void setLinhaJogadaMaq(int valor) {
        String msg = valor;
        this.linhaJogada.setText(msg);
    }
    
    public void setColunaJogadaMaq(int valor) {
        String msg = valor;
        this.colunaJogada.setText(msg);
    }
}

