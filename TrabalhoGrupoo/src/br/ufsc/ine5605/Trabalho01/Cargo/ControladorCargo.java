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
public class ControladorCargo implements IControladorCargo {
    
    private static final ControladorCargo controladorCargo = new ControladorCargo();
    private TelaCargo telaCargo;
    private ArrayList<Cargo> cargos = new ArrayList<>();
    
    public ControladorCargo(){
        this.telaCargo = new TelaCargo();
        this.cargos = new ArrayList<>();
    }
    
    public void inicia(){
        telaCargo.mostrarTela();
    }
    
    public void incluiCargo(Cargo cargo){
        this.cargos.add(cargo);
    }
    
    public void removeCargo(Cargo cargo){
        this.cargos.remove(cargo);
    }
    
    public ArrayList<Cargo> getCargos(){
        return this.cargos;
    }
    
    public static ControladorCargo getInstance(){
        return controladorCargo;
    }
}
