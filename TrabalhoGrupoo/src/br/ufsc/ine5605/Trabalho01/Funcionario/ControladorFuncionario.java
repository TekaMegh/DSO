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
 * @author carcaroff
 */
public class ControladorFuncionario implements IControladorFuncionario{
    
    private static final ControladorFuncionario controladorFuncionario = new ControladorFuncionario();
    private ArrayList<Funcionario> listaFuncionario;
    private int numMatricula;
    private TelaFuncionario telaFuncionario;
    
    public ControladorFuncionario(){
        listaFuncionario = new ArrayList<>();
        this.numMatricula += 1;
        telaFuncionario = new TelaFuncionario();
    }
    /**
     * Abre a tela de Funcionários.
     */
    public void inicia(){
        telaFuncionario.mostrarTela();
    }
    
    /**
     * O método inclui o novo funcionário na lista(arraylist) de funcionarios.
     * @param nome
     * @param nascimento
     * @param telefone
     * @param salario
     * @param cargo
     * @throws Exception 
     */
    @Override
    public void incluiFuncionario(String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception{
        if(nome == null){
            throw new NullPointerException("Nome não pode ter valor nulo!");
        }
        if(nascimento == null){
            throw new NullPointerException("Data de nascimento não pode ter valor nulo!");
        }
        if(telefone == null){
            throw new NullPointerException("Telefone não pode ter valor nulo!");
        }
        if(cargo == null){
            throw new NullPointerException("Cargo não pode ter valor nulo!");
        }
        Funcionario funcionario1 = new Funcionario(numMatricula, nome, nascimento, telefone, salario, cargo);
        if(!hasFuncionarioByNome(nome)){
            listaFuncionario.add(funcionario1);
            numMatricula += 1;
            System.out.println("Funcionário cadastrado com sucesso!");
        } else { 
            throw new Exception ("Não foi possível cadastrar o usuário. Já existe usuário cadastrado com o mesmo nome");
        }
        controladorFuncionario.inicia();
    }
    
    
    /**
     * Exclui um funcionário através da matrícula.
     * @param matricula
     * @throws Exception 
     */
    @Override
    public void removeFuncionario(int matricula) throws Exception{
        if(matricula <= 0){
            throw new IllegalArgumentException("Matrícula não pode ter valor inferior a zero!");
        }
        try{
            listaFuncionario.remove(findFuncionarioByMatricula(matricula));
            System.out.println("Funcionário removido com sucesso!");
        } catch(Exception e){
            throw e;
        }
        controladorFuncionario.inicia();
    }
    
    /**
     * Modifica os dados do funcionário.
     * @param matricula
     * @param nome
     * @param nascimento
     * @param telefone
     * @param salario
     * @param cargo
     * @throws Exception 
     */
    @Override
    public void modificaFuncionario(int matricula, String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception{
        if(matricula <= 0){
            throw new IllegalArgumentException("Matrícula não pode ter valor inferior a zero!");
        }
        if(nome == null){
            throw new NullPointerException("Nome não pode ter valor nulo!");
        }
        if(nascimento == null){
            throw new NullPointerException("Data de nascimento não pode ter valor nulo!");
        }
        if(telefone == null){
            throw new NullPointerException("Telefone não pode ter valor nulo!");
        }
        if(cargo == null){
            throw new NullPointerException("Cargo não pode ter valor nulo!");
        }
        
        try{
            Funcionario funcionario = findFuncionarioByMatricula(matricula);
            funcionario.setNascimento(nascimento);
            funcionario.setCargo(cargo);
            funcionario.setSalario(salario);
            funcionario.setTelefone(telefone);
            funcionario.setNome(nome);
            System.out.println("Funcionário modificado com sucesso!");
        } catch(Exception e){
            throw e;
        }
        controladorFuncionario.inicia();
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

    public ArrayList<Funcionario> getListaFuncionario() {
        return listaFuncionario;
    }

    public void setListaFuncionario(ArrayList<Funcionario> listaFuncionario) {
        this.listaFuncionario = listaFuncionario;
    }
    
    public static ControladorFuncionario getInstance(){
        return controladorFuncionario;
    }
}