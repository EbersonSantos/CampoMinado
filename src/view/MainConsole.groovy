package view

import control.Controlador
import model.Tabuleiro
import niveis.*

class MainConsole {
    static void main(String[] args){
        int tamanho = 8
        Controlador control = new Controlador(tamanho)
        
        Scanner s = new Scanner(System.in)
        INivel maquina1 = new NivelUm()
        INivel maquina2 = new NivelDois() //retirar
        INivel maquina3 = new NivelTres()
        
        print control.getTabuleiroJogo()// printBoardJogo(board,estado)
        
        def jogador = false
        def fim  = false 
        def IA = false
        while(!jogador && !fim && !IA){
            //print control.getTabuleiroCompleto()
            print "Informe a linha: "
            def linha = s.nextInt()
            print "Informe a coluna: "
            def coluna = s.nextInt()
            
            try{
                jogador = control.jogar(linha,coluna)
                print control.getTabuleiroJogo()
                //control.getMatrizCasasAbertas()
            }catch(Exception e){
                print "\n${e.getMessage()} \n\n"
            }
            
            fim = control.fimDeJogo()
            
            if(jogador == true){
                //print control.getTabuleiroCompleto()//printBoard(board)       
                print "Você perdeu!!!"
                
            }else if(!fim){
                print "\nVEZ DA MAQUINA (Aguarde)...\n"
                sleep(3)
                //IA = maquina1.jogar(control)
                IA  = false
                IA = maquina1.jogar(control)
                
                print control.getTabuleiroJogo() //printBoardJogo(board,estado)
                //control.getMatrizCasasAbertas()
                if(IA == true){
                    //print control.getTabuleiroCompleto()
                    print "Eu perdi, sou uma maquina trouxa!!!"
                }
            }            
            
            if(fim){
                print 'Houve um empate, você não é tão trouxa!'
            }
        }
       
    }
}


