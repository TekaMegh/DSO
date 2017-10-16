package br.ufsc.ine5605.Trabalho01.Cargos;

import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TekaMegh
 */
public class ControladorCargo implements IControladorCargo {

    private static ControladorCargo controladorCargo = new ControladorCargo();
    private TelaCargo tela;
    private ArrayList<Cargo> cargos;
    private int numCodigo;

    /**
     * Método construtor da classe
     * Inicializa os atributos da classe
     */
    public ControladorCargo() {
        this.tela = new TelaCargo(this);
        this.cargos = new ArrayList<>();
        this.numCodigo = 1;
    }

    /**
     * 
     */
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
     * @return Cargo
     * Instancia um Cargo com codigo unico
     * Adiciona o Cargo ao ArrayList de Cargo da classe e retorna o Cargo
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
     * Recebe o codigo de um cargo
     * Remove o cargo do ArrayList de Cargo da classe
     */
    @Override
    public void removeCargoByCodigo(int codigo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        boolean hasFuncionario = ControladorPrincipal.getInstance().hasFuncionarioByCargo(cargo);
        if (!hasFuncionario) {
            cargos.remove(cargo);
        }
    }

    /**
     * 
     * @param nomeCargo
     * @return Cargo
     * @throws Exception
     * Recebe o nome de um cargo e retorna o cargo
     */
    public Cargo getCargoByNome(String nomeCargo) throws Exception {
        for (Cargo cargo : cargos) {
            if (cargo.getNome().equals(nomeCargo)) {
                return cargo;
            }
        }
        throw new Exception("Nome de cargo inexistente");
    }

    /**
     * 
     * @param codigo
     * @return
     * @throws Exception 
     * Recebe o codigo de um cargo e retorna o cargo
     */
    public Cargo getCargoByCodigo(int codigo) throws Exception {
        for (Cargo cargo : cargos) {
            if (cargo.getCodigo() == codigo) {
                    
                return cargo;
            }
        }
        throw new Exception("Código de cargo inexistente");
    }

    /**
     * 
     * @param codigo
     * @param deHora
     * @param ateHora
     * @throws Exception 
     * Recebe o codigo de um cargo
     * Adiciona um IntervaloDeAcesso ao ArrayList de IntervaloDeAcesso do Cargo
     */
    public void setIntervaloInCargoByCodigo(int codigo, String deHora, String ateHora) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.addIntervalo(deHora, ateHora);
    }
    
    /**
     * 
     * @param codigo
     * @param intervalo
     * @throws Exception 
     * Recebe o codigo de um cargo e um IntervaloDeAcesso
     * Remove o IntervaloDeAcesso do ArrayList de IntervaloDeAcesso do Cargo
     */
    public void removeIntervalosByCodigo(int codigo, IntervaloDeAcesso intervalo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.removeIntervalo(intervalo);
    }
    
    /**
     * 
     * @param codigo
     * @return ArrayList de IntervaloDeAcesso
     * @throws Exception
     * Recebe o codigo de um cargo
     * Retorna o ArrayList de IntervaloDeAcesso do Cargo
     */
    public ArrayList<IntervaloDeAcesso> getIntervalosByCodigo(int codigo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        return cargo.getIntervalos();
    }

    /**
     * 
     * @param codigo
     * @param nome
     * @throws Exception 
     * Recebe o codigo de um cargo e um novo nome
     * Altera o nome do Cargo para o recebido
     */
    public void setNomeInCargoByCodigo(int codigo, String nome) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.setNome(nome);
    }

    /**
     * 
     * @param nome
     * @return boolean
     * Verifica a existencia de um cargo com o nome recebido
     * Retorna uma booleana indicando a existencia ou não do cargo
     */
    public boolean hasNome(String nome) {
        for (Cargo cargo : cargos) {
            if (cargo.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param codigo
     * @return boolean
     * Verifica a existencia de um cargo com o codigo recebido
     * Retorna uma booleana indicando a existencia ou não do cargo
     */
    public boolean hasCodigo(int codigo) {
        for (Cargo cargo : cargos) {
            if (cargo.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param codigo
     * @param novoCodigo
     * @throws Exception 
     * Recebe o codigo de um cargo do ArrayList de Cargo da classe e um novo codigo
     * Altera o codigo do Cargo para o novo codigo recebido
     */
    public void setCodigoInCargoByCodigo(int codigo, int novoCodigo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.setCodigo(novoCodigo);
    }

    /**
     * 
     * @return Cargo
     * @throws Exception 
     * Solicita um cargo escolhido na Tela e o retorna
     */
    public Cargo chooseCargo() throws Exception {
        return this.tela.chooseCargo();
    }

    /**
     * 
     * @return ControladorCargo
     * Retorna a unica instancia do ControladorCargo
     */
    public static ControladorCargo getInstance() {
        if (controladorCargo == null) {
            controladorCargo = new ControladorCargo();
        }
        return controladorCargo;
    }

    /**
     * 
     * @return boolean
     * Verifica a existencia de cargos no ArrayList de Cargo da classe
     * Retorna uma booleana indicando a existencia ou não de cargos
     */
    public boolean hasCargo() {
        ArrayList<Cargo> listaCargos = this.getCargos();
        return !listaCargos.isEmpty();
    }
}
