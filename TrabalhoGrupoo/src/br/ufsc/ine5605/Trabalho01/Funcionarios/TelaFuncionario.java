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
        int opcao = -1;
        do {
            System.out.println("Selecione a opção desejada: ");
            System.out.println("1 - Adicionar novo funcionário.");
            System.out.println("2 - Modificar dados de funcionário já existente.");
            System.out.println("3 - Remover funcionário.");
            System.out.println("4 - Mostrar todos os funcionários cadastrados.");
            System.out.println("0 - Voltar para o menu principal.");
            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
            } else {
                System.out.println("Opção inválida.");
                sc.nextLine();
            }
        } while (opcao == -1);
        return opcao;
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
        System.out.println("\n");
    }

    /**
     * Imprime a mensagem "Insira o seu nome".
     *
     * @return String nome.
     */
    public String printGetNome() {
        String nome = null;
        boolean emptyNome = false;
        sc.nextLine();
        do {
            System.out.println("Insira o seu nome:");
            nome = sc.nextLine();
            emptyNome = nome.equals("");
            if (emptyNome) {
                System.out.println("Nome inválido. O nome não pode estar em branco.");
            }
        } while (emptyNome);
        return nome;
    }

    /**
     * Imprime a mensagem "Insira o salário:" e checa a validade do input
     * recebido.
     *
     * @return int representando o valor do salário
     */
    public int printGetSalario() {
        sc.nextLine();
	System.out.println("Insira o salário:");
        while (!sc.hasNextInt()) {
            System.out.println("Salário Inválido.");
            System.out.println("Por favor, insira um número que corresponda ao salário do funcionário. ");
	    sc.nextLine();
        }
        return sc.nextInt();
    }

    /**
     * Imprime a mensagem da exceção recebida parametro.
     *
     * @param e (Exceção)
     */
    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Imprime mensagem solicitando pela matrícula e checa a validade do input
     * recebido.
     *
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
     *
     * @return String representando a data de nascimento.
     */
    public String printGetDataNascimento() {
        System.out.println("Insira a data de nascimento(formato dd/MM/yyyy):");
        return sc.next();
    }

    /**
     * Imprime mensagem de erro, indicando formato da String a ser convertido em
     * data como inadequada.
     *
     * @param data
     */
    public void printDataInputError(String data) {
        System.out.println("Formato da data inválida: " + data + ". Exemplo do formato correto: 22/09/1990");
    }

    /**
     * Imprime mensagem solicitando o telefone.
     *
     * @return String representando o telefone.
     */
    public String printGetTelefone() {
        System.out.println("Insira o telefone (formato (12)3456-7890 ou (12)12345-6789) sem espaços:");
        String pattern1 = "^\\(\\d{2}\\)(\\d{4})[- ](\\d{4})";
        String pattern2 = "^\\(\\d{2}\\)(\\d{5})[- ](\\d{4})";
        String telefone = null;
        boolean telefoneValido = false;
        do {
            telefone = sc.next();
            if (!telefone.matches(pattern1) && !telefone.matches(pattern2)) {
                System.out.println("Formato do telefone inválido! Lembrando que é preciso digitar os parenteses\"()\" e hifen\"-\", e que não pode conter espaços. ");
                System.out.println("Por favor, siga um dos exemplos abaixo:");
                System.out.println("Para telefones fixos:");
                System.out.println("(12)3456-7890");
                System.out.println("No caso de celulares:");
                System.out.println("(12)12345-6789");
                System.out.println("Por favor, digite o telefone novamente:");
            } else {
                telefoneValido = true;
            }
        } while (!telefoneValido);
        return telefone;
    }

    /**
     * Imprime mensagem de erro indicando a já existencia de outro funcionario
     * com o mesmo nome informado.
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
     * Imprime mensagem informando a invalidez de funcionário com a matrícula
     * informada.
     */
    public void printMatriculaInvalida() {
        System.out.println("Não existe funcionarios com a matrícula informada.");
    }

    /**
     * Imprime todos os dados dos funcionários contidos na lista recebida como
     * parâmetro.
     *
     * @param listaFuncionario
     */
    public void printLista(ArrayList<Funcionario> listaFuncionario) {
        for (Funcionario funcionario : listaFuncionario) {
            System.out.println("Matrícula: " + funcionario.getMatricula());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salário: " + funcionario.getSalario());
            System.out.println("Telefone: " + funcionario.getTelefone());
            printData(funcionario.getNascimento());
            System.out.println("Nome do cargo: " + funcionario.getCargo().getNome());
            System.out.println("Código do cargo: " + funcionario.getCargo().getCodigo());
            System.out.println("-------------------------------------------------");
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
     * Imprime mensagem informando o sucesso da modificação dos dados do
     * funcionário.
     */
    public void printModifySucess() {
        System.out.println("Funcionário modificado com sucesso!");
    }
    /**
     * Imprime mensagem informando o valor null do atributo Cargo, deixando implicito a
     * volta para o menu de funcionários.
     */
    public void printChooseCargoError() {
        System.out.println("Nenhum cargo selecionado. Voltando ao menu de funcionários...");
    }
}
