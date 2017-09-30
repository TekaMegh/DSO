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
public class CargoComercial extends Cargo{
    
    private ArrayList<IntervaloDeAcesso> intervalosDeAcessos = new ArrayList<>();
    
    public CargoComercial(int codigo, String nome, IntervaloDeAcesso primeiroIntervalo, IntervaloDeAcesso segundoIntervalo){
        super(codigo, nome);
        this.intervalosDeAcessos.add(primeiroIntervalo);
        this.intervalosDeAcessos.add(segundoIntervalo);
    }
    
    public ArrayList<IntervaloDeAcesso> getIntervalosDeAcessos(){
        return this.intervalosDeAcessos;
    }
    
    public void incluiIntervaloDeAcesso(IntervaloDeAcesso intervaloDeAcesso){
        this.intervalosDeAcessos.add(intervaloDeAcesso);
    }
    
    public void removeIntervaloDeAcesso(IntervaloDeAcesso intervaloDeAcesso){
        this.intervalosDeAcessos.remove(intervaloDeAcesso);
    }
}
