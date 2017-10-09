/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Cargo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rak_w
 */
public class ControladorCargo implements IControladorCargo {

    private static final ControladorCargo controladorCargo = new ControladorCargo();
    private TelaCargo tela;
    private ArrayList<Cargo> cargos = new ArrayList<>();
    private int numCodigo;

    public ControladorCargo() {
        this.tela = new TelaCargo(this);
        this.cargos = new ArrayList<>();
        this.numCodigo = 1;
    }

    @Override
    public void inicia() {
        try {
            tela.mostrarTela();
        } catch (Exception ex) {
            Logger.getLogger(ControladorCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void incluiCargo(String nome, boolean mayEnter) {

        Cargo cargo = new Cargo(this.numCodigo, nome, mayEnter);
        this.numCodigo += 1;
        cargos.add(cargo);

    }

    @Override
    public ArrayList<Cargo> getCargos() {
        return this.cargos;
    }

    public static ControladorCargo getInstance() {
        return controladorCargo;
    }

    @Override
    public void removeCargoByCodigo(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cargo getCargoByNome(String nomeCargo) throws Exception {

        for (Cargo cargo : cargos) {
            if (cargo.getNome().equals(nomeCargo)) {
                return cargo;
            }

        }
        throw new Exception("Cargo não existe");

    }

    public Cargo getCargoByCodigo(int codigo) throws Exception {
        for (Cargo cargo : cargos) {
            if (cargo.getCodigo() == codigo) {
                return cargo;
            }

        }
        throw new Exception("Cargo não existe");
    }

    public void setIntervaloInCargoByCodigo(int codigo, String deHora, String ateHora) {
        IntervaloDeAcesso intervalo = new IntervaloDeAcesso(deHora, ateHora);
        for (Cargo cargo : cargos) {
            if (cargo.getCodigo() == codigo) {
                cargo.addIntervalos(intervalo);

            }
        }

    }

    public void setNomeInCargoByCodigo(int codigo, String nome) {
        for (Cargo cargo : cargos) {
            if (cargo.getCodigo() == codigo) {
                cargo.setNome(nome);
            }
        }

    }

    public boolean hasNome(String nome) {
        for (Cargo cargo : cargos) {

            if (cargo.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCodigo(int novoCodigo) {
        for (Cargo cargo : cargos) {

            if (cargo.getCodigo() == novoCodigo) {
                return true;
            }
        }
        return false;
    }

    public void setCodigoInCargoByCodigo(int codigo, int novoCodigo) {
        for (Cargo cargo : cargos) {
            if (cargo.getCodigo() == codigo) {
                cargo.setCodigo(novoCodigo);
            }
        }
    }
}
