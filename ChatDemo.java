///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
///**
// *
// * @author tanggy
// */
//import java.awt.*;
//import java.awt.event.*;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import javax.swing.*;
//public class ChatDemo implements ActionListener, WindowListener{
//    private JFrame fr;
//    private JPanel pn1, pn2, pn3;
//    private JTextArea txtarea;
//    private JTextField tf;
//    private JButton bt1,bt2;
//    private File ff;
//    private String txt;
//    
//    public ChatDemo(){
//        pn2 = new JPanel();
//        tf = new JTextField(45); 
//        pn3 = new JPanel();
//        bt1 = new JButton("Submit");
//        bt2 = new JButton("Reset");
//        pn1 = new JPanel();
//        txtarea = new JTextArea(20, 45); 
//        fr = new JFrame();
//        
//        
//        fr.setLayout(new BorderLayout());
//        fr.add(pn1, BorderLayout.NORTH); fr.add(pn2, BorderLayout.CENTER); fr.add(pn3, BorderLayout.SOUTH);
//
//        pn1.add(txtarea);
//        txtarea.setEditable(false);
//        
//        pn2.add(tf);
//        pn3.add(bt1); pn3.add(bt2);
//        
//        
//        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        fr.setVisible(true);
//        fr.pack();
//        
//        ff = new File("ChatDemo.dat");
//        try{
//            ff.createNewFile();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//                //Submit
//        bt1.addActionListener(this);
//        bt2.addActionListener(this);
//        fr.addWindowListener(this);
//        
//    }
//    @Override
//    public void actionPerformed(ActionEvent e){
//        if(e.getSource().equals(bt1)){
//            
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//            txtarea.append(dtf.format(LocalDateTime.now()) +" : "+tf.getText()+"\n");
//            tf.setText("");
//        }else if(e.getSource().equals(bt2)){
//            txtarea.setText("");
//            tf.setText("");
//       
//        }
//        
//    }
//   
//    @Override
//    public void windowOpened(WindowEvent e){
//        loadText();
//        txtarea.setText(txt);
//    }
//    @Override
//    public void windowClosing(WindowEvent e){
//        txt = txtarea.getText();
//        saveText(txt);
//    }
//    public void saveText(String txt){
//        try(FileWriter fw = new FileWriter(ff)){
//            for(int i = 0; i < txt.length(); i++)
//                fw.write(txt.charAt(i));
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//    public String loadText(){
//        try(FileReader fr = new FileReader(ff)){
//            int ch;
//            while((ch = fr.read()) != -1){
//                txt += (char) ch;
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        return txt; 
//    }
//    public static void main(String[] args) {
//            new ChatDemo();
//
//    }
//    @Override
//    public void windowClosed(WindowEvent e) {}
//    @Override
//    public void windowIconified(WindowEvent e) {}
//    @Override
//    public void windowDeiconified(WindowEvent e) {}
//    @Override
//    public void windowActivated(WindowEvent e) {}
//    @Override
//    public void windowDeactivated(WindowEvent e) {}
//}

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class ChatDemo implements ActionListener, WindowListener{
    private JFrame fr;
    private JTextArea area;
    private JTextField tex;
    private JPanel p1;
    private JButton submit, reset;
   
    private File file1;
    private String txt;
   
    public ChatDemo(){
        fr = new JFrame();
        fr.setLayout(new BorderLayout());
       
        area = new JTextArea(20,45);
        fr.add(area, BorderLayout.NORTH);
       
        tex = new JTextField(45);
        fr.add(tex,BorderLayout.CENTER);
       
        p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        fr.add(p1,BorderLayout.SOUTH);
       
        submit = new JButton("Submit");
        reset = new JButton("Reset");
        p1.add(submit);
        p1.add(reset);
       
        area.setEditable(false);
       
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.pack();
       
        submit.addActionListener(this);
        reset.addActionListener(this);
        fr.addWindowListener(this);
       
        file1 = new File("ChatDemo.dat");
        try{
            file1.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae){
           if(ae.getSource() == reset){
               area.setText("");
               tex.setText("");
           }else if(ae.getSource() == submit){
               DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
               area.setText(dtf.format(LocalDateTime.now()) +":" + tex.getText() + "\n" + area.getText());
               tex.setText("");
           }
    }
    @Override
    public void windowClosing(WindowEvent we){ //saveFile
        try(FileWriter fwt = new FileWriter("ChatDemo.dat")){
            for(int i = 0; i < txt.length(); i++){
                fwt.write(txt.charAt(i));
            }
            fwt.close();
            System.out.println("Save Successful");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void windowOpened(WindowEvent we){ //loadFile
        try(FileReader frd = new FileReader("ChatDemo.dat")){
            int ch;
            while((ch = frd.read()) != -1){
                txt += ((char)ch);
            }
            area.setText(txt);
            frd.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
   
   
    @Override
    public void windowClosed(WindowEvent we) {}
    @Override
    public void windowIconified(WindowEvent we) {}
    @Override
    public void windowDeiconified(WindowEvent we) {}
    @Override
    public void windowActivated(WindowEvent we) {}
    @Override
    public void windowDeactivated(WindowEvent we) {}
   
    public static void main(String[] args) {
        new ChatDemo();
    }
}