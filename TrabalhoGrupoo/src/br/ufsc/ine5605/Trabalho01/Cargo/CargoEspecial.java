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
public class CargoEspecial extends Cargo{
    
    private ArrayList<IntervaloDeAcesso> intervalosDeAcessos = new ArrayList<>();
    
    public CargoEspecial(int codigo, String nome, IntervaloDeAcesso intervaloEspecialDeAcesso){
        super(codigo, nome);
        this.intervalosDeAcessos.add(intervaloEspecialDeAcesso);
    }
    
    public ArrayList<IntervaloDeAcesso> getIntervalosDeAcessos(){
        return this.intervalosDeAcessos;
    }
    
    public void incluiIntervaloDeAcesso(IntervaloDeAcesso intervaloEspecialDeAcesso){
        this.intervalosDeAcessos.add(intervaloEspecialDeAcesso);
    }
    
    public void removeIntervaloDeAcesso(IntervaloDeAcesso intervaloEspecialDeAcesso){
        this.intervalosDeAcessos.remove(intervaloEspecialDeAcesso);
    }
}
