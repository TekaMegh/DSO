package br.ufsc.ine5605.Trabalho01.Cargos;

import java.util.ArrayList;

/**
 *
 * @author TekaMegh
 */
public class Cargo {

    private int codigo;
    private String nome;
    private boolean mayEnter;
    private boolean gerencial;
    private ArrayList<IntervaloDeAcesso> intervalos;

    /**
     * 
     * @param codigo
     * @param nome
     * @param mayEnter
     * Método construtor da classe
     * Inicializa todos os atributos da classe
     */
    public Cargo(int codigo, String nome, boolean mayEnter) {
        this.codigo = codigo;
        this.nome = nome;
        this.mayEnter = mayEnter;
        this.intervalos = new ArrayList<>();
        this.gerencial = false;
    }

    public int getCodigo() {
        return this.codigo;
    }
    
    public String getNome() {
        return this.nome;
    }

    public boolean mayEnter() {
        return mayEnter;
    }

    public boolean isGerencial() {
        return gerencial;
    }

    public ArrayList<IntervaloDeAcesso> getIntervalos() {
        return intervalos;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setMayEnter(boolean mayEnter) {
        this.mayEnter = mayEnter;
    }

    public void setGerencial(boolean gerencial) {
        this.gerencial = gerencial;
    }      

    /**
     * 
     * @param horaInicial
     * @param horaFinal
     * Instancia um IntervaloDeAcesso
     * Adiciona o IntervaloDeAcesso ao ArrayList de IntervaloDeAcesso da classe
     */
    public void addIntervalo(String horaInicial, String horaFinal) {
        IntervaloDeAcesso intervalo = new IntervaloDeAcesso(horaInicial, horaFinal);
        intervalos.add(intervalo);
    }
    
    /**
     * 
     * @param intervalo
     * Remove do ArrayList de IntervaloDeAcesso da classe o IntervaloDeAcesso recebido como parametro
     */
    public void removeIntervalo(IntervaloDeAcesso intervalo) {
        intervalos.remove(intervalo);
    }    
}
