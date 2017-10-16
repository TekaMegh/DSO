package br.ufsc.ine5605.Trabalho01.Cargos;

import java.util.ArrayList;

public interface IControladorCargo {

    public void inicia();

    public Cargo incluiCargo(String nome, boolean mayEnter);

    public void removeCargoByCodigo(int codigo) throws Exception;

    public ArrayList<Cargo> getCargos();

    public Cargo chooseCargo() throws Exception;
}
