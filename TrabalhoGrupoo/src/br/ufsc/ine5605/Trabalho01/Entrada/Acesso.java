<<<<<<< HEAD
package br.ufsc.ine5605.Trabalho01.Entrada;

import br.ufsc.ine5605.Trabalho01.Funcionarios.Funcionario;
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
=======
package br.ufsc.ine5605.Trabalho01.Entrada;

import br.ufsc.ine5605.Trabalho01.Funcionarios.Funcionario;
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
>>>>>>> 3e99f788e5b860d86a557a6e01c4b7b6e01076c9
