import java.io.*;
import java.util.*;

public class Othello {
    int turn;
    int winner;
    int board[][];
    //add required class variables here

    public Othello(String filename) throws Exception {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        turn = sc.nextInt();
        board = new int[8][8];
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j){
                board[i][j] = sc.nextInt();
            }
        }
        winner = -1;
        //Student can choose to add preprocessing here
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

    }

    //add required helper functions here

    
    private int board_score_helper(int b[][],int tur){
        int black_tiles = 0;
        int white_tiles = 0;

        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                if(b[i][j] == 0){
                    black_tiles = black_tiles + 1;
                }
                if(b[i][j] == 1){
                    white_tiles = white_tiles + 1;
                }
            }
        }

        if(tur == 0){
            return black_tiles - white_tiles;
          
        }
        else{
            return white_tiles - black_tiles;
        }

    }


    public int boardScore() {

        return board_score_helper(board,turn);

    }


    private boolean isValidMove(int[][] boar, int tur, int row, int col) {
        // Check if the given move is valid in any of the eight directions
        //int intial = boar[row][col];

        int current_color = tur;

        if(row != 0 && col != 0 && row != 7 && col != 7){
            if( boar[row][col+1] == -1 && (col != 0 && boar[row][col-1] == -1) && boar[row+1][col] == -1 && boar[row-1][col] == -1 && boar[row-1][col+1] == -1 && boar[row-1][col-1] == -1 && boar[row+1][col-1] == -1 && boar[row+1][col+1] == -1){
                return false;
            }
        }
        else if(row == 0 || col == 0 || row == 7 || col == 7){
            if(row == 0){
                if(col != 0 && col != 7){
                    if(boar[row][col-1] == -1 && boar[row][col+1] == -1 && boar[row+1][col] == -1 && boar[row+1][col+1] == -1 && boar[row+1][col-1] == -1){
                        return false;
                    }
                }
                if(col == 0){
                    if(boar[row][col+1] == -1 && boar[row+1][col] == -1 && boar[row+1][col+1] == -1){
                        return false;
                    }
                }
                if(col == 7){
                    if(boar[row][col-1] == -1 && boar[row+1][col] == -1 && boar[row+1][col-1] == -1){
                        return false;
                    }
                }
            }

            if(col == 0){
                if(row != 0 && row != 7){
                    if(boar[row+1][col] == -1 && boar[row-1][col] == -1 && boar[row][col+1] == -1 && boar[row-1][col+1] == -1 && boar[row+1][col+1] == -1){
                        return false;
                    }
                }
                if(row == 7){
                    //System.out.println(col+"col");
                    if(boar[row-1][col] == -1 && boar[row][col+1] == -1 && boar[row-1][col+1] == -1){
                        return false;
                    }
                }
            }

            if(row == 7){
                if(col != 7 && col != 0){
                    if(boar[row][col+1] == -1 && boar[row][col-1] == -1 && boar[row-1][col] == -1 && boar[row-1][col+1] == -1 && boar[row-1][col-1] == -1){
                        return false;
                    }
                }
                if(col == 7){
                    if(boar[row-1][col] == -1 && boar[row][col-1] == -1 && boar[row-1][col-1] == -1){
                        return  false;
                    }
                }
            }

            if(col == 7){
                if(row != 0 && row != 7){
                    if(boar[row+1][col] == -1 && boar[row-1][col] == -1 && boar[row][col-1] == -1 && boar[row-1][col-1] == -1 && boar[row+1][col-1] == -1){
                        return false;
                    }
                }
            }
        }


        if(row != 0 && col != 0 && row != 7 && col != 7){
            if( boar[row][col+1] == current_color && (col != 0 && boar[row][col-1] == current_color) && boar[row+1][col] == current_color && boar[row-1][col] == current_color && boar[row-1][col+1] == current_color && boar[row-1][col-1] == current_color && boar[row+1][col-1] == current_color && boar[row+1][col+1] == current_color){
                return false;
            }
        }
        else if(row == 0 || col == 0 || row == 7 || col == 7){
            if(row == 0){
                if(col != 0 && col != 7){
                    if(boar[row][col-1] == current_color && boar[row][col+1] == current_color && boar[row+1][col] == current_color && boar[row+1][col+1] == current_color && boar[row+1][col-1] == current_color){
                        return false;
                    }
                }
                if(col == 0){
                    if(boar[row][col+1] == current_color && boar[row+1][col] == current_color && boar[row+1][col+1] == current_color){
                        return false;
                    }
                }
                if(col == 7){
                    if(boar[row][col-1] == current_color && boar[row+1][col] == current_color && boar[row+1][col-1] == current_color){
                        return false;
                    }
                }
            }
    
            if(col == 0){
                //System.out.println("row"+row+"col "+col);
                if(row != 0 && row != 7){
                    if(boar[row+1][col] == current_color && boar[row-1][col] == current_color && boar[row][col+1] == current_color && boar[row-1][col+1] == current_color && boar[row+1][col+1] == current_color){
                        return false;
                    }
                }
                if(row == 7){
                    if(boar[row-1][col] == current_color && boar[row][col+1] == current_color && boar[row-1][col+1] == current_color){
                        return false;
                    }
                }
            }
    
            if(row == 7){
                if(col != 7 && col != 0){
                    if(boar[row][col+1] == current_color && boar[row][col-1] == current_color && boar[row-1][col] == current_color && boar[row-1][col+1] == current_color && boar[row-1][col-1] == current_color){
                        return false;
                    }
                }
                if(col == 7){
                    if(boar[row-1][col] == current_color && boar[row][col-1] == current_color && boar[row-1][col-1] == current_color){
                        return  false;
                    }
                }
            }
    
            if(col == 7){
                if(row != 0 && row != 7){
                    if(boar[row+1][col] == current_color && boar[row-1][col] == current_color && boar[row][col-1] == current_color && boar[row-1][col-1] == current_color && boar[row+1][col-1] == current_color){
                        return false;
                    }
                }
            }
    
        }


        boolean found_right = false;
        if(col != 7){
            for(int j = col+1 ; j<boar.length;j++){
                if(boar[row][j] != current_color && boar[row][j] != -1){
                    continue;
                }
                if(boar[row][j] == -1){
                    break;
                }
                if(boar[row][j] == current_color){
                    found_right = true;
                    break;
                }
            }

            if(boar[row][col+1] == current_color){
                found_right = false;
            }
        }
        if(found_right == true){
            return true;
        }
       
        boolean found_left = false;
        if(col != 0){
            for(int j = col-1;j>=0;j--){
                if(boar[row][j] != current_color && boar[row][j] != -1){
                    continue;
                }
                if(boar[row][j] == -1){
                    break;
                }
                if(boar[row][j] == current_color){
                    found_left = true;
                    break;
                }
            }

            if(boar[row][col-1] == current_color){
                found_left = false;
            }
        }
        if(found_left == true){
            return true;
        }
       
       

        
        boolean found_upper = false;
        if(row != 0){
            for(int i = row -1;i>=0;i--){
                if(boar[i][col] != current_color && boar[i][col] != -1){
                    continue;
                }
                if(boar[i][col] == -1){
                    break;
                }
                if(boar[i][col] == current_color){
                    found_upper = true;
                    break;
                }
            }

            if(boar[row-1][col] == current_color){
                found_upper = false;
            }
        }
        if(found_upper == true){
            return true;
        }
       

        boolean found_lower = false;
        if(row != 7){
            for(int i = row+1;i<boar.length;i++){
                if(boar[i][col] != current_color && boar[i][col] != -1){
                    continue;
                }
                if(boar[i][col] == -1){
                    break;
                }
                if(boar[i][col] == current_color){
                    found_lower = true;
                    break;
                }
            }

            if(boar[row+1][col] == current_color){
                found_lower = false;
            }
        }
        if(found_lower == true){
            return true;
        }


        boolean found_upper_right_digonal = false;
        if(row != 0 && col != 7){
            int i = row-1;
            int j = col+1;
            while(i >= 0 && j < boar.length){
                if((boar[i][j] != current_color) && boar[i][j] != -1){
                    i--;
                    j++;
                }
                if(i == -1 || j == 8){
                    break;
                }
                if((boar[i][j] == -1)){
                    break;
                }

                if(boar[i][j] == current_color){
                    found_upper_right_digonal = true;
                    break;
                }
                i--;
                j++;
            }

            if(boar[row-1][col+1] == current_color){
                found_upper_right_digonal = false;
            }
        }

        if(found_upper_right_digonal == true){
            return true;
        }


       
        boolean found_upper_left_digonal = false;
        if(row != 0 && col != 0){

            int i = row-1;
            int j = col-1;
            while(i >= 0 && j >= 0){
                if((boar[i][j] != current_color) && boar[i][j] != -1){
                    i--;
                    j--;
                }
                if(i == -1 || j == -1){
                    break;
                }
                if((boar[i][j] == -1)){
                    break;
                }
                if(boar[i][j] == current_color){
                    found_upper_left_digonal = true;
                    break;
                }
                i--;
                j--;
            }

            if(boar[row-1][col-1] == current_color){
                found_upper_left_digonal = false;

            } 
        }
        
        if(found_upper_left_digonal == true){
            return true;
        }
     
        boolean found_lower_left_digonal = false;
        if(row != 7 && col != 0){
            int i = row+1;
            int j = col-1;
            while(i < boar.length && j >= 0){
                if((boar[i][j] != current_color) && boar[i][j] != -1){
                    i++;
                    j--;
                }
                if(i == 8 || j == -1){
                    break;
                }
                if((boar[i][j] == -1)){
                    break;
                }
                if(boar[i][j] == current_color){
                    found_lower_left_digonal = true;
                    break;
                }
                i++;
                j--;
            }
            if(boar[row+1][col-1] == current_color){
                found_lower_left_digonal = false;
            }
        }
        if(found_lower_left_digonal == true){
            return true;
        }
       

        boolean found_lower_right_digonal = false;
        if(row != 7 && col != 7){
            //System.out.println(col+"row");
            int i = row+1;
            int j = col+1;
            while(i < boar.length && j < boar.length){
                if((boar[i][j] != current_color) && boar[i][j] != -1){
                    i++;
                    j++;
                }
                if(i == 8 || j == 8){
                    break;
                }
                if((boar[i][j] == -1)){
                    break;
                }
                if(boar[i][j] == current_color){
                    found_lower_right_digonal = true;
                    break;
                }
                i++;
                j++;
            }

            if(boar[row+1][col+1] == current_color){
                found_lower_right_digonal = false;
            }
        }

        if(found_lower_right_digonal == true){
            return true;
        }



        if(found_left == true || found_right == true || found_upper == true || found_lower == true || found_upper_left_digonal == true || found_upper_right_digonal == true || found_lower_left_digonal == true || found_lower_right_digonal == true){
            return true;
        }
        else{
            return false;
        }
    }



    private int[][] copyBoard(int[][] boar) {
        int[][] newBoard = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newBoard[i][j] = boar[i][j];
            }
        }
        return newBoard;
    }

    private boolean hasLegalMoves(int[][] boar, int turn) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boar[i][j] == -1) {
                    if (isValidMove(boar, turn, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isGameOver(int[][] boar) {
        return (!hasLegalMoves(boar, 1) && !hasLegalMoves(boar, 0));
    }

    public int[][] getBoardCopy() {
        int copy[][] = new int[8][8];
        for(int i = 0; i < 8; ++i)
            System.arraycopy(board[i], 0, copy[i], 0, 8);
        return copy;
        }


    public int getWinner() {
        return winner;
    }
    
    public int getTurn() {
        return turn;
    }


    private void extreme_right(int boar[][] , int current_i , int current_j){
        int current_color = boar[current_i][current_j];

        int j = current_j+1;
        while(j < boar.length){
            if((boar[current_i][j] != current_color) && boar[current_i][j] != -1){
                j++;
            }
            if(j == 8){
                break;
            }
            if((boar[current_i][j] == -1)){
                break;
            }

            if(boar[current_i][j] == current_color){
                int y = j-1;
                while(y>= current_j+1){
                    boar[current_i][y] = current_color;
                    y--;
                }
                break;
            }
            j++;
        }
        return;
    }

    private void extreme_left(int boar[][] , int current_i , int current_j){
        int current_color = boar[current_i][current_j];

        int j = current_j-1;
        while(j >= 0){
            if((boar[current_i][j] != current_color) && boar[current_i][j] != -1){
                j--;
            }
            if(j == -1){
                break;
            }
            if((boar[current_i][j] == -1)){
                break;
            }

            if(boar[current_i][j] == current_color){
                int y = j+1;
                while(y < current_j){
                    boar[current_i][y] = current_color;
                    y++;
                }
                break;
            }
            j--;
        }
        return;

    }

    private void upper(int boar[][] , int current_i , int current_j){
        int current_color = boar[current_i][current_j];

        int i = current_i-1;
        while(i >= 0){
            if((boar[i][current_j] != current_color) && boar[i][current_j] != -1){
                i--;
            }
            if(i == -1){
                break;
            }
            if((boar[i][current_j] == -1)){
                break;
            }

            if(boar[i][current_j] == current_color){
                int y = i+1;
                while(y <= current_i){
                    boar[y][current_j] = current_color;
                    y++;
                }
                break;
            }
            i--;
        }
        return;
    }

    private void lower(int boar[][] , int current_i , int current_j){
        int current_color = boar[current_i][current_j];

        int i = current_i+1;
        while(i < 8){
            if((boar[i][current_j] != current_color) && boar[i][current_j] != -1){
                i++;
            }
            if(i == 8){
                break;
            }
            if((boar[i][current_j] == -1)){
                break;
            }

            if(boar[i][current_j] == current_color){
                int y = i-1;
                while(y >= current_i + 1){
                    boar[y][current_j] = current_color;
                    y--;
                }
                break;
            }
            i++;
        }
        return;

    }

    private void upper_right_digonal(int boar[][] , int current_i , int current_j){
        int current_color = boar[current_i][current_j];

        int i = current_i-1;
        int j = current_j+1;
        while(i >= 0 && j < boar.length){
            if((boar[i][j] != current_color) && boar[i][j] != -1){
                i--;
                j++;
            }
            if(i == -1 || j == 8){
                break;
            }
            if((boar[i][j] == -1)){
                break;
            }

            if(boar[i][j] == current_color){
                int k = i+1;
                int y = j-1;

                while(k < current_i && y >= current_j+1){
                    boar[k][y] = current_color;
                    k++;
                    y--;
                }
                break;
            }
            i--;
            j++;
        }
        return;

    }

    private void upper_left_digonal(int boar[][] , int current_i , int current_j){
        int current_color = boar[current_i][current_j];

        int i = current_i-1;
        int j = current_j-1;
        while(i >= 0 && j >= 0){
            if((boar[i][j] != current_color) && boar[i][j] != -1){
                i--;
                j--;
            }
            if(i == -1 || j == -1){
                break;
            }
            if((boar[i][j] == -1)){
                break;
            }
            if(boar[i][j] == current_color){
                int k = i;
                int y = j;
                //System.out.println(k+" "+y);
                while(k < current_i && y < current_j){
                    boar[k][y] = current_color;
                    k++;
                    y++;
                }
                break;
            }
            i--;
            j--;
        }
        return;
    }

    private void lower_left_digonal(int boar[][] , int current_i , int current_j){
        int current_color = boar[current_i][current_j];

        int i = current_i+1;
        int j = current_j-1;
        while(i < boar.length && j >= 0){
            if((boar[i][j] != current_color) && boar[i][j] != -1){
                i++;
                j--;
            }
            if(i == 8 || j == -1){
                break;
            }
            if((boar[i][j] == -1)){
                break;
            }
            if(boar[i][j] == current_color){
                int k = i;
                int y = j;
                //System.out.println(k+" "+y);
                while(k >= current_i+1 && y < current_j){
                    boar[k][y] = current_color;
                    k--;
                    y++;
                }
                break;
            }
            i++;
            j--;
        }
        return;
    }

    private void lower_right_digonal(int boar[][] , int current_i , int current_j){
        int current_color = boar[current_i][current_j];
        int i = current_i+1;
        int j = current_j+1;
        while(i < boar.length && j < boar.length){
            if((boar[i][j] != current_color) && boar[i][j] != -1){
                i++;
                j++;
            }
            if(i == 8 || j == 8){
                break;
            }
            if((boar[i][j] == -1)){
                break;
            }
            if(boar[i][j] == current_color){
                int k = i;
                int y = j;
                while(k >= current_i+1 && y >= current_j+1){
                    boar[k][y] = current_color;
                    k--;
                    y--;
                }
                break;
            }
            i++;
            j++;
        }
        return;
    }

    
    private int mini_max_k_look_ahead(int boar[][], int depth,int tur, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0 || isGameOver(boar)) {
            return board_score_helper(boar,turn);
        }
        if(maximizingPlayer == true){
            int max_eval = Integer.MIN_VALUE;
            for(int i = 0;i<boar.length;i++){
                for(int j = 0;j<boar.length;j++){
                    if(boar[i][j] == -1){
                        if(isValidMove(boar, tur, i, j) == true){
                            int [][] c = copyBoard(boar);
                            c[i][j] = tur;
                            extreme_left(c, i, j);
                            lower_left_digonal(c, i, j);
                            upper_left_digonal(c,i,j);
                            extreme_right(c, i, j);
                            upper(c, i, j);
                            lower(c,i,j);
                            upper_right_digonal(c, i, j);
                            lower_right_digonal(c, i, j);
                            int eval = mini_max_k_look_ahead(c, depth-1, tur^1, alpha, beta, !maximizingPlayer);
                            max_eval = Math.max(max_eval,eval);
                            alpha = Math.max(alpha,eval);
                            if(beta <= alpha){
                                break;
                            }
                        }
                    }
                }
            }
            return max_eval;
        }
        else{
            int min_eval = Integer.MAX_VALUE;
            for(int i = 0;i<boar.length;i++){
                for(int j = 0;j<boar.length;j++){
                    if(boar[i][j] == -1){
                        if(isValidMove(boar, tur, i, j)){
                            int [][] c = copyBoard(boar);
                            c[i][j] = tur;
                            extreme_left(c, i, j);
                            lower_left_digonal(c, i, j);
                            upper_left_digonal(c,i,j);
                            extreme_right(c, i, j);
                            upper(c, i, j);
                            lower(c,i,j);
                            upper_right_digonal(c, i, j);
                            lower_right_digonal(c, i, j);
                            int eval = mini_max_k_look_ahead(c, depth-1 , tur^1, alpha, beta ,!maximizingPlayer);
                            min_eval = Math.min(min_eval,eval);
                            beta = Math.min(beta,eval);
                            if(beta <= alpha){
                                break;
                            }
                        }
                    }
                }
            }
            return min_eval;
        }
    }


    
    


    public int bestMove(int k){
        System.out.println("bestmove k "+k);
        ArrayList<Integer> s = new ArrayList<>();
        HashMap<Integer,Integer> h = new HashMap<>();
       
        for(int i = 0;i<board.length;i++){
            for(int j = 0;j<board.length;j++){
                if(board[i][j] == -1){
                    if(isValidMove(board, turn, i, j) == true){
                        int [][] c = copyBoard(board);
                        c[i][j] = turn;
                        extreme_left(c, i, j);
                        lower_left_digonal(c, i, j);
                        upper_left_digonal(c,i,j);
                        extreme_right(c, i, j);
                        upper(c, i, j);
                        lower(c,i,j);
                        upper_right_digonal(c, i, j);
                        lower_right_digonal(c, i, j);
                        int score = mini_max_k_look_ahead(c, k-1, Math.abs(turn-1), Integer.MIN_VALUE, Integer.MAX_VALUE,false);
                        h.put(i*8 + j,score);
                        s.add(score);
                    }
                }
            }
        }

        int max_v = Integer.MIN_VALUE;
        for(int i = 0;i<s.size();i++){
            max_v = Math.max(max_v, s.get(i));
        }
        
        int min = Integer.MAX_VALUE;
        for(Integer key : h.keySet()) {
            if(h.get(key) == max_v){
                min = Math.min(min,key);
            }
        }
        System.out.println("bestmove ans "+min);
        return min;
    }


    public ArrayList<Integer> fullGame(int k) {
        System.out.println("fullgame k "+k);
        ArrayList<Integer> full = new ArrayList<>();

        while(!isGameOver(board)){
            if(hasLegalMoves(board, turn)){
               // System.out.println(turn);
                int best_m = bestMove(k);
                //System.out.println(best_m);
                full.add(best_m);
                int i = best_m / 8;
                int j = best_m % 8;
                board[i][j] = turn;
                extreme_left(board, i, j);
                extreme_right(board, i, j);
                lower(board, i, j);
                upper(board, i, j);
                upper_left_digonal(board, i, j);
                upper_right_digonal(board, i, j);
                lower_left_digonal(board, i, j);
                lower_right_digonal(board, i, j);
                turn = turn^1;
              
            }
            else if(hasLegalMoves(board, turn) == false){
                turn = turn ^1;
                //System.out.println("hsgfnhsdg");
            }
        }

        if(isGameOver(board)){
            int white_winner = board_score_helper(board, 1);
            int black_winner = board_score_helper(board, 0);
            if(white_winner > black_winner){
                winner = 1;
            }
            else if(white_winner<black_winner){
                winner = 0;
            }
            else if(white_winner == black_winner){
                winner = -1;
            }
        }
        System.out.println(winner+" winner");
        System.out.println(full);

        return full;
    }
}
