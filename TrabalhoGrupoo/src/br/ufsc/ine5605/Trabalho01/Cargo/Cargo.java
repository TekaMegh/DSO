/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Cargo;

import java.util.ArrayList;

/**
 *
 * @author rak_w
 */
public class Cargo {

    protected int codigo;
    protected String nome;
    private boolean mayEnter;
    private boolean gerencial;
    private ArrayList<IntervaloDeAcesso> intervalos;

    public Cargo(int codigo, String nome, boolean mayEnter) {
        this.codigo = codigo;
        this.nome = nome;
        this.mayEnter = mayEnter;
        this.intervalos = new ArrayList<>();
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

    public void setMayEnter(boolean mayEnter) {
        this.mayEnter = mayEnter;
    }

    public ArrayList<IntervaloDeAcesso> getIntervalos() {
        return intervalos;
    }

    public void addIntervalos(IntervaloDeAcesso intervalo) {
        intervalos.add(intervalo);
    }

    public boolean isGerencial() {
        return gerencial;
    }

    public void setGerencial(boolean gerencial) {
        this.gerencial = gerencial;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
