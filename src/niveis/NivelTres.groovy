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
class NivelTres implements INivel{
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
        def quantidadeBombas = 0
        
        for(int linha = 1; linha <= tamanho;linha++){
            for(int coluna = 1; coluna <= tamanho; coluna++){
                
                if(sureIsNotBomb(linha,coluna)){
                    //print "\nEstratégia 1: LINHA: "+linha+" COLUNA: "+coluna+"\n\n"
                    int[] casa = new int[2]
                    casa[0] = linha
                    casa[1] = coluna
                    return casa
                }
                
            }
        }
        
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
        celulasSemBombas(control)
    }
    
    void celulasSemBombas(Controlador control){
        def tam = control.getTamanhoReal()
        
        def tabuleiroAberto = control.getTabuleiroCasasAbertas()
        def casasFechadas = new ArrayList<int[]>()
        
        for(int i = 1; i < tam-1;i++){
            for(int j = 1; j < tam-1;j++){
                
                if(tabuleiroAberto.getCelula(i,j) != null){
                    if(!tabuleiroAberto.celulaIsEmpty(i,j)){
                        def qtBombas = 0
                        
                        for(int linha = -1; linha <= 1;linha++){
                            for(int coluna = -1; coluna <= 1; coluna++){
                                if(sureIsBomb(i+linha,j+coluna)){
                                    qtBombas++      //CONTA QUANTAS BOMBAS ESTÂO AO REDOR DA CASA
                                }else if(tabuleiroAberto.getCelula(i+linha,j+coluna) == null){   //ADICIONA AS CASAS FECHADAS AO REDOR DA CASA
                                    int[] casa = new int[2]
                                    casa[0] = i+linha
                                    casa[1] = j+coluna
                                    casasFechadas.add(casa)
                                }
                            }
                        }
                        
                        if(qtBombas == tabuleiroAberto.getCelula(i,j).getValor()){ //SE A QUANTIDADE DE BOMBAS AO REDOR DA CASA FOR IGUAL AO NUMERO DA
                            for(casa in casasFechadas){                            //CASA, AS OUTRAS CASAS FECHADAS NÂO SÃO BOMBAS
                                setIsNotBomb(casa[0],casa[1])
                            }
                        }
                        casasFechadas.clear()
                    }
                }
            }
        }
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

