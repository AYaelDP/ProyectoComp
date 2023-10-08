import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TipoToken> palabrasReservadas;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and", TipoToken.AND);
        palabrasReservadas.put("else", TipoToken.ELSE);
        palabrasReservadas.put("false", TipoToken.FALSE);
        palabrasReservadas.put("for", TipoToken.FOR);
        palabrasReservadas.put("fun", TipoToken.FUN);
        palabrasReservadas.put("if", TipoToken.IF);
        palabrasReservadas.put("null", TipoToken.NULL);
        palabrasReservadas.put("or", TipoToken.OR);
        palabrasReservadas.put("print", TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true", TipoToken.TRUE);
        palabrasReservadas.put("var", TipoToken.VAR);
        palabrasReservadas.put("while", TipoToken.WHILE);
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    public Scanner(String source) {
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {
        int estado = 0;
        String lexema = "";
        char c;

        for (int i = 0; i < source.length(); i++) {
            c = source.charAt(i);

            switch (estado) {
                case 0:
                    // Tokens de un solo caracter (Diagrama del alumno) DIAGRAMA 6
                    else if (c == '+') {
                        Token t = new Token(TipoToken.PLUS, lexema);
                        tokens.add(t);
                    } else if (c == '-') {
                        Token t = new Token(TipoToken.MINUS, lexema);
                        tokens.add(t);
                    } else if (c == '.') {
                        Token t = new Token(TipoToken.DOT, lexema);
                        tokens.add(t);
                    } else if (c == ',') {
                        Token t = new Token(TipoToken.COMMA, lexema);
                        tokens.add(t);
                    } else if (c == '(') {
                        Token t = new Token(TipoToken.RIGHT_PAREN, lexema);
                        tokens.add(t);
                    } else if (c == ')') {
                        Token t = new Token(TipoToken.LEFT_PAREN, lexema);
                        tokens.add(t);
                    } else if (c == '{') {
                        Token t = new Token(TipoToken.RIGHT_BRACE, lexema);
                        tokens.add(t);
                    } else if (c == '}') {
                        Token t = new Token(TipoToken.LEFT_BRACE, lexema);
                        tokens.add(t);
                    } else if (c == '*') {
                        Token t = new Token(TipoToken.STAR, lexema);
                        tokens.add(t);
                    } else if (c == ';') {
                        Token t = new Token(TipoToken.SEMICOLON, lexema);
                        tokens.add(t);
                    } else if (c == '['){
                        System.out.println("Error: caracter '[' no valido");
                        System.exit(1);
                    } else if (c == ']'){
                        System.out.println("Error: caracter ']' no valido");
                        System.exit(1);
                    }
                    break;

                
                case 13:
                    if (Character.isLetterOrDigit(c)) {
                        estado = 13;
                        lexema += c;
                    } else {
                        TipoToken tt = palabrasReservadas.get(lexema);

                        if (tt == null) {
                            Token t = new Token(TipoToken.IDENTIFIER, lexema);
                            tokens.add(t);
                        } else {
                            Token t = new Token(tt, lexema);
                            tokens.add(t);
                        }

                        estado = 0;
                        lexema = "";
                        i--;

                    }
                    break;

                case 19: // Diagrama 3
                    if (Character.isDigit(c)) {
                        estado = 20;
                        lexema += c;
                    }
                    break;

                case 20: // Diagrama 3
                    if (Character.isDigit(i)) {
                        estado = 20;
                        lexema += c;
                    } else {
                        Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;
            }

        }

        return tokens;
    }
}
