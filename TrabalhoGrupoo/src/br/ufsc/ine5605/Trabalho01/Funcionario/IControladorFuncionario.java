/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Funcionario;

/**
 *
 * @author carcaroff
 */
import br.ufsc.ine5605.Trabalho01.Cargo.Cargo;
import java.util.Date;

public interface IControladorFuncionario {
    /**
     * Abre a tela de Funcionários e encaminha opção selecionada para o metodo de switch.
     */
    public void inicia();
    
    /**
     * O método inclui o novo funcionário na lista(arraylist) de funcionarios.
     * @param nome
     * @param nascimento
     * @param telefone
     * @param salario
     * @param cargo
     * @throws Exception 
     */
    public void incluiFuncionario(String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception;
    
    /**
     * Exclui um funcionário através da matrícula.
     * @param matricula
     * @throws Exception 
     */
    public void removeFuncionario(int matricula) throws Exception;
    
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
    public void modificaFuncionario(int matricula, String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception;
}
