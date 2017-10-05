package pl.kti.dbdemo.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pl.kti.dbdemo.data.DBManager;
import pl.kti.dbdemo.data.ResultSetTableModel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

public class DBDemoFrame extends JFrame {
	private static final long serialVersionUID = -123123123124141L;
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	JFrame app_frame = new DBDemoFrame();
    	app_frame.setVisible(true);

    }
    
	
	private JTextArea _editor;
	private JButton _sendButton; // srednik Izy
	// table - orderproducts //
		
	// table - orders //
	private JButton _newOrder;
	private JButton _AllOrders;
	private JButton _FindOrder;
	private JButton _RemoveOrder;
	// table - invoices // 
	private JButton _NewInvoice;
	private JButton _AllInvoices;
	private JButton _FindInvoice;
	private JButton _RemoveInvoice;
	// table - products //
	private JButton _newProduct;
	private JButton _AllProducts;
	private JButton _FindProduct;
	private JButton _RemoveProduct;
	//table - newWarehouse // 
	private JButton _newWarehouse;
	private JButton _AllWarehouses;
	private JButton _FindWarehouse;
	private JButton _RemoveWarehouse;
	//table - productorders //
	private JButton _AllOrderProduct;
	private JButton _FindOrderProduct;
	private JButton _NewOrderProduct;
	private JButton _RemoveOrderProduct;
	private JButton _AllInWareHouse;
	
	private JTable _table;
	private ResultSetTableModel _tableModel;
	private JLabel label;
	private JLabel lblInvoices;
	private JLabel lblProducts;
	private JLabel lblWarehouses;
	private JLabel lblOrders;

	
	public DBDemoFrame() { 
		super("PRODUCT MANAGMENT");
		getContentPane().setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		
		initGUI();
	}

	private void initGUI() {
		
		// query area
		_editor = new JTextArea("");
		_editor.setFont(new Font("Ariel", 0, 12));
		_editor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JScrollPane editorScroll = new JScrollPane();
		editorScroll.setViewportView(_editor);
		editorScroll.setPreferredSize(new Dimension(100, 100));
		
		// data table
		_tableModel = new ResultSetTableModel(null);
		_table = new JTable(_tableModel);
		_table.setRowSelectionAllowed(true);
        _table.setColumnSelectionAllowed(false);
		JScrollPane tableScroll = new JScrollPane(_table);
        
        //BUTTONS & HANDLERS//
        
		_sendButton = new JButton("Send query");
		_sendButton.setBackground(new Color(211, 211, 211));
		// handler 
		_sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendQuery(_editor.getText());
			}
		}); // end - Send Query
		
// table - orders //
		
		 _newOrder = new JButton("New order");
		 _newOrder.setBackground(new Color(211, 211, 211));
			//handler
		 _newOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg4) {
				JTextField field1 = new JTextField();
				JTextField field2 = new JTextField();
				JTextField field3 = new JTextField();
				JTextField field4 = new JTextField();
				JTextField field5 = new JTextField();
				Object[] message = {
				    "order's ID:", field1,
				    "order date (RRRR/MM/DD):", field2,
				    "name:", field3,
				    "last name:", field4,
				    "shipped date (RRRR/MM/DD):", field5,
				};
				int option = JOptionPane.showConfirmDialog(getParent(), message, "New order", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
				    String value1 = field1.getText();
				    String value2 = field2.getText();
				    String value3 = field3.getText();
				    String value4 = field4.getText();
				    String value5 = field5.getText();
				    
				    String NewOrder = (value1 +", '"+ value2 +"', '"+ value3 +"', '"+ value4 +"', '"+ value5 +"'"); 
					//
					String Commend = "INSERT INTO sql8167592.orders ( ID_order , order_date , name_order , lastname_order , shippeddate_order ) VALUES ( " + NewOrder + " ) ;";
					sendQuery(Commend);

					String CommendAll = "SELECT * FROM sql8167592.orders";
					sendQuery(CommendAll);
				    
				}

			}
			
		});
				
		_AllOrders = new JButton("Show All Orders");
		_AllOrders.setBackground(new Color(211, 211, 211));
			//handler
		_AllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg2) {
				String Commend= "SELECT * FROM sql8167592.orders";
				sendQuery(Commend);
			}
		}); //end - pokaz wszystkie zamowienia
		
		_FindOrder = new JButton("Find order");
		_FindOrder.setBackground(new Color(211, 211, 211));
		//handler
		_FindOrder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg4){
				JDialog dialog = new JDialog();
				dialog.setTitle("Find order...");
				dialog.setSize(316, 100); //316,100
				dialog.setLocation(800,100);
				dialog.getContentPane().setLayout(null);

				JButton _ID = new JButton("by ID");
				_ID.setBackground(new Color(211, 211, 211));
				_ID.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg4){
						JOptionPane PopUp = new JOptionPane();
						String query = PopUp.showInputDialog("Please input order's ID");
						PopUp.setValue(query);
						String Commend = "SELECT * FROM orders WHERE ID_order=" + query;
						sendQuery(Commend);
						
					}
				});
				_ID.setBounds(160, 20, 120, 23);
				dialog.getContentPane().add(_ID);
				dialog.setVisible(true);
				
				JButton _LastName = new JButton("by Last Name");
				_LastName.setBackground(new Color(211, 211, 211));
				_LastName.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg4){
						JOptionPane PopUp = new JOptionPane();
						String query = PopUp.showInputDialog("Please input client's last name");
						PopUp.setValue(query);
						String Commend = "SELECT * FROM orders WHERE lastname_order = '" +query + "'";
						sendQuery(Commend);
						
					}
				});
				_LastName.setBounds(20, 20, 120, 23);
				dialog.getContentPane().add(_LastName);
				dialog.setVisible(true);
			}
		}); //end - znajdz zamowienie o numerze		
		
		_RemoveOrder = new JButton("Remove order");
		_RemoveOrder.setBackground(new Color(211, 211, 211));
		//handler
		_RemoveOrder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg4){
				JOptionPane PopUp= new JOptionPane();
				String query = PopUp.showInputDialog("Please input order's id");
				PopUp.setValue(query);
				String Commend = "DELETE FROM orders WHERE ID_order=" + query;
				sendQuery(Commend);
			}
		}); //end - usun zamowienie o numerze		
		
