package com.game;
import javax.swing.JFrame;
public class game{
    public static final int Width = 445, Height = 629;
    private GameBoard board;
    private JFrame window;
    public game(){
        window = new JFrame("Tetris");
        window.setSize(Width,Height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        board = new GameBoard();
        window.add(board);
        window.addKeyListener(board);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        new game();
    }
}