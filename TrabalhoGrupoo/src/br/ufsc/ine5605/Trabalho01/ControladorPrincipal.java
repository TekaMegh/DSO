
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01;

import br.ufsc.ine5605.Trabalho01.Cargos.Cargo;
import br.ufsc.ine5605.Trabalho01.Cargos.ControladorCargo;
import br.ufsc.ine5605.Trabalho01.Cargos.IControladorCargo;
import br.ufsc.ine5605.Trabalho01.Entrada.ControladorAcesso;
import br.ufsc.ine5605.Trabalho01.Entrada.IControladorAcesso;
import br.ufsc.ine5605.Trabalho01.Funcionarios.ControladorFuncionario;
import br.ufsc.ine5605.Trabalho01.Funcionarios.Funcionario;
import br.ufsc.ine5605.Trabalho01.Funcionarios.IControladorFuncionario;
import java.util.ArrayList;

/**
 *
 * @author rak_w
 */
public class ControladorPrincipal {

    private static ControladorPrincipal controladorPrincipal;
    private IControladorCargo ctrlCargo;
    private IControladorFuncionario ctrlFuncionario;
    private IControladorAcesso ctrlAcesso;

    private TelaPrincipal tela;

    public ControladorPrincipal() {
        tela = new TelaPrincipal();
        ctrlCargo = ControladorCargo.getInstance();
        ctrlFuncionario = ControladorFuncionario.getInstance();
        ctrlAcesso = ControladorAcesso.getInstance();
    }

    /**
     * Retorna o controlador Principal.
     *
     * @return ControladorPrincipal
     */
    public static ControladorPrincipal getInstance() {
        if (controladorPrincipal == null) {
            controladorPrincipal = new ControladorPrincipal();
        }
        return controladorPrincipal;
    }

    /**
     * Mostra a tela principal, e encaminha a opção escolhida para tratamento.
     */
    public void inicia() {
        while (true) {
            int opcao = tela.exibeMenuPrincipal();
            try {
                opcaoSwitch(opcao);
            } catch (Exception e) {
                tela.print(e.getMessage());
            }
        }
    }

    /**
     * Faz o tratamento da opção selecionada.
     *
     * @param opcao
     */
    public void opcaoSwitch(int opcao) {

        switch (opcao) {
            case 1:
                ctrlAcesso.inicia();
                break;
            case 2:
                ctrlCargo.inicia();
                break;
            case 3:
                ctrlFuncionario.inicia();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                tela.printInvalidOptionError();
                ControladorPrincipal.getInstance().inicia();
        }

    }

    public ArrayList<Funcionario> getListaFuncionarios() {
        return ctrlFuncionario.getListaFuncionario();
    }

    /**
     * Percorre lista de funcionarios cadastrados até encontrar um com a
     * matricula dada.
     *
     *
     * @param matricula
     * @return funcionario encontrado caso a matrícula exista;
     * @return null caso não haja funcionario com a mesma matricula;
     */
    public Funcionario getFuncionarioByMatricula(int matricula) {
        ArrayList<Funcionario> listaFuncionarios = ctrlFuncionario.getListaFuncionario();

        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }

        return null;

    }

    public Cargo chooseCargo() throws Exception {

        return ctrlCargo.chooseCargo();

    }

    public boolean hasFuncionarioByCargo(Cargo cargo) {
        boolean hasFuncionario = ctrlFuncionario.hasFuncionarioByCargo(cargo);
        if (hasFuncionario) {
            ctrlFuncionario.printFuncionarioByCargo(cargo);
        }
        return hasFuncionario;
    }

    public boolean hasFuncionarioByMatricula(int matricula) {
        return ctrlFuncionario.hasFuncionarioByMatricula(matricula);
    }

}
