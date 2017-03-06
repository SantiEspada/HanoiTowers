import java.util.Scanner;
import java.util.Stack;

public class Hanoi {
    private int n;
    private int movements;
    private Stack<Integer> tower1, tower2, tower3;

    Hanoi(int n){
        this.n = n;

        tower1 = fill();
        tower2 = new Stack<>();
        tower3 = new Stack<>();
    }

    public void playGame(){
        System.out.println("Game will begin! This should be solved in " + getMinimumMovements() + " movements\n");
        printStacks();
        move(this.n, tower1, tower2, tower3);
        System.out.println("\nMinimum movements required: " + getMinimumMovements() + " | Movements done: " + (movements-1));

        if(movements-1 == getMinimumMovements()){
            System.out.println("YASSSSSSS the algorithm works flawlessly");
        } else {
            System.out.println("oops, something went wrong");
        }
    }

    private void move(int n, Stack<Integer> from, Stack<Integer> to, Stack<Integer> spare){
        if(n == 1){
            from.remove((Integer) 1);
            to.push(n);
            printStacks();
        } else {
            move(n-1, from, spare, to);
            from.remove((Integer) n);
            to.add(n);
            printStacks();
            move(n-1, spare, to, from);
        }
    }

    private void printStacks(){
        System.out.println("\nStep #"+movements+"\n");

        for(int i = 0; i < 3; i++) printStep(0);
        System.out.println();

        for(int i = n-1; i >= 0; i--){
            printStep((tower1.size() > i) ? tower1.elementAt(i) : 0);
            printStep((tower2.size() > i) ? tower2.elementAt(i) : 0);
            printStep((tower3.size() > i) ? tower3.elementAt(i) : 0);
            System.out.println();
        }

        for(int i = 0; i < 3; i++){
            if(i == n){
                for(int j = 0; j < (2*n)-1; j++){
                    System.out.print("=");
                }
            } else {
                for(int j = 0; j < (2*n)-1; j++){
                    System.out.print("=");
                }
                for(int j = 0; j < 2; j++) System.out.print(" ");
            }
        }
        System.out.println();

        this.movements++;
    }

    private void printStep(int n){
        n*=2;
        n-=1;
        int max = (this.n*2-1)-n;
        if(n != -1) {
            for (int i = 0; i < max / 2; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < n; i++) {
                System.out.print("*");
            }
            for (int i = -2; i < max / 2; i++) {
                System.out.print(" ");
            }
        } else {
            max--;
            max/=2;
            for(int i = 0; i < max; i++){
                System.out.print(" ");
            }
            System.out.print("|");
            for(int i = -2; i < max; i++){
                System.out.print(" ");
            }
        }
    }

    public int getMinimumMovements(){
        return (int) (Math.pow(2, n) - 1);
    }

    public int getMovements() {
        return movements;
    }

    private Stack<Integer> fill(){
        Stack<Integer> stack = new Stack<>();

        for(int i = n; i > 0; i--){
            stack.push(i);
        }

        return stack;
    }

    public static void main(String[] args){
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        while(!end) {
            System.out.println("\nEnter a number to play or type 'quit' to finish the program");
            String input = sc.next();
            if(input.toLowerCase().equals("quit")){
                end = true;
            } else {
                int n = Integer.parseInt(input);
                Hanoi hanoi = new Hanoi(n);
                hanoi.playGame();
            }
        }

        System.out.println("Exiting... thanks!");
    }
}
