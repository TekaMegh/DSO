/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Funcionario;

/**
 *
 * @author carcaroff
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TelaFuncionario {

    private final Scanner sc;

    public TelaFuncionario() {
        sc = new Scanner(System.in);
    }

    /**
     * Mostra a tela do funcionário.
     *
     * @return int opção selecionada.
     */
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

    /**
     * Retorna o próximo inteiro lido pelo Scanner.
     *
     * @param message
     * @return inteiro lido pelo Scanner.
     */
    public int getNextIntFromScanner() {
        return sc.nextInt();
    }

    /**
     * Retorna a próxima String lida pelo Scanner.
     *
     * @param message
     * @return
     */
    public String getNextStringFromScanner() {
        return sc.next();
    }

    /**
     * Recebe como parâmetro em formato Date, e imprime a data no formato:
     * dd/MM/yyyy.
     *
     * @param data
     */
    public void printData(Date data) {
        System.out.print("Data de nascimento: ");
        System.out.printf("%td %<tB %<tY", data);
    }

    /**
     * Verifica se há um int no Scanner.
     *
     * @return boolean indicando a disponibilidade de uma int no Scanner.
     */
    public boolean hasNextInt() {
        return sc.hasNextInt();
    }

    /**
     * Imprime a mensagem "Insira o seu nome".
     *
     * @return nome.
     */
    public String printGetNome() {
        System.out.println("Insira o seu nome:");
        String nome = sc.nextLine();
        sc.next();
        return nome;
    }

    /**
     * Imprime a mensagem "Insira o salário:".
     */
    public int printGetSalario() {
        System.out.println("Insira o salário:");
        while (!sc.hasNextInt()) {
            System.out.println("Salário Inválido.");
            System.out.println("Por favor, insira um número que corresponda ao salário do funcionário. ");
        }
        return sc.nextInt();
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public int printGetMatricula() {
        System.out.println("Insira o número da matrícula: ");
        while (!sc.hasNextInt()) {
            System.out.println("Matrícula Inválida!");
            System.out.println("Por favor, insira o número da matrícula: ");
        }
        return sc.nextInt();
    }

    public String printGetDataNascimento() {
        System.out.println("Insira a data de nascimento(formato dd/MM/yyyy):");
        return sc.next();
    }

    public void printDataInputError(String data) {
        System.out.println("Formato da data inválida: " + data + ". Exemplo do formato correto: 22/09/1990");
    }

    public String printGetTelefone() {
        System.out.println("Insira o telefone:");
        String telefone = sc.nextLine();
        sc.next();
        return telefone;
    }

    public String getNextLineFromScanner() {
        return sc.nextLine();
    }

    public void printNameAlreadyExistsError() {
        System.out.println("Já existe usuário cadastrado com esse nome! Por favor insira novamente o seu nome:");
    }

    public void printInputError() {
        System.out.println("Opção inválida. Por favor, selecione a opção desejada: ");
    }

    public void printFuncionarioListEmptyError() {
        System.out.println("Não há funcionários cadastrados no momento. Por favor, tente cadastrar um funcionário primeiro.");
    }

    public void printMatriculaInvalida() {
        System.out.println("Não existe funcionarios com a matrícula informada.");
    }

    public void printLista(ArrayList<Funcionario> listaFuncionario) {
        for (Funcionario funcionario : listaFuncionario) {
            System.out.println("Matrícula: " + funcionario.getMatricula());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salário: " + funcionario.getSalario());
            System.out.println("Telefone: " + funcionario.getTelefone());
            printData(funcionario.getNascimento());
            System.out.println("Nome do cargo: " + funcionario.getCargo().getNome());
            System.out.println("Código do cargo: " + funcionario.getCargo().getCodigo());
        }
    }

    public void printMainMenu() {
        System.out.println("Voltando para o menu principal...");
    }

    public void printCadastroSucesso() {
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public void printRemoveSucess() {
        System.out.println("Funcionário removido com sucesso!");
    }

    public void printModifySucess() {
        System.out.println("Funcionário modificado com sucesso!");
    }
}
