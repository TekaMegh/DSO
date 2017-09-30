/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.Trabalho01.Acesso;

/**
 *
 * @author rak_w
 */
public enum TipoAcesso {
    
    SEMMATRICULA("Matrícula já existe."),
    NAOPOSSUIACESSO("Esse funcionario nao possui acesso."),
    HORARIONAOPERMITIDO("Funcionario fora do horario autorizado para acesso"),
    ACESSOBLOQUEADO("Acesso bloqueado");
    
    private String descricao;
    
    /**
     * criacao do tipo de acesso
     * @param descricaoTipo 
     * 
     */
    TipoAcesso(String descricaoTipo){
        descricao = descricaoTipo;
    }
    
    
    public String descricao(){
        return descricao;
    }
    
}