// table - invoices //
		
		_NewInvoice = new JButton("New Invoice"); // Iza naprawila srednik
		_NewInvoice.setBackground(new Color(211, 211, 211));
			//handler
		_NewInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg4) {
				int index = _table.getSelectedRow();
				TableModel model = _table.getModel();
				JTextField field1 = new JTextField();
				JTextField field2 = new JTextField();
				JTextField field3 = new JTextField();
				JTextField field4 = new JTextField();
				JTextField field5 = new JTextField();
				JTextField field6 = new JTextField();
				JTextField field7 = new JTextField();
				JTextField field8 = new JTextField();
				JTextField field9 = new JTextField();
				Object[] message = {
				    "invoice's ID:", field1,
				    "name:", field2,
				    "lastname:", field3,
				    "city:", field4,
				    "post code:", field5,
				    "street:", field6,
				    "street number:", field7,
				    "date (RRRR/MM/DD):", field8,
				    "order's ID:", field9,				    
				    
				};
				int row = _table.getSelectedRow();
				if(row > -1){
					String value9 = _table.getModel().getValueAt(row, 0).toString();
					field9.setText(value9);
					
					String value2 = _table.getModel().getValueAt(row, 2).toString();
					field2.setText(value2);
					
					String value3 = _table.getModel().getValueAt(row, 3).toString();
					field3.setText(value3);
				
				}
				int option = JOptionPane.showConfirmDialog(getParent(), message, "New invoice", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
					String value1 = field1.getText();
				    String value2 = field2.getText();
				    String value3 = field3.getText();
				    String value4 = field4.getText();
				    String value5 = field5.getText();
				    String value6 = field6.getText();
				    String value7 = field7.getText();
				    String value8 = field8.getText();
					String value9 = field8.getText();

				    String NewInvoice = value1 +", '"+ value2 +"', '"+ value3 +"', '"+ value4 +"', '"+ value5 +"', '"+ value6+"', "+ value7 + ",'"+ value8 + "'," + value9+ ", (SELECT SUM(product_amount) AS finalprice_invoice FROM orderproducts WHERE ID_order=" + value9 +")"; 
					//
					String Commend = "INSERT INTO invoices ( ID_invoice , name_invoice , lastname_invoice , city_invoice, postcode_invoice , street_invoice, streetnumber_invoice, date_invoice, ID_order, finalprice_invoice ) VALUES ( " + NewInvoice + ");";
					sendQuery(Commend);

					String CommendAll = "SELECT * FROM invoices";
					sendQuery(CommendAll);
				    
				}

			}
			
		}); //end - nowa faktura
		
		_AllInvoices = new JButton("Show All Invoices");
		_AllInvoices.setBackground(new Color(211, 211, 211));
			//handler
		_AllInvoices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg4){
				String Commend= "SELECT * FROM invoices";
				sendQuery(Commend);
			}
		}); //end - wszystkie faktury
		
		_FindInvoice = new JButton("Find invoice");
		_FindInvoice.setBackground(new Color(211, 211, 211));
			//handler
		_FindInvoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg4){
				JDialog dialog = new JDialog();
				dialog.setTitle("Find Invoice...");
				dialog.setSize(316, 100); //316,100
				dialog.setLocation(800,100);
				dialog.getContentPane().setLayout(null);

				JButton _ID = new JButton("by invoice's ID");
				_ID.setBackground(new Color(211, 211, 211));
				_ID.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg4){
						JOptionPane PopUp = new JOptionPane();
						String query = PopUp.showInputDialog("Please input invoice's ID");
						PopUp.setValue(query);
						String Commend = "SELECT * FROM invoices WHERE ID_invoice=" + query;
						sendQuery(Commend);
						
					}
				});
				_ID.setBounds(160, 20, 120, 23);
				dialog.getContentPane().add(_ID);
				dialog.setVisible(true);
				
				JButton _IDOrder = new JButton("by order's ID");
				_IDOrder.setBackground(new Color(211, 211, 211));
				_IDOrder.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg4){
						JOptionPane PopUp = new JOptionPane();
						String query = PopUp.showInputDialog("Please input order's ID");
						PopUp.setValue(query);
						String Commend = "SELECT * FROM invoices WHERE ID_order = " +query;
						sendQuery(Commend);
						
					}
				});
				_IDOrder.setBounds(20, 20, 120, 23);
				dialog.getContentPane().add(_IDOrder);
				dialog.setVisible(true);
			}
		}); //end - znajdz fakture
	
		_RemoveInvoice = new JButton("Remove invoice");
		_RemoveInvoice.setBackground(new Color(211, 211, 211));
		//handler
		_RemoveInvoice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg4){
				JOptionPane PopUp = new JOptionPane();
				String query = PopUp.showInputDialog("Please input invoice's id");
				PopUp.setValue(query);
				String Commend = "DELETE FROM invoices WHERE ID_invoice=" + query;
				sendQuery(Commend);
			}
		}); //end - usun zamowienie o numerze
		
