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
     *
     * @param owner
     * Método construtor da classe
     * Inicializa os atributos da classe
     */
    public TelaCargo(ControladorCargo owner) {
        this.owner = owner;
    }

    /**
     * 
     * @throws Exception
     * Mostra a tela do menu de cargos (menu principal da classe).
     */
    public void mostrarTela() throws Exception {

        System.out.println("------------Tela de Cargos--------------");
        System.out.println("1- Cadastrar cargo");
        System.out.println("2- Visualizar cargos existentes");
        System.out.println("3- Modificar cargo existente por código");
        System.out.println("4- Remover cargo existente por código");
        System.out.println("0- Voltar");
        int opcao = leia.nextInt();
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
             * Obtem o codigo de um cargo para modificação
             * Redireciona para a modificação de cargo
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
                this.mostrarTela();
                break;
            /**
             * Obtem o codigo de um cargo para remoção
             * Redireciona para a remoção de cargo
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
                this.mostrarTela();
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
     *
     * @throws Exception
     * Obtem dados para cadastrar cargos
     */
    public void cadastroCargo() throws Exception {
        boolean opcaoInvalida;
        boolean mayEnter;
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
            int opcao = leia.nextInt();
            /**
             * Trata o cadastro de cargos
             */
            switch (opcao) {
                /**
                 * Cadastra cargos com acesso ao Financeiro
                 * Redireciona para o cadastro de acesso ao Financeiro
                 */
                case 1:
                    mayEnter = true;
                    Cargo cargoMayEnter = owner.incluiCargo(nomeCargo, mayEnter);
                    System.out.println("Cargo cadastrado com sucesso.");
                    System.out.println("Código do cargo: " + cargoMayEnter.getCodigo());
                    this.cadastroAcesso(owner.getCargoByNome(nomeCargo));
                    break;
                /**
                 * Cadastra cargos sem acesso ao Financeiro
                 * Redireciona para o menu principal da classe
                 */
                case 2:
                    mayEnter = false;
                    Cargo cargoComum = owner.incluiCargo(nomeCargo, mayEnter);
                    System.out.println("Cargo cadastrado com sucesso.");
                    System.out.println("Este cargo não possui acesso ao Financeiro.");
                    System.out.println("Código do cargo: " + cargoComum.getCodigo());
                    this.mostrarTela();
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
     *
     * @throws Exception
     * Realiza exibe(print) os dados dos cargos existentes
     * Redireciona para o menu principal da classe
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
        this.mostrarTela();
    }

    /**
     *
     * @param codigo
     * @throws Exception
     * Exibe o menu de modificação de cargos
     */
    public void modificaCargoByCodigo(int codigo) throws Exception {
        boolean opcaoInvalida;
        do {
            opcaoInvalida = false;
            System.out.println("----------Modificação do cargo: " + owner.getCargoByCodigo(codigo).getNome() + "----------");
            System.out.println("1- Mudar nome");
            System.out.println("2- Mudar codigo");
            System.out.println("3- Mudar intervalo");
            System.out.println("4- Incluir intervalo");
            System.out.println("5- Remover intervalo");
            System.out.println("0- Voltar");
            int opcao = leia.nextInt();
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
                        this.mostrarTela();
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
                        this.mostrarTela();
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
                    this.mostrarTela();
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
     *
     * @param cargo
     * @throws Exception
     * Exibe o menu de cadastro de acesso ao Financeiro
     */
    public void cadastroAcesso(Cargo cargo) throws Exception {
        boolean opcaoInvalida;
        do {
            opcaoInvalida = false;
            System.out.println("----------Cadastro de Acesso----------");
            System.out.println("Em que intervalo(s) de tempo o cargo possui acesso ao Financeiro?");
            System.out.println("1- Esse é um cargo gerencial (Livre acesso ao Financeiro)");
            System.out.println("2- Horário comercial (Das 08:00 às 12:00 e 14:00 às 18:00)");
            System.out.println("3- Outro (cadastrar novo(s) intervalo(s))");
            int opcao = leia.nextInt();
            /**
             * Trata as opções do menu de cadastro de acesso ao Financeiro
             */
            switch (opcao) {
                /**
                 * Registra o cargo como cargo gerencial
                 * Redireciona para o menu principal da classe
                 */
                case 1:
                    cargo.setGerencial(true);
                    System.out.println("O cargo " + cargo.getNome() + " foi registrado como gerencial.");
                    this.mostrarTela();
                    break;
                /**
                 * Registra o cargo com intervalos de acesso comercial
                 * Redireciona para o menu principal da classe
                 */
                case 2:
                    owner.setIntervaloInCargoByCodigo(cargo.getCodigo(), "08:00", "12:00");
                    owner.setIntervaloInCargoByCodigo(cargo.getCodigo(), "14:00", "18:00");
                    System.out.println("O cargo " + cargo.getNome() + " foi registrado com acesso em horário comercial.");
                    this.mostrarTela();
                    break;
                /**
                 * Redireciona para o cadastro de intervalos de acesso ao Financeiro
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
     *
     * @param cargo
     * @throws Exception
     * Realiza o cadastro de intervalos de acesso ao Financeiro
     */
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
            /**
             * Trata as opções do cadastro
             */
            switch (opcao) {
                /**
                 * Cadastra um intervalo de acesso
                 */
                case 1:
                    do {
                        opcaoInvalida = false;
                        owner.setIntervaloInCargoByCodigo(cargo.getCodigo(), horaInicial, horaFinal);
                        System.out.println("Intervalo cadastrado com sucesso.");
                        System.out.println("Deseja cadastrar outro intervalo?");
                        System.out.println("1- Sim");
                        System.out.println("2- Nao");
                        opcao = leia.nextInt();
                        /**
                         * Trata o cadastro de intervalo de acesso
                         */
                        switch (opcao) {
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
                                this.mostrarTela();
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
     *
     * @return Cargo
     * @throws Exception
     * Solicita a escolha de um cargo dentre os cargos existentes e o retorna
     */
    public Cargo chooseCargo() throws Exception {
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
            int opcao = leia.nextInt();
            return listaCargos.get(opcao - 1);
        }
    }

    /**
     *
     * @param intervalos
     * Realiza exibe(print) Intervalo(s) de Acesso
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
     *
     * @param intervalo
     * @param codigo
     * @throws Exception
     * Realiza a modificação de um intervalo de acesso,
     * removendo o intervalo desejado e adicionando um intervalo substituto
     */
    public void modificaIntervalo(IntervaloDeAcesso intervalo, int codigo) throws Exception {
        owner.removeIntervalosByCodigo(codigo, intervalo);
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
}
