package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;

/*
 * 爆炸类
 */
public class Explode {
    double x,y;
    static Image[] imgs = new Image[15];
    static {
        for(int i=0;i<15;i++){
            imgs[i] = GameUtil.getImage("images/e"+(i+1)+".png");
            imgs[i].getWidth(null);
        }
    }

    int count;

    public void draw(Graphics g){
        if(count<=14){
            g.drawImage(imgs[count], (int)x, (int)y, null);
            count++;
        }
    }

    public Explode(double x,double y){
        this.x = x;
        this.y = y;
    }
}