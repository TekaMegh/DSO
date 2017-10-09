/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Cargo;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author rak_w
 */
public class IntervaloDeAcesso {

    private Date horarioInicial;
    private Date horarioFinal;
    private SimpleDateFormat formatadorHora;

    public IntervaloDeAcesso(String horaInicial, String horaFinal) {
        formatadorHora = new SimpleDateFormat("HH:mm");
        try {
            this.horarioInicial = formatadorHora.parse(horaInicial);
            this.horarioFinal = formatadorHora.parse(horaFinal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Date getHorarioInicial() {
        return this.horarioInicial;
    }

    public void setHorarioInicial(String horaInicial) {
        formatadorHora = new SimpleDateFormat("HH:mm");
        try {
            this.horarioInicial = formatadorHora.parse(horaInicial);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Date getHorarioFinal() {
        return this.horarioFinal;
    }

    public void setHorarioFinal(String horaFinal) {
        formatadorHora = new SimpleDateFormat("HH:mm");
        try {
            this.horarioInicial = formatadorHora.parse(horaFinal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
