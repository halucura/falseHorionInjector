package falseHorion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HorionInjector extends JDialog {

    private JPanel contentPane;
    private Font MINECRAFTIA;
    private int mouseX, mouseY;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HorionInjector dialog = new HorionInjector();
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HorionInjector() {
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setUndecorated(true);
        contentPane = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getXOnScreen();
                mouseY = e.getYOnScreen();
            }
        });
        contentPane.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getXOnScreen() - mouseX;
                int deltaY = e.getYOnScreen() - mouseY;
                setLocation(getX() + deltaX, getY() + deltaY);
                mouseX = e.getXOnScreen();
                mouseY = e.getYOnScreen();
            }
        });
        setContentPane(contentPane);
        contentPane.setLayout(null);
        try {
            MINECRAFTIA = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("minecraftia.ttf"));
            MINECRAFTIA = MINECRAFTIA.deriveFont(Font.PLAIN, 20);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        RoundButton btnNewButton = new RoundButton("inject");
        btnNewButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "create by halucura ^^", null, JOptionPane.PLAIN_MESSAGE);
                try {
					@SuppressWarnings("unused")
					Process process = Runtime.getRuntime().exec("powershell -Command \"Start-Process 'explorer.exe' -ArgumentList 'shell:AppsFolder\\Microsoft.MinecraftUWP_8wekyb3d8bbwe!App'\"");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });
        btnNewButton.setFont(MINECRAFTIA);
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBounds(130, 192, 181, 52);
        contentPane.add(btnNewButton);
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(HorionInjector.class.getResource("/falseHorion/horion-banner.png")));
        lblNewLabel.setBounds(91, 38, 255, 130);
        contentPane.add(lblNewLabel);
        JLabel lblNewLabel_1 = new JLabel("v1.1.2");
        lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 15));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(12, 260, 88, 30);
        contentPane.add(lblNewLabel_1);
        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setIcon(new ImageIcon(HorionInjector.class.getResource("/falseHorion/x.png")));
        btnNewButton_1.addMouseListener(new MouseAdapter() {
  
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        btnNewButton_1.setBounds(407, 10, 31, 30);
        contentPane.add(btnNewButton_1);
        btnNewButton_1.setOpaque(false);
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setBorderPainted(false);
    }
    public static class RoundButton extends JButton {
        public RoundButton(String text) {
            super(text);
            setPreferredSize(new Dimension(181, 52));
            setContentAreaFilled(false);
        }
        protected void paintComponent(Graphics g) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.GREEN);
            int width = getSize().width - 1;
            int height = getSize().height - 1;
            int arcSize = 30; 
            g.fillRoundRect(0, 0, width, height, arcSize, arcSize);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
        }
    }
}