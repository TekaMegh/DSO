package br.ufsc.ine5605.Trabalho01.Funcionario;

import br.ufsc.ine5605.Trabalho01.Cargo.Cargo;
import java.util.Date;

public interface IControladorFuncionario {

    public void inicia();

    public void incluiFuncionario(String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception;

    public void removeFuncionario(int matricula) throws Exception;

    public void modificaFuncionario(int matricula, String nome, Date nascimento, String telefone, int salario, Cargo cargo) throws Exception;
}
