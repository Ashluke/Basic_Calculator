package src;
public class Parser {
    public static double eval(String expr){
        // Temporary class. Clean & efficient
        return new Object(){

            // Position, and every digit/character
            int pos = -1, ch;

            // Next digit/char function
            void nextChar(){
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            // To express each sign
            boolean eat(int charToEat){
                if(ch == charToEat){
                    nextChar();
                    return true;
                }
                return false;
            }

            // Initialize program, can be put inside parseExpression() but chose not too, looks clean.
            double parse(){
                nextChar();
                return parseAdditive();
            }

            // Handles additive operands
            double parseAdditive(){
                double x = parseMultiplitative();
                while(true){
                    if(eat('+')) x += parseMultiplitative();
                    else if(eat('-')) x -= parseMultiplitative();
                    else return x;
                }
            }

            // Handles multiplitative operands
            double parseMultiplitative(){
                double x = parseTerm();
                while(true){
                    if(eat('*')) x *= parseTerm();
                    else if(eat('/')) x /= parseTerm();
                    else return x;
                }
            }

            // Handles digit and sign differentiation
            double parseTerm(){

                // Handles unary (negative & positive numbers). '+' can be removed.
                if(eat('+')) return parseTerm();
                if(eat('-')) return -parseTerm();

                double x;
                int startPos = this.pos;

                // Handles string to double conversion
                if((ch >= '0' && ch <= '9') || ch == '.'){
                    while((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expr.substring(startPos, this.pos));
                }

                else{
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                return x;
            }
        }.parse(); // Calling the initializator, will be .parseAdditive() if chose to put the function of parse() to parseAdditive()
    }
}
