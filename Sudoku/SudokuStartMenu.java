package Sudoku;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;


public class SudokuStartMenu extends JFrame{

    public static void main(String[] args) throws Exception {
        
        JFrame frm = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frm.setMinimumSize(new Dimension(800,700));
        
        JButton btnStart = new JButton("Start");
       
        frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frm.setMinimumSize(new Dimension(650,650));

        int btnHeight = 50;
        int btnWidth = 300;

        // background.setBackground(BG_GIVEN);  


        // INI UNTUK NARUH BACKGROUND KALAU ADA YANG MAU DITAMBAHIN

        BufferedImage bgImage = ImageIO.read(new File("Sudoku/bg-sudoku.png"));
        Image scaledBgImage = bgImage.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        ImageIcon backgroundImage = new ImageIcon(scaledBgImage);
        JLabel background = new JLabel(backgroundImage);
        background.setIcon(backgroundImage);
        background.setHorizontalAlignment(JLabel.CENTER);

        JMenuItem menuItem;
        JDialog aboutDeveloper;
        JTextArea textArea;
        

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("                                         Menu");
        menu.setSize(1000, 50);
        int menuHeight = 50;
        int menuWidth = 300;
        menu.setPreferredSize(new Dimension(menuWidth, menuHeight)); // Mengatur ukuran
        

        menuItem = new JMenuItem("                              About Developer");
        menuItem.setPreferredSize(menu.getPreferredSize());
        aboutDeveloper = new JDialog();
        Icon developerImage = new ImageIcon("foto-fp.jpg");
        JLabel labelImage = new JLabel(developerImage); 
        menuItem.addActionListener(e -> {
           aboutDeveloper.setVisible(true);
        });
        
        menu.add(menuItem);
        menuBar.add(menu);
        frm.add(menuBar);
      
        // Initialize the about menu
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JLabel labelAbout = new JLabel("Capstone Project - Group #3 (2.0 Version)");
        String aboutText = "This Sudoku and TicTacToe game is part of the final project assignment for the Data Structure and Algorithms course. Sudoku is created by applying the DFS algorithm and the Stack data structure.\n\n"+
                 "The following is our project team:\n" +
                 "1. Andika Cahya Sutisna (5026221013)\n" +
                 "2. Muhammad Ahdaf Amali (5026221129)\n" +
                 "3. Putu Panji Wiradharma (5026221170)\n";

        textArea.setText(aboutText);
        textArea.setBackground(aboutDeveloper.getBackground());
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        aboutDeveloper.setLayout(new BorderLayout());
        aboutDeveloper.add(labelImage, BorderLayout.NORTH);
        aboutDeveloper.add(labelAbout, BorderLayout.CENTER);
        aboutDeveloper.add(textArea, BorderLayout.CENTER);
        aboutDeveloper.setSize(500, 300);
        aboutDeveloper.setLocationRelativeTo(null);
        aboutDeveloper.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        

        JLabel Copyright = new JLabel("KELOMPOK 3");
        Copyright.setFont(new Font("Poppins", Font.PLAIN,30));
        Copyright.setForeground(Color.BLACK);
        Copyright.setOpaque(true);

        btnStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        new Main();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                });

            }
            
        });

        frm.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                Dimension size = frm.getSize();
                Dimension CopyrightSize = Copyright.getPreferredSize();
                int height = (int) size.getHeight();
                int width = (int) size.getWidth();

                background.setBounds(0, 0, width, height);
                btnStart.setBounds((width * 1 / 2) - (btnWidth / 2), (height * 3 / 4) - (btnHeight / 2), btnWidth, btnHeight);
                menuBar.setBounds((width * 1 / 2) - (menuWidth / 2), (height - menuHeight - 25)* 7/8, menuWidth, menuHeight);

                Copyright.setBounds((width - CopyrightSize.width ) / 2, height * 7 /8, CopyrightSize.width, CopyrightSize.height);
            }
        });

        // btnStart.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         System.exit(0);

        //     }
        // });

        // Play sound when play game
        try{
        Sound music  = new Sound("words.wav");
        music.play();
        }catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }

        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frm.getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - frm.getHeight()) / 2);

        frm.add(Copyright);
        // frame.add(logo);
        frm.add(btnStart);
        frm.add(background);

        frm.setLayout(null);
        frm.setVisible(true);
    }
}