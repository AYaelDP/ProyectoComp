import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//HOLA AMIGOS
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
                    if (c == '"') {
                        estado = 24;
                        lexema += c;
                    } else if (c== '/'){
                        estado= 26;
                        lexema += c;
                    }
                    break;
                case 24: // Estado para construir una cadena de texto               
                 if (Character.isJavaIdentifierPart(c)) {
                    estado = 24;
                    lexema += c;
                 } else if (c == '"') {
                    lexema += c;
                    Token t = new Token(TipoToken.STRING, lexema, lexema.replace('"', ' ').trim());
                    tokens.add(t);
                    estado = 0;
                    lexema = "";
                 } else {
                   System.out.println("Error");
                 }
                    break;
                case 26: // Diagrama 5
                    if (c == '*') {
                        estado = 27;
                        lexema += c;
                    } else if (c == '/') {
                        estado = 30;
                        lexema += c;
                    } else {
                        Token t = new Token(TipoToken.SLASH, lexema);
                        tokens.add(t);
                        // estado32 Slash
                        estado = 0;
                        lexema = "";
                        i--;
                    }

                    break;
                case 27: // Diagrama 5
                    if (c == '*') {
                        estado = 28;
                        lexema += c;
                    } else{
                        estado = 27;
                        lexema += c;
                    }
                    break;
                case 28:
                    if ( c == '*'){
                        estado = 28;
                        lexema += c;
                    } else if ( c == '/') {
                        estado = 0;
                        lexema ="";
                    } else if(Character.isJavaIdentifierPart(c)){
                        estado = 27;
                        lexema +=c;
                    } else {
                        System.out.println("Error");
                    }
                    break;
                 case 30:
                   if (c == '\n'){
                       estado = 0;
                       lexema = "";
                   }else{ 
                       estado = 30;
                       lexema +=c;
                   }
                     break;
            }
        }

        return tokens;
    }
}