// table - products //
		
		_newProduct = new JButton("New product");
		_newProduct.setBackground(new Color(211, 211, 211));
			//handler
		_newProduct.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg4) {
			JTextField field1 = new JTextField();
			JTextField field2 = new JTextField();
			JTextField field3 = new JTextField();
			JTextField field4 = new JTextField();
			JTextField field5 = new JTextField();
			Object[] message = {
			    "product's ID:", field1,
			    "product name:", field2,
			    "quanitity:", field3,
			    "unit price (format: 0.0):", field4,
			    "warehouse's ID:", field5,
			};
			int row = _table.getSelectedRow();
			if(row > -1){
				String value5 = _table.getModel().getValueAt(row, 0).toString();
				field5.setText(value5);			
			}
			int option = JOptionPane.showConfirmDialog(getParent(), message, "New product", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION)
			{
			    String value1 = field1.getText();
			    String value2 = field2.getText();
			    String value3 = field3.getText();
			    String value4 = field4.getText();
			    String value5 = field5.getText();
			    
			    String NewProduct = (value1 +", '"+ value2 +"', '"+ value3 +"', '"+ value4 +"', "+ value5); 
				//
				String Commend = "INSERT INTO products ( ID_product , name_product , quanitity_product , unit_price , ID_warehouse ) VALUES ( " + NewProduct + " ) ;";
				sendQuery(Commend);

				String CommendAll = "SELECT * FROM products";
				sendQuery(CommendAll);
			    
			}

		}
		
	});
					
		_AllProducts = new JButton("Show All products");
		_AllProducts.setBackground(new Color(211, 211, 211));
			//handler
		_AllProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg2) {
				String CommendAll = "SELECT * FROM products";
				sendQuery(CommendAll);
			}
		 }); //end - pokaz wszystkie produkty
				
		_FindProduct = new JButton("Find product");
		_FindProduct.setBackground(new Color(211, 211, 211));
			//handler
		_FindProduct.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg4){
					JDialog dialog = new JDialog();
					dialog.setTitle("Find product...");
					dialog.setSize(316, 100); //316,100
					dialog.setLocation(800,100);
					dialog.getContentPane().setLayout(null);

					JButton _ID = new JButton("by ID");
					_ID.setBackground(new Color(211, 211, 211));
					_ID.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg4){
							JOptionPane PopUp = new JOptionPane();
							String query = PopUp.showInputDialog("Please input product's ID");
							PopUp.setValue(query);
							String Commend = "SELECT * FROM products WHERE ID_product=" + query;
							sendQuery(Commend);
							
						}
					});
					_ID.setBounds(160, 20, 120, 23);
					dialog.getContentPane().add(_ID);
					dialog.setVisible(true);
					
					JButton _Name = new JButton("by name");
					_Name.setBackground(new Color(211, 211, 211));
					_Name.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg4){
							JOptionPane PopUp = new JOptionPane();
							String query = PopUp.showInputDialog("Please input product's name (Please, use CAPS LOCK)");
							PopUp.setValue(query);
							String Commend = "SELECT * FROM products WHERE name_product = '" +query + "'";
							sendQuery(Commend);
						}
					});
					_Name.setBounds(20, 20, 120, 23);
					dialog.getContentPane().add(_Name);
					dialog.setVisible(true);
				}
			}); //end - znajdz product
		
		_RemoveProduct = new JButton("Remove product");
		_RemoveProduct.setBackground(new Color(211, 211, 211));
		//handler
		_RemoveProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg4){
				JOptionPane PopUp = new JOptionPane();
				String query = PopUp.showInputDialog("Please input product's id");
				PopUp.setValue(query);
				String Commend = "DELETE FROM products WHERE ID_product=" + query;
				sendQuery(Commend);
			}
		}); //end - usun produkt o numerze
		
