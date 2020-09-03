package cn.sxt.game;

import java.awt.*;

/*
游戏物体根类
 */
public class GameObject {
    Image image;
    double x, y;
    int height, width;
    int speed;

    public void drawSelf(Graphics g){
        g.drawImage(image,(int)x,(int)y,null);

    }

    public GameObject(Image image, double x, double y, int height, int width, int speed) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
    }

    public GameObject(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public GameObject() {
    }

    public Rectangle rectangle(){
        return new Rectangle((int)x,(int)y,height,width);
    }

    /**
     * 返回物体对应矩形区域，便于后续在碰撞检测中使用
     * @return
     */
    public Rectangle getRect(){
        return  new Rectangle((int)x,(int) y, width, height);
    }
}
