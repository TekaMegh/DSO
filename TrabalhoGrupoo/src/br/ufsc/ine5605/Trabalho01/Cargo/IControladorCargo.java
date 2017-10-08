/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Cargo;

import java.util.ArrayList;

/**
 *
 * @author Avell
 */
interface IControladorCargo {
    
    public void inicia();
    
    public void incluiCargo(Cargo cargo);
    
    public void removeCargo(Cargo cargo);
    
    public ArrayList<Cargo> getCargos();
    
}