// table - warehouses //
		
		_newWarehouse = new JButton("New warehouse");
		_newWarehouse.setBackground(new Color(211, 211, 211));
			//handler
		_newWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				JTextField field1 = new JTextField();
				JTextField field2 = new JTextField();
				JTextField field3 = new JTextField();
				JTextField field4 = new JTextField();
				JTextField field5 = new JTextField();
				JTextField field6 = new JTextField();
				Object[] message = {
				    "warehouse's ID:", field1,
				    "warehouse's name:", field2,
				    "city:", field3,
				    "postcode:", field4,
				    "street:", field5,
				    "street code:", field6,
				};
				int option = JOptionPane.showConfirmDialog(getParent(), message, "New warehouse", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
				    String value1 = field1.getText();
				    String value2 = field2.getText();
				    String value3 = field3.getText();
				    String value4 = field4.getText();
				    String value5 = field5.getText();
				    String value6 = field6.getText();
				    String query = value1 +", '"+ value2 +"', '"+ value3 +"', '"+ value4 +"', '"+ value5 +"', '"+ value6+"'" ; 
					//
					String Commend = "INSERT INTO warehouses ( ID_warehouse , name_warehouse , city_warehouse , postcode_warehouse, street_warehouse , code_warehouse ) VALUES ( " + query +  " ) ;";
					sendQuery(Commend);
					// update product quanitity
					// add order and show all
					String CommendAll = "SELECT * FROM warehouses";
					sendQuery(CommendAll);
				    
				}

			}
		 }); //end - dodaj magazyn
					
		_AllWarehouses = new JButton("Show All warehouses");
		_AllWarehouses.setBackground(new Color(211, 211, 211));
			//handler
		_AllWarehouses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg2) {
				String CommendAll= "SELECT * FROM warehouses";
				sendQuery(CommendAll);
			}
		 }); //end - pokaz wszystkie produkty
				
		_FindWarehouse = new JButton("Find warehouse");
		_FindWarehouse.setBackground(new Color(211, 211, 211));
			//handler
		_FindWarehouse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg4){
				JDialog dialog = new JDialog();
				dialog.setTitle("Find warehouse...");
				dialog.setSize(316, 100); //316,100
				dialog.setLocation(800,100);
				dialog.getContentPane().setLayout(null);

				JButton _ID = new JButton("by ID");
				_ID.setBackground(new Color(211, 211, 211));
				_ID.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg4){
						JOptionPane PopUp = new JOptionPane();
						String query = PopUp.showInputDialog("Please input warehouse's ID");
						PopUp.setValue(query);
						String Commend = "SELECT * FROM warehouses WHERE ID_warehouse=" + query;
						sendQuery(Commend);
						
					}
				});
				_ID.setBounds(160, 20, 120, 23);
				dialog.getContentPane().add(_ID);
				dialog.setVisible(true);
				
				JButton _City = new JButton("by city");
				_City.setBackground(new Color(211, 211, 211));
				_City.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg4){
						JOptionPane PopUp = new JOptionPane();
						String query = PopUp.showInputDialog("Please input city (Please, don't use polish letters)");
						PopUp.setValue(query);
						String Commend = "SELECT * FROM warehouses WHERE city_warehouse = '" +query + "'";
						sendQuery(Commend);
					}
				});
				_City.setBounds(20, 20, 120, 23);
				dialog.getContentPane().add(_City);
				dialog.setVisible(true);
			}
		}); //end - znajdz hurtownie

		_RemoveWarehouse = new JButton("Remove warehouse");
		_RemoveWarehouse.setBackground(new Color(211, 211, 211));
			//handler
		_RemoveWarehouse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg4){
				JOptionPane PopUp = new JOptionPane();
				String query = PopUp.showInputDialog("Please input warehouse's id");
				PopUp.setValue(query);
				String Commend = "DELETE FROM warehouses WHERE ID_warehouse=" + query;
				sendQuery(Commend);
			}
		}); //end - usun magazyn o numerze

		_AllInWareHouse = new JButton("All products in warehouse");
		_AllInWareHouse.setBackground(new Color(211, 211, 211));
		_AllInWareHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane PopUp = new JOptionPane();
				String query = PopUp.showInputDialog("Please, input warehouse's ID");
				PopUp.setValue(query);
				String Commend = "SELECT * FROM products WHERE ID_warehouse =" + query;
				sendQuery(Commend);
			
			}
		});
		
