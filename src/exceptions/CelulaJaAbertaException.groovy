/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions

/**
 *
 * @author Usuário
 */
class CelulaJaAbertaException extends Exception {
        
    CelulaJaAbertaException(){
        super("A celula escolhida já está aberta!")
    }
	
}

