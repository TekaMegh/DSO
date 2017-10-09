/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01;

import br.ufsc.ine5605.Trabalho01.Entrada.ControladorAcesso;
import br.ufsc.ine5605.Trabalho01.Cargo.ControladorCargo;
import br.ufsc.ine5605.Trabalho01.Funcionario.ControladorFuncionario;

/**
 *
 * @author rak_w
 */
public class ControladorPrincipal {
    
    private final static ControladorPrincipal controladorPrincipal = new ControladorPrincipal();

    private TelaPrincipal tela;
    
    public ControladorPrincipal() {
        tela = new TelaPrincipal();
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
    
    public void opcaoSwitch(int opcao){
        switch(opcao){
            case 1:
                ControladorAcesso.getInstance().inicia();
                break;
            case 2:
                ControladorCargo.getInstance().inicia();
                break;
            case 3:
                ControladorFuncionario.getInstance().inicia();
            case 0:
                System.exit(0);
            default:
                tela.print("Opção inválida.");
                controladorPrincipal.inicia();
        }
    }

    /**
     * Retorna o controlador Principal.
     * @return ControladorPrincipal
     */
    public static ControladorPrincipal getInstance() {
        return controladorPrincipal;
    }
    
    
    
}
