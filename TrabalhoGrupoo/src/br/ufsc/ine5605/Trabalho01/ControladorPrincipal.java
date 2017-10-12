/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01;

import br.ufsc.ine5605.Trabalho01.Cargo.ControladorCargo;
import br.ufsc.ine5605.Trabalho01.Entrada.ControladorAcesso;
import br.ufsc.ine5605.Trabalho01.Funcionario.ControladorFuncionario;

/**
 *
 * @author rak_w
 */
public class ControladorPrincipal {
    
    private static ControladorPrincipal controladorPrincipal;
    private ControladorCargo ctrlCargo;
    private ControladorFuncionario ctrlFuncionario;
    private ControladorAcesso ctrlAcesso;

    private TelaPrincipal tela;
    
    public ControladorPrincipal() {
        tela = new TelaPrincipal();
        ctrlCargo = ControladorCargo.getInstance();
        ctrlFuncionario = ControladorFuncionario.getInstance();
        ctrlAcesso = ControladorAcesso.getInstance();
    }
    
    /**
     * Mostra a tela principal, e encaminha a opção escolhida para tratamento.
     */
    public void inicia() {
        int opcao = tela.exibeMenuPrincipal();
        try{
            opcaoSwitch(opcao);
        } catch (Exception e){
            tela.print(e.getMessage());
        }
    }
    /**
     * Faz o tratamento da opção selecionada.
     * @param opcao 
     */
    public void opcaoSwitch(int opcao){
        switch(opcao){
            case 1:
                ctrlAcesso.inicia();
                break;
            case 2:
                ctrlCargo.inicia();
                break;
            case 3:
                ctrlFuncionario.inicia();
            case 0:
                System.exit(0);
                break;
            default:
                tela.printInvalidOptionError();
                ControladorPrincipal.getInstance().inicia();
        }
    }

    /**
     * Retorna o controlador Principal.
     * @return ControladorPrincipal
     */
    public static ControladorPrincipal getInstance() {
        if(controladorPrincipal == null){
             controladorPrincipal = new ControladorPrincipal();
        }
        return controladorPrincipal;
    }
    
    
    
}
