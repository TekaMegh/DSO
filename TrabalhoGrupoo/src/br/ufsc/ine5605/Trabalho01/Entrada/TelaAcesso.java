/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Entrada;

import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.util.Scanner;

/**
 *
 * @author Avell
 */
public class TelaAcesso {

    private Scanner teclado;

    public TelaAcesso() {
        teclado = new Scanner(System.in);
    }

    public void exibeMenuPrincipal() {
        System.out.println("----------Tela Acesso----------");
        System.out.println("1 - Entrar no setor financeiro");
        System.out.println("2 - Registros de Acessos");
        System.out.println("0 - Menu Anterior");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        int opcao = teclado.nextInt();
        switch (opcao) {
            case 1:
                this.entrarSetor();
                break;
            case 2:
                this.registros();
                break;
            case 3:
                ControladorPrincipal.getInstance().inicia();
                break;
        }

    }

    public void entrarSetor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void registros() {
        System.out.println("----------Tela Acesso: Registros----------");
        System.out.println("1 - Relatório de Acessos Negados");
        System.out.println("0 - Voltar");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        int opcao = teclado.nextInt();
        switch (opcao) {
            case 1:
                this.acessosNegados();
                break;
            case 0:
                this.exibeMenuPrincipal();
                break;
        }
    }

    private void acessosNegados() {
        System.out.println("----------Tela Acesso: Relatório de Acessos Negados----------");
        System.out.println("1 - Por tipo de restricao");
        System.out.println("2 - Por Matricula");
        System.out.println("3 - Voltar");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        int opcao = teclado.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("");

            case 2:
                System.out.println("Digite a matricula: ");
                int matricula = teclado.nextInt();
                System.out.println(ControladorAcesso.getInstance().getAcessosByMatricula(matricula));

        }
    }
}
