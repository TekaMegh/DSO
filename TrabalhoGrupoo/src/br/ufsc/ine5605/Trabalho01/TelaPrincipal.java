
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01;

/**
 *
 * @author carcaroff
 */
import java.util.Scanner;

public class TelaPrincipal {

    private Scanner sc;
    
    public TelaPrincipal(){
        sc = new Scanner(System.in);
    }
    public int exibeMenuPrincipal() {
        System.out.println("----------Tela Principal--------");
        System.out.println("Selecione a opção desejada: ");
        System.out.println("1 - Menu de Acessos.");
        System.out.println("2 - Menu de Cargos.");
        System.out.println("3 - Menu de Funcionários.");
        System.out.println("0 - Fechar o Sistema.");
        return sc.nextInt();
    }
    
    public void print(String mensagem){
        System.out.println(mensagem);
    }

    public void printInvalidOptionError() {
        System.out.println("Opção inválida.");
    }
}

