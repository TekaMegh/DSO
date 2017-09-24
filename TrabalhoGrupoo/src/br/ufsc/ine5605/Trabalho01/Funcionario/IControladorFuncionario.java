/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Funcionario;

/**
 *
 * @author Avell
 */
import br.ufsc.ine5605.Trabalho01.Cargo.Cargo;
import java.util.Date;

public interface IControladorFuncionario {
    
    public void incluiFuncionario(int matricula, String nome, Date nascimento, String telefone, int salario, Cargo cargo);
    
    public void removeFuncionario(int matricula);
    
    public void modificaFuncionario(int matricula);
}