//table - orderproducts //
		
		_AllOrderProduct = new JButton("Show all orders' details");
		_AllOrderProduct.setBackground(new Color(211, 211, 211));
		_AllOrderProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane PopUp = new JOptionPane();
				String query = PopUp.showInputDialog("Please, input order's ID");
				PopUp.setValue(query);
				String Commend = "SELECT * FROM orderproducts WHERE ID_order =" + query;
				sendQuery(Commend);
			
			}
		});
		
		_FindOrderProduct = new JButton("Find order detail");
		_FindOrderProduct.setBackground(new Color(211, 211, 211));
		_FindOrderProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setTitle("Find product...");
				dialog.setSize(316, 100); //316,100
				dialog.setLocation(800,100);
				dialog.getContentPane().setLayout(null);

				JButton _ID = new JButton("by ID");
				_ID.setBackground(new Color(211, 211, 211));
				_ID.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg4){
						JOptionPane PopUp = new JOptionPane();
						String query = PopUp.showInputDialog("Please input order details' ID");
						PopUp.setValue(query);
						String Commend = "SELECT * FROM orderproducts WHERE ID_orderproduct=" + query;
						sendQuery(Commend);
						
					}
				});
				_ID.setBounds(160, 20, 120, 23);
				dialog.getContentPane().add(_ID);
				dialog.setVisible(true);
				
				JButton _Product = new JButton("by product's ID");
				_Product.setBackground(new Color(211, 211, 211));
				_Product.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg4){
						JOptionPane PopUp = new JOptionPane();
						String query = PopUp.showInputDialog("Please input product's ID");
						PopUp.setValue(query);
						String Commend = "SELECT * FROM orderproducts WHERE ID_product = " +query ;
						sendQuery(Commend);
					}
				});
				_Product.setBounds(20, 20, 120, 23);
				dialog.getContentPane().add(_Product);
				dialog.setVisible(true);
			
			}
		});
		
		_NewOrderProduct = new JButton("New order detail");
		_NewOrderProduct.setBackground(new Color(211, 211, 211));
		_NewOrderProduct.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg1) {
			JTextField field1 = new JTextField();
			JTextField field2 = new JTextField();
			JTextField field3 = new JTextField();
			JTextField field4 = new JTextField();
			Object[] message = {
			    "orderproduct's ID:", field1,
			    "order's ID:", field2,
			    "product's ID:", field3,
			    "quantity:", field4,
			};
			int row = _table.getSelectedRow();
			if(row > -1){
				String value2 = _table.getModel().getValueAt(row, 0).toString();
				field2.setText(value2);			
			}
			int option = JOptionPane.showConfirmDialog(getParent(), message, "New order detail", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION)
			{
			    String value1 = field1.getText();
			    String value2 = field2.getText();
			    String value3 = field3.getText();
			    String value4 = field4.getText();
			    String query = value1 +", "+ value2 +", "+ value3 +", '"+ value4 +"', ((" + value4 +")*(SELECT unit_price FROM products WHERE ID_product = "+ value3 +"))";
				//
			    String NewOrderProduct = "INSERT INTO orderproducts ( ID_orderproducts , ID_order , ID_product , quantity_order, product_amount ) VALUES ( " + query +  " ) ;";
				sendQuery(NewOrderProduct);
				// update product quanitity
			
				String CommendUpdate = "UPDATE products SET IFNULL(quanitity_product = quanitity_product - "+value4+",0) WHERE ID_product = "+ value3 +";";
				sendQuery(CommendUpdate);
				// add order and show all
				String CommendAll = "SELECT * FROM orderproducts";
				sendQuery(CommendAll);
			    
			}

		}
	 });
		
		_RemoveOrderProduct = new JButton("Remove order detail");
		_RemoveOrderProduct.setBackground(new Color(211, 211, 211));
		_RemoveOrderProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg4){
				JOptionPane PopUp = new JOptionPane();
				String query = PopUp.showInputDialog("Please input order's id");
				PopUp.setValue(query);
				String Commend = "DELETE FROM orderproducts WHERE ID_orderproducts=" + query;
				sendQuery(Commend);
			}
		});
		
			
		label = new JLabel("");
		
		lblOrders = new JLabel("Orders");
		lblOrders.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblInvoices = new JLabel("Invoices");
		lblInvoices.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblProducts = new JLabel("Products");
		lblProducts.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblWarehouses = new JLabel("Warehouese");
		lblWarehouses.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(492)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(editorScroll, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(_sendButton, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
						.addComponent(tableScroll, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(_AllProducts, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(_NewInvoice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(_AllOrderProduct, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(_AllOrders, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(_newOrder, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(_NewOrderProduct, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(_AllInvoices, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(_newProduct, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(_RemoveInvoice, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(_FindOrderProduct, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(_RemoveOrder, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(_FindOrder, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(_FindInvoice, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(_FindProduct, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(_RemoveProduct, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(_RemoveOrderProduct, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(_AllInWareHouse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
								.addComponent(_newWarehouse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
								.addComponent(_AllWarehouses, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(_FindWarehouse, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addComponent(_RemoveWarehouse, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
						.addComponent(lblInvoices, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProducts, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWarehouses, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrders, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(_sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(editorScroll, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tableScroll, GroupLayout.PREFERRED_SIZE, 871, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(lblOrders)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(_AllOrders, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(_FindOrder))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(_newOrder)
								.addComponent(_RemoveOrder))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(_FindOrderProduct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(_AllOrderProduct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(_NewOrderProduct)
								.addComponent(_RemoveOrderProduct))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblInvoices)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(_AllInvoices)
								.addComponent(_FindInvoice))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(_NewInvoice)
								.addComponent(_RemoveInvoice))
							.addGap(11)
							.addComponent(lblProducts)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(_AllProducts)
								.addComponent(_FindProduct))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(_newProduct)
								.addComponent(_RemoveProduct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(11)
							.addComponent(lblWarehouses)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(_FindWarehouse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(_AllWarehouses, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(_newWarehouse)
								.addComponent(_RemoveWarehouse))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(_AllInWareHouse)
							.addGap(532)))
					.addGap(46)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}
		
	
	//end - initGUI
public void sendQuery(String query) {
	try {
		if (query.length() > 6 && 
			( 
				query.substring(0, 6).equalsIgnoreCase("insert") || 
				query.substring(0, 6).equalsIgnoreCase("update") ||
				query.substring(0, 6).equalsIgnoreCase("delete") 
			)
		) {
				Statement stmt = DBManager.getConnection().createStatement();
				stmt.executeUpdate(query);
			    _tableModel.setResultSet(null);
			    stmt.close();
			} else if ((query.length() > 6) && (query.substring(0,6).equalsIgnoreCase("select"))){
				// create SQL statement and execute query read from the _editor
				Statement stmt = DBManager.getConnection().createStatement();
				ResultSet queryResult = stmt.executeQuery(query);
				// pass the resultSet to the table model (use setResultSet method from the model)
				_tableModel.setResultSet(queryResult);
				// close the resultSet and statement
				queryResult.close();
			    stmt.close();
			}
			else
			{
				Statement stmt = DBManager.getConnection().createStatement();
				stmt.execute(query);
			    stmt.close();
			}
		} catch (Exception e) {
			_editor.setText(e.getMessage());
		}
	}
}
