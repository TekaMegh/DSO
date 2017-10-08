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
import java.util.Date;
import java.util.Scanner;

public class TelaFuncionario {
    private final Scanner sc;
    
    public TelaFuncionario(){
        sc = new Scanner(System.in);
    }
    
    /**
     * Mostra a tela do funcionário.
     * @return int opção selecionada.
     */
    public int mostrarTela(){
        System.out.println("-----------Tela de Fucionários-----------");
        System.out.println("Selecione a opção desejada: ");
        System.out.println("1 - Adicionar novo funcionário.");
        System.out.println("2 - Modificar dados de funcionário já existente.");
        System.out.println("3 - Remover funcionário.");
        System.out.println("4 - Mostrar todos os funcionários cadastrados.");
        System.out.println("0 - Voltar para o menu principal.");
        return sc.nextInt();   
    }
    /**
     * Imprime a mensagem/String passada como parâmetro.
     * @param mensagem 
     */
    public void print(String mensagem){
        System.out.println(mensagem);
    }
    /**
     * Imprime a mensagem/String passada como parâmetro e
     * retorna o próximo inteiro lido pelo Scanner.
     * @param message
     * @return inteiro lido pelo Scanner.
     */
    public int getNextIntFromScanner(String message){
        System.out.println(message);
        return sc.nextInt();
    }
    /**
     * Imprime a mensagem/String passada como parâmetro
     * e retorna a próxima String lida pelo Scanner.
     * @param message
     * @return 
     */
    public String getNextStringFromScanner(String message){
        System.out.println(message);
        return sc.next();
    }
    /**
     * Recebe como parâmetro em formato Date, e imprime
     * a data no formato: dd/MM/yyyy.
     * @param data 
     */
    public void printData(Date data) {
        System.out.print("Data de nascimento: ");
        System.out.printf("%td %<tB %<tY", data);
        System.out.println("");
    }
    
    public Scanner getScanner(){
        return sc;
    }
}