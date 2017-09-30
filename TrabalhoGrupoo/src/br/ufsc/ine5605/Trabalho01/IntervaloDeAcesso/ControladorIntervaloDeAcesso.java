/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.IntervaloDeAcesso;

/**
 *
 * @author rak_w
 */
public class ControladorIntervaloDeAcesso {
    
    private IntervaloDeAcesso intervaloDeAcesso;
    
    public ControladorIntervaloDeAcesso(String horaInicial, String horaFinal){
        this.intervaloDeAcesso = new IntervaloDeAcesso(horaInicial, horaFinal);
    }
    
    public IntervaloDeAcesso getIntervaloDeAcesso(){
        return this.intervaloDeAcesso;
    }
    
    public void setIntervaloDeAcesso(String horaInicial, String horaFinal){
        this.intervaloDeAcesso = new IntervaloDeAcesso(horaInicial, horaFinal);
    }
}
