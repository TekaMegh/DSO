/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Funcionarios;

import br.ufsc.ine5605.Trabalho01.Cargos.Cargo;
import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author carcaroff
 */
public class ControladorFuncionario implements IControladorFuncionario {

    private static ControladorFuncionario controladorFuncionario;
    private final ArrayList<Funcionario> listaFuncionario;
    private int numMatricula;
    private final TelaFuncionario telaFuncionario;

    public ControladorFuncionario() {
        listaFuncionario = new ArrayList<>();
        this.numMatricula += 1;
        telaFuncionario = new TelaFuncionario();
    }

    /**
     * Abre a tela de Funcionários e encaminha opção selecionada para o metodo
     * de switch.
     */
    @Override
    public void inicia() {
        int opcao = telaFuncionario.mostrarTela();
        opcaoSwitch(opcao);
    }

    /**
     * Trata a opção informada pela tela.
     *
     * @param opcao
     */
    public void opcaoSwitch(int opcao) {
        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
        switch (opcao) {
            case 1:
                //Parte 1 - nome;
                String nome = null;
                boolean hasFuncionarioByNome = false;
                do {
                    nome = telaFuncionario.printGetNome();
                    hasFuncionarioByNome = ControladorFuncionario.getInstance().hasFuncionarioByNome(nome);
                    if (hasFuncionarioByNome) {
                        telaFuncionario.printNameAlreadyExistsError();
                    }
                } while (hasFuncionarioByNome);

                //Parte 2 - DataNascimento;
                boolean dateParseSucess = false;
                Date dataNascimento = null;
                do {

                    String data = telaFuncionario.printGetDataNascimento();;
                    try {
                        dataNascimento = formataData.parse(data);
                        dateParseSucess = true;
                    } catch (Exception e) {
                        telaFuncionario.printExceptionMessage(e);
                        telaFuncionario.printDataInputError(data);
                    }
                } while (!dateParseSucess);

                //Parte 3 - Telefone;
                String telefone = telaFuncionario.printGetTelefone();

                //Parte 4 - Salario;
                int salario = telaFuncionario.printGetSalario();

                //Parte 5 - Cargo;
                Cargo cargo = null;
                try{
                    cargo = ControladorPrincipal.getInstance().chooseCargo();
                } catch(Exception e){
                    telaFuncionario.printExceptionMessage(e);
                }

                try {
                    ControladorFuncionario.getInstance().incluiFuncionario(nome, dataNascimento, telefone, salario, cargo);
                } catch (Exception e) {
                    telaFuncionario.printExceptionMessage(e);
                }
                break;
            case 2:

                //Checagem da lista
                if (listaFuncionario.isEmpty()) {
                    telaFuncionario.printFuncionarioListEmptyError();
                    ControladorFuncionario.getInstance().inicia();
                }

                //Parte 1 - Matricula
                int matricula = telaFuncionario.printGetMatricula();

                while (!hasFuncionarioByMatricula(matricula)) {
                    telaFuncionario.printMatriculaInvalida();
                    matricula = telaFuncionario.printGetMatricula();
                }

                //Parte 2 - Nome
                nome = telaFuncionario.printGetNome();

                //Parte 3 - Data de nascimento
                boolean sucess = false;
                dataNascimento = null;
                do {
                    String data = telaFuncionario.printGetDataNascimento();
                    try {
                        dataNascimento = formataData.parse(data);
                        sucess = true;
                    } catch (Exception e) {
                        telaFuncionario.printExceptionMessage(e);
                    }
                } while (!sucess);

                //Parte 4 - telefone
                telefone = telaFuncionario.printGetTelefone();

                //Parte 5 - Salário
                salario = telaFuncionario.printGetSalario();

                //Parte 6 - Cargo
                cargo = null;
                try{
                    cargo = ControladorPrincipal.getInstance().chooseCargo();
                }catch (Exception e){
                    telaFuncionario.printExceptionMessage(e);
                }

                try {
                    ControladorFuncionario.getInstance().modificaFuncionario(matricula, nome, dataNascimento, telefone, salario, cargo);
                } catch (Exception e) {
                    telaFuncionario.printExceptionMessage(e);
                    ControladorPrincipal.getInstance().inicia();
                }
                break;

            case 3:
                //Checagem da lista
                if (listaFuncionario.isEmpty()) {
                    telaFuncionario.printFuncionarioListEmptyError();
                    ControladorFuncionario.getInstance().inicia();
                }
                //Parte 1 - Matricula
                matricula = telaFuncionario.printGetMatricula();

                while (!hasFuncionarioByMatricula(matricula)) {
                    telaFuncionario.printMatriculaInvalida();
                    matricula = telaFuncionario.printGetMatricula();
                }

                try {
                    ControladorFuncionario.getInstance().removeFuncionario(matricula);
                } catch (Exception e) {
                    telaFuncionario.printExceptionMessage(e);
                    ControladorPrincipal.getInstance().inicia();
                }
                break;
            case 4:
                //Checagem da lista
                if (listaFuncionario.isEmpty()) {
                    telaFuncionario.printFuncionarioListEmptyError();
                }
                //Impressao dos dados dos funcionario da lista na tela
                telaFuncionario.printLista(listaFuncionario);
                ControladorFuncionario.getInstance().inicia();
                break;
            case 0:
                //Volta para o menu principal
                telaFuncionario.printReturnMainMenu();
                ControladorPrincipal.getInstance().inicia();
            default:
                telaFuncionario.printInvalidOptionError();
                ControladorFuncionario.getInstance().inicia();
        }
    }

