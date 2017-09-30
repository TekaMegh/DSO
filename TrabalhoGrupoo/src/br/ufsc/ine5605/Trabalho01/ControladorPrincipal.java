/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01;

import java.util.ArrayList;
/**
 *
 * @author rak_w
 */
public class ControladorPrincipal {
    
    private static ControladorPrincipal ctrl = new ControladorPrincipal();

    private TelaPrincipal tela;
    
    private ControladorPrincipal() {
        tela = new TelaPrincipal();
    }

    public void inicia() {
        tela.exibeMenuPrincipal();
    }

    /**
     * 
     * @return única instância do controlador principal.
     */
    public static ControladorPrincipal getInstance() {

        if (ctrl == null) {
            ctrl = new ControladorPrincipal();
        }

        return ctrl;
    }
    
    
    
}
