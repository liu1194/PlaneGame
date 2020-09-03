package cn.sxt.game;

import sun.awt.windows.WFontConfiguration;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;


public class MyGameFrame extends Frame {

    //双缓冲防止闪烁
    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    Image bgImg = GameUtil.getImage("images/desk2.jpg");
    Image planeImg = GameUtil.getImage("images/plane3.png");

//    Image a = GameUtil.getImage("images/e3.png");

    Plane plane = new Plane(planeImg,382,400);

    Shell shell = new Shell();
    //50发炮弹
//    Shell[] shells = new Shell[50];
    ArrayList<Shell>  shellList = new ArrayList<Shell>();


    //boom
    Explode bao;

    //计时
    Date startTime = new Date();    //游戏起始时刻
    Date endTime;  //游戏结束时刻
    int period = 0;

    //paint方法作用是：画出整个窗口及内部内容。被系统自动调用。
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.drawImage(bgImg, 0, 0, null);
        //画飞机
        plane.drawSelf(g);

        //画出容器中所有的子弹
        for(int i=0;i<shellList.size();i++){
            Shell b =  shellList.get(i);
            b.draw(g);

            //飞机和所有炮弹对象进行矩形检测
            boolean peng = b.getRect().intersects(plane.getRect());
            if(peng){
                plane.live = false;   //飞机死掉,画面不显示
                if(bao==null){
                    bao = new Explode(plane.x,plane.y);
                    endTime = new Date();
                    period = (int) ((endTime.getTime() - startTime.getTime())/1000);
                }

                bao.draw(g);
            }
            if (!plane.live){
                g.setColor(Color.RED);
                Font f = new Font("宋体",Font.BOLD,50);
                g.setFont(f);
                g.drawString("时间:"+period+"秒",(int) ((Constant.FRAME_WIDTH-100)/2),(int) ((Constant.FRAME_HEIGHT-100)/2));
            }
        }
        g.setColor(c);
    }

    //内部类.创建线程反复重画窗口
    class PaintThread extends Thread{
        @Override
        public void run() {
            while (true){
//                System.out.println("窗口被画了一次");
                repaint();
                try {
                    Thread.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //内部类 键盘监听
    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }
    /*
    *  初始化飞机游戏的主窗口
    */
    public void launchFrame(){
        //在游戏窗口打印标题
        setTitle("PlaneGame");
        //窗口大小：宽度500，高度500
        setSize(865, 500);
        //窗口左上角顶点的坐标位置
        setLocation(300, 100);
        //窗口默认不可见，设为可见
        setVisible(true);
        //增加关闭窗口监听，这样用户点击右上角关闭图标，可以关闭游戏程序
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start();
        //启动键盘监听
        addKeyListener(new KeyMonitor());

        //初始化5个炮弹
        for(int i=0;i<5;i++){
            Shell b = new Shell();
            shellList.add(b);
        }

    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
}
