/*
package com.mycompany25.grafosgui;

import java.awt.*;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class NewMain {


    public static void main(String[] args) {
        
        marco m1=new marco();
        m1.setVisible(true);
        m1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}


class marco extends JFrame{
    public marco(){
        Toolkit gui=Toolkit.getDefaultToolkit();
        
        setSize(500,500);
        setLocation(750,250);
        
        setBounds(750,250,500,500);
        setResizable(false);
        //Pantalla Completa
        //        setExtendedState(Frame.MAXIMIZED_BOTH);
        
        setTitle("Grafos"); 
        Image img1=gui.getImage("/home/alexander/Pictures/favicon.ico");
        setIconImage(img1);
        Lamina l1=new Lamina();
        add(l1);
        
        
    }
    
}

class Lamina extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Dibujar un texto
        //        g.drawString("Learning Swing",100,100);
        
        //Dibujar rectangulo
        g.drawOval(25,25, 50, 50);
        g.drawOval(100,100, 50, 50);
        g.drawLine(50, 50, 125, 125);
        
        //Permita modificar el trazo, rotar. Mejor que las de arriba
        Graphics2D g2=(Graphics2D) g;
        g2.setPaint(Color.RED);
        Rectangle2D rect=new Rectangle2D.Double(100,100,200,150);
        g2.fill(rect);
        
        Ellipse2D eli=new Ellipse2D.Double();
        eli.setFrame(rect);
       
        //fill() -> rellenar de color el shape
        
       
        g2.setPaint(Color.BLUE);
        g2.fill(eli);
        g2.draw(new Line2D.Double(100,100,300,250));
        
        g2.setPaint(new Color(0,255,0).brighter().brighter().brighter().brighter());
        
        double cenX=rect.getCenterX();
        double cenY=rect.getCenterY();
        double radio=150;
        
        Ellipse2D cir=new Ellipse2D.Double();
        cir.setFrameFromCenter(cenX, cenY, cenX+radio, cenY+radio);
        g2.draw(cir);   
        
        //COLORES
        //Graphics2D > setPaint(Color);
        // Color -> Constantes de color
        
        
    }
}


*/