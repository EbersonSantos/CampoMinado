/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package niveis

import control.Controlador
import model.Tabuleiro
import model.Celula

/**
 *
 * @author Usuário
 */
class NivelDois implements INivel{
    
    private int linhaJogada
    private int colunaJogada
    
    private int[][] matrizBombas
    
    void setIsBomb(int linha, int coluna){
        matrizBombas[linha][coluna] = 1
    }
    
    void setIsNotBomb(int linha, int coluna){
        matrizBombas[linha][coluna] = -1
    }
    
    def sureIsBomb(int linha, int coluna){
        if(matrizBombas[linha][coluna] == 1){
            return true
        }else{
            return false
        }
    }
    
    def sureIsNotBomb(int linha, int coluna){
        if(matrizBombas[linha][coluna] == -1){
            return true
        }else{
            return false
        }
    }
    
    @Override
    def jogar(Controlador control){
        def casas = logica(control)
        linhaJogada = casas[0]
        colunaJogada = casas[1]
        //print "Nível dois: \nLINHA: "+linha+" COLUNA: "+coluna+"\n\n"
        
        def resultado = control.jogar(linhaJogada, colunaJogada) //RANDOM SEM ABRIR AS CASAS JA ABERTAS
        return resultado
    }
    
//    def printMatrizBombas(int[][] cells){
//        
//        def tam = cells.length
//        def str = "  "
//        
//        for(int i = 1; i < tam-1;i++){
//            str += " $i  "
//        }
//        str += "\n"
//        
//        for(int i = 1; i < tam-1;i++){
//            str += "$i  "
//            for(int j = 1; j < tam-1;j++){
//                if(cells[i][j] != -1){
//                    str += "${cells[i][j]} | "
//                }else{
//                    str += "${cells[i][j]}| "
//                }
//            }   
//            str += "\n"
//        }
//        str += "\n"
//        print str
//    }

    @Override
    def logica(Controlador control){
        preencherMatrizBombas(control)
        
        //printMatrizBombas(matrizBombas)
        
        def tamanho = control.getTamanho()
        
        int linha, coluna
        def estadoCelula = true //CONFERE SE A CASA ESTA ABERTA
        def isBomb = true //CONFERE SE A CASA POSSUI BOMBA
        
        while(isBomb || estadoCelula){ //ENQUANTO A CASA FOR BOMBA OU ESTÀ ABERTA, CONTINUA GERANDO ALEATORIOS ATE O CONTRARIO
            linha = gerarAleatorios(tamanho) 
            coluna = gerarAleatorios(tamanho)
            isBomb = sureIsBomb(linha,coluna)
            estadoCelula = control.celulaIsOpen(linha,coluna)
        }
        
        int[] casa = new int[2]
        casa[0] = linha
        casa[1] = coluna
        return casa
    }
    
    void preencherMatrizBombas(Controlador control){
        celulasComBombas(control)
    }
    
                    
    void celulasComBombas(Controlador control){
        def tam = control.getTamanhoReal()
        matrizBombas = new int[tam][tam]
        
        Tabuleiro tabuleiroAberto = control.getTabuleiroCasasAbertas()
        
        def casasFechadas = new ArrayList<int[]>()
       
        for(int i = 1; i < tam-1;i++){
            for(int j = 1; j < tam-1;j++){
                
                def fechadas = 0
                if(tabuleiroAberto.getCelula(i,j) != null){
                    if(!tabuleiroAberto.celulaIsEmpty(i,j)){
                      
                        for(int linha = -1; linha <= 1;linha++){
                            for(int coluna = -1; coluna <= 1; coluna++){
                                if(tabuleiroAberto.getCelula(i+linha,j+coluna) == null){                                    
                                    fechadas++             //CONTA QUANTAS CASAS FECHADAS HÀ AO REDOR DA CASA E ARMAZENA ESSAS CASAS
                                    int[] casa = new int[2]
                                    casa[0] = i+linha
                                    casa[1] = j+coluna
                                    casasFechadas.add(casa)
                                }
                            }
                        }
                    
                        if(tabuleiroAberto.getCelula(i,j).getValor() == fechadas){  //SE O NÚMERO DA CASA È IGUAL AO DE CASAS FECHADAS, TODAS SÂO BOMBAS
                            for(casa in casasFechadas){
                                setIsBomb(casa[0],casa[1])
                            }
                        }
                        casasFechadas.clear()
                   
                    }
                }
            }
        }
    }

    def gerarAleatorios(int range){
        Random rand1 = new Random()
        int num = rand1.nextInt(range) + 1 //RANGE DE 1 A RANGE
        return num
    }
    
    def getLinhaJogada(){
        return linhaJogada
    }
    def getColunaJogada(){
        return colunaJogada
    }
}