    /**
     * O método inclui o novo funcionário na lista(arraylist) de funcionarios.
     *
     * @param nome
     * @param nascimento
     * @param telefone
     * @param salario
     * @param cargo
     * @throws Exception
     */
    @Override
    public void incluiFuncionario(String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception {
        if (nome == null) {
            throw new NullPointerException("Nome não pode ter valor nulo!");
        }
        if (nascimento == null) {
            throw new NullPointerException("Data de nascimento não pode ter valor nulo!");
        }
        if (telefone == null) {
            throw new NullPointerException("Telefone não pode ter valor nulo!");
        }
        if (cargo == null) {
            throw new NullPointerException("Cargo não pode ter valor nulo!");
        }
        Funcionario funcionario1 = new Funcionario(numMatricula, nome, nascimento, telefone, salario, cargo);
        listaFuncionario.add(funcionario1);
        numMatricula += 1;
        telaFuncionario.printCadastroSucesso();
        ControladorFuncionario.getInstance().inicia();
    }

    /**
     * Exclui um funcionário através da matrícula.
     *
     * @param matricula
     * @throws Exception
     */
    @Override
    public void removeFuncionario(int matricula) throws Exception {
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matrícula não pode ter valor inferior a zero!");
        }
        try {
            listaFuncionario.remove(findFuncionarioByMatricula(matricula));
            telaFuncionario.printRemoveSucess();
        } catch (Exception e) {
            telaFuncionario.printExceptionMessage(e);
        }
        ControladorFuncionario.getInstance().inicia();
    }

    /**
     * Modifica os dados do funcionário.
     *
     * @param matricula
     * @param nome
     * @param nascimento
     * @param telefone
     * @param salario
     * @param cargo
     * @throws Exception
     */
    @Override
    public void modificaFuncionario(int matricula, String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception {
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matrícula não pode ter valor inferior a zero!");
        }
        if (nome == null) {
            throw new NullPointerException("Nome não pode ter valor nulo!");
        }
        if (nascimento == null) {
            throw new NullPointerException("Data de nascimento não pode ter valor nulo!");
        }
        if (telefone == null) {
            throw new NullPointerException("Telefone não pode ter valor nulo!");
        }
        if (cargo == null) {
            throw new NullPointerException("Cargo não pode ter valor nulo!");
        }

        try {
            Funcionario funcionario = findFuncionarioByMatricula(matricula);
            funcionario.setNascimento(nascimento);
            funcionario.setCargo(cargo);
            funcionario.setSalario(salario);
            funcionario.setTelefone(telefone);
            funcionario.setNome(nome);
            telaFuncionario.printModifySucess();
        } catch (Exception e) {
            telaFuncionario.printExceptionMessage(e);
        }
        controladorFuncionario.inicia();
    }

    /**
     * Procura e retorna o funcionário associado à matricula informada.
     *
     * @param matricula
     * @return Funcionário associado à matricula informada.
     * @throws Exception
     */
    public Funcionario findFuncionarioByMatricula(int matricula) throws Exception {
        for (Funcionario funcionario : listaFuncionario) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        throw new RuntimeException("Não existe o funcionário com a matrícula informada");
    }

    /**
     * Procura e retorna um valor booleano informando a existencia ou não do
     * funcionário associado ao nome informado.
     *
     * @param nome
     * @return boolean
     */
    public boolean hasFuncionarioByNome(String nome) {
        for (Funcionario funcionario : listaFuncionario) {
            if (funcionario.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Procura e retorna um valor booleano informando a existencia ou não do
     * funcionário associado à matricula informada.
     *
     * @param matricula
     * @return boolean
     */
    public boolean hasFuncionarioByMatricula(int matricula) {
        for (Funcionario funcionario : listaFuncionario) {
            if (funcionario.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Funcionario> getListaFuncionario() {
        return listaFuncionario;
    }
    /**
     * Retorna a instancia do controlador Funcionário.
     * @return Controlador Funcionario
     */
    public static ControladorFuncionario getInstance() {
        if (controladorFuncionario == null) {
            controladorFuncionario = new ControladorFuncionario();
        }
        return controladorFuncionario;
    }
    /**
     * Percorre lista de funcionários checando a presença
     * de funcionários com o cargo informado.
     * @param cargo
     * @return boolean indicando a presença ou não de
     * funcionários com o cargo informado.
     */
    public boolean hasFuncionarioByCargo(Cargo cargo) {
        for(Funcionario funcionario: listaFuncionario){
            if(funcionario.getCargo().equals(cargo)){
                printFuncionarioByCargo(cargo);
                return true;
            }
        }
        return false;
    }
    /**
     * Procura por funcionários associados ao cargo passado
     * como parâmetro adiciona numa lista, a qual será impressa
     * na tela posteriormente.
     * @param cargo 
     */
    public void printFuncionarioByCargo(Cargo cargo) {
        ArrayList<Funcionario> lista = new ArrayList<>();
        for(Funcionario funcionario: listaFuncionario){
            if(funcionario.getCargo().equals(cargo)){
                lista.add(funcionario);
            }
        }
        telaFuncionario.printLista(lista);
    }
}
