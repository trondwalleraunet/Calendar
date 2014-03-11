package visual;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JPanel {

    private static JFrame mainFrame; 

    private JPanel leftPanel;
    private JPanel checkPanel;
    private JScrollPane checkScrollPane;
    private JTextField searchField;
    private JPanel searchPanel;
    private JScrollPane searchScrollPane;

    public static void main(String[] args) {
    	
    	try {
    	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    	        if ("Nimbus".equals(info.getName())) {
    	            UIManager.setLookAndFeel(info.getClassName());
    	            break;
    	        }
    	    }
    	} catch (Exception e) {
    	    try {
    	        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    	    } catch (Exception ex) {}
    	}
    	
    	
        mainFrame = new JFrame();
        mainFrame.setPreferredSize(new Dimension(1200, 800));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // TODO: Show confirmation dialogue about logging out!
        mainFrame.add(new MainFrame());
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        /* DETTA E EN KOMMENTAR */
    }

    public MainFrame() {
    	addMouseListener(new Listener());
    	setPreferredSize(new Dimension(800, 600));
    	setBackground(Color.white);
		setVisible(true);
		setLayout(new BorderLayout(0,1));
		
		add(createLeftWindow(), BorderLayout.WEST);
    }
    
    
    public JPanel createLeftWindow() {
    	leftPanel = new JPanel();
    	leftPanel.setBorder(BorderFactory.createRaisedBevelBorder());
    	SpringLayout springLayout = new SpringLayout();
    	leftPanel.setLayout(springLayout);
    	leftPanel.setBackground(Color.white);
    	
    	searchField = new visual.CustomJTextField(new JTextField(), "/SEARCH.png", "Search..");
    	searchField.addKeyListener(new Listener());
    	
    	searchField.setPreferredSize(new Dimension(180, 30));
    	
    	Container container = new Container();
    	container.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.gridx = 0; c.gridy = 0; c.ipadx = 20;
    	
    	JCheckBox cb1 = new JCheckBox();
    	cb1.addActionListener(new Listener());
    	cb1.setActionCommand("Andre kalendere");
        HoverLabel cb1Description = new HoverLabel(new JLabel(), new Color(0, 148, 214), Color.white, Color.white, "/check.png");
        cb1Description.setText("Andre kalendere");
        
        JButton nyAvtaleBtn = new JButton("Ny avtale");
        nyAvtaleBtn.setPreferredSize(new Dimension(235, 40));
        
        ImageIcon caret = new ImageIcon(this.getClass().getResource("/concat.png"));
        JButton searchDropDown = new JButton("S�k etter bruker");
        searchDropDown.setActionCommand("Search button");
        searchDropDown.setPreferredSize(new Dimension(180, 30));
        searchDropDown.addActionListener(new Listener());
        searchDropDown.setIcon(caret);
        searchDropDown.setHorizontalTextPosition(SwingConstants.LEFT);
        leftPanel.add(searchDropDown);
        
        createUserSearch();
        
        springLayout.putConstraint(SpringLayout.NORTH, searchDropDown, 40, SpringLayout.SOUTH, nyAvtaleBtn); // OK
        springLayout.putConstraint(SpringLayout.NORTH, container, 30, SpringLayout.SOUTH, searchDropDown); // OK
        springLayout.putConstraint(SpringLayout.NORTH, searchScrollPane, 0, SpringLayout.SOUTH, searchDropDown);

        container.add(cb1Description, c);
        c.gridx = 1;
        container.add(cb1, c);
        
    	leftPanel.add(nyAvtaleBtn);
    	leftPanel.add(searchScrollPane);
        leftPanel.add(container, SpringLayout.EAST);

        searchPanel.add(searchField);
        
        createPersonCheckPanel();
        springLayout.putConstraint(SpringLayout.NORTH, checkScrollPane, 50, SpringLayout.SOUTH, container);        
        leftPanel.add(checkScrollPane);
        leftPanel.setPreferredSize(new Dimension(240, 800));
    	leftPanel.setVisible(true);
    	
    	return leftPanel;
    }
    
    public void createPersonCheckPanel() {
        checkPanel = new JPanel();
        //checkPanel.setPreferredSize(new Dimension(230, 400));
        checkPanel.setBackground(Color.lightGray);
        checkPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        checkPanel.setLayout(new GridBagLayout());
        checkPanel.setVisible(true);
        
    	checkScrollPane = new JScrollPane(checkPanel);
//    	checkScrollPane.getHorizontalScrollBar().setUnitIncrement(16);
    	checkScrollPane.setVisible(false);
    	checkScrollPane.setPreferredSize(new Dimension(230,200));
    }
    
    public void createUserSearch() {
    	searchPanel = new JPanel();
    	searchPanel.setPreferredSize(new Dimension(180, 300));
    	searchPanel.setBackground(Color.white);
    	searchPanel.setVisible(true);
    	searchScrollPane = new JScrollPane(searchPanel);
    	searchScrollPane.setBackground(Color.white);
    	searchScrollPane.setBorder(BorderFactory.createStrokeBorder(new BasicStroke()));
    	searchScrollPane.setVisible(false);
    	searchScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
    
    public class Listener implements ActionListener, KeyListener, MouseListener {
    	
    	@Override
    	public void actionPerformed(ActionEvent e) { // pre requisite that the events comes accomodated with an action command!
    		System.out.println("Action performed");
    		switch(e.getActionCommand()) {
    		case "Search button":
    			searchScrollPane.setVisible(!searchScrollPane.isVisible());
    			mainFrame.repaint();
    			break;
    		case "New Appointment":
    			System.out.println("NEW APPOINTMENT CHOSEN");
    			break;
    		case "Something":
    			System.out.println("Chose something");
    			break;
    		case "Andre kalendere":
    			checkScrollPane.setVisible(!checkScrollPane.isVisible());
    			break;
    		}
    	}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Can not press and hold. This avoids stressing the server unnecessarily
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(!searchScrollPane.isFocusOwner() && searchScrollPane.isVisible()) {
				searchScrollPane.setVisible(false);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    	
    }

}