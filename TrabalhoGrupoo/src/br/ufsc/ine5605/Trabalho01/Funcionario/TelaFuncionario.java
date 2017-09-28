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

public class TelaFuncionario {
    public void inicia(){
        System.out.println("-----------Tela de Fucionários-----------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Selecione a opção desejada: ");
        System.out.println("1 - Adicionar novo funcionário.");
        System.out.println("2 - Modificar dados de funcionário já existente.");
        System.out.println("3 - Remover funcionário.");
        int opcao = sc.nextInt();
        switch(opcao){
            case 1: System.out.println("Insira o nome:");
                    String nome = sc.next();
                    System.out.println("Insira o telefone:");
                    String telefone = sc.next();
                    System.out.println("Insira o salário:");
                    int salario = sc.nextInt();
                    
        }
    }

}