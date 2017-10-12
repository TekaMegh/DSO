package br.ufsc.ine5605.Trabalho01.Entrada;

import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.util.Scanner;

public class TelaAcesso {

    private ControladorAcesso owner;
    private Scanner leia;

    public TelaAcesso(ControladorAcesso owner) {
        leia = new Scanner(System.in);
        this.owner = owner;
    }

    public void exibeMenuPrincipal() {
        System.out.println("----------Tela Acesso----------");
        System.out.println("1 - Entrar no setor financeiro");
        System.out.println("2 - Registros de Acessos");
        System.out.println("0 - Menu Anterior");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        int opcao = leia.nextInt();
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
        int matricula;
        String horaDeAcesso;
        System.out.println("----------Tela Acesso: Entrar----------");
        System.out.println("Digite sua matrícula: ");
        matricula = leia.nextInt();
        System.out.println("Digite o horario de acesso (HH:mm): ");
        horaDeAcesso = leia.next();
        //tratar enum
        owner.getInstance().validaAcesso(matricula, horaDeAcesso);

    }

    private void registros() {
        System.out.println("----------Tela Acesso: Registros----------");
        System.out.println("1 - Relatório de Acessos Negados");
        System.out.println("0 - Voltar");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        int opcao = leia.nextInt();
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
        int opcao = leia.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("");
            case 2:
                System.out.println("Digite a matricula: ");
                int matricula = leia.nextInt();
                System.out.println(ControladorAcesso.getInstance().getAcessosByMatricula(matricula));
        }
    }
}
