/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Funcionarios;

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
     * Imprime a mensagem "Insira o seu nome".
     * @return String nome.
     */
    public String printGetNome() {
        System.out.println("Insira o seu nome:");
        sc.next();
        String nome = sc.nextLine();
        return nome;
    }

    /**
     * Imprime a mensagem "Insira o salário:" e checa
     * a validade do input recebido.
     * @return int representando o valor do salário
     */
    public int printGetSalario() {
        System.out.println("Insira o salário:");
        while (!sc.hasNextInt()) {
            System.out.println("Salário Inválido.");
            System.out.println("Por favor, insira um número que corresponda ao salário do funcionário. ");
        }
        return sc.nextInt();
    }
    
    /**
     * Imprime a mensagem da exceção recebida 
     * parametro.
     * @param e (Exceção) 
     */
    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
    
    /**
     * Imprime mensagem solicitando pela matrícula e
     * checa a validade do input recebido.
     * @return inteiro representando a matricula
     */
    public int printGetMatricula() {
        System.out.println("Insira o número da matrícula: ");
        while (!sc.hasNextInt()) {
            System.out.println("Matrícula Inválida!");
            System.out.println("Por favor, insira o número da matrícula: ");
        }
        return sc.nextInt();
    }
    /**
     * Imprime mensagem solicitando pela data de nascimento no formato
     * dd/MM/yyyy.
     * @return String representando a data de nascimento.
     */
    public String printGetDataNascimento() {
        System.out.println("Insira a data de nascimento(formato dd/MM/yyyy):");
        return sc.next();
    }
    /**
     * Imprime mensagem de erro, indicando formato da String
     * a ser convertido em data como inadequada.
     * @param data 
     */
    public void printDataInputError(String data) {
        System.out.println("Formato da data inválida: " + data + ". Exemplo do formato correto: 22/09/1990");
    }
    /**
     * Imprime mensagem solicitando o telefone.
     * @return String representando o telefone.
     */
    public String printGetTelefone() {
        System.out.println("Insira o telefone:");
        sc.next();
        String telefone = sc.nextLine();
        return telefone;
    }
    /**
     * Imprime mensagem de erro indicando a já existencia de 
     * outro funcionario com o mesmo nome informado.
     */
    public void printNameAlreadyExistsError() {
        System.out.println("Já existe usuário cadastrado com esse nome! Por favor insira novamente o seu nome:");
    }
    /**
     * Imprime mensagem indicando inexistencia da opção solicitada.
     */
    public void printInvalidOptionError() {
        System.out.println("Opção inválida. Por favor, selecione a opção desejada: ");
    }
    /**
     * Imprime mensagem informando que a lista de funcionários está vazia.
     */
    public void printFuncionarioListEmptyError() {
        System.out.println("Não há funcionários cadastrados no momento. Por favor, tente cadastrar um funcionário primeiro.");
    }
    /**
     * Imprime mensagem informando a invalidez de funcionário
     * com a matrícula informada.
     */
    public void printMatriculaInvalida() {
        System.out.println("Não existe funcionarios com a matrícula informada.");
    }
    /**
     * Imprime todos os dados dos funcionários contidos na lista recebida
     * como parâmetro.
     * @param listaFuncionario 
     */
    public void printLista(ArrayList<Funcionario> listaFuncionario) {
        for (Funcionario funcionario : listaFuncionario) {
            System.out.println("Matrícula: " + funcionario.getMatricula());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salário: " + funcionario.getSalario());
            System.out.println("Telefone: " + funcionario.getTelefone());
            System.out.println("\n");
            printData(funcionario.getNascimento());
            System.out.println("\n");
            System.out.println("Nome do cargo: " + funcionario.getCargo().getNome());
            System.out.println("Código do cargo: " + funcionario.getCargo().getCodigo());
        }
    }
    /**
     * Imprime mensagem informando a volta para o menu principal.
     */
    public void printReturnMainMenu() {
        System.out.println("Voltando para o menu principal...");
    }
    /**
     * Imprime mensagem informando o sucesso do cadastro.
     */
    public void printCadastroSucesso() {
        System.out.println("Funcionário cadastrado com sucesso!");
    }
    /**
     * Imprime mensagem informando o sucesso ao remover funcionário.
     */
    public void printRemoveSucess() {
        System.out.println("Funcionário removido com sucesso!");
    }
    /**
     * Imprime mensagem informando o sucesso da modificação
     * dos dados do funcionário.
     */
    public void printModifySucess() {
        System.out.println("Funcionário modificado com sucesso!");
    }
}
