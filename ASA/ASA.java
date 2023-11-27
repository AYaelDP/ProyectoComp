package asa;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ASA {
    private celda[][] tabla = new celda[28][20]; // Declaramos la tabla de analisis sintactico
    private int i = 0;
    private boolean seEncuentranErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;

    ASA(List<Token> tokens) {

        this.tokens = tokens;
        preanalisis = this.tokens.get(i);

        listaRegla0.add("Q");
        listaRegla0.add("select");
        listaRegla0.add("D");
        listaRegla0.add("from");

        // Producción 0 (Q → select D from T)
        listaRegla0.add("T");

        listaRegla1.add("D");
        listaRegla1.add("distinct");

        // Producción 1 (D → distinct P)
        listaRegla1.add("P");

        listaRegla2.add("D");

        // Producción 2 (D → P)
        listaRegla2.add("P");

        listaRegla3.add("P");

        // Producción 3 (P → *)
        listaRegla3.add("*");

        listaRegla4.add("P");

        // Producción 4 (P → A)
        listaRegla4.add("A");

        listaRegla5.add("A");
        listaRegla5.add("A");
        listaRegla5.add(",");

        // Producción 5 (A → A , A1)
        listaRegla5.add("A1");

        listaRegla6.add("A");

        // Producción 6 (A → A1)
        listaRegla6.add("A1");

        listaRegla7.add("A1");
        listaRegla7.add("id");

        // Producción 7 (A1 → id A2)
        listaRegla7.add("A2");

        listaRegla8.add("A2");
        listaRegla8.add(".");

        // Producción 8 (A2 → . id)
        listaRegla8.add("id");

        listaRegla10.add("A1");

        // Producción 9 (A2 → Ɛ)
        listaRegla10.add("id");

        listaRegla11.add("T");
        listaRegla11.add("T");
        listaRegla11.add(",");

        // Producción 10 (T → T , T1)
        listaRegla11.add("T1");
        listaRegla12.add("T");

        // Producción 11 (T → T1)
        listaRegla12.add("T1");

        listaRegla13.add("T1");
        listaRegla13.add("id");

        // Producción 12 (T1 → id T2)
        listaRegla13.add("T2");

        listaRegla14.add("T2");

        // Producción 13 (T2 → id)
        listaRegla14.add("id");

        listaRegla15.add("T1");

        // Producción 14 (T2 → Ɛ)
        listaRegla15.add("id");

        ArrayList<String> aux = new ArrayList<String>();

        aux.clear(); // Limpiamos elementos
        aux.add("acc"); // Agregamos elementos

        tabla[1][7] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("12");

        tabla[2][2] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("14");

        tabla[2][3] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("18");

        tabla[2][5] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("3"); // Agregamos elementos

        tabla[2][9] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("23"); // Agregamos elementos

        tabla[2][10] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("15"); // Agregamos elementos

        tabla[2][11] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("22"); // Agregamos elementos

        tabla[2][12] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("4");

        tabla[3][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("6");

        tabla[4][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("7");

        tabla[4][5] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("5"); // Agregamos elementos

        tabla[4][15] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("10");

        tabla[5][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("0");

        tabla[5][7] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("12");

        tabla[6][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("12");

        tabla[6][7] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("9");

        tabla[7][5] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("8"); // Agregamos elementos

        tabla[7][17] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("13");

        tabla[8][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("13");

        tabla[8][7] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("14");

        tabla[9][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("14");

        tabla[9][7] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("7");

        tabla[10][5] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("11"); // Agregamos elementos

        tabla[10][16] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("11");

        tabla[11][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("11");

        tabla[11][7] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("14");

        tabla[12][3] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("18");

        tabla[12][5] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("13"); // Agregamos elementos

        tabla[12][10] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("15"); // Agregamos elementos

        tabla[12][11] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("22"); // Agregamos elementos

        tabla[12][12] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("1");

        tabla[13][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("3");

        tabla[14][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("4");

        tabla[15][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("16");

        tabla[15][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("18");

        tabla[16][5] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("17"); // Agregamos elementos

        tabla[16][12] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("5");

        tabla[17][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("5");

        tabla[17][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("20");

        tabla[18][6] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("19"); // Agregamos elementos

        tabla[18][13] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("7");

        tabla[19][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("7");

        tabla[19][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("21");

        tabla[20][5] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("8");

        tabla[21][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("8");

        tabla[21][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("6");

        tabla[22][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("6");

        tabla[22][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("2");

        tabla[23][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("s"); // Agregamos elementos
        aux.add("2");

        tabla[0][0] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("1"); // Agregamos elementos

        tabla[0][8] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("10");

        tabla[18][1] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("10");

        tabla[18][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("15");

        tabla[7][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("15");

        tabla[7][7] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("24"); // Agregamos elementos

        tabla[4][16] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("12");

        tabla[24][4] = new celda(aux);
        aux.clear(); // Limpiamos elementos
        aux.add("r"); // Agregamos elementos
        aux.add("12");

        tabla[24][7] = new celda(aux);

    }

    public boolean parse() {
        Stack<Integer> pila = new Stack<Integer>();
        //
        pila.clear();
        int a = preanalisis.buscar();
        int s = 0;
        for (;;) {
            if (!pila.empty())
                //
                s = (int) pila.peek();
            else
                pila.push(s);
            if (tabla[s][a] != null && tabla[s][a].getCelda().get(0) == "s") { // Manejo de
                                                                               // desplazamientos
                pila.push(Integer.valueOf(tabla[s][a].getCelda().get(1)));
                i++;
                preanalisis = tokens.get(i);
                a = preanalisis.buscar();
            } else if (tabla[s][a] != null && tabla[s][a].getCelda().get(0) == "r") { // Se manejan las
                                                                                      // reducciones

                //

                int tam = produccion.size() - 1;
                String A = produccion.get(0);

                for (int j = 0; j < tam; j++) {
                    pila.pop();
                }

                System.out.print(tabla[s][a].getCelda().get(1) + ")" + produccion.get(0) + "->"); // Imprimimos
                                                                                                  // las
                // producciones

                for (int i = 1; i <= tam; i++)
                    System.out.print(produccion.get(i));
                System.out.println("");

                pila.push(Integer.valueOf(tabla[(int) pila.peek()][buscar(A)].getCelda().get(0)));

            } else if (tabla[s][a] != null && tabla[s][a].getCelda().get(0) == "acc") {
                break;
            } else {
                seEncuentranErrores = true;
                break;
            }
        }

        if (preanalisis.tipo == TipoToken.EOF && !seEncuentranErrores) {
            System.out.println("La consulta es correcta");
            return true;
        } else {
            System.out.println("Se han encontrado errores en la consulta");
        }

        return false;
    }

    // Devuelve la lista correspondiente a la regla de produccion
    public ArrayList<String> reds(String aux) {
        if (aux.equals("0")) {
            return listaRegla0;
        } else if (aux.equals("1")) {
            return listaRegla1;
        } else if (aux.equals("2")) {
            return listaRegla2;
        } else if (aux.equals("3")) {
            return listaRegla3;
        } else if (aux.equals("4")) {
            return listaRegla4;
        } else if (aux.equals("5")) {
            return listaRegla5;
        } else if (aux.equals("6")) {
            return listaRegla6;
        } else if (aux.equals("7")) {
            return listaRegla7;
        } else if (aux.equals("8")) {
            return listaRegla8;
        } else if (aux.equals("9")) {
            return listaRegla9;
        } else if (aux.equals("10")) {
            return listaRegla10;
        } else if (aux.equals("11")) {
            return listaRegla11;
        } else if (aux.equals("12")) {
            return listaRegla12;
        } else if (aux.equals("13")) {
            return listaRegla13;
        } else if (aux.equals("14")) {
            return listaRegla14;
        } else if (aux.equals("15")) {
            return listaRegla15;
        }
        return listaRegla16;

    }

    // Busca el índice asociado a la cadena A en analisis sintactico
    public int buscar(String A) {
        if (A.equals("Q")) {
            return 8;
        } else if (A.equals("D")) {
            return 9;
        } else if (A.equals("P")) {
            return 10;
        } else if (A.equals("A")) {
            return 11;
        } else if (A.equals("A1")) {
            return 12;
        } else if (A.equals("A2")) {
            return 13;
        } else if (A.equals("A3")) {
            return 14;
        } else if (A.equals("T")) {
            return 15;
        } else if (A.equals("T1")) {
            return 16;
        } else if (A.equals("T2")) {
            return 17;
        } else if (A.equals("T3")) {
            return 18;
        }
        return -1;
    }

    // Variables para almacenar elementos correspondientes a cada regla de
    // produccion
    private ArrayList<String> listaRegla0 = new ArrayList<String>();
    private ArrayList<String> listaRegla1 = new ArrayList<String>();
    private ArrayList<String> listaRegla2 = new ArrayList<String>();
    private ArrayList<String> listaRegla3 = new ArrayList<String>();
    private ArrayList<String> listaRegla4 = new ArrayList<String>();
    private ArrayList<String> listaRegla5 = new ArrayList<String>();
    private ArrayList<String> listaRegla6 = new ArrayList<String>();
    private ArrayList<String> listaRegla7 = new ArrayList<String>();
    private ArrayList<String> listaRegla8 = new ArrayList<String>();
    private ArrayList<String> listaRegla9 = new ArrayList<String>();
    private ArrayList<String> listaRegla10 = new ArrayList<String>();
    private ArrayList<String> listaRegla11 = new ArrayList<String>();
    private ArrayList<String> listaRegla12 = new ArrayList<String>();
    private ArrayList<String> listaRegla13 = new ArrayList<String>();
    private ArrayList<String> listaRegla14 = new ArrayList<String>();
    private ArrayList<String> listaRegla15 = new ArrayList<String>();
    private ArrayList<String> listaRegla16 = new ArrayList<String>();
}
