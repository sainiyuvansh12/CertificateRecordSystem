package shopcertificate.ui;

import shopcertificate.dao.UserDAO;
import shopcertificate.db.DatabaseConnection;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final DatabaseConnection db;
    private final JTextField username=new JTextField("admin",18);
    private final JPasswordField password=new JPasswordField(18);
    public LoginFrame(DatabaseConnection db){
        this.db=db;setTitle("Student Certificate Record System");setDefaultCloseOperation(EXIT_ON_CLOSE);setSize(850,520);setLocationRelativeTo(null);setResizable(false);
        JPanel root=new JPanel(new GridLayout(1,2));root.setBackground(new Color(247,246,242));
        JPanel intro=new JPanel();intro.setLayout(new BoxLayout(intro,BoxLayout.Y_AXIS));intro.setBorder(BorderFactory.createEmptyBorder(82,54,42,42));intro.setBackground(new Color(37,76,76));
        JLabel mark=new JLabel("ACADEMIC SERVICES  /  RECORDS OFFICE");mark.setForeground(new Color(220,196,151));mark.setFont(new Font("Segoe UI",Font.BOLD,12));
        JLabel title=new JLabel("Student Certificate");title.setForeground(Color.WHITE);title.setFont(new Font("Segoe UI",Font.PLAIN,29));
        JLabel title2=new JLabel("Record System");title2.setForeground(Color.WHITE);title2.setFont(new Font("Segoe UI",Font.BOLD,30));
        JLabel desc=new JLabel("<html>Certificate request and issuance tracking<br>for institutional records.</html>");desc.setForeground(new Color(221,231,225));desc.setFont(new Font("Segoe UI",Font.PLAIN,15));
        intro.add(mark);intro.add(Box.createVerticalStrut(48));intro.add(title);intro.add(title2);intro.add(Box.createVerticalStrut(20));intro.add(desc);intro.add(Box.createVerticalGlue());
        JPanel form=new JPanel(new GridBagLayout());form.setBackground(new Color(247,246,242));GridBagConstraints g=new GridBagConstraints();g.gridx=0;g.anchor=GridBagConstraints.WEST;g.insets=new Insets(8,0,8,0);
        JLabel heading=new JLabel("Office sign in");heading.setFont(new Font("Segoe UI",Font.BOLD,24));g.gridy=0;form.add(heading,g);
        JLabel sub=new JLabel("Advanced Java Programming Lab");sub.setForeground(new Color(115,115,110));g.gridy++;form.add(sub,g);
        g.gridy++;g.insets=new Insets(24,0,4,0);form.add(label("Username"),g);g.gridy++;g.insets=new Insets(4,0,14,0);form.add(username,g);
        g.gridy++;g.insets=new Insets(4,0,4,0);form.add(label("Password"),g);g.gridy++;g.insets=new Insets(4,0,18,0);form.add(password,g);
        JButton login=new JButton("Sign in");login.setBackground(new Color(37,76,76));login.setForeground(Color.WHITE);login.setFocusPainted(false);login.setPreferredSize(new Dimension(245,40));g.gridy++;g.fill=GridBagConstraints.HORIZONTAL;g.insets=new Insets(5,0,8,0);form.add(login,g);
        JLabel credit=new JLabel("Developed by Yuvansh Saini | PIET24CR033");credit.setFont(new Font("Segoe UI",Font.PLAIN,10));credit.setForeground(new Color(130,130,125));g.gridy++;g.insets=new Insets(26,0,0,0);form.add(credit,g);
        login.addActionListener(e->signIn());password.addActionListener(e->signIn());add(root);root.add(intro);root.add(form);
    }
    private JLabel label(String s){JLabel l=new JLabel(s);l.setFont(new Font("Segoe UI",Font.PLAIN,13));return l;}
    private void signIn(){try{if(new UserDAO(db).authenticate(username.getText().trim(),new String(password.getPassword()))){new MainFrame(db,username.getText().trim()).setVisible(true);dispose();}else JOptionPane.showMessageDialog(this,"The username or password was not recognized.","Sign in",JOptionPane.WARNING_MESSAGE);}catch(Exception ex){JOptionPane.showMessageDialog(this,"Cannot connect to the certificate database. Check MySQL and the connection settings.\n\n"+ex.getMessage(),"Database connection",JOptionPane.ERROR_MESSAGE);}}
}

