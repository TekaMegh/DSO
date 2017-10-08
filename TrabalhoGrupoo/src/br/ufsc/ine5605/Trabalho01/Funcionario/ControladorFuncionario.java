/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Funcionario;

import br.ufsc.ine5605.Trabalho01.Cargo.Cargo;
import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author carcaroff
 */
public class ControladorFuncionario implements IControladorFuncionario{
    
    private static final ControladorFuncionario controladorFuncionario = new ControladorFuncionario();
    private final ArrayList<Funcionario> listaFuncionario;
    private int numMatricula;
    private final TelaFuncionario telaFuncionario;
    
    public ControladorFuncionario(){
        listaFuncionario = new ArrayList<>();
        this.numMatricula += 1;
        telaFuncionario = new TelaFuncionario();
    }
    /**
     * Abre a tela de Funcionários e encaminha opção selecionada para o metodo de switch.
     */
    @Override
    public void inicia(){
        int opcao = telaFuncionario.mostrarTela();
        opcaoSwitch(opcao);
    }
    
    /**
     * Trata a opção informada pela tela.
     * @param opcao 
     */
    public void opcaoSwitch(int opcao){
        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
        switch(opcao){
            case 1: 
                
                String nome = telaFuncionario.getNextStringFromScanner("Insira o seu nome:");
                while(ControladorFuncionario.getInstance().hasFuncionarioByNome(nome)){
                    nome = telaFuncionario.getNextStringFromScanner("Já existe usuário cadastrado com esse nome! Por favor insira novamente o seu nome:");
                }
                boolean sucess = false;
                Date dataNascimento = null;
                do{
                    String data = telaFuncionario.getNextStringFromScanner("Insira a data de nascimento(formato dd/MM/yyyy):");
                    try{
                        dataNascimento = formataData.parse(data);
                        sucess = true;
                    } catch(Exception e){
                        telaFuncionario.print("Formato da data inválida: " + data +". Exemplo do formato correto: 22/09/1990");
                    }
                } while(!sucess);
                
                String telefone = telaFuncionario.getNextStringFromScanner("Insira o telefone:");
                telaFuncionario.print("Insira o salário: ");
                while(!telaFuncionario.getScanner().hasNextInt()){
                    telaFuncionario.print("Input inválido. Por favor, insira o salário do novo funcionário: ");
                    telaFuncionario.getScanner().next();
                }
                int salario = telaFuncionario.getNextIntFromScanner("");
                Cargo cargo = new Cargo(1, "Masoque?");
                try{
                    ControladorFuncionario.getInstance().incluiFuncionario(nome, dataNascimento, telefone, salario, cargo);
                } catch(Exception e){
                    telaFuncionario.print(e.getMessage());
                }
                break;
            case 2: 
                if(listaFuncionario.isEmpty()){
                    telaFuncionario.print("Não há funcionários cadastrados no momento. Por favor, tente cadastrar um funcionário primeiro.");
                    ControladorFuncionario.getInstance().inicia();
                }                
                telaFuncionario.print("Insira o número da matrícula: ");
                while(!telaFuncionario.getScanner().hasNextInt()){
                    telaFuncionario.print("Matrícula inválida. Por favor, insira novamente a matrícula: ");
                    telaFuncionario.getScanner().next();
                }
                int matricula = telaFuncionario.getNextIntFromScanner("");
                
                while(!hasFuncionarioByMatricula(matricula)){
                    matricula = telaFuncionario.getNextIntFromScanner("Matrícula inválida. Por favor insira novamente o número da matrícula:");
                }
                String nome1 = telaFuncionario.getNextStringFromScanner("Insira o nome:");
                
                boolean sucess1 = false;
                Date dataNascimento1 = null;
                do{
                    String data = telaFuncionario.getNextStringFromScanner("Insira a data de nascimento(formato dd/MM/yyyy):");
                    try{
                        dataNascimento1 = formataData.parse(data);
                        sucess1 = true;
                    } catch(Exception e){
                        telaFuncionario.print(e.getMessage());
                    }
                } while(!sucess1);
                
                String telefone1 = telaFuncionario.getNextStringFromScanner("Insira o telefone:");
                telaFuncionario.print("Insira o salário: ");
                while(!telaFuncionario.getScanner().hasNextInt()){
                    telaFuncionario.print("Input inválido. Por favor, insira o salário do novo funcionário: ");
                    telaFuncionario.getScanner().next();
                }
                int salario1 = telaFuncionario.getNextIntFromScanner("");
                Cargo cargo1 = new Cargo(1, "Masoque?");
                try{
                    ControladorFuncionario.getInstance().modificaFuncionario(matricula, nome1, dataNascimento1, telefone1, salario1, cargo1);
                } catch(Exception e){
                    telaFuncionario.print(e.getMessage());
                    ControladorPrincipal.getInstance().inicia();
                }
            break;
                
            case 3: 
                if(listaFuncionario.isEmpty()){
                    telaFuncionario.print("Não há funcionários cadastrados no momento. Por favor, tente cadastrar um funcionário primeiro.");
                    ControladorFuncionario.getInstance().inicia();
                }
                telaFuncionario.print("Insira o número da matrícula: ");
                while(!telaFuncionario.getScanner().hasNextInt()){
                    telaFuncionario.print("Matrícula inválida. Por favor, insira novamente a matrícula: ");
                    telaFuncionario.getScanner().next();
                }
                int matricula1 = telaFuncionario.getNextIntFromScanner("Insira o número da matrícula:");
                
                while(!hasFuncionarioByMatricula(matricula1)){
                    matricula1 = telaFuncionario.getNextIntFromScanner("Matrícula inválida. Por favor insira novamente o número da matrícula:");
                }
                try{
                    ControladorFuncionario.getInstance().removeFuncionario(matricula1);
                } catch (Exception e){
                    telaFuncionario.print(e.getMessage());
                    ControladorPrincipal.getInstance().inicia();
                }
                break;
            case 4: 
                ArrayList<Funcionario> lista = ControladorFuncionario.getInstance().getListaFuncionario();
                if(lista.isEmpty()){
                    telaFuncionario.print("Não há nenhum funcionário cadastrado!");    
                } else {
                    for(Funcionario funcionario : lista){    
                        telaFuncionario.print("Matrícula: " + funcionario.getMatricula());
                        telaFuncionario.print("Nome: " + funcionario.getNome());
                        telaFuncionario.print("Salário: " + funcionario.getSalario());
                        telaFuncionario.print("Telefone: " + funcionario.getTelefone());
                        telaFuncionario.printData(funcionario.getNascimento());
                        telaFuncionario.print("Nome do cargo: " + funcionario.getCargo().getNomeCargo());
                        telaFuncionario.print("Código do cargo: " + funcionario.getCargo().getCodigoCargo());
                    }
                }
                ControladorFuncionario.getInstance().inicia();
                break;
            case 0: 
                telaFuncionario.print("Voltando para o menu principal...");
                ControladorPrincipal.getInstance().inicia();
            default: 
                telaFuncionario.print("Opção inválida. Por favor, selecione a opção desejada: ");
                ControladorFuncionario.getInstance().inicia();
        }
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
        listaFuncionario.add(funcionario1);
        numMatricula += 1;
        telaFuncionario.print("Funcionário cadastrado com sucesso!");
        ControladorFuncionario.getInstance().inicia();
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
            telaFuncionario.print("Funcionário removido com sucesso!");
        } catch(Exception e){
            telaFuncionario.print(e.getMessage());
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
            telaFuncionario.print("Funcionário modificado com sucesso!");
        } catch(Exception e){
            telaFuncionario.print(e.getMessage());
        }
        controladorFuncionario.inicia();
    }
    /**
     * Procura e retorna o funcionário associado à matricula informada.
     * @param matricula
     * @return Funcionário associado à matricula informada.
     * @throws Exception 
     */
    public Funcionario findFuncionarioByMatricula(int matricula) throws Exception{
        for(Funcionario funcionario : listaFuncionario){
            if(funcionario.getMatricula() == matricula){
                return funcionario;
            }
        }
        throw new RuntimeException("Não existe o funcionário com a matrícula informada");
    }
    
    /**
     * Procura e retorna um valor booleano informando a existencia ou não do funcionário
     * associado ao nome informado.
     * @param nome
     * @return boolean
     */
    public boolean hasFuncionarioByNome(String nome){
        for(Funcionario funcionario : listaFuncionario){
            if(funcionario.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }
    /**
     * Procura e retorna um valor booleano informando a existencia ou não do funcionário
     * associado à matricula informada.
     * @param matricula
     * @return boolean
     */
    public boolean hasFuncionarioByMatricula(int matricula){
        for(Funcionario funcionario : listaFuncionario){
            if(funcionario.getMatricula() == matricula){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Funcionario> getListaFuncionario() {
        return listaFuncionario;
    }

    public static ControladorFuncionario getInstance(){
        return controladorFuncionario;
    }
}