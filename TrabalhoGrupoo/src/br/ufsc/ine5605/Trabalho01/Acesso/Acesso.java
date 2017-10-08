/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Acesso;

import br.ufsc.ine5605.Trabalho01.Funcionario.Funcionario;
import java.util.Date;

/**
 *
 * @author rak_w
 */
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

    public Date getHoraDeAcesso(){
        return horadeacesso;
    }
}
