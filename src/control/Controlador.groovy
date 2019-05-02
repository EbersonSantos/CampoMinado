
package control

import exceptions.CelulaJaAbertaException
import exceptions.CelulaNaoExisteException
import model.Tabuleiro
import model.Celula

class Controlador{
    
    private Tabuleiro board
    private tamanhoBoard
    private boolean showAll
    
    
    Controlador(int n){
        board = null
        tamanhoBoard = n
    }
    
    Controlador(){
        board = null
        tamanhoBoard = 7
    }
    
    def celulaIsOpen(int linha, int coluna){
        return board.celulaIsOpen(linha,coluna)
    }
    
    def getTabuleiro(){ //retorna o tabuleiro apenas se uma bomba for aberta
       if(showAll){
           return board
       }else{
           return null
       }
    }
    
    def getTabuleiroJogo(){   // Mostra o tabuleiro em modo de jogo RETIRARRR <<<<---------------
        //return board.getTabuleiro()
        def str = "  "
        
        for(int i = 1; i < board.getTamanhoReal()-1;i++){
            str += " $i  "
        }
        str += "\n"
        for(int i = 1; i < board.getTamanhoReal()-1;i++){  //printar tabuleiro real 1..board.length-1
            str += "$i  "
            for(int j = 1; j < board.getTamanhoReal()-1;j++){
                if(board.celulaIsOpen(i,j)){
                    if(board.celulaIsEmpty(i,j)){
                        str += "  | "
                    }else if(!board.celulaIsBomb(i,j)){
                        str += "${board.getCelula(i,j).getValor()} | "
                    }else{
                        str += "* | "
                    }    
                    
                }else{
                    str += "- | "
                }
            }   
            str += "\n"
        }
        str += "\n"
    }
    
    def getTabuleiroCompleto(){   //mostra o tabuleiro completo em modo de jogo RETIRARRR <<<<---------------
        def str = "  "
        
        for(int i = 1; i < board.getTamanhoReal()-1;i++){
            str += " $i  "
        }
        str += "\n"
        
        for(int i = 1; i < board.getTamanhoReal()-1;i++){  //printar tabuleiro real 1..board.length-1
            str += "$i  "
            for(int j = 1; j < board.getTamanhoReal()-1;j++){
                if(board.celulaIsEmpty(i,j)){
                    str += "  | "
                }else if(!board.celulaIsBomb(i,j)){
                    str += "${board.getCelula(i,j).getValor()} | "
                }else{
                    str += "* | "
                }
            }   
            str += "\n"
        }
        str += "\n"
    }
    
    //retorna um tabuleiro com as celulas abertas, celulas
    //fechadas retornalm null
    
    def getTabuleiroCasasAbertas(){
       
        Celula[][] matrix = new Celula[getTamanhoReal()][getTamanhoReal()]
        def abertos = 0
        
        for(int i = 0; i < getTamanhoReal();i++){
            for(int j = 0; j < getTamanhoReal();j++){
                if(board.celulaIsOpen(i,j)){    //olha se a casa está aberta e copia o valor do tabuleiro
                    matrix[i][j] = board.getCelula(i,j)
                    abertos++
                }else{
                    matrix[i][j] =  null
                }
                //print "${matriz[i-1][j-1]} | "
            }
            //print "\n"
        }
        Tabuleiro t = new Tabuleiro(matrix, abertos)
        return t
    }
    
    def getTamanho(){
        return tamanhoBoard
    }
    
  
    
    def getTamanhoReal(){
        return tamanhoBoard+2
    }
    
    def jogar(int linha, int coluna){ //quando se aceta em uma bomba, o tabuleiro todo pode ser mostrado
        if(board == null){
            board = new Tabuleiro(tamanhoBoard, linha, coluna)
        }
        def result = board.jogar(linha,coluna)
        if(result){
            showAll = true
        }
        return result
    }
   
    def fimDeJogo(){
        int abertos = board.getAbertos()
        int bombas = board.quantidadeBombas()
        int tamanho = (board.getTamanho()) ** 2
        if(abertos == tamanho-bombas){
            return true
        }else{
            return false
        }
    }
    
}

