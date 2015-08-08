import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class MyGame extends JFrame
{
Vector v =  new Vector();
JLabel la = new  JLabel();
JLabel y1;JLabel x1;

JLabel l1 = new  JLabel();
JLabel l2 = new  JLabel();
JLabel l3 = new  JLabel();
JLabel l4 = new  JLabel();
JLabel l5;
JLabel l6;
Container c;
static int ch,g1,g2,g3,g4;
JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER,2,3));
Thread t =  Thread.currentThread();
public void scoreCard()
{
if(l4.getBounds().intersects(l5.getBounds()))
{
  x = (int)(Math.random()*260);
  y = (int)(Math.random()*260);
  l6=new JLabel();
  l6.setBounds(((JLabel)(v.get(0))).getBounds());
  l6.setOpaque(true);
  l6.setBackground(Color.GREEN);
  v.add(0,l6);
  //l5.setBackground(Color.blue);
  c.add(l6);
  l5.setLocation(x,y);
    if((v.size()%5==4))
     { l5.setSize(13,10);
       l5.setBackground(Color.BLUE);
        x1.setText("    "+(Integer.parseInt(x1.getText().trim())+10)+"    ");}
    else
     { l5.setSize(10,8);
       l5.setBackground(Color.BLACK);
        x1.setText("    "+(Integer.parseInt(x1.getText().trim())+5)+"    ");}
}
}
public void changeCod(int x,int y)
{  int i;
   for(i=0;i<v.size()-1;i++)
    ((JLabel)(v.elementAt(i))).setLocation(((JLabel)(v.elementAt(i+1))).getX(),((JLabel)(v.elementAt(i+1))).getY());
    ((JLabel)(v.elementAt(i))).setLocation(((JLabel)(v.elementAt(i))).getX()+x,((JLabel)(v.elementAt(i))).getY()+y);
}
/*public void autoChangeCod(int x,int y)
{  changeCod(x,y);
   try{  AutoRightMove.sleep(500); }
       catch(Exception ex) {}
}*/
public void gameOver()
{
 int i=0;
  if(!(la.getBounds().contains(l4.getBounds())))
   {  JOptionPane.showMessageDialog(null,"Game Over","Snake & Frog",JOptionPane.QUESTION_MESSAGE);
      System.exit(0);}
  for(i=0;i<v.size()-1;i++)
   {   if(l4.getBounds().intersects(((JLabel)(v.get(i))).getBounds()))
       {   JOptionPane.showMessageDialog(null,"Game Over","Snake & Frog",JOptionPane.QUESTION_MESSAGE);
           System.exit(0);}
   }
}
int x=0,y=0;
public MyGame()
{
c = super.getContentPane();
c.setLayout(null);
super.setBounds(800,100,300,400);
c.setBackground(Color.lightGray);

p.setBounds(0,310,300,75);
c.add(p);

l1.setBounds(20,20,10,8);
l1.setBackground(Color.CYAN);
l1.setOpaque(true);
c.add(l1);
v.add(l1);

l2.setBounds(33,20,10,8);
l2.setBackground(Color.black);
l2.setOpaque(true);
c.add(l2);
v.add(l2);

l3.setBounds(46,20,10,8);
l3.setBackground(Color.black);
l3.setOpaque(true);
c.add(l3);
v.add(l3);

l4.setBounds(59,20,10,8);
l4.setBackground(Color.red);
l4.setOpaque(true);
c.add(l4);
v.add(l4);

l5 = new  JLabel();
l5.setBounds(200,20,10,8);
l5.setBackground(Color.black);
l5.setOpaque(true);
c.add(l5);

la.setBounds(0,0,300,300);
//la.setBackground(Color.YELLOW);
//la.setOpaque(true);
    c.add(la);
     p.setBackground(Color.blue);
     y1= new JLabel("  SCORE  " );
     y1.setBackground(Color.MAGENTA);
     y1.setOpaque(true);
     y1.setFont(new Font("",Font.BOLD,20));
     x1= new JLabel("      0      " );
     x1.setBackground(Color.ORANGE);
     x1.setOpaque(true);
     x1.setFont(new Font("",Font.BOLD,20));
     p.add(y1);
     p.add(x1);

super.addKeyListener(new ArrowAction());
super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
super.setVisible(true);
}
class ArrowAction extends KeyAdapter
{
public void keyPressed(KeyEvent ke)
{
ch=ke.getKeyCode();
if(ch==39)
{   if(l4.getX() > l3.getX())
       if(g1==0)
            new AutoRightMove();
    if(l4.getX() == l3.getX())
    {    changeCod(13,0);
                new AutoRightMove();  }
}
else if(ch==37)
{ if(l4.getX() < l3.getX())
   if(g2==0)
    new AutoLeftMove();
  if(l4.getX() == l3.getX())
   { changeCod(-13,0);  new AutoLeftMove();     }
}
else if(ch==40)
{
 if(l4.getY() >l3.getY())
 if(g4==0)
    new AutoDownMove();
 if(l4.getY()== l3.getY())
 { changeCod(0,10);   new AutoDownMove();  }

}
else if(ch==38)
{
  if(l4.getY() < l3.getY())
  if(g3==0)
    new AutoUpMove();
 if(l4.getY()== l3.getY())
  {   changeCod(0,-10);  new AutoUpMove();}
}
gameOver();
}
public int getch()
{
    return ch;
}
public void keyTyped(KeyEvent ke){}
}
class  AutoRightMove extends Thread
{
 AutoRightMove() { super.start();}
 public void run()
 { while(l4.getX() > l3.getX())
   {  scoreCard();  gameOver();
    changeCod(13,0);
   try{  AutoRightMove.sleep(300); }
       catch(Exception ex) {}
       g1++; }
 }
}
class  AutoLeftMove extends Thread
{
 AutoLeftMove() { super.start();}
 public void run()
 {  while(l4.getX() < l3.getX())
   {  scoreCard();  gameOver(); changeCod(-13,0);
   try{  AutoLeftMove.sleep(300); }
       catch(Exception ex) {}
       g2++;     }
 }
}
class  AutoUpMove extends Thread
{
 AutoUpMove() { super.start();}
 public void run()
 {  while(l4.getY() < l3.getY())
   {  scoreCard();  gameOver();
      changeCod(0,-10);
   try{  AutoUpMove.sleep(300); }
       catch(Exception ex) {}
       g3++; }
 }
}
class  AutoDownMove extends Thread
{
 AutoDownMove() { super.start();}
 public void run()
 {  while(l4.getY() > l3.getY())
   {  scoreCard();  gameOver(); changeCod(0,10);
   try{  AutoDownMove.sleep(300); }
       catch(Exception ex) {}
        g4++; }
 }
}
public static void main(String[] args)
{ new MyGame();}
}