package br.ufsc.ine5605.Trabalho01;

import br.ufsc.ine5605.Trabalho01.Entrada.ControladorAcesso;
import br.ufsc.ine5605.Trabalho01.Cargo.ControladorCargo;
import br.ufsc.ine5605.Trabalho01.Cargo.IntervaloDeAcesso;
import br.ufsc.ine5605.Trabalho01.Funcionario.ControladorFuncionario;
import br.ufsc.ine5605.Trabalho01.Funcionario.Funcionario;
import java.util.ArrayList;

public class ControladorPrincipal {

    private static final ControladorPrincipal controladorPrincipal = new ControladorPrincipal();

    private TelaPrincipal tela;

    public ControladorPrincipal() {
        tela = new TelaPrincipal();
    }

    public void inicia() {
        int opcao = tela.exibeMenuPrincipal();
        try {
            opcaoSwitch(opcao);
        } catch (Exception e) {
            tela.print(e.getMessage());
        }
    }

    public void opcaoSwitch(int opcao) {
        switch(opcao) {
            case 1:
                ControladorAcesso.getInstance().inicia();
                break;
            case 2:
                ControladorCargo.getInstance().inicia();
                break;
            case 3:
                ControladorFuncionario.getInstance().inicia();
            case 0:
                System.exit(0);
            default:
                tela.print("Opção inválida.");
                controladorPrincipal.inicia();
        }
    }

    public static ControladorPrincipal getInstance() {
        return controladorPrincipal;
    }

    public ArrayList<Funcionario> getListaFuncionarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Funcionario getFuncionarioByMatricula(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<IntervaloDeAcesso> getListaIntervaloDeAcessos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
