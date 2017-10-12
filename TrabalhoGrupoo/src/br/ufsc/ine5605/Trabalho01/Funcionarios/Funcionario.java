package br.ufsc.ine5605.Trabalho01.Funcionarios;

import java.util.Date;
import br.ufsc.ine5605.Trabalho01.Cargos.Cargo;

public class Funcionario {

    private final int matricula;

    private String nome;

    private Date nascimento;

    private String telefone;

    private int salario;

    private Cargo cargo;

    public Funcionario(int matricula, String nome, Date nascimento, String telefone, int salario, Cargo cargo) {
        this.matricula = matricula;
        this.nome = nome;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.salario = salario;
        this.cargo = cargo;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}