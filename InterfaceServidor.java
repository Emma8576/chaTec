/*
Instituto Tecnológico de Costa Rica
Trabajo extraclase #1
Implementación de un chat instantáneo
Estudiantes: Bryan Monge y Emmanuel Calvo
Profesor: Leonardo Araya
Ingeniería en Computadores
II Semestre 2023
 */
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class InterfaceServidor extends JFrame implements ActionListener, Runnable{   
    private JTextArea areaTexto;
    private JLabel label1;
    private JLabel label2;
    JButton boton1;
    JButton boton2;

    /*se establece un constructor y se agrega etiqueta*/
    public InterfaceServidor(){
        setLayout(null);

        /*Se crea componente JTextArea*/
        areaTexto = new JTextArea();
        areaTexto.setBounds(10,70,380,400);
        areaTexto.setEditable(false);
        add(areaTexto);

        /*Se crea Botón de cerrar*/
        Font font = new Font("Arial", Font.BOLD, 30);
        boton1 = new JButton("<");
        boton1.setBounds(0,5,60,30);
        boton1.setFont(font);
        boton1.setMargin(new Insets(0,0,0,0));
        boton1.setFocusPainted(false);
        add(boton1);
        boton1.addActionListener(this);

        /*Se crea el botón de enviar */
        boton2 = new JButton(">");
        boton2.setBounds(320,475,60,30);
        boton2.setFont(font);
        boton2.setMargin(new Insets(0,0,0,0));
        boton2.setFocusPainted(false);
        add(boton2);
        boton2.addActionListener(this);

        /*Se crea etiqueta #1 "chaTec"*/
        label1 = new JLabel("chaTec");  
        label1.setBounds(0,0,400,40);
        label1.setBackground(Color.DARK_GRAY);
        label1.setOpaque(true);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(Color.WHITE);
        label1.setFont(font);
        add(label1);
        
        /*Se crea la etiqueta #2 "servidor" */
        Font font2 = new Font("Arial", Font.BOLD, 18);
        label2 = new JLabel("Servidor");  
        label2.setBounds(0,44,400,18);
        label2.setBackground(Color.LIGHT_GRAY);
        label2.setOpaque(true);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setForeground(Color.BLACK);
        label2.setFont(font2);
        add(label2);

        /*Hilo del run()  */
        Thread mihilo = new Thread(this);
        mihilo.start();

    }

    /*Eventos de los botones*/
    public void actionPerformed(ActionEvent evento){
        if(evento.getSource() == boton1){
            System.exit(0);
        }else if (evento.getSource() == boton2){
            System.out.print("Hola");

        }
    }
   /* Se implementan las configuraciones de la interfaz*/ 
   public static void main(String args[]) {           
        InterfaceServidor interface1 = new InterfaceServidor();         
        interface1.setBounds(0, 0, 400, 550);    
        interface1.setVisible(true);                  
        interface1.setLocationRelativeTo(null);       
        interface1.setResizable(false);       
    }
    /*Se implementa la escucha del servidor para recepción de mensajes */
    public void run(){
        try {
            ServerSocket servidor = new ServerSocket(9999);

            while(true){
            Socket miSocket = servidor.accept();

            DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());

            String mensaje = flujo_entrada.readUTF();
            areaTexto.append("\n" + mensaje);
            miSocket.close();
            }
        }catch (IOException error){
            error.printStackTrace();
        }

    }

}