/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

/**
*
* @author tanggy
*/
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
public class ChatDemo implements ActionListener, WindowListener{
   private JFrame fr;
   private JPanel pn1, pn2, pn3;
   private JTextArea txtarea;
   private JTextField tf;
   private JButton bt1,bt2;
   private File ff;
   private String txt;
   
   public ChatDemo(){
       pn2 = new JPanel();
       tf = new JTextField(45); 
       pn3 = new JPanel();
       bt1 = new JButton("Submit");
       bt2 = new JButton("Reset");
       pn1 = new JPanel();
       txtarea = new JTextArea(20, 45); 
       fr = new JFrame();
       
       
       fr.setLayout(new BorderLayout());
       fr.add(pn1, BorderLayout.NORTH); fr.add(pn2, BorderLayout.CENTER); fr.add(pn3, BorderLayout.SOUTH);

       pn1.add(txtarea);
       txtarea.setEditable(false);
       
       pn2.add(tf);
       pn3.add(bt1); pn3.add(bt2);
       
       
       fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       fr.setVisible(true);
       fr.pack();
       
       ff = new File("ChatDemo.dat");
       try{
           ff.createNewFile();
       }catch(IOException e){
           e.printStackTrace();
       }
               //Submit
       bt1.addActionListener(this);
       bt2.addActionListener(this);
       fr.addWindowListener(this);
       
   }
   @Override
   public void actionPerformed(ActionEvent e){
       if(e.getSource().equals(bt1)){
           
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
           txtarea.append(dtf.format(LocalDateTime.now()) +" : "+tf.getText()+"\n");
           tf.setText("");
       }else if(e.getSource().equals(bt2)){
           txtarea.setText("");
           tf.setText("");
      
       }
       
   }
  
   @Override
   public void windowOpened(WindowEvent e){
       loadText();
       txtarea.setText(txt);
   }
   @Override
   public void windowClosing(WindowEvent e){
       txt = txtarea.getText();
       saveText(txt);
   }
   public void saveText(String txt){
       try(FileWriter fw = new FileWriter(ff)){
           for(int i = 0; i < txt.length(); i++)
               fw.write(txt.charAt(i));
       }catch(IOException e){
           e.printStackTrace();
       }
   }
   public String loadText(){
       try(FileReader fr = new FileReader(ff)){
           int ch;
           while((ch = fr.read()) != -1){
               txt += (char) ch;
           }
       }catch(IOException e){
           e.printStackTrace();
       }
       return txt; 
   }
   public static void main(String[] args) {
           new ChatDemo();

   }
   @Override
   public void windowClosed(WindowEvent e) {}
   @Override
   public void windowIconified(WindowEvent e) {}
   @Override
   public void windowDeiconified(WindowEvent e) {}
   @Override
   public void windowActivated(WindowEvent e) {}
   @Override
   public void windowDeactivated(WindowEvent e) {}
}

