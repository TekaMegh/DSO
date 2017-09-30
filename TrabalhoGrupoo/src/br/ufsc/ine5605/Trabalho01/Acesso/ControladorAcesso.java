/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Acesso;

import br.ufsc.ine5605.Trabalho01.Funcionario.Funcionario;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rak_w
 */
public class ControladorAcesso implements IControladorAcesso {

    private static ControladorAcesso ctrl;
    private ArrayList<Acesso> acessos;
    private ArrayList<Funcionario> funcionariosComAcessoNegado;
    private TelaAcesso tela;

    /**
     *
     * @return Retorna a única instância de Controlador de acesso como um
     * IControladorAcesso.
     */
    public ControladorAcesso ControladorAcesso() {
        acessos = new ArrayList<>();
        tela = new TelaAcesso();
        return this;
    }

    public static IControladorAcesso getInstance() {

        if (ctrl == null) {
            ctrl = new ControladorAcesso();
        }
        return ctrl;
    }

    public void inicia() {
        tela.exibeMenuPrincipal();
    }

    @Override
    public String validaAcesso(int matricula, Date horaDeAcesso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param tipo
     * @return ArrayList dos acessos filtrados pelo tipo recebido
     */
    @Override
    public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo) {
        ArrayList<Acesso> listaAcessosByTipo = new ArrayList<>();
        for (Acesso acesso : acessos) {
            if (acesso.getTipo() == tipo) {
                listaAcessosByTipo.add(acesso);
            }
        }
        return listaAcessosByTipo;
    }

    @Override
    public ArrayList<Acesso> getAcessosByMatricula(int matricula) {

        ArrayList<Acesso> listaAcessosByMatricula = new ArrayList<>();

        for (Funcionario funcionario : funcionariosComAcessoNegado) {
            if (funcionario.getMatricula() == matricula) {

                return funcionario.getAcessosNegados();

            }
        }
        return null;
    }

}
