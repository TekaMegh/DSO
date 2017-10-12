package br.ufsc.ine5605.Trabalho01.Entrada;

import java.util.ArrayList;
import java.util.Date;

public interface IControladorAcesso {

    public void inicia();

    public TipoAcesso validaAcesso(int matricula, String horaDeAcesso);

    public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo);

    public ArrayList<Acesso> getAcessosByMatricula(int matricula);
}
