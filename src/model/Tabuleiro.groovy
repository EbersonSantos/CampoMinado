package model

import exceptions.CelulaNaoExisteException
import exceptions.CelulaJaAbertaException

class Tabuleiro {

    private Celula[][] tabuleiro  //tabuleiro de celulas
    private int abertos
    private int jogadas
    
    Tabuleiro(int n, int notLinha, int notColuna){
        tabuleiro = new Celula[n+2][n+2]
        jogadas = 0
        for(int i = 0; i < tabuleiro.length;i++){
            for(int j = 0; j < tabuleiro.length; j++){    
                tabuleiro[i][j] = new Celula()
            }
        }
        
        this.abertos = 0
        this.preencherTabuleiro(notLinha, notColuna)
    }
    
    Tabuleiro(Celula[][] board,int abertos){
        this.tabuleiro = board
        this.abertos = abertos
    }
    
    //TODO
    def getCelula(int linha, int coluna){
        return tabuleiro[linha][coluna]
//        if(tabuleiro[linha][coluna].isOpen()){    //JEITO MAIS ADEQUADO, TOU COLOCANDO DO OUTRO PRA PEGAR O TABULEIRO COMPLETO
//            return tabuleiro[linha][coluna]
//        }else{
//            return null
//        }
    }
    
    def celulaIsOpen(int linha, int coluna){
       return tabuleiro[linha][coluna].isOpen()
    }
    
    def celulaIsEmpty(int linha, int coluna){
        return tabuleiro[linha][coluna].isEmpty()
    }
    
    def celulaIsBomb(int linha, int coluna){
        return tabuleiro[linha][coluna].isBomb()
    }
    
    def getTamanho(){
        return tabuleiro.length-2
    }
    
    def getTamanhoReal(){
        return tabuleiro.length
    }
    
    def jogar(int linha, int coluna){ //retorna true se abre uma bomba
        
        Celula celula = tabuleiro[linha][coluna]
        celula.setEstado(1)//abre a celula
        jogadas++
        this.abertos++
        if(celula.isBomb()){
            return true
        }else if(celula.isEmpty()){            
            for(int i = -1; i <= 1;i++){
                for(int j = -1; j <= 1; j++){
                    def cond1 = !tabuleiro[linha+i][coluna+j].isOpen() && tabuleiro[linha+i][coluna+j].isEmpty()
                    def cond2 = !tabuleiro[linha+i][coluna+j].isOpen() && !tabuleiro[linha+i][coluna+j].isBomb()
                    if(cond1 || cond2){
                        jogar(linha+i,coluna+j)
                    }

                }
            }
            return false
        }
    }
    
    def quantidadeBombas(){
        int celulas = (tabuleiro.length-2) ** 2;
        return (int)Math.ceil(celulas/7);
    }
    
    def getAbertos(){
        return abertos
    }
    
    private void preencherTabuleiro(int notLinha, int notColuna){
        this.preencherEstado()
        this.preencherBombas(notLinha, notColuna)
        this.preencherDicas()
    }
    
    private void preencherBombas(int notLinha, int notColuna){
        boolean sorteado
        int linha, coluna
        Random r = new Random()
        for(int i = 0 ; i < quantidadeBombas(); i++){
            sorteado = true
            
            while(sorteado){
                linha = r.nextInt(tabuleiro.length-2)+1
                coluna = r.nextInt(tabuleiro.length-2)+1
      
                if(linha == notLinha && coluna == notColuna){
                    sorteado = true
                }else if(tabuleiro[linha][coluna].isBomb()){
                    sorteado = true
                }else{
                    sorteado = false
                }
            }
            
            tabuleiro[linha][coluna].setBomba(true)
        }
    }
    
    private void preencherDicas(){
        for(int i = 1; i <= tabuleiro.length-2;i++){
            for(int j = 1; j <= tabuleiro.length-2;j++){
                
                for(int linha = -1; linha <= 1;linha++){
                    for(int coluna = -1; coluna <= 1; coluna++){ 
                        if(!tabuleiro[i][j].isBomb()){
                            if(tabuleiro[i+linha][j+coluna].isBomb()){
                                tabuleiro[i][j].valor++
                            }
                        }
                    }
                } 
            }   
        }
    }
    
    private void preencherEstado(){
        for(int i = 0; i < tabuleiro.length;i++){
            for(int j = 0; j < tabuleiro.length; j++){
                if(i == 0 || j== 0 || i == tabuleiro.length-1 || j == tabuleiro.length-1){
                    tabuleiro[i][j].setEstado(1)
                }
            }
        }
    }        
        
}

