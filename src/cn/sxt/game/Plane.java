package cn.sxt.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{

//    int speed = 3;
    boolean up,down,left,right;
    boolean live = true;

    @Override
    public void drawSelf(Graphics g) {
        if (live){
            g.drawImage(image,(int)x,(int)y,null);
            if (left)
                x -= speed;
            if (right)
                x += speed;
            if (up)
                y -= speed;
            if (down)
                y += speed;
        }else {

        }

    }

    public Plane(Image image, double x, double y) {
        super();
        this.image = image;
        this.x = x;
        this.y = y;
        this.speed = 3;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void addDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }
}
