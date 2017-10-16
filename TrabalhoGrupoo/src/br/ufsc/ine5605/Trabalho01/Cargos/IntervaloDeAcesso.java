package br.ufsc.ine5605.Trabalho01.Cargos;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author TekaMegh
 */
public class IntervaloDeAcesso {

    private Date horarioInicial;
    private Date horarioFinal;
    private SimpleDateFormat formatadorHora;

    /**
     *
     * @param horaInicial
     * @param horaFinal 
     * Método construtor da classe 
     * Inicializa os atributos da classe
     */
    public IntervaloDeAcesso(String horaInicial, String horaFinal) {
        formatadorHora = new SimpleDateFormat("HH:mm");
        try {
            this.horarioInicial = formatadorHora.parse(horaInicial);
            this.horarioFinal = formatadorHora.parse(horaFinal);
        } catch (ParseException e) {
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
        } catch (ParseException e) {
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
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
