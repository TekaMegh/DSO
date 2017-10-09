/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Entrada;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rak_w
 */
public interface IControladorAcesso {
    /**
     * Abre a tela de acesso.
     */
    public void inicia();
    /**
     * @param matricula
     * @param horaDeAcesso
     * @return String afirmando se foi possível a entrada ou não.
     *
     *
     */
    public String validaAcesso(int matricula, Date horaDeAcesso);

    /**
     *
     * @param tipo
     * @return ArrayList de Acessos baseada no tipo enviado como parâmetro.
     */
    public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo);

    public ArrayList<Acesso> getAcessosByMatricula(int matricula);

}
