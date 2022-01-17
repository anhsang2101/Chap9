package ex1;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ex1.ConnectDB;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class ex1 implements ActionListener{
	JFrame frame = new JFrame("ComboBox");
	ResultSet rs;
	ResultSetMetaData rstmeta;
	Vector vData=null, vTitle=null; 
	JTable table;
	JComboBox comboBox;
	public ex1() {
		frame.getContentPane().setLayout(new GridLayout(2,1));
		
		ConnectDB s = new ConnectDB();
	    rs = s.SelectDB("Select * from Car");
	    try {
			rstmeta = rs.getMetaData();
			int num_column = rstmeta.getColumnCount();
			
			 vTitle = new Vector(num_column);
			
			 for (int i=1; i<=num_column;i++){
			 vTitle.add(rstmeta.getColumnLabel(i));
			 }
			 
			 vData = new Vector(10,10);
			
			 while (rs.next()){
			 Vector row = new Vector(num_column);
			 for (int i=1; i<=num_column;i++)				
				 row.add(rs.getString(i));				
				 vData.add(row);
			 }			 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    JPanel panel = new JPanel();
	    frame.getContentPane().add(panel);
	    
	    comboBox = new JComboBox();
	    comboBox.addActionListener(this);
	    comboBox.setModel(new DefaultComboBoxModel(new String[] {"Car", "Bicycle", "Motobike"}));
	    panel.add(comboBox);
	    
	    table = new JTable(vData,vTitle);
	    JScrollPane tableResult = new JScrollPane(table);
		
	    frame.getContentPane().add(tableResult);
		frame.pack();
		frame.setLocation(200,30);
		frame.setSize(800,400);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(comboBox.getSelectedItem().toString()=="Car") {
			fillTableCar();
		}
		else if(comboBox.getSelectedItem().toString()=="Motobike") {
			fillTableMotobike();
		}
		else if(comboBox.getSelectedItem().toString()=="Bicycle") {
			fillTableBicycle();
		}
	}
	
	public void fillTableCar(){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for(Car v : getAllCar()) {
			Object dataRow[] = new Object[2];
			dataRow[0] = v.getId();
			dataRow[1] = v.getColor();

			model.addRow(dataRow);
		}
	}
	public void fillTableMotobike() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for(Motobike v : getAllMotobike()) {
			Object dataRow[] = new Object[2];
			dataRow[0] = v.getId();
			dataRow[1] = v.getColor();

			model.addRow(dataRow);
		}
	}
	public void fillTableBicycle() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for(Bicycle v : getAllBicycle()) {
			Object dataRow[] = new Object[2];
			dataRow[0] = v.getId();
			dataRow[1] = v.getColor();

			model.addRow(dataRow);
		}
	}
	
	public List<Car> getAllCar(){
		List<Car> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			String sql = "Select * From Car";
			ConnectDB co = new ConnectDB();
			rs = co.SelectDB(sql);
			while(rs.next()) {
				Car c = new Car();
				c.setId(rs.getInt(1));
				c.setColor(rs.getString(2));
				list.add(c);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public List<Motobike> getAllMotobike(){
		List<Motobike> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			String sql = "Select * From Motobike";
			ConnectDB co = new ConnectDB();
			rs = co.SelectDB(sql);
			while(rs.next()) {
				Motobike c = new Motobike();
				c.setId(rs.getInt(1));
				c.setColor(rs.getString(2));
				list.add(c);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public List<Bicycle> getAllBicycle(){
		List<Bicycle> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			String sql = "Select * From Bicycle";
			ConnectDB co = new ConnectDB();
			rs = co.SelectDB(sql);
			while(rs.next()) {
				Bicycle c = new Bicycle();
				c.setId(rs.getInt(1));
				c.setColor(rs.getString(2));
				list.add(c);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static void main(String[] args) {
		new ex1();
	}
}

class Car{
	int id;
	String color;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", color=" + color + "]";
	}
}
class Motobike{
	int id;
	String color;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Motobike [id=" + id + ", color=" + color + "]";
	}
}
class Bicycle{
	int id;
	String color;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Bicycle [id=" + id + ", color=" + color + "]";
	}
}

