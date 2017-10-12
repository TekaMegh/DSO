package br.ufsc.ine5605.Trabalho01.Entrada;

public enum TipoAcesso {

    SEMMATRICULA("Não existe funcionário com essa matrícula."),
    NAOPOSSUIACESSO("Esse funcionario nao possui acesso."),
    HORARIONAOPERMITIDO("Funcionario fora do horario autorizado para acesso."),
    ACESSOBLOQUEADO("Acesso bloqueado."),
    AUTORIZADO("Acesso autorizado.");

    private String descricao;

    TipoAcesso(String descricaoTipo) {
        descricao = descricaoTipo;
    }

    public String descricao() {
        return descricao;
    }

}
