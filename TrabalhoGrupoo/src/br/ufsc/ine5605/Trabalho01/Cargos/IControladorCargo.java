package br.ufsc.ine5605.Trabalho01.Cargos;

import java.util.ArrayList;

public interface IControladorCargo {

    public void inicia();

    public void incluiCargo(String nome, boolean mayEnter);

    public void removeCargoByCodigo(int codigo);

    public ArrayList<Cargo> getCargos();
}
