/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Cargos;

import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rak_w
 */
public class TelaCargo {

    Scanner leia = new Scanner(System.in);
    private ControladorCargo owner;

    public TelaCargo(ControladorCargo owner) {
        this.owner = owner;
    }

    public void mostrarTela() throws Exception {

        System.out.println("------------Tela de Cargos--------------");
        System.out.println("1- Cadastrar cargo");
        System.out.println("2- Visualizar cargos existentes");
        System.out.println("3- Modificar cargo existente por código");
        System.out.println("0- Voltar");
        int opcao = leia.nextInt();
        switch (opcao) {
            case 1:

                this.cadastroCargo();

            case 2:

                this.visualizaCargos();

            case 3:
                System.out.println("------------Tela de Cargos--------------");
                System.out.println("Codigo do cargo que gostaria de modificar: ");
                int codigo = leia.nextInt();
                this.modificaCargo(codigo);

            case 0:

                ControladorPrincipal.getInstance().inicia();

            default:

                System.out.println("Opcao invalida");
                owner.inicia();

        }

    }

    private void cadastroCargo() throws Exception {

        boolean mayEnter;
        String nomeCargo;
        System.out.println("------------Tela de Cargos--------------");
        System.out.println("Nome do novo cargo: ");
        nomeCargo = leia.next();
        System.out.println("Esse cargo possui acesso ao financeiro?");
        System.out.println("1- Sim");
        System.out.println("2- Nao");
        int opcao = leia.nextInt();
        switch (opcao) {
            case 1:
                mayEnter = true;
                owner.incluiCargo(nomeCargo, mayEnter);
                this.cadastroIntervalo(owner.getCargoByNome(nomeCargo));
                break;
            case 2:
                mayEnter = false;
                owner.incluiCargo(nomeCargo, mayEnter);
                System.out.println("Cargo criado com sucesso.");
                this.mostrarTela();
                break;
            default:
                System.out.println("Opcao invalida. Repita a operacao.");
                this.cadastroCargo();
        }

    }

    public void visualizaCargos() {
        System.out.println("Os cargos já cadastrados são: ");
        for (Cargo cargo : owner.getCargos()) {
            System.out.println("" + cargo.getNome() + "");
        }

    }

