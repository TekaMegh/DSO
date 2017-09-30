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
    
    private ArrayList<Cargo> cargos = new ArrayList<>();
    
    public void incluiCargo(Cargo cargo){
        this.cargos.add(cargo);
    }
    
    public void removeCargo(Cargo cargo){
        this.cargos.remove(cargo);
    }
    
    public ArrayList<Cargo> getCargos(){
        return this.cargos;
    }
}
