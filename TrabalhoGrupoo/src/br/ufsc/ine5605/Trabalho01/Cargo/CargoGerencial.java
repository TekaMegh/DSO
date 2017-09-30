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
public class CargoGerencial extends Cargo{
    
    private ArrayList<IntervaloDeAcesso> intervalosDeAcessos = new ArrayList<>();
    
    public CargoGerencial(int codigo, String nome){
        super(codigo, nome);
        IntervaloDeAcesso intervaloDeAcesso = new IntervaloDeAcesso("00:00", "23:59");
        this.intervalosDeAcessos.add(intervaloDeAcesso);
     }
}
