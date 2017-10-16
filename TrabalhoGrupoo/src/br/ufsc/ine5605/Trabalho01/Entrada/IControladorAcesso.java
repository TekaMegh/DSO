package br.ufsc.ine5605.Trabalho01.Entrada;

import java.util.ArrayList;
import java.util.Date;

public interface IControladorAcesso {

    public void inicia();

    public String validaAcesso(int matricula, Date horaDeAcesso);

    public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo);

    public ArrayList<Acesso> getAcessosByMatricula(int matricula);
}
