/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package niveis
import control.Controlador

class NivelUm implements INivel{
    
    private int linhaJogada
    private int colunaJogada
    
    @Override
    def jogar(Controlador control){
        def casas = logica(control)
        linhaJogada = casas[0]
        colunaJogada = casas[1]
        //print "Nível um: \nLINHA: "+casa1+" COLUNA: "+casa2+"\n\n"
        def resultado = control.jogar(linhaJogada, colunaJogada) //RANDOM SEM ABRIR AS CASAS JA ABERTAS
        return resultado
    }
    
    @Override
    def logica(Controlador control) {
        def range = control.getTamanho()
        int casa1, casa2
        def open = true
        
        while(open){ //ENQUANTO A CASA ESTA ABERTA, CONTINUA GERANDO ALEATORIOS ATE O CONTRARIO
            casa1 = gerarAleatorios(range) 
            casa2 = gerarAleatorios(range)
            open = control.celulaIsOpen(casa1,casa2) //CONFERE SE A CASA ESTA ABERTA
        }
        
        //print "\nLINHA: "+casa1+" COLUNA: "+casa2+"\n\n"
        int[] casas = new int[2]
        casas[0] = casa1
        casas[1] = casa2
        return casas
    }
    
    def gerarAleatorios(int range){
        Random rand1 = new Random()
        int casa = rand1.nextInt(range) + 1 //RANGE DE 1 A RANGE
        return casa
    }
    
    def getLinhaJogada(){
        return linhaJogada
    }
    def getColunaJogada(){
        return colunaJogada
    }
    
    
}

