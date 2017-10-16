package br.ufsc.ine5605.Trabalho01.Entrada;

import br.ufsc.ine5605.Trabalho01.Cargos.IntervaloDeAcesso;
import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import br.ufsc.ine5605.Trabalho01.Funcionarios.Funcionario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControladorAcesso implements IControladorAcesso {

    private static ControladorAcesso ctrl;
    SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
    private ArrayList<Acesso> acessos;
    private TelaAcesso tela;

    private ControladorAcesso() {
        acessos = new ArrayList<>();
        tela = new TelaAcesso(this);
    }

    public static IControladorAcesso getInstance() {
        if (ctrl == null) {

            ctrl = new ControladorAcesso();
        }

        return ctrl;
    }

    /**
     * Começa o trabalho com a tela.
     */
    @Override
    public void inicia() {
        this.menuPrincipal();
    }

    /**
     * Mantém o menu principal em loop enquanto as opcoes escolhidas no menu principal da tela Acesso forem inválidas.
     */
    public void menuPrincipal() {
        boolean b = true;
        while (b) {
            int inteiro = -1;

            do {

                try {
                    inteiro = this.parseInt(tela.exibeMenuPrincipal());
                } catch (NumberFormatException e) {
                    tela.printOpcaoInvalidaException(e);
                }

            } while (inteiro == -1);

            switch (inteiro) {
                case 1:
                    while (true) {
                        try {
                            tela.entrarSetor();
                        } catch (ParseException e) {
                            tela.printDataInvalidaException(e);
                        } catch (NumberFormatException e) {
                            tela.printOpcaoInvalidaException(e);
                        }
                        break;
                    }
                    break;
                case 2:
                    this.menuRegistros();
                    break;
                case 0:
                    b = false;
                    break;
                default:
                    tela.printOpcaoInvalida();
                    break;
            }
        }

    }

    /**
     * mantém o menu de registros em loop enquanto a opcao for inválida
     */
    private void menuRegistros() {
        boolean b = true;
        while (b) {

            int inteiro = -1;

            do {
                try {
                    inteiro = this.parseInt(tela.registros());
                } catch (NumberFormatException e) {
                    tela.printOpcaoInvalidaException(e);
                }
            } while (inteiro == -1);

            switch (inteiro) {
                case 1:
                    this.menuAcessosNegados();
                    break;
                case 0:
                    b = false;
                    break;
            }
        }
    }

    /**
     * Mantém o menu de Acessos negados em loop enquanto as opcoes forem
     * inválidas. Além de tratar da impressão das listas de Acessos de acordo
     * com a escolha do usuário.
     */
    private void menuAcessosNegados() {
        boolean b = true;
        while (b) {

            int inteiro = -1;

            do {
                try {
                    inteiro = this.parseInt(tela.menuAcessosNegados());
                } catch (NumberFormatException e) {
                    tela.printOpcaoInvalidaException(e);
                }
            } while (inteiro == -1);

            switch (inteiro) {
                case 1:
                    int opcaoTipoAcesso;
                    try {
                        opcaoTipoAcesso = this.parseInt(tela.tipoAcesso());
                    } catch (NumberFormatException e) {
                        tela.printOpcaoInvalidaException(e);
                        break;
                    }
                    switch (opcaoTipoAcesso) {

                        case 1:
                            tela.printListaAcessoByTipo(this.getAcessosByTipo(TipoAcesso.HORARIONAOPERMITIDO));
                            break;
                        case 2:
                            tela.printListaAcessoByTipo(this.getAcessosByTipo(TipoAcesso.ACESSOBLOQUEADO));
                            break;
                        case 3:
                            tela.printListaAcessoByTipo(this.getAcessosByTipo(TipoAcesso.NAOPOSSUIACESSO));
                            break;
                        case 4:
                            b = false;
                            break;
                    }
                    break;
                case 2:
                    int opcaoMatricula;
                    try {
                        opcaoMatricula = this.parseInt(tela.opcaoMatricula());
                    } catch (NumberFormatException e) {
                        tela.printOpcaoInvalidaException(e);
                        break;
                    }
                    if (!ControladorPrincipal.getInstance().hasFuncionarioByMatricula(opcaoMatricula)) {
                        tela.printMatriculaInvalidaException();
                        break;
                    }

                    tela.printListaAcessoByMatricula(this.getAcessosByMatricula(opcaoMatricula), opcaoMatricula);
                    break;
                case 0:
                    b = false;
                    break;
            }
        }
    }

    /**
     * mantém o menu principal da tela Acesso em loop enquanto as opcoes forem
     * inválidas.
     *
     * @param matricula
     * @param horaDeAcesso
     * @return
     */
    @Override
    public String validaAcesso(int matricula, Date horaDeAcesso) {

        Funcionario funcionario = null;

        if (ControladorPrincipal.getInstance().hasFuncionarioByMatricula(matricula)) {
            funcionario = ControladorPrincipal.getInstance().getFuncionarioByMatricula(matricula);
        } else {
            return TipoAcesso.SEMMATRICULA.descricao();
        }

        if (funcionario.isBlocked()) {
            acessos.add(new Acesso(TipoAcesso.ACESSOBLOQUEADO, matricula, horaDeAcesso));
            return TipoAcesso.ACESSOBLOQUEADO.descricao();

        } else if (!funcionario.getCargo().mayEnter()) {
            if (getAcessosByMatricula(funcionario.getMatricula()).size() == 2) {
                funcionario.setBlocked(true);
            }
            acessos.add(new Acesso(TipoAcesso.NAOPOSSUIACESSO, matricula, horaDeAcesso));
            return TipoAcesso.NAOPOSSUIACESSO.descricao();

        } else if (funcionario.getCargo().isGerencial()) {

            return TipoAcesso.AUTORIZADO.descricao();

        } else if (validaHorario(funcionario, horaDeAcesso)) {

            return TipoAcesso.AUTORIZADO.descricao();

        } else {
            if (getAcessosByMatricula(funcionario.getMatricula()).size() == 2) {
                funcionario.setBlocked(true);
            }
            acessos.add(new Acesso(TipoAcesso.HORARIONAOPERMITIDO, matricula, horaDeAcesso));
            return TipoAcesso.HORARIONAOPERMITIDO.descricao();
        }

    }

    /**
     * Testa se horario recebido está entre os intervalos de acesso permitidos
     * de acordo com o cargo do funcionario recebido.
     *
     * @param funcionario
     * @param horaAtual
     * @return true caso o horário esteja dentro dos intervalos; false caso o
     * horário esteja fora dos intervalos
     *
     *
     */
    public boolean validaHorario(Funcionario funcionario, Date horaAtual) {

        Date now, inicio, fim;
        ArrayList<IntervaloDeAcesso> intervalos = funcionario.getCargo().getIntervalos();
        now = horaAtual;
        for (IntervaloDeAcesso interv : intervalos) {

            inicio = interv.getHorarioInicial();
            fim = interv.getHorarioFinal();

            if (now.getTime() <= fim.getTime() && now.getTime() >= inicio.getTime()) {
                return true;
            }

        }
        return false;
    }

    /**
     * Recebe Tipo de Acesso e retorna todas as tentativas já registradas;
     *
     * @param tipo
     * @return lista de <Acesso>
     */
    @Override
    public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo) {
        ArrayList<Acesso> listaAcessosByTipo = new ArrayList();
        for (Acesso acesso : acessos) {
            if (acesso.getTipo() == tipo) {
                listaAcessosByTipo.add(acesso);
            }
        }
        return listaAcessosByTipo;
    }

    /**
     * Recebe matrícula de funcionario e retorna suas tentativas de acesso já
     * registradas;
     *
     * @param matricula
     * @return lista de <Acesso>
     */
    @Override
    public ArrayList<Acesso> getAcessosByMatricula(int matricula) {
        ArrayList<Acesso> listaAcessosByMatricula = new ArrayList();
        for (Acesso acesso : acessos) {
            if (acesso.getMatricula() == matricula) {
                listaAcessosByMatricula.add(acesso);
            }
        }
        return listaAcessosByMatricula;
    }

    /**
     * Realiza um comando "parse" na String recebida, a transformando em "int".
     *
     * @param entrada
     * @return
     * @throws NumberFormatException
     */
    public int parseInt(String entrada) throws NumberFormatException {

        return Integer.parseInt(entrada);

    }

    /**
     * Realiza um comando "parse" na String recebida, a transformando em um
     * "Date".
     *
     * @param string
     * @return Date da String recebida
     * @throws java.text.ParseException case a String não esteja no formato
     * "HH:mm"
     */
    public Date parseDate(String string) throws ParseException {

        return formatadorHora.parse(string);

    }

//    public Date correctDate(Date horaDeAcesso) {
//        Date now = new Date();
//        
//        horaDeAcesso.setYear(now.getYear());
//        horaDeAcesso.setMonth(now.getMonth());
//        horaDeAcesso.setDate(now.getDate());
//        
//        return horaDeAcesso;
//      }
}