    public void modificaCargo(int codigo) throws Exception {

        System.out.println("----------Modificacao do cargo: " + owner.getCargoByCodigo(codigo).getNome() + "----------");
        System.out.println("1- Mudar nome");
        System.out.println("2- Mudar codigo");
        System.out.println("3- Mudar intervalo");
        int opcao = leia.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Qual o novo nome?");
                String nome = leia.next();
                if (!owner.hasNome(nome)) {
                    owner.setNomeInCargoByCodigo(codigo, nome);
                    System.out.println("nome alterado com sucesso.");
                    this.modificaCargo(codigo);
                } else {
                    System.out.println("nome já existente.");
                    this.modificaCargo(codigo);
                }
            case 2:
                System.out.println("Qual o novo codigo?");
                int novoCodigo = leia.nextInt();
                if (!owner.hasCodigo(novoCodigo)) {
                    owner.setCodigoInCargoByCodigo(codigo, novoCodigo);
                    System.out.println("Código alterado com sucesso.");
                    this.modificaCargo(codigo);
                } else {
                    System.out.println("Código já existente.");
                    this.modificaCargo(codigo);
                }

        }
    }

    public void cadastroIntervalo(Cargo cargo) throws Exception {

        System.out.println("----------Cadstro de intervalo----------");
        System.out.println("Em que intervalo(s) de tempo ele possui acesso?");
        System.out.println("1- Esse é um cargo gerencial (Sempre)");
        System.out.println("2- Horário comercial (Das 08:00 às 18:00)");
        System.out.println("3- Outro (cadastrar novo(s) intervalo(s))");
        int opcao = leia.nextInt();
        switch (opcao) {
            case 1:
                cargo.setGerencial(true);
                System.out.println("O cargo " + cargo.getNome() + " foi registrado como gerencial.");
                this.mostrarTela();
                break;

            case 2:

                owner.setIntervaloInCargoByCodigo(cargo.getCodigo(), "08:00", "18:00");
                System.out.println("O cargo " + cargo.getNome() + " foi registrado com acesso em horário comercial.");
                this.mostrarTela();
                break;
            case 3:
                this.cadastroOutroIntervalo(cargo);

        }

    }

    public void cadastroOutroIntervalo(Cargo cargo) throws Exception {
        boolean opcaoInvalida;
        String horaInicial;
        String horaFinal;
        System.out.println("Qual a hora inicial do intervalo? (HH:mm)");
        horaInicial = leia.next();
        System.out.println("Qual a hora final do intervalo? (HH:mm)");
        horaFinal = leia.next();
        do {
            opcaoInvalida = false;
            System.out.println("A hora inicial é " + horaInicial + " e a hora final é " + horaFinal + "?");
            System.out.println("1- Sim");
            System.out.println("2- Nao");
            int opcao = leia.nextInt();
            switch (opcao) {
                case 1:
                    do {
                        opcaoInvalida = false;
                        owner.setIntervaloInCargoByCodigo(cargo.getCodigo(), horaInicial, horaFinal);
                        System.out.println("Intervalo cadastrado com sucesso.");
                        System.out.println("Deseja cadastrar outro intervalo?");
                        System.out.println("1- Sim");
                        System.out.println("2- Nao");
                        opcao = leia.nextInt();
                        switch (opcao) {
                            case 1:
                                this.cadastroOutroIntervalo(cargo);
                                break;
                            case 2:
                                System.out.println("Cadastro de Intervalos finalizado.");
                                this.mostrarTela();
                                break;
                            default:
                                System.out.println("Opcao invalida");
                                opcaoInvalida = true;
                        }
                    } while (opcaoInvalida);
                case 2:
                    this.cadastroOutroIntervalo(cargo);
                    break;

                default:
                    System.out.println("Opcao invalida");
                    opcaoInvalida = true;

            }
        } while (opcaoInvalida);
    }

    public Cargo escolhaDeCargo() throws Exception {
        boolean opcaoInvalida;
        System.out.println("----------Listagem de Cargos----------");
        ArrayList<Cargo> listaCargos = owner.getCargos();
        if (listaCargos.isEmpty()) {
            System.out.println("Não há cargos cadastrados. Para prosseguir com o cadastro de funcionários, por favor, cadastre um cargo primeiro.");
            do {
                opcaoInvalida = false;
                System.out.println("1- Cadastrar um novo cargo");
                System.out.println("2- Retornar para o menu de funcionários");
                int opcao = leia.nextInt();
                switch (opcao) {
                    case 1:
                        this.cadastroCargo();
                        break;
                    case 2:
                        return null;
                    default:
                        System.out.println("Opcao invalida");
                        opcaoInvalida = true;
                }

            } while (opcaoInvalida);
            System.out.println("----------Seleção de Cargo do Funcionário----------");
            return null;
            /*telaFuncionario.print("Selecione o cargo associado ao novo funcionário:");
                int i = 0;
                for (Cargo cargo : listaCargos) {
                    telaFuncionario.print("" + i + " - " + cargo.getNome());
                }
                int opcaoCargo = 0;
                boolean intValido = false;
                do {
                    while (!telaFuncionario.getScanner().hasNextInt()) {
                        telaFuncionario.print("Numero inválido. Por favor, selecione um cargo abaixo: ");
                        for (Cargo cargo : listaCargos) {
                            telaFuncionario.print("" + i + " - " + cargo.getNome());
                        }
                        telaFuncionario.getScanner().next();
                    }
                    opcaoCargo = telaFuncionario.getScanner().nextInt();
                    if (opcaoCargo < 0 || opcaoCargo > listaCargos.size()) {
                        telaFuncionario.print("Numero inválido. Por favor, selecione um cargo abaixo: ");
                        for (Cargo cargo : listaCargos) {
                            telaFuncionario.print("" + i + " - " + cargo.getNome());
                        }
                    } else {
                        intValido = true;
                    }
                } while (!intValido);
                ArrayList<Cargo> listaCargo = ControladorCargo.getInstance().getCargos();
                Cargo cargo = listaCargo.get(opcaoCargo);
                try {
                    ControladorFuncionario.getInstance().incluiFuncionario(nome, dataNascimento, telefone, salario, cargo);
                } catch (Exception e) {
                    telaFuncionario.print(e.getMessage());
                }
                break;
            case 2:
                ArrayList<Cargo> listaCargos1 = ControladorCargo.getInstance().getCargos();
                if (listaCargos1.isEmpty()) {
                    telaFuncionario.print("Não há cargos cadastrados. Para prosseguir com o cadastro de funcionários, por favor, cadastre um cargo primeiro.");
                    ControladorFuncionario.getInstance().inicia();
                }
                if (listaFuncionario.isEmpty()) {
                    telaFuncionario.print("Não há funcionários cadastrados no momento. Por favor, tente cadastrar um funcionário primeiro.");
                    ControladorFuncionario.getInstance().inicia();
                }
                telaFuncionario.print("Insira o número da matrícula: ");
                while (!telaFuncionario.getScanner().hasNextInt()) {
                    telaFuncionario.print("Matrícula inválida. Por favor, insira novamente a matrícula: ");
                    telaFuncionario.getScanner().next();
                }
                int matricula = telaFuncionario.getNextIntFromScanner("");

                while (!hasFuncionarioByMatricula(matricula)) {
                    matricula = telaFuncionario.getNextIntFromScanner("Matrícula inválida. Por favor insira novamente o número da matrícula:");
                }
                String nome1 = telaFuncionario.getNextStringFromScanner("Insira o nome:");

                boolean sucess1 = false;
                Date dataNascimento1 = null;
                do {
                    String data = telaFuncionario.getNextStringFromScanner("Insira a data de nascimento(formato dd/MM/yyyy):");
                    try {
                        dataNascimento1 = formataData.parse(data);
                        sucess1 = true;
                    } catch (Exception e) {
                        telaFuncionario.print(e.getMessage());
                    }
                } while (!sucess1);

                String telefone1 = telaFuncionario.getNextStringFromScanner("Insira o telefone:");
                telaFuncionario.print("Insira o salário: ");
                while (!telaFuncionario.getScanner().hasNextInt()) {
                    telaFuncionario.print("Input inválido. Por favor, insira o salário do novo funcionário: ");
                    telaFuncionario.getScanner().next();
                }
                int salario1 = telaFuncionario.getNextIntFromScanner("");
                telaFuncionario.print("Selecione o cargo associado ao novo funcionário:");
                int j = 0;
                for (Cargo cargo1 : listaCargos1) {
                    telaFuncionario.print("" + j + " - " + cargo1.getNome());
                }
                int opcaoCargo1 = 0;
                boolean intValido1 = false;
                do {
                    while (!telaFuncionario.getScanner().hasNextInt()) {
                        telaFuncionario.print("Numero inválido. Por favor, selecione um cargo abaixo: ");
                        for (Cargo cargo1 : listaCargos1) {
                            telaFuncionario.print("" + j + " - " + cargo1.getNome());
                        }
                        telaFuncionario.getScanner().next();
                    }
                    opcaoCargo = telaFuncionario.getScanner().nextInt();
                    if (opcaoCargo < 0 || opcaoCargo > listaCargos1.size()) {
                        telaFuncionario.print("Numero inválido. Por favor, selecione um cargo abaixo: ");
                        for (Cargo cargo1 : listaCargos1) {
                            telaFuncionario.print("" + j + " - " + cargo1.getNome());
                        }
                    } else {
                        intValido1 = true;
                    }
                } while (!intValido1);
                ArrayList<Cargo> listaCargo1 = ControladorCargo.getInstance().getCargos();
                Cargo cargo1 = listaCargo1.get(opcaoCargo1);
                try {
                    ControladorFuncionario.getInstance().modificaFuncionario(matricula, nome1, dataNascimento1, telefone1, salario1, cargo1);
                } catch (Exception e) {
                    telaFuncionario.print(e.getMessage());
                    ControladorPrincipal.getInstance().inicia();
                }
                break;
             */
        }
        return null;

    }
}