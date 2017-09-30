/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Cargo;

import br.ufsc.ine5605.Trabalho01.IntervaloDeAcesso.IntervaloDeAcesso;
import java.util.ArrayList;

/**
 *
 * @author rak_w
 */
public class CargoComum extends Cargo{
    
    private ArrayList<IntervaloDeAcesso> intervalosDeAcessos = new ArrayList<>();
    
    public CargoComum(int codigo, String nome){
        super(codigo, nome);
        this.intervalosDeAcessos = null;
    }
}
