package br.ufsc.ine5605.Trabalho01.Entrada;

import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TelaAcesso {

    private ControladorAcesso owner;
    private Scanner leia;

    public TelaAcesso(ControladorAcesso owner) {
        leia = new Scanner(System.in);
        this.owner = owner;
    }

    /**
     * Exibe a tela inicial do Acesso
     *
     * @return String com a opcao do menu para ser tratada pelo controlador.
     */
    public String exibeMenuPrincipal() {

        System.out.println("----------Tela Acesso----------");
        System.out.println("1 - Entrar no setor financeiro");
        System.out.println("2 - Registros de Acessos");
        System.out.println("0 - Menu Anterior");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        return leia.next();

    }

    /**
     * Menu de entrada na sala do financeiro.
     *
     * @throws NumberFormatException caso a matrícula inserida não seja um "int"
     * @throws ParseException case o horario de acesso inserido não seja válido
     * ou não esteja no formato adequado.
     */
    public void entrarSetor() throws NumberFormatException, ParseException {

        int matricula;
        Date horaDeAcesso;
        System.out.println("----------Tela Acesso: Entrar----------");
        System.out.println("Digite sua matrícula: ");
        matricula = owner.parseInt(leia.next());
        System.out.println("Digite o horario de acesso (HH:mm): ");
        horaDeAcesso = owner.parseDate(leia.next());

        System.out.println(owner.getInstance().validaAcesso(matricula, horaDeAcesso));

    }

    /**
     * Exibe a tela de registros
     *
     * @return String com a opcao do menu para ser tratada pelo controlador.
     */
    public String registros() {
        System.out.println("----------Tela Acesso: Registros----------");
        System.out.println("1 - Relatório de Acessos Negados");
        System.out.println("0 - Voltar");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        return leia.next();

    }

    /**
     * Exibe a tela de Relatórios de Acessos Negados
     *
     * @return String com a opcao do menu para ser tratada pelo controlador.
     */
    public String menuAcessosNegados() {
        System.out.println("----------Tela Acesso: Relatório de Acessos Negados----------");
        System.out.println("1 - Por tipo de restricao");
        System.out.println("2 - Por Matricula");
        System.out.println("0 - Voltar");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        return leia.next();

    }

    /**
     * Exibe a tela para seleção do tipo de Acesso do qual será feita a lista
     * por parte do controlador
     *
     * @return String coma opcao do menu para se tratada pelo controlador.
     */
    public String tipoAcesso() {
        System.out.println("----------Tela Acesso: Relatório de Acessos Negados----------");
        System.out.println("1 - Horário não permitido");
        System.out.println("2 - Acesso Bloqueado");
        System.out.println("3 - Cargo sem acesso");
        System.out.println("0 - Voltar");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        return leia.next();
    }

    /**
     * imprime mensagem "A opcao não é um número válido"
     *
     * @param e (NumberFormatException)
     */
    public void printOpcaoInvalidaException(NumberFormatException e) {

        System.out.println(e.getMessage());
        System.out.println("A opcao não é um número válido.");

    }

    /**
     * imprime a mensagem "Não é uma hora válida. Formato correto = HH:mm"
     *
     * @param e (ParseException)
     */
    public void printDataInvalidaException(ParseException e) {
        System.out.println(e.getMessage());
        System.out.println("Não é uma hora válida. Formato correto = HH:mm");
    }

    /**
     * Imprime "Opcao escolhida é inválida. Tente novamente." Usada pelos
     * métodos de tratamento de opcao do controlador.
     */
    public void printOpcaoInvalida() {
        System.out.println("Opcao escolhida é inválida. Tente novamente.");
    }

    /**
     * Imprime na tela a lista de Acessos com do tipo passado como parâmetro.
     * falando a Matrícula à qual estão ligadas, A data (dia, mês e ano) da
     * tentativa, e a Hora do acesso.
     *
     * @param acessosByTipo
     */
    public void printListaAcessoByTipo(ArrayList<Acesso> acessosByTipo) {
        DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatadorHora = new SimpleDateFormat("HH:mm");
        if (acessosByTipo.size() == 0) {
            System.out.println("Não existem acessos negados desse tipo");
        }
        for (Acesso acesso : acessosByTipo) {
            System.out.println("--------- ");
            String data = formatadorData.format(acesso.getDataDaTentativa());
            String horaAcesso = formatadorHora.format(acesso.getHoraDeAcesso());
            System.out.println("--Matricula: " + acesso.getMatricula() + " \nData da tentativa: " + data + " \nHora do acesso: " + horaAcesso + " \n");
        }

    }

    /**
     * Imprime na tela a lista de Acessos referentes à matrícula recebida como
     * parâmetro. Imprime a data da tentativa de acesso (dia, mês e ano), e o
     * motivo pelo qual o acesso foi impedido.
     *
     * @param lista
     * @param matricula
     */
    public void printListaAcessoByMatricula(ArrayList<Acesso> lista, int matricula) {

        DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatadorHora = new SimpleDateFormat("HH:mm");
        System.out.println("Acessos negados por " + ControladorPrincipal.getInstance().getFuncionarioByMatricula(matricula).getNome() + ": ");
        if (lista.size() == 0) {
            System.out.println("Não existem acessos negados nessa matrícula.");
        }
        for (Acesso acesso : lista) {
            if (acesso.getMatricula() == matricula) {
                System.out.println("--------- ");
                String data = formatadorData.format(acesso.getDataDaTentativa());
                String hora = formatadorHora.format(acesso.getHoraDeAcesso());
                System.out.println("-- Data da Tentativa de acesso: " + data + " \nHora do acesso: " + hora + " \nmotivo: " + acesso.getTipo().descricao() + "");
            }

        }

    }

    public String opcaoMatricula() {
        System.out.println("Digite a matrícula: ");
        return leia.next();
    }

    void printMatriculaInvalidaException() {
        System.out.println("Não existe funcionário com essa matrícula. Tente Novamente.");
    }

}
