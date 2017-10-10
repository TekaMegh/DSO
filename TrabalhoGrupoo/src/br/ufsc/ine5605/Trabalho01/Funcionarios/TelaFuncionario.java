package br.ufsc.ine5605.Trabalho01.Funcionarios;

import java.util.Date;
import java.util.Scanner;

public class TelaFuncionario {

    private final Scanner sc;

    public TelaFuncionario() {
        sc = new Scanner(System.in);
    }

    public int mostrarTela() {
        System.out.println("-----------Tela de Fucionários-----------");
        System.out.println("Selecione a opção desejada: ");
        System.out.println("1 - Adicionar novo funcionário.");
        System.out.println("2 - Modificar dados de funcionário já existente.");
        System.out.println("3 - Remover funcionário.");
        System.out.println("4 - Mostrar todos os funcionários cadastrados.");
        System.out.println("0 - Voltar para o menu principal.");
        return sc.nextInt();
    }

    public void print(String mensagem) {
        System.out.println(mensagem);
    }

    public int getNextIntFromScanner(String message) {
        System.out.println(message);
        return sc.nextInt();
    }

    public String getNextStringFromScanner(String message) {
        System.out.println(message);
        return sc.next();
    }

    public void printData(Date data) {
        System.out.print("Data de nascimento: ");
        System.out.printf("%td %<tB %<tY", data);
        System.out.println("");
    }

    public Scanner getScanner() {
        return sc;
    }
}
