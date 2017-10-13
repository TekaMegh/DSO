package br.ufsc.ine5605.Trabalho01.Entrada;

import br.ufsc.ine5605.Trabalho01.Cargos.IntervaloDeAcesso;
import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import br.ufsc.ine5605.Trabalho01.Funcionarios.Funcionario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorAcesso implements IControladorAcesso {

    private static ControladorAcesso ctrl;
    SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
    private ArrayList<Acesso> acessos;
    private TelaAcesso tela;

    public ControladorAcesso ControladorAcesso() {
        acessos = new ArrayList();
        tela = new TelaAcesso(this);
        return this;
    }

    public static ControladorAcesso getInstance() {
        if (ctrl == null) {
            ctrl = new ControladorAcesso();
        }
        return ctrl;
    }

    @Override
    public void inicia() {
        tela.exibeMenuPrincipal();
    }

    @Override
    public TipoAcesso validaAcesso(int matricula, String horaDeAcesso) {

        Funcionario funcionario = null;
        ArrayList<Funcionario> listaFuncionarios = ControladorPrincipal.getInstance().getListaFuncionarios();

        for (Funcionario func : listaFuncionarios) {
            if (func.getMatricula() == matricula) {
                funcionario = ControladorPrincipal.getInstance().getFuncionarioByMatricula(matricula);
            } else {
                return TipoAcesso.SEMMATRICULA;
            }
        }

        if (funcionario.isBlocked()) {
            try {
                acessos.add(new Acesso(TipoAcesso.ACESSOBLOQUEADO, matricula, formatadorHora.parse(horaDeAcesso)));
            } catch (ParseException ex) {
                Logger.getLogger(ControladorAcesso.class.getName()).log(Level.SEVERE, null, ex);
            }
            return TipoAcesso.ACESSOBLOQUEADO;
            
        } else if (!funcionario.getCargo().mayEnter()) {
            try {
                acessos.add(new Acesso(TipoAcesso.NAOPOSSUIACESSO, matricula, formatadorHora.parse(horaDeAcesso)));
            } catch (ParseException ex) {
                Logger.getLogger(ControladorAcesso.class.getName()).log(Level.SEVERE, null, ex);
            }
            return TipoAcesso.NAOPOSSUIACESSO;
            
        } else if (funcionario.getCargo().isGerencial()) {
            return TipoAcesso.AUTORIZADO;
        } else {

            try {
                if (validaHorario(funcionario, horaDeAcesso)) {
                    return TipoAcesso.AUTORIZADO;
                } else {
                    acessos.add(new Acesso(TipoAcesso.HORARIONAOPERMITIDO, matricula, formatadorHora.parse(horaDeAcesso)));
                    return TipoAcesso.HORARIONAOPERMITIDO;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ControladorAcesso.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;

    }

    public boolean validaHorario(Funcionario funcionario, String horaAtual) throws ParseException {
        Date now, start, end;
        ArrayList<IntervaloDeAcesso> intervalos = funcionario.getCargo().getIntervalos();
        
        now = formatadorHora.parse(horaAtual);

        for (int i = 0; i < intervalos.size(); i++) {
            IntervaloDeAcesso get = intervalos.get(i);
            start = get.getHorarioInicial();
            end = get.getHorarioFinal();
            if (!(now.after(start) && now.before(end))) {
                return false;
            }

        }
        return true;

    }

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
}
