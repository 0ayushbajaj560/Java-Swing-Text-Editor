import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;



public class UI 
{
	PrintWriter writer;
	static String file_path;
	static String file_name;
	private final ImageIcon red_icon,bi,bli,gi,oi;
	public static JFrame frame;
	
	UI()
	{
		red_icon = new ImageIcon("icons/red.png");
		bi = new ImageIcon("icons/black.png");
		bli = new ImageIcon("icons/blue.png");
		gi = new ImageIcon("icons/green.png");
		oi = new ImageIcon("icons/orange.png");
		
		file_path=file_name=null;
		
		frame = new JFrame();
		frame.setTitle("No Title");
		frame.setSize(800,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JMenuBar menubar = new JMenuBar();
		
		final JTextArea textarea = new JTextArea();
		textarea.setBounds(0,100,800,400);
		textarea.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
		frame.add(textarea);
		
		JMenu menu = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	String line = null;
	             BufferedReader in = null;

	             JFileChooser filechoose = new JFileChooser();
  	        	 int result = filechoose.showOpenDialog(null);
  	        	 
	             if(result == filechoose.APPROVE_OPTION)
	             {
		             try 
		             {
						in = new BufferedReader(new FileReader(filechoose.getSelectedFile()));
					} 
		             catch (FileNotFoundException e) 
		             {
						e.printStackTrace();
					}
		             try 
		             {
						line = in.readLine();
					} 
		             catch (IOException e) 
		             {
						e.printStackTrace();
					}
	
	            	 textarea.setText("");
		             while(line != null)
		             {
		            	 textarea.append(line+"\n");
		                    try 
		                    {
								line = in.readLine();
							}
		                    catch (IOException e) 
		                    {
								e.printStackTrace();
							}
		             }
	             }

	        }
	    });
		menu.add(open);		
		

		
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
	        	
	        	if((!(file_name == null)))
	        	{
		        	FileOutputStream wr;
					try 
					{
						wr = new FileOutputStream(file_path);
						wr.write(("").getBytes());
						String s = textarea.getText();
						wr.write((s).getBytes());
						wr.close();
			        	
					} 
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
		        	
	        	}
	        	else
	        	{
	        		JFileChooser fileChoose = new JFileChooser();
		        	int result = fileChoose.showSaveDialog(null);
		        	/*
		        	//fileChoose.get*/
		        	 if ( fileChoose.APPROVE_OPTION==result) 
		        	 {
		        		 file_path = fileChoose.getSelectedFile()+".txt";
		 	        	file_name = fileChoose.getSelectedFile().getName();
						try 
						{
							writer = new PrintWriter(file_path, "UTF-8");
							String s = textarea.getText();
							writer.println(s);
			        		 writer.close();
			        		 frame.setTitle(file_name);
			        		 System.out.println(file_name);
			        		 System.out.println(file_path);
						} 
						catch (FileNotFoundException e) 
						{
							e.printStackTrace();
						} 
						catch (UnsupportedEncodingException e) 
						{
							e.printStackTrace();
						}
		        	 }
	        	}
        }
    });
		menu.add(save);
		

		JMenuItem save_as = new JMenuItem("Save As");
		save_as.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	JFileChooser fileChoose = new JFileChooser();
	        	int result = fileChoose.showSaveDialog(null);
	        	/*
	        	//fileChoose.get*/
	        	 if ( fileChoose.APPROVE_OPTION==result) 
	        	 {
	        		 file_path = fileChoose.getSelectedFile()+".txt";
	 	        	file_name = fileChoose.getSelectedFile().getName();
					try 
					{
						writer = new PrintWriter(file_path, "UTF-8");
						String s = textarea.getText();
						writer.println(s);
		        		 writer.close();
		        		 frame.setTitle(file_name);
		        		 System.out.println(file_name);
		        		 System.out.println(file_path);
					} 
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					} 
					catch (UnsupportedEncodingException e) 
					{
						e.printStackTrace();
					}
	        		 
	             }
	        	
	        	
        }
    });
		menu.add(save_as);
		

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
	        	frame.dispose();
        }
    });
		
		menu.add(exit);
		
		JMenu menu2 = new JMenu("Edit");
		
		JMenuItem home_add = new JMenuItem("Home Address");
		home_add.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.replaceSelection("");
	        	textarea.insert(" <Home Address> ", textarea.getCaretPosition());
	        }
	    });
		menu2.add(home_add);	
		
		JMenuItem school_add = new JMenuItem("School Address");
		school_add.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.replaceSelection("");
	        	textarea.insert(" <School Address> ", textarea.getCaretPosition());
	        }
	    });
		menu2.add(school_add);	
		
		JMenuItem com_add = new JMenuItem("Company Address");
		com_add.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.replaceSelection("");
	        	textarea.insert(" <Company Address> ", textarea.getCaretPosition());
	        }
	    });
		menu2.add(com_add);	
		
		
		
		JMenu menu3 = new JMenu("Options");
		
		JMenuItem fg = new JMenu("BackGround");
		menu3.add(fg);	
		
		JMenuItem red = new JMenuItem("Red",red_icon);
		red.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setBackground(Color.red);
	        }
	    });
		JMenuItem orange = new JMenuItem("Orange",oi);
		orange.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setBackground(Color.ORANGE);
	        }
	    });
		JMenuItem black = new JMenuItem("Black",bi);
		black.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setBackground(Color.black);
	        }
	    });
		JMenuItem blue = new JMenuItem("Blue",bli);
		blue.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setBackground(Color.blue);
	        }
	    });
		JMenuItem green = new JMenuItem("Green",gi);
		green.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setBackground(Color.GREEN);
	        }
	    });
		fg.add(red);
		fg.add(orange);
		fg.add(black);
		fg.add(blue);
		fg.add(green);
		
		JMenuItem bg = new JMenu("ForeGround");	
		menu3.add(bg);	
		
		JMenuItem fred = new JMenuItem("Red",red_icon);
		fred.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setForeground(Color.red);
	        }
	    });
		JMenuItem forange = new JMenuItem("Orange",oi);
		forange.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setForeground(Color.ORANGE);
	        }
	    });
		JMenuItem fblack = new JMenuItem("Black",bi);
		fblack.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setForeground(Color.black);
	        }
	    });
		
		JMenuItem fblue = new JMenuItem("Blue",bli);
		fblue.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setForeground(Color.blue);
	        }
	    });
		JMenuItem fgreen = new JMenuItem("Green",gi);
		fgreen.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setForeground(Color.GREEN);
	        }
	    });
		bg.add(fred);
		bg.add(forange);
		bg.add(fblack);
		bg.add(fblue);
		bg.add(fgreen);
		
		JMenuItem fonts = new JMenu("Font Size");
		
		JMenuItem f10 = new JMenuItem("10");
		f10.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
	        }
	    });
		
		JMenuItem f12 = new JMenuItem("12");
		f12.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
	        }
	    });
		
		JMenuItem f14 = new JMenuItem("14");
		f14.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
	        }
	    });
		
		JMenuItem f16 = new JMenuItem("16");
		f16.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
	        }
	    });
		
		JMenuItem f18 = new JMenuItem("18");
		f18.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent ev) 
	        {
	        	textarea.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
	        }
	    });
		
		fonts.add(f10);
		fonts.add(f12);
		fonts.add(f14);
		fonts.add(f16);
		fonts.add(f18);
		
		
		menu3.add(fonts);	
		
		menubar.add(menu);
		menubar.add(menu2);
		menubar.add(menu3);
		
		frame.setJMenuBar(menubar);
		frame.validate();
		frame.repaint();
	}
	
	public static void main(String args[])
	{
		UI frame = new UI();
	}
}
