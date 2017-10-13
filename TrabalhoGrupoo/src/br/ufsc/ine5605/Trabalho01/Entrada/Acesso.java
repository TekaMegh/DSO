
package br.ufsc.ine5605.Trabalho01.Entrada;

import java.util.Calendar;
import java.util.Date;

public class Acesso {

    private TipoAcesso tipo;
    private int matricula;
    private Date horadeacesso;
    private Date dataDaTentativa;

    public Acesso(TipoAcesso tipo, int matricula, Date horadeacesso) {
        this.tipo = tipo;
        this.matricula = matricula;
        this.horadeacesso = horadeacesso;
        this.dataDaTentativa = Calendar.getInstance().getTime();
    }

    public TipoAcesso getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcesso tipo) {
        this.tipo = tipo;
    }

    public int getMatricula() {
        return matricula;
    }

    public Date getHoraDeAcesso() {
        return horadeacesso;
    }

    public Date getDataDaTentativa() {
        return dataDaTentativa;
    }
    
    
    
    
}
