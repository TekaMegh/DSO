package br.ufsc.ine5605.Trabalho01.Entrada;

import br.ufsc.ine5605.Trabalho01.ControladorPrincipal;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class TelaAcesso {

    private ControladorAcesso owner;
    private Scanner leia;

    public TelaAcesso(ControladorAcesso owner) {
        leia = new Scanner(System.in);
        this.owner = owner;
    }

    public void exibeMenuPrincipal() {
        
        System.out.println("----------Tela Acesso----------");
        System.out.println("1 - Entrar no setor financeiro");
        System.out.println("2 - Registros de Acessos");
        System.out.println("0 - Menu Anterior");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        int opcao = leia.nextInt();
        switch (opcao) {
            case 1:
                this.entrarSetor();
                break;
            case 2:
                this.registros();
                break;
            case 0:
                ControladorPrincipal.getInstance().inicia();
                break;
            default:
                System.out.println("Opcão inválida");
                this.exibeMenuPrincipal();
                break;
        }
    }

    public void entrarSetor() {
        int matricula=0;
        String horaDeAcesso;
        System.out.println("----------Tela Acesso: Entrar----------");
        System.out.println("Digite sua matrícula: ");
        try {
            matricula = leia.nextInt();
        }catch (IllegalArgumentException e){
            System.out.println("A matricula é composta apenas por números. Por favor, tente novamente.");
            this.entrarSetor();
        }
        System.out.println("Digite o horario de acesso (HH:mm): ");
        horaDeAcesso = leia.next();
        
        
        switch (owner.getInstance().validaAcesso(matricula, horaDeAcesso)){
            case AUTORIZADO:
                System.out.println(TipoAcesso.AUTORIZADO.descricao());
                break;
            case ACESSOBLOQUEADO:
                System.out.println(TipoAcesso.ACESSOBLOQUEADO.descricao());
                System.out.println("Por favor, tente novamente.");
                break;

            case HORARIONAOPERMITIDO:
                System.out.println(TipoAcesso.HORARIONAOPERMITIDO.descricao());
                System.out.println("Por favor, tente novamente.");
                break;
            case NAOPOSSUIACESSO:
                System.out.println(TipoAcesso.NAOPOSSUIACESSO.descricao());
                System.out.println("Por favor, tente novamente.");
                break;
            case SEMMATRICULA:
                System.out.println(TipoAcesso.SEMMATRICULA.descricao());
                System.out.println("Por favor, tente novamente.");
                break;
        }
        
        this.exibeMenuPrincipal();
    }

    private void registros() {
        System.out.println("----------Tela Acesso: Registros----------");
        System.out.println("1 - Relatório de Acessos Negados");
        System.out.println("0 - Voltar");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        int opcao = leia.nextInt();
        switch (opcao) {
            case 1:
                this.acessosNegados();
                break;
            case 0:
                this.exibeMenuPrincipal();
                break;
        }
    }

    private void acessosNegados() {
        System.out.println("----------Tela Acesso: Relatório de Acessos Negados----------");
        System.out.println("1 - Por tipo de restricao");
        System.out.println("2 - Por Matricula");
        System.out.println("3 - Voltar");
        System.out.println("----------------------------------------");
        System.out.println("Escolha opcao: ");
        int opcao = leia.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("");
            case 2:
                System.out.println("Digite a matricula: ");
                int matricula = leia.nextInt();
                ArrayList<Acesso> lista = ControladorAcesso.getInstance().getAcessosByMatricula(matricula);
                DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                System.out.println("Acessos negados por "+ControladorPrincipal.getInstance().getFuncionarioByMatricula(matricula).getNome()+": ");
                for (Acesso acesso : lista) {
                    if(acesso.getMatricula() == matricula){
                        System.out.println("--------- ");
                        String data = formatador.format(acesso.getDataDaTentativa());
                        System.out.println("-- Hora da Tentativa de acesso: "+data+" motivo: "+acesso.getTipo().descricao()+"");
                    }
                    
                }
                
        }
    }
}
