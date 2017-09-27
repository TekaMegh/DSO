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
    private int numMatricula;
    
    public ControladorFuncionario(){
        listaFuncionario = new ArrayList<>();
        this.numMatricula += 1;
    }
    
    @Override
    public void incluiFuncionario(String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception{
        if(nome == null){
            throw new IllegalArgumentException("Nome não pode ter valor nulo!");
        }
        if(nascimento == null){
            throw new IllegalArgumentException("Data de nascimento não pode ter valor nulo!");
        }
        if(telefone == null){
            throw new IllegalArgumentException("Telefone não pode ter valor nulo!");
        }
        if(cargo == null){
            throw new IllegalArgumentException("Cargo não pode ter valor nulo!");
        }
        Funcionario funcionario1 = new Funcionario(numMatricula, nome, nascimento, telefone, salario, cargo);
        if(!hasFuncionarioByNome(nome)){
            listaFuncionario.add(funcionario1);
            numMatricula += 1;
        } else { 
            throw new Exception ("Não foi possível cadastrar o usuário. Já existe usuário cadastrado com o mesmo nome");
        }
    }
    
    @Override
    public void removeFuncionario(int matricula) throws Exception{
        if(matricula <= 0){
            throw new IllegalArgumentException("Matrícula não pode ter valor inferior a zero!");
        }
        try{
            findFuncionarioByMatricula(matricula);
        } catch(Exception e){
            throw e;
        }
        listaFuncionario.remove(findFuncionarioByMatricula(matricula));
    }
    
    @Override
    public void modificaFuncionario(int matricula, String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception{
        if(matricula <= 0){
            throw new IllegalArgumentException("Matrícula não pode ter valor inferior a zero!");
        }
        if(nome == null){
            throw new IllegalArgumentException("Nome não pode ter valor nulo!");
        }
        if(nascimento == null){
            throw new IllegalArgumentException("Data de nascimento não pode ter valor nulo!");
        }
        if(telefone == null){
            throw new IllegalArgumentException("Telefone não pode ter valor nulo!");
        }
        if(cargo == null){
            throw new IllegalArgumentException("Cargo não pode ter valor nulo!");
        }
        try{
            findFuncionarioByMatricula(matricula);
        } catch(Exception e){
            throw e;
        }
        Funcionario funcionario = findFuncionarioByMatricula(matricula);
        funcionario.setNascimento(nascimento);
        funcionario.setCargo(cargo);
        funcionario.setSalario(salario);
        funcionario.setTelefone(telefone);
        funcionario.setNome(nome);
    }
    
    public Funcionario findFuncionarioByMatricula(int matricula) throws Exception{
        for(Funcionario funcionario : listaFuncionario){
            if(funcionario.getMatricula() == matricula){
                return funcionario;
            }
        }
        throw new RuntimeException("Não existe o funcionário com a matrícula informada");
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
