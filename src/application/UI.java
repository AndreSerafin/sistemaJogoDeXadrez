package application;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }catch(RuntimeException e) {
            throw new InputMismatchException("Erro lendo ChessPosition. Valores validos são de a1 até h8");
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        System.out.println(" |________________________|");
        int index = 0;
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + "|");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j],index);
                index++;
            }
            index++;
            System.out.print("|");
            System.out.println();
        }
        System.out.println(" |________________________|");
        System.out.println("   A  B  C  D  E  F  G  H");
    }

    private static void printPiece(ChessPiece piece, int index) {
        if (piece == null){
            if(index % 2 == 0) {
                System.out.print(ANSI_BLACK_BACKGROUND + " - " + ANSI_RESET);
            }else {
                System.out.print(ANSI_WHITE_BACKGROUND + " - " + ANSI_RESET);
            }
        }else {
            if(piece.getColor() == Color.WHITE){
                if(index % 2 == 0) {
                    System.out.print(ANSI_BLACK_BACKGROUND + " " + ANSI_RED + piece + " " + ANSI_RESET );
                }else {
                    System.out.print(ANSI_WHITE_BACKGROUND + " " + ANSI_RED + piece + " " + ANSI_RESET);
                }
            }else{
                if(index % 2 == 0) {
                    System.out.print(ANSI_BLACK_BACKGROUND + " " + ANSI_PURPLE + piece + " " + ANSI_RESET );
                }else {
                    System.out.print(ANSI_WHITE_BACKGROUND + " " + ANSI_PURPLE + piece + " " + ANSI_RESET);
                }
            }
        }
    }
}
