package asa;

import java.util.ArrayList;

public class celda {
    private ArrayList<String> celda = new ArrayList<String>();

    celda(ArrayList<String> aux) {
        celda.addAll(aux);
    }

    public ArrayList<String> getCelda() {
        return celda;
    }

}
