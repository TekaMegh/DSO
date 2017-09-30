/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.IntervaloDeAcesso;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author rak_w
 */

public class IntervaloDeAcesso {
    private Date horarioInicial;
    private Date horarioFinal;
    
    public IntervaloDeAcesso(String horaInicial, String horaFinal){
        SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
        this.horarioInicial = formatadorHora.parse(horaInicial);
        this.horarioFinal = formatadorHora.parse(horaFinal);
    }
    
    public Date getHorarioInicial(){
        return this.horarioInicial;
    }
    
    public void setHorarioInicial(String horaInicial){
        SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
        this.horarioInicial = formatadorHora.parse(horaInicial);
    }
    
    public Date getHorarioFinal(){
        return this.horarioFinal;
    }
    
    public void setHorarioFinal(String horaFinal){
        SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
        this.horarioFinal = formatadorHora.parse(horaFinal);
    }
}
