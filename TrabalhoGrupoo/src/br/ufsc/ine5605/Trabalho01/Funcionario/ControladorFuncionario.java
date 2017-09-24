/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Funcionario;

import br.ufsc.ine5605.Trabalho01.Cargo.Cargo;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author rak_w
 */
public class ControladorFuncionario implements IControladorFuncionario{
    
    private ArrayList<Funcionario> listaFuncionario;
    
    public ControladorFuncionario(){
        listaFuncionario = new ArrayList<>();
    }
    
    @Override
    public void incluiFuncionario(int matricula, String nome, Date nascimento, String telefone, int salario, Cargo cargo){
        Funcionario funcionario1 = new Funcionario(matricula, nome, nascimento, telefone, salario, cargo);
        if(!hasFuncionarioByNome(nome) && !hasFuncionarioByMatricula(matricula)){
            listaFuncionario.add(funcionario1);
        }
    }
    
    @Override
    public void removeFuncionario(int matricula){
        
    }
    
    @Override
    public void modificaFuncionario(int matricula){
        
    }
    
    public boolean hasFuncionarioByMatricula(int matricula){
        for(Funcionario funcionario : listaFuncionario){
            if(funcionario.getMatricula() == matricula){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasFuncionarioByNome(String nome){
        for(Funcionario funcionario : listaFuncionario){
            if(funcionario.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }
}
