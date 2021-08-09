package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameBoard extends JPanel implements KeyListener {


    private static int FPS = 60;
    private static int delay = FPS / 1000;

    public static final int Board_width = 10;
    public static final int Board_height = 20;
    public static final int Block_size =30;
    private Timer looper;
    private Color[][] board = new Color[Board_width][Board_height];
    private Color[][] shape = {
            {Color.RED,Color.RED,Color.RED},
            {null,Color.RED,null}
    };
    private int x = 4,y = 0;
    private int normal = 600;
    private int fast = 50;
    private int delayTimeForMovement  = normal;
    private long beginTime;

    private int deltaX = 0;
    private boolean collision = false;

    public GameBoard(){
        looper = new Timer(delay, new ActionListener() {
            int n = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(collision){
                    return;
                }
                //check movement horizontal
                if (!(x+ deltaX + shape[0].length>10) && !(x+deltaX <0)) {
                    x += deltaX;

                }
                deltaX = 0;
                if (System.currentTimeMillis()-beginTime>delayTimeForMovement){
                    if (!(y +1 + shape.length > Board_height)){
                        y++;
                    }
                    else{
                        collision = true;
                    }
                    beginTime = System.currentTimeMillis();
                }

                repaint();
            }
        });
        looper.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());

        //draw the shape
        for(int row = 0; row < shape.length; row++){
            for (int col = 0; col<shape[0].length;col++){
                if(shape[row][col]!= null){
                    g.setColor(shape[row][col]);
                g.fillRect(col*Block_size + x * Block_size ,row*Block_size + y* Block_size,Block_size,Block_size);
            }}
        }

        // draw the board
        g.setColor(Color.WHITE);
        for (int row = 0; row<Board_height;row++){
            g.drawLine(0,Block_size*row,Block_size*Board_width,Block_size*row);
        }
        for (int col = 0; col<Board_width+1;col++){
            g.drawLine(col*Block_size,0,col*Block_size,Block_size*Board_height);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode()==KeyEvent.VK_DOWN){
        delayTimeForMovement = fast;

    }
    else if (e.getKeyCode()==KeyEvent.VK_LEFT){
        deltaX = -1;

    }
    else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
        deltaX = +1;

    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            delayTimeForMovement = normal;
        }
    }
}
