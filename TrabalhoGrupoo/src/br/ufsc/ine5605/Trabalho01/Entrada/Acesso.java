package br.ufsc.ine5605.Trabalho01.Entrada;

import br.ufsc.ine5605.Trabalho01.Funcionario.Funcionario;
import java.util.Date;

public class Acesso {

    private TipoAcesso tipo;

    private int matricula;

    private Date horadeacesso;

    public Acesso(TipoAcesso tipo, int matricula, Date horadeacesso) {
        this.tipo = tipo;
        this.matricula = matricula;
        this.horadeacesso = horadeacesso;
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
}
