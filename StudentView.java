/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tanggy
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class StudentView implements ActionListener, WindowListener{
    private JFrame fr;
    private JPanel pn1, pn2;
    private JLabel id, name, money;
    private JTextField tf1, tf2,tf3;
    private JButton depo, wit;
    public Student std;
    private File fil;
    
    public StudentView(){
        fr = new JFrame();
        pn1 = new JPanel();
        pn2 = new JPanel();
//        pn3 = new JPanel();
//        pn4 = new JPanel();
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        depo = new JButton("Deposit");
        wit = new JButton("Withdraw");
        id = new JLabel("ID : ");
        name = new JLabel("Name : ");
        money = new JLabel("Money : ");
        std = new Student();
        
        fil = new File("StudentM.dat");
        try{
            fil.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        fr.setLayout(new BorderLayout());
        pn1.setLayout(new GridLayout(3,2));
        pn2.setLayout(new FlowLayout());
        
        pn1.add(id); pn1.add(tf1);
        pn1.add(name); pn1.add(tf2);
        pn1.add(money); pn1.add(tf3);
        pn2.add(depo); pn2.add(wit);
        
        tf3.setEditable(false);

        fr.add(pn1, BorderLayout.NORTH); fr.add(pn2, BorderLayout.CENTER);
        depo.addActionListener(this);
        wit.addActionListener(this);
        
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.pack();
        fr.addWindowListener(this);
    }
    public static void main(String[] args) {
        new StudentView();
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(depo)){
            std.setMoney(std.getMoney()+100);
        }else if(e.getSource().equals(wit)){
            if(std.getMoney() < 0){
                std.setMoney(0);
            }else
                std.setMoney(std.getMoney()-100);
        }
        
        std.setID(Integer.parseInt(tf1.getText()));
        std.setName(tf2.getText());
        std.setMoney(Integer.parseInt(tf3.getText()));
        tf3.setText(std.getMoney()+"");
    } 
    @Override
    public void windowClosing(WindowEvent we){
        try(FileOutputStream fout = new FileOutputStream("StudentM.dat")){

            ObjectOutputStream objout = new ObjectOutputStream(fout);
            objout.writeObject(std);
           
            objout.close(); 
            fout.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void windowOpened(WindowEvent wo){
        try(FileInputStream fin = new FileInputStream("StudentM.dat")){

            ObjectInputStream objin = new ObjectInputStream(fin);
            
            std =(Student) objin.readObject();
            
            objin.close();
            fin.close();
            
            tf1.setText(std.getID()+"");
            tf2.setText(std.getName());
            tf3.setText(std.getMoney()+"");
            
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException c){
            c.printStackTrace();
        }
        
    }
    public void windowClosed(WindowEvent we){}
    @Override
    public void windowIconified(WindowEvent we){}
    @Override
    public void windowDeiconified(WindowEvent we){}
    @Override
    public void windowActivated(WindowEvent we){}
    @Override
    public void windowDeactivated(WindowEvent we){}
}
