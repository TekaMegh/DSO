/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Cargos;

import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author TekaMegh
 */
public class TelaCargo {

    Scanner leia = new Scanner(System.in);
    private ControladorCargo owner;

    /**
     * Método construtor da classe Inicializa os atributos da classe
     *
     * @param owner
     *
     */
    public TelaCargo(ControladorCargo owner) {
        this.owner = owner;
    }

    /**
     * Mostra a tela do menu de cargos (menu principal da classe).
     *
     * @throws Exception
     *
     */
    public void mostrarTelaPrincipal() throws Exception {
        int opcao = -1;
        System.out.println("------------Tela de Cargos--------------");
        System.out.println("1- Cadastrar cargo");
        System.out.println("2- Visualizar cargos existentes");
        System.out.println("3- Modificar cargo existente por código");
        System.out.println("4- Remover cargo existente por código");
        System.out.println("0- Voltar");

        do {
            try {
                opcao = owner.parseInt(leia.next());
            } catch (NumberFormatException e) {
                this.printOpcaoInvalidaException(e);
            }
        } while (opcao == -1);
        /**
         * Tratamento das opções do menu principal da classe.
         */
        switch (opcao) {
            /**
             * Redireciona para o cadastro de cargos.
             */
            case 1:
                this.cadastroCargo();
                break;
            /**
             * Redireciona para a exibição dos cargos existentes.
             */
            case 2:
                this.visualizaCargos();
                break;
            /**
             * Obtem o codigo de um cargo para modificação Redireciona para a
             * modificação de cargo
             */
            case 3:
                System.out.println("------------Tela de Cargos--------------");
                System.out.println("Codigo do cargo que gostaria de modificar: ");
                int codigoModificar = leia.nextInt();
                try {
                    this.modificaCargoByCodigo(codigoModificar);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.mostrarTelaPrincipal();
                break;
            /**
             * Obtem o codigo de um cargo para remoção Redireciona para a
             * remoção de cargo
             */
            case 4:
                System.out.println("------------Tela de Cargos--------------");
                System.out.println("Codigo do cargo que gostaria de remover: ");
                int codigoRemover = leia.nextInt();
                try {
                    owner.removeCargoByCodigo(codigoRemover);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                this.mostrarTelaPrincipal();
                break;
            /**
             * Redireciona para o menu principal da classe
             */
            case 0:
                ControladorPrincipal.getInstance().inicia();
                break;
            /**
             * Trata uma opção invalida recebida
             */
            default:
                System.out.println("Opcao invalida");
                owner.inicia();
                break;
        }
    }

    /**
     * Obtem dados para cadastrar cargos
     *
     * @throws Exception
     *
     */
    public void cadastroCargo() throws Exception {
        boolean opcaoInvalida;
        boolean mayEnter;
        int opcao = -1;
        String nomeCargo;
        System.out.println("------------Tela de Cargos--------------");
        System.out.println("Nome do novo cargo: ");
        leia.nextLine();
        nomeCargo = leia.nextLine();
        do {
            opcaoInvalida = false;
            System.out.println("Esse cargo possui acesso ao financeiro?");
            System.out.println("1- Sim");
            System.out.println("2- Nao");
            do {
                try {
                    opcao = owner.parseInt(leia.next());
                } catch (NumberFormatException e) {
                    this.printOpcaoInvalidaException(e);
                }
            } while (opcao == -1);
            /**
             * Trata o cadastro de cargos
             */
            switch (opcao) {
                /**
                 * Cadastra cargos com acesso ao Financeiro Redireciona para o
                 * cadastro de acesso ao Financeiro
                 */
                case 1:
                    mayEnter = true;
                    Cargo cargoMayEnter = owner.incluiCargo(nomeCargo, mayEnter);
                    System.out.println("Cargo cadastrado com sucesso.");
                    System.out.println("Código do cargo: " + cargoMayEnter.getCodigo());
                    this.cadastroAcesso(owner.getCargoByNome(nomeCargo));
                    break;
                /**
                 * Cadastra cargos sem acesso ao Financeiro Redireciona para o
                 * menu principal da classe
                 */
                case 2:
                    mayEnter = false;
                    Cargo cargoComum = owner.incluiCargo(nomeCargo, mayEnter);
                    System.out.println("Cargo cadastrado com sucesso.");
                    System.out.println("Este cargo não possui acesso ao Financeiro.");
                    System.out.println("Código do cargo: " + cargoComum.getCodigo());
                    this.mostrarTelaPrincipal();
                    break;
                /**
                 * Trata uma opção invalida recebida
                 */
                default:
                    System.out.println("Opcao invalida");
                    opcaoInvalida = true;
                    break;
            }
        } while (opcaoInvalida);
    }

    /**
     * Realiza exibe(print) os dados dos cargos existentes Redireciona para o
     * menu principal da classe
     *
     * @throws Exception
     */
    public void visualizaCargos() throws Exception {
        boolean hasCargo = owner.hasCargo();
        System.out.println("------------Tela de Cargos--------------");
        if (hasCargo) {
            System.out.println("Os cargos já cadastrados são: ");
            for (Cargo cargo : owner.getCargos()) {
                System.out.println("Cargo: " + cargo.getNome() + " - Código: " + cargo.getCodigo() + " ");
                if (cargo.mayEnter()) {
                    if (cargo.isGerencial()) {
                        System.out.println("Este cargo possui acesso livre ao Financeiro.");
                    } else {
                        ArrayList<IntervaloDeAcesso> intervalos = owner.getIntervalosByCodigo(cargo.getCodigo());
                        this.printIntervalosDeAcesso(intervalos);
                    }
                } else {
                    System.out.println("Este cargo não possui acesso ao Financeiro.");
                }
                System.out.println();
            }
        } else {
            System.out.println("Não há cargos cadastrados!");
        }
        this.mostrarTelaPrincipal();
    }

    /**
     * Exibe o menu de modificação de cargos
     *
     * @param codigo
     * @throws Exception
     */
    public void modificaCargoByCodigo(int codigo) throws Exception {
        boolean b = true;
        do {
            int opcao = -1;

            System.out.println("----------Modificação do cargo: " + owner.getCargoByCodigo(codigo).getNome() + "----------");
            System.out.println("1- Mudar nome");
            System.out.println("2- Mudar codigo");
            System.out.println("3- Mudar intervalo");
            System.out.println("4- Incluir intervalo");
            System.out.println("5- Remover intervalo");
            System.out.println("0- Voltar");

            do {
                try {
                    opcao = owner.parseInt(leia.next());
                } catch (NumberFormatException e) {
                    this.printOpcaoInvalidaException(e);
                }
            } while (opcao == -1);
            /**
             * Trata as opções do menu de modificações de cargos
             */
            switch (opcao) {
                /**
                 * Trata a modificação de nome de cargos
                 */
                case 1:
                    System.out.println("Qual o novo nome?");
                    String nome = leia.next();
                    if (!owner.hasNome(nome)) {
                        owner.setNomeInCargoByCodigo(codigo, nome);
                        System.out.println("Nome alterado com sucesso.");
                        this.mostrarTelaPrincipal();
                    } else {
                        System.out.println("Nome já existente. Por favor escolha outro.");
                        this.modificaCargoByCodigo(codigo);
                    }
                    break;
                /**
                 * Trata a modificação de codigo de cargos
                 */
                case 2:
                    System.out.println("Qual o novo codigo?");
                    int novoCodigo = leia.nextInt();
                    if (!owner.hasCodigo(novoCodigo)) {
                        owner.setCodigoInCargoByCodigo(codigo, novoCodigo);
                        System.out.println("Código alterado com sucesso.");
                        this.mostrarTelaPrincipal();
                    } else {
                        System.out.println("Código já existente.");
                        this.modificaCargoByCodigo(codigo);
                    }
                    break;
                /**
                 * Trata a modificação de intervalos de acesso de cargos
                 */
                case 3:
                    System.out.println("------------Tela de Cargos--------------");
                    if (!owner.getCargoByCodigo(codigo).mayEnter()) {
                        System.out.println("Intervalos de acesso inexistentes");
                        System.out.println("Este cargo não possui acesso ao Financeiro.");
                    } else if (owner.getCargoByCodigo(codigo).isGerencial()) {
                        System.out.println("Intervalos de acesso inexistentes");
                        System.out.println("Este cargo possui livre acesso ao Financeiro");
                    } else {
                        System.out.println("O cargo apresenta o(s) seguinte(s) intervalos de acesso ao Financeiro:");
                        ArrayList<IntervaloDeAcesso> intervalos = owner.getIntervalosByCodigo(codigo);
                        this.printIntervalosDeAcesso(intervalos);
                        System.out.println("Qual o intervalo que deseja alterar?");
                        int indiceIntervalo = leia.nextInt();
                        if (indiceIntervalo > 0 && indiceIntervalo <= intervalos.size()) {
                            System.out.println("O intervalo " + indiceIntervalo + " será alterado para o intervalo especificado a seguir:");
                            this.modificaIntervalo(intervalos.get(indiceIntervalo - 1), codigo);
                        } else {
                            System.out.println("Intervalo inexistente");
                        }
                    }
                    break;
                /**
                 * Trata a inclusão de intervalos de acesso de cargos
                 */
                case 4:
                    System.out.println("------------Tela de Cargos--------------");
                    if (!owner.getCargoByCodigo(codigo).mayEnter()) {
                        System.out.println("Não é possível adicionar intervalos ao cargo.");
                        System.out.println("Este cargo não possui acesso ao Financeiro.");
                    } else if (owner.getCargoByCodigo(codigo).isGerencial()) {
                        System.out.println("Não é possível adicionar intervalos ao cargo.");
                        System.out.println("Este cargo possui livre acesso ao Financeiro");
                    } else {
                        this.cadastroOutroIntervalo(owner.getCargoByCodigo(codigo));
                    }
                    break;
                /**
                 * Trata a exclusão de intervalos de acesso de cargos
                 */
                case 5:
                    System.out.println("------------Tela de Cargos--------------");
                    if (!owner.getCargoByCodigo(codigo).mayEnter()) {
                        System.out.println("Intervalos de acesso inexistentes");
                        System.out.println("Este cargo não possui acesso ao Financeiro.");
                    } else if (owner.getCargoByCodigo(codigo).isGerencial()) {
                        System.out.println("Intervalos de acesso inexistentes");
                        System.out.println("Este cargo possui livre acesso ao Financeiro");
                    } else {
                        System.out.println("O cargo apresenta o(s) seguinte(s) intervalos de acesso ao Financeiro:");
                        ArrayList<IntervaloDeAcesso> intervalos = owner.getIntervalosByCodigo(codigo);
                        this.printIntervalosDeAcesso(intervalos);
                        System.out.println("Qual o intervalo que deseja remover?");
                        int indiceIntervalo = leia.nextInt();
                        if (indiceIntervalo > 0 && indiceIntervalo <= intervalos.size()) {
                            owner.removeIntervalosByCodigo(codigo, intervalos.get(indiceIntervalo - 1));
                            System.out.println("O intervalo " + indiceIntervalo + " removido com sucesso!");
                        }
                    }
                    break;
                /**
                 * Redireciona para o menu principal da classe
                 */
                case 0:

                    b = false;
                    break;
                /**
                 * Trata uma opção invalida recebida
                 */
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while (b);
    }

    /**
     * Exibe o menu de cadastro de acesso ao Financeiro
     *
     * @param cargo
     * @throws Exception
     */
    public void cadastroAcesso(Cargo cargo) throws Exception {
        boolean opcaoInvalida;
        do {
            opcaoInvalida = false;
            int opcao = -1;
            System.out.println("----------Cadastro de Acesso----------");
            System.out.println("Em que intervalo(s) de tempo o cargo possui acesso ao Financeiro?");
            System.out.println("1- Esse é um cargo gerencial (Livre acesso ao Financeiro)");
            System.out.println("2- Horário comercial (Das 08:00 às 12:00 e 14:00 às 18:00)");
            System.out.println("3- Outro (cadastrar novo(s) intervalo(s))");

            do {
                try {
                    opcao = owner.parseInt(leia.next());
                } catch (NumberFormatException e) {
                    this.printOpcaoInvalidaException(e);
                }
            } while (opcao == -1);
            /**
             * Trata as opções do menu de cadastro de acesso ao Financeiro
             */
            switch (opcao) {
                /**
                 * Registra o cargo como cargo gerencial Redireciona para o menu
                 * principal da classe
                 */
                case 1:
                    cargo.setGerencial(true);
                    System.out.println("O cargo " + cargo.getNome() + " foi registrado como gerencial.");
                    this.mostrarTelaPrincipal();
                    break;
                /**
                 * Registra o cargo com intervalos de acesso comercial
                 * Redireciona para o menu principal da classe
                 */
                case 2:
                    owner.setIntervaloInCargoByCodigo(cargo.getCodigo(), "08:00", "12:00");
                    owner.setIntervaloInCargoByCodigo(cargo.getCodigo(), "14:00", "18:00");
                    System.out.println("O cargo " + cargo.getNome() + " foi registrado com acesso em horário comercial.");
                    this.mostrarTelaPrincipal();
                    break;
                /**
                 * Redireciona para o cadastro de intervalos de acesso ao
                 * Financeiro
                 */
                case 3:
                    this.cadastroOutroIntervalo(cargo);
                    break;
                /**
                 * Trata uma opção invalida recebida
                 */
                default:
                    System.out.println("Opcao invalida");
                    opcaoInvalida = true;
                    break;
            }
        } while (opcaoInvalida);
    }

    /**
     * Realiza o cadastro de intervalos de acesso ao Financeiro
     *
     * @param cargo
     * @throws Exception
     */
    public void cadastroOutroIntervalo(Cargo cargo) throws Exception {
        boolean opcaoInvalida;
        int opcao = -1;
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
            do {
                try {
                    opcao = owner.parseInt(leia.next());
                } catch (NumberFormatException e) {
                    this.printOpcaoInvalidaException(e);
                }
            } while (opcao == -1);
            /**
             * Trata as opções do cadastro
             */
            switch (opcao) {
                /**
                 * Cadastra um intervalo de acesso
                 */
                case 1:
                    int opcao2 = -1;
                    do {
                        opcaoInvalida = false;
                        owner.setIntervaloInCargoByCodigo(cargo.getCodigo(), horaInicial, horaFinal);
                        System.out.println("Intervalo cadastrado com sucesso.");
                        System.out.println("Deseja cadastrar outro intervalo?");
                        System.out.println("1- Sim");
                        System.out.println("2- Nao");
                        do {
                            try {
                                opcao2 = owner.parseInt(leia.next());
                            } catch (NumberFormatException e) {
                                this.printOpcaoInvalidaException(e);
                            }
                        } while (opcao2 == -1);
                        /**
                         * Trata o cadastro de intervalo de acesso
                         */
                        switch (opcao2) {
                            /**
                             * Redireciona para o cadastro de um novo intervalo
                             */
                            case 1:
                                this.cadastroOutroIntervalo(cargo);
                                break;
                            /**
                             * Redireciona para o menu principal da tela
                             */
                            case 2:
                                System.out.println("Cadastro de Intervalos finalizado.");
                                this.mostrarTelaPrincipal();
                                break;
                            /**
                             * Trata uma opção invalida recebida
                             */
                            default:
                                System.out.println("Opcao invalida");
                                opcaoInvalida = true;
                                break;
                        }
                    } while (opcaoInvalida);
                /**
                 * Reinicia o cadastro de intervalos
                 */
                case 2:
                    this.cadastroOutroIntervalo(cargo);
                    break;
                /**
                 * Trata uma opção invalida recebida
                 */
                default:
                    System.out.println("Opcao invalida");
                    opcaoInvalida = true;
                    break;
            }
        } while (opcaoInvalida);
    }

    /**
     * Solicita a escolha de um cargo dentre os cargos existentes e o retorna
     *
     * @return Cargo
     * @throws Exception
     */
    public Cargo chooseCargo() throws Exception {
        boolean opcaoInvalida;
        int opcao = -1;
        System.out.println("----------Listagem de Cargos----------");
        ArrayList<Cargo> listaCargos = owner.getCargos();
        if (listaCargos.isEmpty()) {
            System.out.println("Não há cargos cadastrados. Para prosseguir com o cadastro de funcionários, por favor, cadastre um cargo primeiro.");
            do {
                opcaoInvalida = false;
                System.out.println("1- Cadastrar um novo cargo");
                System.out.println("2- Retornar para o menu de funcionários");
                do {
                    try {
                        opcao = owner.parseInt(leia.next());
                    } catch (NumberFormatException e) {
                        this.printOpcaoInvalidaException(e);
                    }
                } while (opcao == -1);
                /**
                 * Trata das opções quando não há cargos para escolha
                 */
                switch (opcao) {
                    /**
                     * Redireciona para o cadastro de cargos
                     */
                    case 1:
                        this.cadastroCargo();
                        break;
                    /**
                     * Retorna o método nulo
                     */
                    case 2:
                        return null;
                    /**
                     * Trata uma opção invalida recebida
                     */
                    default:
                        System.out.println("Opcao invalida");
                        opcaoInvalida = true;
                        break;
                }
            } while (opcaoInvalida);
            return null;
        } else {
            System.out.println("Escolha um cargo para o funcionário: ");
            for (int i = 1; i <= listaCargos.size(); i++) {
                Cargo cargo = listaCargos.get(i - 1);
                System.out.println(i + " - " + cargo.getNome() + " - Código: " + cargo.getCodigo());
            }
            int opcao2;
            opcao2 = leia.nextInt();
            return listaCargos.get(opcao2 - 1);
        }
    }

    /**
     * Realiza exibe(print) Intervalo(s) de Acesso
     *
     * @param intervalos
     */
    public void printIntervalosDeAcesso(ArrayList<IntervaloDeAcesso> intervalos) {
        DateFormat formatador = new SimpleDateFormat("HH:mm");
        int indice = 1;
        for (IntervaloDeAcesso intervalo : intervalos) {
            String horaInicial = formatador.format(intervalo.getHorarioInicial());
            String horaFinal = formatador.format(intervalo.getHorarioFinal());
            System.out.println("Intevalo de acesso " + indice + ":");
            System.out.println("De " + horaInicial + " até " + horaFinal);
            indice++;
        }
    }

    /**
     * Realiza a modificação de um intervalo de acesso, removendo o intervalo
     * desejado e adicionando um intervalo substituto
     *
     * @param intervalo
     * @param codigo
     * @throws Exception
     */
    public void modificaIntervalo(IntervaloDeAcesso intervalo, int codigo) throws Exception {
        owner.removeIntervalosByCodigo(codigo, intervalo);
        boolean opcaoInvalida;
        int opcao = -1;
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
            do {
                try {
                    opcao = owner.parseInt(leia.next());
                } catch (NumberFormatException e) {
                    this.printOpcaoInvalidaException(e);
                }
            } while (opcao == -1);
            /**
             * Trata a confirmação da alteração de intervalo
             */
            switch (opcao) {
                /**
                 * Adiciona o intervalo de alteração (substituto)
                 */
                case 1:
                    owner.setIntervaloInCargoByCodigo(codigo, horaInicial, horaFinal);
                    System.out.println("Intervalo de acesso alterado com sucesso!");
                    break;
                /**
                 * Reseta a modificação de intervalo
                 */
                case 2:
                    this.modificaIntervalo(intervalo, codigo);
                    break;
                /**
                 * Trata uma opção invalida recebida
                 */
                default:
                    System.out.println("Opcao invalida");
                    opcaoInvalida = true;
                    break;
            }
        } while (opcaoInvalida);
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

}
