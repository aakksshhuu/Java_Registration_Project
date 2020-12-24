import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class record extends JFrame {

	Connection con1;
	PreparedStatement insert;
	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtcourse;
	private JTextField txtmobile;
	private JTable jTable;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					record frame = new record();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
//////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void table_update()
	{
		int c;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/registration","root","");
			insert=con1.prepareStatement("select * from record");
			ResultSet rs=insert.executeQuery();
			ResultSetMetaData rss=rs.getMetaData();
			c=rss.getColumnCount();
			DefaultTableModel DT=(DefaultTableModel)jTable.getModel();
			DT.setRowCount(0);
			while(rs.next())
			{
				Vector v=new Vector();
				for(int i=1;i<=c;i++)
				{
					v.add(rs.getString("id"));
					v.add(rs.getString("name"));
					v.add(rs.getString("mobile"));
					v.add(rs.getString("course"));
				}
				DT.addRow(v);
			}
		} 
		catch (ClassNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();	
		}
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public record() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Registration");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(220, 0, 242, 39);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 94, 327, 251);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtname = new JTextField();
		txtname.setBounds(131, 32, 145, 20);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtcourse = new JTextField();
		txtcourse.setBounds(131, 127, 145, 20);
		panel.add(txtcourse);
		txtcourse.setColumns(10);
		
		txtmobile = new JTextField();
		txtmobile.setBounds(131, 79, 129, 20);
		panel.add(txtmobile);
		txtmobile.setColumns(5);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(26, 38, 49, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(26, 85, 71, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Course");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(26, 133, 71, 14);
		panel.add(lblNewLabel_3);
		
		
//////////////////////////////////////////////////////////////////////////////////////
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {	
			//add data//
			public void actionPerformed(ActionEvent e) {
				

				String url="jdbc:mysql://localhost:3306/registration";
				String Uname="root";
				String pass="";
				
				String name=txtname.getText();
				String mobile=txtmobile.getText();
				String course=txtcourse.getText();
				
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					con1=DriverManager.getConnection(url,Uname,pass);
					insert=con1.prepareStatement("insert into record(name,mobile,course)values(?,?,?)");
					insert.setString(1, name);
					insert.setString(2, mobile);
					insert.setString(3, course);
					insert.executeUpdate();	
					
					txtname.setText("");
					txtmobile.setText("");
					txtcourse.setText("");
					txtname.requestFocus();
					
				} 
				catch (ClassNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();	
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table_update();
			}
	    });
//////////////////////////////////////////////////////////////////////////////////////		
		
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(38, 192, 71, 31);
		panel.add(btnNewButton);
		
		
///////////////////////////////////////////////////////////////////////////////////////////	
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel DT=(DefaultTableModel)jTable.getModel();
				int selectedIndex=jTable.getSelectedRow();
				try 
				{
					int id=Integer.parseInt(DT.getValueAt(selectedIndex, 0).toString());
					String name=txtname.getText();
					String mobile=txtmobile.getText();
					String course=txtcourse.getText();
					Class.forName("com.mysql.jdbc.Driver");
					con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/registration","root","");
					insert=con1.prepareStatement("update record set name=?,mobile=?,course=? where id=?");
					insert.setString(1, name);
					insert.setString(2, mobile);
					insert.setString(3, course);
					insert.setInt(4,id);
					insert.executeUpdate();
					table_update();
					txtname.setText("");
					txtmobile.setText("");
					txtcourse.setText("");
					txtname.requestFocus();
					
				} 
				catch (ClassNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();	
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
///////////////////////////////////////////////////////////////////////////////
		
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(131, 192, 71, 31);
		panel.add(btnNewButton_1);
//////////////////////////////////////////////////////////////////////////////////
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel DT=(DefaultTableModel)jTable.getModel();
				int selectedIndex=jTable.getSelectedRow();
				try 
				{
					int id=Integer.parseInt(DT.getValueAt(selectedIndex, 0).toString());
					int dialogResult=JOptionPane.showConfirmDialog(null,"Do you want to delete the Record","Warning",JOptionPane.YES_NO_OPTION);
					if(dialogResult==JOptionPane.YES_OPTION)
					{
						Class.forName("com.mysql.jdbc.Driver");
						con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/registration","root","");
						insert=con1.prepareStatement("delete from record where id=?");
						insert.setInt(1,id);
						insert.executeUpdate();
						table_update();
						txtname.setText("");
						txtmobile.setText("");
						txtcourse.setText("");
						txtname.requestFocus();
					}	
				} 
				catch (ClassNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();	
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
/////////////////////////////////////////////////////////////////////////////////////////		
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(224, 192, 89, 31);
		panel.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 50, 382, 319);
		contentPane.add(scrollPane);
		
		jTable = new JTable();
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel DT=(DefaultTableModel)jTable.getModel();
				int selectedIndex=jTable.getSelectedRow();
				txtname.setText(DT.getValueAt(selectedIndex,1).toString());
				txtmobile.setText(DT.getValueAt(selectedIndex,2).toString());
				txtcourse.setText(DT.getValueAt(selectedIndex,3).toString());
				
			}
		});
		jTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sno", "Name", "Mobile", "Course"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(jTable);
		
		JLabel lblNewLabel_4 = new JLabel("Course Registration");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(20, 78, 176, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_3 = new JButton("Data");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_update();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setBounds(640, 29, 89, 23);
		contentPane.add(btnNewButton_3);
	}
}
