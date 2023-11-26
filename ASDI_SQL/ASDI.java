import java.util.*;

public class ASDI {
    private List<Token> tokens;
    private int tokenActual = 0;
    private TAnalisis tabla;

    public ASDI(List<Token> tokens, TAnalisis tabla) {
        this.tokens = tokens;
        this.tabla = tabla;
    }

    public boolean parse() {
        Stack<Object> pila = new Stack<>();
        pila.push(TipoToken.EOF);
        pila.push("Q");  // Símbolo inicial de la gramática

        while (!pila.isEmpty()) {
            Object cima = pila.peek();
            Token token = tokens.get(tokenActual);

            if (cima instanceof TipoToken) {
                if (cima == token.tipo) {
                    pila.pop();
                    tokenActual++;
                } else {
                    return false;
                }
            } else {
                String noTerminal = (String) cima;
                List<Object> produccion = tabla.getProduccion(noTerminal, token.tipo);
                if (produccion != null) {
                    pila.pop();
                    for (int i = produccion.size() - 1; i >= 0; i--) {
                        pila.push(produccion.get(i));
                    }
                } else {
                    return false;
                }
            }
        }

        return tokenActual == tokens.size();
    }
}

