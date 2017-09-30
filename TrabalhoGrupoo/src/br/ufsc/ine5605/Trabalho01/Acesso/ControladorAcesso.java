/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Acesso;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rak_w
 */
public class ControladorAcesso implements IControladorAcesso {

    private ArrayList<Acesso> acessos;

    /**
     *
     * @return Retorna o Controlador de acesso como um IControladorAcesso.
     */
    public IControladorAcesso ControladorAcesso() {
        acessos = new ArrayList<>();
        return this;
    }

    @Override
    public String validaAcesso(int matricula, Date horaDeAcesso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
