package application;

import chess.*;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[1;31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[1;35m";
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

    private static String repeatCharacter(char a, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(a);
        }
        return sb.toString();
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println();
        System.out.printf("Turno: %d\n", chessMatch.getTurn());
        if(!chessMatch.getCheckMate()) {
            if (chessMatch.getCurrentPlayer() == Color.WHITE) {
                System.out.println("Aguardando jogador:" + ANSI_RED + " Vermelho" + ANSI_RESET);
            } else {
                System.out.println("Aguardando jogador:" + ANSI_GREEN + " Verde" + ANSI_RESET);
            }
            if (chessMatch.getCheck()) {
                System.out.println("CHEQUE!");
            }
        }else {
            System.out.println("CHEQUE-MATE");
            if (chessMatch.getCurrentPlayer() == Color.WHITE) {
                System.out.println("Vencedor" + ANSI_RED + "Vermelho" + ANSI_RESET);
            } else {
                System.out.println("Vencedor:" + ANSI_GREEN + "Verde" + ANSI_RESET);
            }
        }
    }
    public static void printBoard(ChessPiece[][] pieces) {
        System.out.println(" ┌" + repeatCharacter('─',24) + "┐");
        int index = 0;
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + "│");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j],false, index);
                index++;
            }
            index++;
            System.out.print("│");
            System.out.println();
        }
        System.out.println(" └"+ repeatCharacter('─', 24) + "┘");
        System.out.println("   A  B  C  D  E  F  G  H");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        System.out.println(" ┌" + repeatCharacter('─',24) + "┐");
        int index = 0;
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + "│");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j],possibleMoves[i][j], index);
                index++;
            }
            index++;
            System.out.print("│");
            System.out.println();
        }
        System.out.println(" └"+ repeatCharacter('─', 24) + "┘");
        System.out.println("   A  B  C  D  E  F  G  H");
    }

    private static void printPiece(ChessPiece piece,boolean background, int index) {

        if(index % 2 == 0) {
            System.out.print(ANSI_WHITE_BACKGROUND);
            if(background) {
                System.out.print(ANSI_RESET + ANSI_CYAN_BACKGROUND);
                if(piece == null) {
                    System.out.print(ANSI_WHITE + " ■ " + ANSI_RESET);
                }else if(piece.getColor() == Color.WHITE) {
                    System.out.print(" " + ANSI_RED + piece + " " +ANSI_RESET);
                }else if(piece.getColor() == Color.BLACK) {
                    System.out.print(" " + ANSI_GREEN + piece + " " + ANSI_RESET);
                }
            }else {
                if(piece == null) {
                    System.out.print("   " + ANSI_RESET);
                }else if(piece.getColor() == Color.WHITE) {
                    System.out.print(" " + ANSI_RED + piece + " " +ANSI_RESET);
                }else if(piece.getColor() == Color.BLACK) {
                    System.out.print(" " + ANSI_GREEN + piece + " " + ANSI_RESET);
                }
            }
        }else {
            System.out.print(ANSI_BLACK_BACKGROUND);
            if(background) {
                System.out.print(ANSI_RESET + ANSI_CYAN_BACKGROUND);
                if(piece == null) {
                    System.out.print(ANSI_BLACK + " ■ " + ANSI_RESET);
                }else if(piece.getColor() == Color.WHITE) {
                    System.out.print(" " + ANSI_RED + piece + " " +ANSI_RESET);
                }else if(piece.getColor() == Color.BLACK) {
                    System.out.print(" " + ANSI_GREEN + piece + " " + ANSI_RESET);
                }
            }else {
                if(piece == null) {
                    System.out.print("   " + ANSI_RESET);
                }else if(piece.getColor() == Color.WHITE) {
                    System.out.print(" " + ANSI_RED + piece + " " +ANSI_RESET);
                }else if(piece.getColor() == Color.BLACK) {
                    System.out.print(" " + ANSI_GREEN + piece + " " + ANSI_RESET);
                }
            }
        }
    }

    private static void printCapturedPieces(List<ChessPiece> captured) {
        List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println("Peças capturadas: ");
        System.out.print("Vermelhas: ");
        System.out.print(ANSI_RED);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Verdes: ");
        System.out.print(ANSI_GREEN);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}
