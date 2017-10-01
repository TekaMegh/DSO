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
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.ufsc.ine5605.Trabalho01.Cargo.Cargo;
import java.util.ArrayList;

public class TelaFuncionario {
    private final Scanner sc;
    
    public TelaFuncionario(){
        sc = new Scanner(System.in);
    }
    public void mostrarTela(){
        System.out.println("-----------Tela de Fucionários-----------");
        System.out.println("Selecione a opção desejada: ");
        System.out.println("1 - Adicionar novo funcionário.");
        System.out.println("2 - Modificar dados de funcionário já existente.");
        System.out.println("3 - Remover funcionário.");
        System.out.println("4 - Mostrar todos os funcionários cadastrados.");
        System.out.println("0 - Voltar para o menu principal.");
        int opcao = sc.nextInt();
        switch(opcao){
            case 1: 
                    System.out.println("Insira o nome:");
                    String nome = sc.next();
                    System.out.println("Insira a data de nascimento(formato dd/MM/yyyy):");
                    SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
                    boolean sucess = false;
                    Date dataNascimento = null;
                    do{    
                        try{
                            dataNascimento = formataData.parse(sc.next());
                            sucess = true;
                        } catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                    } while(sucess == false);
                    System.out.println("Insira o telefone:");
                    String telefone = sc.next();
                    System.out.println("Insira o salário:");
                    int salario = sc.nextInt();
                    Cargo cargo = new Cargo(1, "Masoque?");
                    try{
                        ControladorFuncionario.getInstance().incluiFuncionario(nome, dataNascimento, telefone, salario, cargo);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                break;
            case 2: 
                    System.out.println("Insira o número da matrícula:");
                    int matricula = sc.nextInt();
                    System.out.println("Insira o nome:");
                    String nome1 = sc.next();
                    System.out.println("Insira a data de nascimento(formato dd/MM/yyyy):");
                    SimpleDateFormat formataData1 = new SimpleDateFormat("dd/MM/yyyy");
                    boolean sucess1 = false;
                    Date dataNascimento1 = null;
                    do{    
                        try{
                            dataNascimento = formataData1.parse(sc.next());
                            sucess = true;
                        } catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                    } while(sucess1 == false);
                    System.out.println("Insira o telefone:");
                    String telefone1 = sc.next();
                    System.out.println("Insira o salário:");
                    int salario1 = sc.nextInt();
                    Cargo cargo1 = new Cargo(1, "Masoque?");
                    try{
                        ControladorFuncionario.getInstance().modificaFuncionario(matricula, nome1, dataNascimento1, telefone1, salario1, cargo1);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                break;
            
            case 3: System.out.println("Insira o número da matrícula:");
                    int matricula1 = sc.nextInt();
                    try{
                        ControladorFuncionario.getInstance().removeFuncionario(matricula1);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                break;
            case 4: ArrayList<Funcionario> lista = ControladorFuncionario.getInstance().getListaFuncionario();
                    for(Funcionario funcionario : lista){
                        System.out.println(funcionario.getMatricula());
                        System.out.println(funcionario.getNome());
                        System.out.println(funcionario.getSalario());
                        System.out.println(funcionario.getTelefone());
                        System.out.println(funcionario.getNascimento());
                        System.out.println(funcionario.getCargo().getNomeCargo());
                        System.out.println(funcionario.getCargo().getCodigoCargo());
                    }
            case 0: System.out.println("Voltando para o menu principal...");
                    return;
            default: System.out.println("Opção inválida. Por favor, selecione a opção desejada: ");
        
        }
    }

}