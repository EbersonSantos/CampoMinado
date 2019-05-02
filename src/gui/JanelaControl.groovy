package gui

import com.sun.media.jfxmedia.AudioClip
import control.Controlador
import java.applet.AudioClip
import java.applet.Applet
import niveis.*
import niveis.NivelDois
import javax.swing.JOptionPane
import java.net.URL


import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.Color

class JanelaControl implements ActionListener{
    
    private Controlador app
    private Janela janela
    private INivel maquina
    private vitoriasJog
    private vitoriasMaq
    public  AudioClip audioReacao
    public  AudioClip audioJogo
    
    JanelaControl(Controlador app){
        this.app = app
        this.maquina = new NivelUm()
        this.janela = new Janela(this)
        this.janela.setStatus("Turno do Jogador")
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        case "jogar":
            int linha = this.janela.getTcm().getLinhaButton(e.getSource()) //linha do botao
            int coluna = this.janela.getTcm().getColunaButton(e.getSource()) //coluna do botao
            
            def jogadorPerdeu = this.jogador(e, linha, coluna) //checa se o jogador clicou em uma bomba
            //def maquinaPerdeu = false
            def fim = this.app.fimDeJogo() //checa se as bombas acabaram
            if(jogadorPerdeu){
               this.janela.setStatus("O jogador perdeu!!!")
               this.janela.getTi().jogadorPerdeu()
               sons("boom_x.wav")
               audioReacao.play()
            }else if(!fim){
               this.janela.setStatus("Turno da Máquina")
               
               
               def maquinaPerdeu = this.maquina() //checa se a maquina jogou em uma bomba
               this.janela.getTi().setLinhaJogadaMaq(maquina.getLinhaJogada())
               this.janela.getTi().setColunaJogadaMaq(maquina.getColunaJogada())
               fim = this.app.fimDeJogo()
               if(maquinaPerdeu){
                   this.janela.setStatus("A máquina perdeu!!!")
                   this.janela.getTi().jogadorGanhou()
                   sons("cheering.wav")
                   audioReacao.play()
               }else if(fim){
                   this.janela.setStatus("Houve um empate!!!")
                   this.janela.getTi().houveEmpate()
               }
            }else{
                this.janela.setStatus("Houve um empate!!!")
                this.janela.getTi().houveEmpate()
            }
            
            break
        case "facil":
            this.maquina = new NivelUm()
            this.janela.getTcm().removeAll()
            this.app = new Controlador()
            this.janela.resetJanela()            
            break
        case "medio":
            this.maquina = new NivelDois()
            this.janela.getTcm().removeAll()
            this.app = new Controlador(11)
            this.janela.resetJanela()
            break
        case "dificil":  //TODO
            this.maquina = new NivelTres()
            this.janela.getTcm().removeAll()
            this.app = new Controlador(16)
            this.janela.resetJanela()
            break
        case "sobre":
            JOptionPane.showMessageDialog(null, '''        CAMPO MINADO - DISCIPLINA PLP\n        DESENVOLVEDORES: \n\
        ALYSSON MANSO - ANDERSON MELO\n\
        EBERSON SANTOS - MANOEL NATALÍCIO''', "SOBRE", 1)
            
            break    
        case "sair":
            System.exit(0)
            break
        case "ativar":
            if(audioJogo != null){
                audioJogo.stop()
                audioJogo = null
            }else{            
                URL url = getClass().getResource("8bitSound.wav")
                audioJogo = Applet.newAudioClip(url)
                audioJogo.play()
                audioJogo.loop()
                
            }
        }
    }
    
    private void sons(String som){
        URL url = getClass().getResource(som)
        this.audioReacao = Applet.newAudioClip(url)  
    }   

    
    private boolean jogador(ActionEvent e, int linha, int coluna){
        def perdeu = this.app.jogar(linha,coluna) //se o jogador acertou uma bomba
        
        def tabuleiro
            
        if(!perdeu){
            tabuleiro = this.app.getTabuleiroCasasAbertas()
        }else{
            tabuleiro = this.app.getTabuleiro() //esse método só funciona se o jogador ou a máquina acertar um bomba
        }
            
        for(int i = 1; i < tabuleiro.getTamanhoReal()-1; i++){  //percorrer tabuleiro de casas abertas
            for(int j = 1; j < tabuleiro.getTamanhoReal()-1; j++){
                if(tabuleiro.getCelula(i,j) != null){ //celulas abertas
                    this.janela.getTcm().enabledButton(i-1, j-1, tabuleiro.getCelula(i,j).getValor().toString())
                } 
            }
        }
        
        if(perdeu){
            e.getSource().setBackground(Color.RED)
        }
        return perdeu
    }
    
    private boolean maquina(){
        def perdeu = this.maquina.jogar(this.app)
        
        def tabuleiro
            
        if(!perdeu){
            tabuleiro = this.app.getTabuleiroCasasAbertas()
        }else{
            tabuleiro = this.app.getTabuleiro() //esse método só funciona se o jogador ou a máquina acertar um bomba
        }
            
        for(int i = 1; i < tabuleiro.getTamanhoReal()-1; i++){  //percorrer tabuleiro de casas abertas
            for(int j = 1; j < tabuleiro.getTamanhoReal()-1; j++){
                if(tabuleiro.getCelula(i,j) != null){ //celulas abertas
                    this.janela.getTcm().enabledButton(i-1, j-1, tabuleiro.getCelula(i,j).getValor().toString())
                } 
            }
        }
        
        return perdeu
    }
    

}