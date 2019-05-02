/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model

class Celula {
    
    int estado //1:aberto 0:fechado
    int valor //-1:bomba 0:nulo 1-8: bombas em volta
    boolean bomba //informa se a celula é uma bomba
    
    def setEstado(int estado){
        if(estado == 0 || estado == 1){
            this.estado = estado
        }
    }
    
    def setBomba(boolean bomba){
        if(bomba){
            this.bomba = bomba
            valor = -1
        }else{
            this.bomba = bomba
        }
    }
    
    def isOpen(){
       if(estado == 1){
           return true
       }else{
           return false
       }
    }
    
    def isBomb(){
       return bomba 
    }

    def isEmpty(){
        if(valor == 0){
            return true
        }else{
            return false
        }
    }

}