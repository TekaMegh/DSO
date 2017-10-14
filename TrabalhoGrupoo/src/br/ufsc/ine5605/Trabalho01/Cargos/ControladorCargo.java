package br.ufsc.ine5605.Trabalho01.Cargos;

import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorCargo implements IControladorCargo {

    private static ControladorCargo controladorCargo = new ControladorCargo();

    private TelaCargo tela;

    private ArrayList<Cargo> cargos;

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

    /**
     *
     * @param nome
     * @param mayEnter
     * @return
     */
    @Override
    public Cargo incluiCargo(String nome, boolean mayEnter) {
        boolean codigoExiste = false;
        do {
            codigoExiste = this.hasCodigo(numCodigo);
            if (codigoExiste) {
                this.numCodigo += 1;
            }
        } while (codigoExiste);
        Cargo cargo = new Cargo(this.numCodigo, nome, mayEnter);
        this.numCodigo += 1;
        this.cargos.add(cargo);
        return cargo;
    }

    @Override
    public ArrayList<Cargo> getCargos() {
        return this.cargos;
    }

    /**
     *
     * @param codigo
     * @throws Exception
     */
    @Override
    public void removeCargoByCodigo(int codigo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        boolean hasFuncionario = ControladorPrincipal.getInstance().hasFuncionarioByCargo(cargo);
        if (!hasFuncionario) {
            cargos.remove(cargo);
        }
    }

    public Cargo getCargoByNome(String nomeCargo) throws Exception {
        for (Cargo cargo : cargos) {
            if (cargo.getNome().equals(nomeCargo)) {
                return cargo;
            }
        }
        throw new Exception("Código de cargo inexistente");
    }

    public Cargo getCargoByCodigo(int codigo) throws Exception {
        for (Cargo cargo : cargos) {
            if (cargo.getCodigo() == codigo) {
                    
                return cargo;
            }
        }
        throw new Exception("Código de cargo inexistente");
    }

    public void setIntervaloInCargoByCodigo(int codigo, String deHora, String ateHora) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.addIntervalo(deHora, ateHora);
    }
    
    public void removeIntervalosByCodigo(int codigo, IntervaloDeAcesso intervalo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.removeIntervalo(intervalo);
    }
    
    public ArrayList<IntervaloDeAcesso> getIntervalosByCodigo(int codigo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        return cargo.getIntervalos();
    }

    public void setNomeInCargoByCodigo(int codigo, String nome) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.setNome(nome);
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

    public void setCodigoInCargoByCodigo(int codigo, int novoCodigo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.setCodigo(novoCodigo);
    }

    public Cargo chooseCargo() throws Exception {
        return this.tela.chooseCargo();
    }

    public static ControladorCargo getInstance() {
        if (controladorCargo == null) {
            controladorCargo = new ControladorCargo();
        }
        return controladorCargo;
    }

    public boolean hasCargo() {
        ArrayList<Cargo> listaCargos = this.getCargos();
        if (listaCargos.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
