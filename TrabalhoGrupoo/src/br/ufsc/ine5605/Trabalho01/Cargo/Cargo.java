/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Cargo;

/**
 *
 * @author rak_w
 */
public class Cargo {
    
     protected int codigoCargo;
     protected String nomeCargo;
     
     public Cargo(int codigo, String nome){
         this.codigoCargo = codigo;
         this.nomeCargo = nome;
     }
     
     public int getCodigoCargo(){
         return this.codigoCargo;
     }
     
     public String getNomeCargo(){
         return this.nomeCargo;
     }
}
