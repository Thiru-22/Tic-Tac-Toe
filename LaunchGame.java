
import java.util.*;

class GameLogic{

    protected static char[][] board;
    protected int row;
    protected int col;
    protected char c='X';
    protected char c1='O';
    protected boolean correct;
    protected String person1,person2,person;
    protected Scanner sc=new Scanner(System.in);

    GameLogic(){
        board=new char[3][3];
        this.initBoard();
    }
    public void initBoard(){                             //To initialize the board with empty Spaces
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=' ';
            }
        }
    }
    public void displayBoard(){                       //display the current state of the borad
        System.out.println("-------------");
        for(int i=0;i<board.length;i++) {
            System.out.print("| ");
            for(int j=0;j<board[i].length;j++) {
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
            System.out.print("-------------");
            System.out.println();
        }	  
    }
    public static boolean positionBoard(int row,int col,char c){
       
        if(board[row][col]==' '){     //Update the board with the given move is valid
            board[row][col]=c;
            return true;
        }
        else{
            return false;
        }
       
    }

    public static boolean checkWin(int row,int col){                   //Check if the current moves leads to a win
        try {
            if(board[0][col]==board[1][col]&& board[1][col]==board[2][col] && board[0][col]!=' '){
                return true;
            }
            else if(board[row][0]==board[row][1] && board[row][1]==board[row][2] && board[row][0]!=' '){
                return true;
            }
            else if(board[2][0]==board[1][1]&&board[1][1]==board[0][2] && board[2][0]!=' '){
                return true;
            }
            else if(board[0][0]==board[1][1]&&board[1][1]==board[2][2] && board[0][0]!=' '){
                return true;
            }
            else
                return false;
            
        } catch (Exception e) {
            System.out.println("You are give the position as a valid it's wrong");
            return false;
        }       
    }
    protected int getInput(String inputType){     //validate and get user input for the row and col
        int input=-1;
        while(true){                              //Intialize with the invalid input the loop will continue
            System.out.println("Enter "+inputType+" (0,1 or 2):");
            try {
                if(sc.hasNextInt()){             //check if the next input as an integer
                    input=sc.nextInt();
                    if(input>=0 && input<=2){
                        break;
                    }else{                     //if the input is not an integer display the error msg
                        System.out.println("Invalid "+inputType+" Please Enter the number between 0 to 2."); }
                }
                else{                              //if the input is not an integer display the error msg
                    System.out.println("Invalid input this not number");
                    sc.next();                   //clear the invalid input
                }         
            }
            catch (InputMismatchException e) {
                System.out.println("Enter only numbers between 0 to 2");
                sc.nextLine();                 //clear the invalid input from the scanner buffer
            }
        }
        return input;
    }

}

class Human extends GameLogic{                    //Human vs. Human to play the game
    
    Human(){
        super();
        System.out.println("Lets Begin!!!");
        System.out.println("Enter 1st Person Name");
        this.person1=this.sc.nextLine().trim();
        if(this.person1.isEmpty()){
            this.person1="Thiru";
        }
        System.out.println("Enter 2nd Person Name");
        this.person2=this.sc.nextLine().trim();
        if(this.person2.isEmpty()){
            this.person2="Alex";
        }
        this.displayBoard();
        this.start();
    }
    public void start(){                               //Loop for human vs. human play game
        for(int i=0;i<9;i++){      
            if(i%2==0){
                System.out.println("It's Your Turn "+person1);
                
                this.row=this.getInput("row");
                
                this.col=this.getInput("col"); 
                correct=positionBoard(row, col, c);
                if(correct==false){
                    System.out.println(person1+" You enter the wrong position!! please enter one more time");i--;
                }
                if(checkWin(row,col)==true){
                    displayBoard();
                    System.out.println(person1+" won the game!!!!");
                    return;
                }
            }
            else{
                System.out.println("It's Your Turn "+person2);
                this.row=this.getInput("row");
                this.col=this.getInput("col");
                correct=positionBoard(row, col, c1);
                if(correct==false){
                    System.out.println(person2 +" You enter the wrong position!! please enter one more time");i--;
                }
                if(checkWin(row,col)==true){
                    displayBoard();
                    System.out.println(person2+"  won the game!!!!!");
                    return;
            }
            
        }
        displayBoard();
        if(i==8){
            System.out.println("Hurray!!!!Match is draw");
        }
    }
    }

}

class Computer extends GameLogic{              //Human vs. Computer to play the game
    

    Computer(){
        super();
        System.out.println("Lets Begin!!!");
        System.out.println("Enter Person Name");
        this.person=this.sc.nextLine().trim();
        if(this.person.isEmpty()){
            this.person="Thiru";
        }
        this.displayBoard();
        this.start();
    }

    public void start(){                         //Loop for human vs. Computer play game
        for(int i=0;i<9;i++){
            if(i%2==0){
                System.out.println("It's Your Turn "+person);
                this.row=this.getInput("row");
                this.col=this.getInput("col"); 
                correct=positionBoard(row, col, c);
                if(!correct){
                    System.out.println(person+" You enter the wrong position!! please enter one more time");i--;
                }
                else if(checkWin(row,col)==true){
                    displayBoard();
                    System.out.println(person+"  won the game!!!!");
                    return;
                }
            }
            else{
                
                System.out.println("It's My Turn ");
                Random r=new Random();

                do{
                    row=r.nextInt(3);
                    col=r.nextInt(3);
                }
                
                while(!positionBoard(row,col,c1));
                if(checkWin(row,col)==true){
                    displayBoard();
                    System.out.println("I  won the game!!!!!");
                    return;
            }
            }
            displayBoard();
            if(i==8){
                System.out.println("Hurray!!!!Match is draw");
        }
        }
    }
}
public class LaunchGame{                     
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       System.out.println("\t\tWELCOME TO TIC-TAC-TOE GAME");
       System.out.println("\t\t------------------------------");
       System.out.println("Playing with Who\n1.Computer \n2.Human");
       System.out.println("Enter your Choice");
       int choice=sc.nextInt();
       Computer c;
       Human h;
       if(choice==1){
         c=new Computer();
       }
       else if(choice==2){
        h=new Human();
       }
       else{
        System.out.println("Please give the valid choice");
       }
       sc.close();
    }
}