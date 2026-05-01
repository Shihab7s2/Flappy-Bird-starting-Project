import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame implements ActionListener {

    JLabel title, contactLbl, userLbl, passLbl;
    JTextField contactTxt, userTxt;
    JPasswordField passTxt;
    JButton btnCreate;

    UserManager um = new UserManager();

    public RegisterFrame() {

     setTitle("Register Form");
    setSize(400, 320);
     setLayout(null);
     setLocationRelativeTo(null);

     title = new JLabel("Register Here");
     title.setFont(new Font("Arial", Font.BOLD, 20));
     title.setBounds(120, 15, 200, 30);

     contactLbl = new JLabel("Email / Phone:");
    contactLbl.setBounds(30, 70, 120, 25);

     contactTxt = new JTextField();
     contactTxt.setBounds(150, 70, 180, 25);

    userLbl = new JLabel("Username:");
    userLbl.setBounds(30, 110, 100, 25);

     userTxt = new JTextField();
     userTxt.setBounds(150, 110, 180, 25);

     passLbl = new JLabel("Password:");
     passLbl.setBounds(30, 150, 100, 25);

    passTxt = new JPasswordField();
     passTxt.setBounds(150, 150, 180, 25);

     btnCreate = new JButton("Create");
     btnCreate.setBounds(130, 210, 120, 30);
     btnCreate.addActionListener(this);

     add(title);
     add(contactLbl);
     add(contactTxt);
    add(userLbl);       
     add(userTxt);
     add(passLbl);
     add(passTxt);
     add(btnCreate);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String contact = contactTxt.getText();
        String user = userTxt.getText();
        String pass = new String(passTxt.getPassword());

        if(contact.equals("") || user.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(this, "Fill all fields first!"); }
        else { if(um.userExists(user)) {
                JOptionPane.showMessageDialog(this, "This username is taken!");
            }
            else { um.registerUser(contact, user, pass);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                dispose();
            }
        }
    }
}