/*
 * AA.java
 *
 * Created on __DATE__, __TIME__
 */

package com.darkmi.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.apache.log4j.PropertyConfigurator;

import com.darkmi.Function;
import com.darkmi.ReadProperty;
import com.darkmi.SaveProperty;


/**
 * 
 * @author __USER__
 */
public class Test extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7392249897580632997L;

	/** Creates new form AA */
	public Test() {
		initComponents();
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	@SuppressWarnings("serial")
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jTextField7 = new javax.swing.JTextField();
		jTextField8 = new javax.swing.JTextField();
		jTextField13 = new javax.swing.JTextField();
		jTextField14 = new javax.swing.JTextField();
		jCheckBox1 = new javax.swing.JCheckBox();
		jCheckBox2 = new javax.swing.JCheckBox();
		jPanel10 = new javax.swing.JPanel();
		jLabel25 = new javax.swing.JLabel();
		jTextField19 = new javax.swing.JTextField();
		jLabel26 = new javax.swing.JLabel();
		jTextField20 = new javax.swing.JTextField();
		jLabel27 = new javax.swing.JLabel();
		jTextField21 = new javax.swing.JTextField();
		jLabel28 = new javax.swing.JLabel();
		jTextField22 = new javax.swing.JTextField();
		jTextField23 = new javax.swing.JTextField();
		jLabel29 = new javax.swing.JLabel();
		jTextField24 = new javax.swing.JTextField();
		jLabel30 = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
		jCheckBox7 = new javax.swing.JCheckBox();
		jPanel8 = new javax.swing.JPanel();
		jTextField5 = new javax.swing.JTextField();
		jTextField6 = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel22 = new javax.swing.JLabel();
		jLabel24 = new javax.swing.JLabel();
		jLabel23 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jPanel5 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jTextField9 = new javax.swing.JTextField();
		jTextField10 = new javax.swing.JTextField();
		jTextField15 = new javax.swing.JTextField();
		jTextField16 = new javax.swing.JTextField();
		jCheckBox3 = new javax.swing.JCheckBox();
		jCheckBox4 = new javax.swing.JCheckBox();
		jPanel11 = new javax.swing.JPanel();
		jLabel31 = new javax.swing.JLabel();
		jTextField25 = new javax.swing.JTextField();
		jLabel32 = new javax.swing.JLabel();
		jTextField26 = new javax.swing.JTextField();
		jLabel33 = new javax.swing.JLabel();
		jTextField27 = new javax.swing.JTextField();
		jLabel34 = new javax.swing.JLabel();
		jTextField28 = new javax.swing.JTextField();
		jTextField29 = new javax.swing.JTextField();
		jLabel35 = new javax.swing.JLabel();
		jTextField30 = new javax.swing.JTextField();
		jLabel36 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		jCheckBox8 = new javax.swing.JCheckBox();
		jPanel7 = new javax.swing.JPanel();
		jPanel9 = new javax.swing.JPanel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jTextField11 = new javax.swing.JTextField();
		jTextField12 = new javax.swing.JTextField();
		jTextField17 = new javax.swing.JTextField();
		jTextField18 = new javax.swing.JTextField();
		jCheckBox5 = new javax.swing.JCheckBox();
		jCheckBox6 = new javax.swing.JCheckBox();
		jPanel12 = new javax.swing.JPanel();
		jLabel37 = new javax.swing.JLabel();
		jTextField31 = new javax.swing.JTextField();
		jLabel38 = new javax.swing.JLabel();
		jTextField32 = new javax.swing.JTextField();
		jLabel39 = new javax.swing.JLabel();
		jTextField33 = new javax.swing.JTextField();
		jLabel40 = new javax.swing.JLabel();
		jTextField34 = new javax.swing.JTextField();
		jTextField35 = new javax.swing.JTextField();
		jLabel41 = new javax.swing.JLabel();
		jTextField36 = new javax.swing.JTextField();
		jLabel42 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		jCheckBox9 = new javax.swing.JCheckBox();
		jPanel13 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jPanel14 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		jLabel43 = new javax.swing.JLabel();
		jLabel44 = new javax.swing.JLabel();
		jTextField4 = new javax.swing.JTextField();
		jTextField37 = new javax.swing.JTextField();
		jTextField38 = new javax.swing.JTextField();
		jPanel15 = new javax.swing.JPanel();
		jLabel46 = new javax.swing.JLabel();
		jLabel47 = new javax.swing.JLabel();
		jTextField40 = new javax.swing.JTextField();
		jTextField41 = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jLabel48 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		jTabbedPane1.setAutoscrolls(true);

		jPanel1.setAutoscrolls(true);

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("IVR\u6d4b\u8bd5"));
		jPanel3.setToolTipText("");

		jLabel7.setText("IP\uff1a");

		jLabel8.setText("Port\uff1a");

		jLabel13.setText("\u767b\u9646\u6d4b\u8bd5\u6570\u91cf\uff1a");

		jLabel14.setText("\u70b9\u64ad\u6d4b\u8bd5\u6570\u91cf\uff1a");

		jTextField7.setText("192.168.14.61");
		jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				jTextField7FocusLost(evt);
			}
		});

		jTextField8.setText("5000");

		jTextField13.setText("3");

		jTextField14.setText("3");

		jCheckBox1.setSelected(true);
		jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox1ActionPerformed(evt);
			}
		});

		jCheckBox2.setSelected(true);
		jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel4Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel4Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														jPanel4Layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(jLabel7).addComponent(jLabel8)
																.addComponent(jLabel13)).addComponent(jLabel14))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel4Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jTextField14).addComponent(jTextField7)
												.addComponent(jTextField13).addComponent(jTextField8))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jCheckBox1).addComponent(jCheckBox2))
								.addContainerGap(7, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel4Layout
								.createSequentialGroup()
								.addGroup(
										jPanel4Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel7)
												.addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel4Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel8))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel4Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel13)
												.addComponent(jCheckBox1))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel4Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														jPanel4Layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jTextField14,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jLabel14))
												.addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));

		jLabel25.setText("\u8d77\u59cb\u7535\u8bdd\u53f7\u7801\uff1a");

		jTextField19.setText("13000000008");

		jLabel26.setText("\u7535\u8bdd\u6b65\u957f\uff1a");

		jTextField20.setText("1");

		jLabel27.setText("\u8d77\u59cbCA\u5361\u53f7\uff1a");

		jTextField21.setText("2000000000000080");

		jLabel28.setText("CA\u5361\u53f7\u6b65\u957f\uff1a");

		jTextField22.setText("1");

		jTextField23.setText("13000000008");

		jLabel29.setText("\u7ec8\u6b62\u7535\u8bdd\u53f7\u7801\uff1a");

		jTextField24.setText("2000000000000080");

		jLabel30.setText("\u7ec8\u6b62CA\u5361\u53f7\uff1a");

		javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
		jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout
				.setHorizontalGroup(jPanel10Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel10Layout
										.createSequentialGroup()
										.addGroup(
												jPanel10Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel10Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel10Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel10Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel25)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField19,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												138,
																												Short.MAX_VALUE))
																						.addGroup(
																								jPanel10Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel29)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField23,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												138,
																												Short.MAX_VALUE))
																						.addGroup(
																								jPanel10Layout
																										.createSequentialGroup()
																										.addGap(24, 24,
																												24)
																										.addComponent(
																												jLabel26)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField20,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												138,
																												Short.MAX_VALUE))))
														.addGroup(
																jPanel10Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel10Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel10Layout
																										.createSequentialGroup()
																										.addGap(20, 20,
																												20)
																										.addComponent(
																												jLabel27))
																						.addGroup(
																								jPanel10Layout
																										.createSequentialGroup()
																										.addGap(22, 22,
																												22)
																										.addGroup(
																												jPanel10Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel28)
																														.addComponent(
																																jLabel30))))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel10Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jTextField22,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								138, Short.MAX_VALUE)
																						.addComponent(
																								jTextField24,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								138, Short.MAX_VALUE)
																						.addComponent(
																								jTextField21,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								138, Short.MAX_VALUE))))
										.addContainerGap()));
		jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel10Layout
								.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(
										jPanel10Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel25)
												.addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel10Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel29)
												.addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel10Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel26))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel10Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel27)
												.addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel10Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel30)
												.addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel10Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel28)
												.addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))));

		jLabel19.setText("\u542f\u52a8IVR\u6d4b\u8bd5\uff1a");

		jCheckBox7.setSelected(true);
		jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox7ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addGap(18, 18, 18)
																		.addComponent(jLabel19)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(jCheckBox7))
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(jPanel4,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(24, Short.MAX_VALUE))
						.addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel3Layout
								.createSequentialGroup()
								.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(35, 35, 35)
								.addGroup(
										jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jCheckBox7).addComponent(jLabel19)).addContainerGap()));

		jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("\u901a\u7528\u8bbe\u7f6e"));

		jTextField5.setText("2");

		jTextField6.setText("10");

		jLabel6.setText("\u603b\u6d4b\u8bd5\u65f6\u95f4(\u5206\u949f)\uff1a");

		jLabel5.setText("\u8d85\u65f6\u65f6\u95f4(\u79d2)\uff1a");

		jLabel22.setText("\u8ddd\u79bb\u7ed3\u675f\u6d4b\u8bd5\u8fd8\u6709\uff1a");

		jLabel24.setText("00000");

		jLabel23.setText("\u5206\u949f");

		jButton1.setText("\u5f00\u59cb\u6d4b\u8bd5");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("\u505c\u6b62\u6d4b\u8bd5");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout
				.setHorizontalGroup(jPanel8Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel8Layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																										.addGroup(
																												jPanel8Layout
																														.createSequentialGroup()
																														.addGap(8,
																																8,
																																8)
																														.addComponent(
																																jLabel5)
																														.addGap(18,
																																18,
																																18)
																														.addComponent(
																																jTextField5,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																82,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGroup(
																												jPanel8Layout
																														.createSequentialGroup()
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																														.addComponent(
																																jLabel6)
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																														.addComponent(
																																jTextField6)))
																						.addGroup(
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel22)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jLabel24)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jLabel23))))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addGap(22, 22, 22)
																		.addComponent(jButton1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(jButton2)))
										.addContainerGap(28, Short.MAX_VALUE)));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel8Layout
								.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(
										jPanel8Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel5)
												.addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(16, 16, 16)
								.addGroup(
										jPanel8Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6)
												.addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel22).addComponent(jLabel23).addComponent(jLabel24))
								.addGap(26, 26, 26)
								.addGroup(
										jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton1).addComponent(jButton2))));

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("PhoneRemote"));
		jPanel5.setToolTipText("");

		jLabel9.setText("IP\uff1a");

		jLabel10.setText("Port\uff1a");

		jLabel15.setText("\u767b\u9646\u6d4b\u8bd5\u6570\u91cf\uff1a");

		jLabel16.setText("\u70b9\u64ad\u6d4b\u8bd5\u6570\u91cf\uff1a");

		jTextField9.setEditable(false);
		jTextField9.setText("192.168.14.61");
		jTextField9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField9ActionPerformed(evt);
			}
		});

		jTextField10.setEditable(false);
		jTextField10.setText("5001");

		jTextField15.setEditable(false);
		jTextField15.setText("30");

		jTextField16.setEditable(false);
		jTextField16.setText("40");

		jCheckBox3.setEnabled(false);
		jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox3ActionPerformed(evt);
			}
		});

		jCheckBox4.setEnabled(false);
		jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox4ActionPerformed(evt);
			}
		});

		jLabel31.setText("\u8d77\u59cb\u7535\u8bdd\u53f7\u7801\uff1a");

		jTextField25.setText("13000000008");

		jLabel32.setText("\u7535\u8bdd\u6b65\u957f\uff1a");

		jTextField26.setText("1");

		jLabel33.setText("\u8d77\u59cbCA\u5361\u53f7\uff1a");

		jTextField27.setText("2000000000000080");

		jLabel34.setText("CA\u5361\u53f7\u6b65\u957f\uff1a");

		jTextField28.setText("1");

		jTextField29.setText("13000000008");

		jLabel35.setText("\u7ec8\u6b62\u7535\u8bdd\u53f7\u7801\uff1a");

		jTextField30.setText("2000000000000080");

		jLabel36.setText("\u7ec8\u6b62CA\u5361\u53f7\uff1a");

		javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
		jPanel11.setLayout(jPanel11Layout);
		jPanel11Layout
				.setHorizontalGroup(jPanel11Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel11Layout
										.createSequentialGroup()
										.addGroup(
												jPanel11Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel11Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel11Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel11Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel31)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField25,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												135,
																												Short.MAX_VALUE))
																						.addGroup(
																								jPanel11Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel35)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField29,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												135,
																												Short.MAX_VALUE))
																						.addGroup(
																								jPanel11Layout
																										.createSequentialGroup()
																										.addGap(24, 24,
																												24)
																										.addComponent(
																												jLabel32)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField26,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												135,
																												Short.MAX_VALUE))))
														.addGroup(
																jPanel11Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel11Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel11Layout
																										.createSequentialGroup()
																										.addGap(20, 20,
																												20)
																										.addComponent(
																												jLabel33))
																						.addGroup(
																								jPanel11Layout
																										.createSequentialGroup()
																										.addGap(22, 22,
																												22)
																										.addGroup(
																												jPanel11Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel34)
																														.addComponent(
																																jLabel36))))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel11Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jTextField28,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								135, Short.MAX_VALUE)
																						.addComponent(
																								jTextField30,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								135, Short.MAX_VALUE)
																						.addComponent(
																								jTextField27,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								135, Short.MAX_VALUE))))
										.addContainerGap()));
		jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel11Layout
								.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(
										jPanel11Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel31)
												.addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel11Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel35)
												.addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel11Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel32))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel11Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel33)
												.addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel11Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel36)
												.addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel11Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel34)
												.addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))));

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel6Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(
																				jPanel6Layout
																						.createSequentialGroup()
																						.addGap(48, 48, 48)
																						.addGroup(
																								jPanel6Layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																										.addComponent(
																												jLabel9)
																										.addComponent(
																												jLabel10)))
																		.addComponent(jLabel15)).addComponent(jLabel16))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false).addComponent(jTextField16)
														.addComponent(jTextField9).addComponent(jTextField15)
														.addComponent(jTextField10))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jCheckBox3).addComponent(jCheckBox4))
										.addContainerGap(38, Short.MAX_VALUE))
						.addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel6Layout
								.createSequentialGroup()
								.addGroup(
										jPanel6Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel9)
												.addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel6Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel10))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel6Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel15)
												.addComponent(jCheckBox3))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel6Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel16)
												.addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(22, 22, 22)));

		jLabel20.setText("\u542f\u52a8PhoneRemote\u6d4b\u8bd5\uff1a");

		jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox8ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addComponent(jLabel20)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(jCheckBox8))).addContainerGap()));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel5Layout
								.createSequentialGroup()
								.addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 305,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(8, 8, 8)
								.addGroup(
										jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jCheckBox8).addComponent(jLabel20)).addContainerGap()));

		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("PhoneClient"));
		jPanel7.setToolTipText("");

		jLabel11.setText("IP\uff1a");

		jLabel12.setText("Port\uff1a");

		jLabel17.setText("\u767b\u9646\u6d4b\u8bd5\u6570\u91cf\uff1a");

		jLabel18.setText("\u70b9\u64ad\u6d4b\u8bd5\u6570\u91cf\uff1a");

		jTextField11.setEditable(false);
		jTextField11.setText("192.168.14.61");
		jTextField11.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField11ActionPerformed(evt);
			}
		});

		jTextField12.setEditable(false);
		jTextField12.setText("5002");

		jTextField17.setEditable(false);
		jTextField17.setText("10");

		jTextField18.setEditable(false);
		jTextField18.setText("60");

		jCheckBox5.setEnabled(false);
		jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox5ActionPerformed(evt);
			}
		});

		jCheckBox6.setEnabled(false);
		jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox6ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addGap(48, 48, 48)
																		.addGroup(
																				jPanel9Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(jLabel11)
																						.addComponent(jLabel12)))
														.addComponent(jLabel17).addComponent(jLabel18))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false).addComponent(jTextField18)
														.addComponent(jTextField11).addComponent(jTextField17)
														.addComponent(jTextField12))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jCheckBox5).addComponent(jCheckBox6))
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel9Layout
								.createSequentialGroup()
								.addGroup(
										jPanel9Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel11)
												.addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel9Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel12))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel9Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel17)
												.addComponent(jCheckBox5))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel9Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel18)
												.addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
														javax.swing.GroupLayout.PREFERRED_SIZE))));

		jLabel37.setText("\u8d77\u59cb\u7535\u8bdd\u53f7\u7801\uff1a");

		jTextField31.setText("13000000008");

		jLabel38.setText("\u7535\u8bdd\u6b65\u957f\uff1a");

		jTextField32.setText("1");

		jLabel39.setText("\u8d77\u59cbCA\u5361\u53f7\uff1a");

		jTextField33.setText("2000000000000080");

		jLabel40.setText("CA\u5361\u53f7\u6b65\u957f\uff1a");

		jTextField34.setText("1");

		jTextField35.setText("13000000008");

		jLabel41.setText("\u7ec8\u6b62\u7535\u8bdd\u53f7\u7801\uff1a");

		jTextField36.setText("2000000000000080");

		jLabel42.setText("\u7ec8\u6b62CA\u5361\u53f7\uff1a");

		javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
		jPanel12.setLayout(jPanel12Layout);
		jPanel12Layout
				.setHorizontalGroup(jPanel12Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel12Layout
										.createSequentialGroup()
										.addGroup(
												jPanel12Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel12Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel12Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel12Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel37)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField31,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												135,
																												Short.MAX_VALUE))
																						.addGroup(
																								jPanel12Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel41)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField35,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												135,
																												Short.MAX_VALUE))
																						.addGroup(
																								jPanel12Layout
																										.createSequentialGroup()
																										.addGap(24, 24,
																												24)
																										.addComponent(
																												jLabel38)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextField32,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												135,
																												Short.MAX_VALUE))))
														.addGroup(
																jPanel12Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel12Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel12Layout
																										.createSequentialGroup()
																										.addGap(20, 20,
																												20)
																										.addComponent(
																												jLabel39))
																						.addGroup(
																								jPanel12Layout
																										.createSequentialGroup()
																										.addGap(22, 22,
																												22)
																										.addGroup(
																												jPanel12Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel40)
																														.addComponent(
																																jLabel42))))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel12Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jTextField34,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								135, Short.MAX_VALUE)
																						.addComponent(
																								jTextField36,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								135, Short.MAX_VALUE)
																						.addComponent(
																								jTextField33,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								135, Short.MAX_VALUE))))
										.addContainerGap()));
		jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel12Layout
								.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(
										jPanel12Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel37)
												.addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel12Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel41)
												.addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel12Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel38))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel12Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel39)
												.addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel12Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel42)
												.addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel12Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel40)
												.addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))));

		jLabel21.setText("\u542f\u52a8PhoneClient\u6d4b\u8bd5\uff1a");

		jCheckBox9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox9ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addComponent(jLabel21)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(jCheckBox9)))
										.addContainerGap(29, Short.MAX_VALUE)));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel7Layout
								.createSequentialGroup()
								.addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(26, 26, 26)
								.addGroup(
										jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jCheckBox9).addComponent(jLabel21))
								.addContainerGap(10, Short.MAX_VALUE)));

		jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("IVR\u6309\u952e\u8bbe\u7f6e"));

		jLabel1.setText("\u6d4f\u89c8\u6309\u952e(\u767b\u9646)");

		jLabel2.setText("\u6d4f\u89c8\u6309\u952e(\u70b9\u64ad)");

		jLabel3.setText("\u64ad\u653e\u6309\u952e(\u70b9\u64ad)");

		jTextField1.setText("46825");

		jTextField2.setText("46825");

		jTextField3.setText("46825");

		javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
		jPanel13.setLayout(jPanel13Layout);
		jPanel13Layout.setHorizontalGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel13Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel13Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(
														jPanel13Layout
																.createSequentialGroup()
																.addComponent(jLabel1)
																.addGap(18, 18, 18)
																.addComponent(jTextField1,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 93,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														jPanel13Layout.createSequentialGroup().addComponent(jLabel2)
																.addGap(18, 18, 18).addComponent(jTextField2))
												.addGroup(
														jPanel13Layout.createSequentialGroup().addComponent(jLabel3)
																.addGap(18, 18, 18).addComponent(jTextField3)))
								.addContainerGap(31, Short.MAX_VALUE)));
		jPanel13Layout.setVerticalGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel13Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel13Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel13Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel13Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("PhoneRemote\u6309\u952e\u8bbe\u7f6e"));

		jLabel4.setText("\u6d4f\u89c8\u6309\u952e(\u767b\u9646)");

		jLabel43.setText("\u6d4f\u89c8\u6309\u952e(\u70b9\u64ad)");

		jLabel44.setText("\u64ad\u653e\u6309\u952e(\u70b9\u64ad)");

		jTextField4.setText("46825");

		jTextField37.setText("64825");

		jTextField38.setText("28645");

		javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
		jPanel14.setLayout(jPanel14Layout);
		jPanel14Layout.setHorizontalGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel14Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel14Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(
														jPanel14Layout
																.createSequentialGroup()
																.addComponent(jLabel4)
																.addGap(18, 18, 18)
																.addComponent(jTextField4,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 93,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														jPanel14Layout.createSequentialGroup().addComponent(jLabel43)
																.addGap(18, 18, 18).addComponent(jTextField37))
												.addGroup(
														jPanel14Layout.createSequentialGroup().addComponent(jLabel44)
																.addGap(18, 18, 18).addComponent(jTextField38)))
								.addContainerGap(58, Short.MAX_VALUE)));
		jPanel14Layout.setVerticalGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel14Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel14Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel4)
												.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel14Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel43)
												.addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel14Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel44)
												.addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("PhoneClient\u6309\u952e\u8bbe\u7f6e"));

		jLabel46.setText("OfferingID:");

		jLabel47.setText("\u64ad\u653e\u6309\u952e(\u70b9\u64ad)");

		jTextField40.setText("46825");

		jTextField41.setText("45682");

		javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
		jPanel15.setLayout(jPanel15Layout);
		jPanel15Layout.setHorizontalGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel15Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel47).addComponent(jLabel46))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel15Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jTextField41, javax.swing.GroupLayout.DEFAULT_SIZE, 111,
														Short.MAX_VALUE)
												.addComponent(jTextField40, javax.swing.GroupLayout.DEFAULT_SIZE, 111,
														Short.MAX_VALUE)).addContainerGap()));
		jPanel15Layout.setVerticalGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel15Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel15Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel46)
												.addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(27, 27, 27)
								.addGroup(
										jPanel15Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel47)
												.addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(16, Short.MAX_VALUE)));

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
				{ "浏览按键(登陆)", "左", "右", "上", "下", "确定", "返回页面" }, { "浏览按键(点播)", "左", "右", "上", "下", "确定", "返回页面" },
				{ "播放按键(点播)", "快退", "快进", "无", "无", "播放", "无" } }, new String[] { "功能/数字", "数字4", "数字6", "数字8", "数字2",
				"数字5", "数字0" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(jTable1);

		jLabel48.setText("\u6309\u952e\u8bf4\u660e");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(10, 10, 10)
																		.addComponent(jPanel13,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(jPanel8,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addGroup(
												jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(26, 26, 26)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jPanel14,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanel5,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(jLabel48))
																		.addGap(18, 18, 18)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jPanel15,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jPanel7,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(jScrollPane1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				621, Short.MAX_VALUE)))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel1Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addComponent(
																				jPanel7,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jPanel5,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addGap(30, 30, 30)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(
												jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				27, Short.MAX_VALUE)
																		.addComponent(jPanel8,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(24, 24, 24))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(43, 43, 43)
																		.addComponent(jLabel48)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(jScrollPane1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				82,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));

		jTabbedPane1.addTab("RCServer", jPanel1);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 893, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 764, Short.MAX_VALUE));

		jTabbedPane1.addTab("SuperVOD", jPanel2);

		//		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
		//				getContentPane());
		//		getContentPane().setLayout(layout);
		//		layout.setHorizontalGroup(layout.createParallelGroup(
		//				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
		//				javax.swing.GroupLayout.Alignment.TRAILING,
		//				layout.createSequentialGroup().addContainerGap()
		//						.addComponent(jTabbedPane1).addContainerGap()));
		//		layout.setVerticalGroup(layout.createParallelGroup(
		//				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
		//				layout.createSequentialGroup().addContainerGap()
		//						.addComponent(jTabbedPane1).addContainerGap()));

		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(new Rectangle(28, 17, 142, 114));
		jScrollPane.setViewportView(jPanel1);
		this.add(jScrollPane);

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			TestEntity.isClose = true;
			jButton2.setEnabled(false);
			messageDialog("一分钟之后系统会自动关闭，现在正在发送所有用户停止服务\r\n不要手动关闭系统");
			Thread.sleep(60000);
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {
		CharSequence str = jTextField7.getText();
		String ss = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";
		boolean bool = Pattern.matches(ss, str);
		if (!bool) {
			messageDialog("输入的不是IP");
			jTextField7.selectAll();
			jTextField7.requestFocus();
		}

	}

	private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox8.isSelected()) {
			jTextField9.setEditable(true);
			jTextField10.setEditable(true);
			jTextField15.setEditable(true);
			jTextField16.setEditable(true);
			jCheckBox3.setEnabled(true);
			jCheckBox4.setEnabled(true);
			jCheckBox3.setSelected(true);
			jCheckBox4.setSelected(true);
		} else {
			jTextField9.setEditable(false);
			jTextField10.setEditable(false);
			jTextField15.setEditable(false);
			jTextField16.setEditable(false);
			jCheckBox3.setEnabled(false);
			jCheckBox4.setEnabled(false);
			jCheckBox3.setSelected(false);
			jCheckBox4.setSelected(false);
		}
	}

	private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox3.isSelected() == false) {
			jCheckBox8.setSelected(false);
			jCheckBox8ActionPerformed(evt);
		}
		if (jCheckBox4.isSelected()) {
			jTextField16.setEditable(true);
		} else {
			jTextField16.setEditable(false);
		}
	}

	private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox4.isSelected() == false) {
			jCheckBox8.setSelected(false);
			jCheckBox8ActionPerformed(evt);
		}
		if (jCheckBox3.isSelected()) {
			jTextField15.setEditable(true);
		} else {
			jTextField15.setEditable(false);
		}
	}

	private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jCheckBox9ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox9.isSelected()) {
			jTextField11.setEditable(true);
			jTextField12.setEditable(true);
			// jTextField17.setEditable(true);
			jTextField18.setEditable(true);
			// jCheckBox5.setEnabled(true);
			jCheckBox6.setEnabled(true);
			// jCheckBox5.setSelected(true);
			jCheckBox6.setSelected(true);
		} else {
			jTextField11.setEditable(false);
			jTextField12.setEditable(false);
			// jTextField17.setEditable(false);
			jTextField18.setEditable(false);
			// jCheckBox5.setEnabled(false);
			jCheckBox6.setEnabled(false);
			jCheckBox5.setSelected(false);
			jCheckBox6.setSelected(false);
		}
	}

	private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox5.isSelected() == false) {
			jCheckBox9.setSelected(false);
			jCheckBox9ActionPerformed(evt);
		}
		if (jCheckBox6.isSelected()) {
			jTextField18.setEditable(true);
		} else {
			jTextField18.setEditable(false);
		}
	}

	private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox6.isSelected() == false) {
			jCheckBox9.setSelected(false);
			jCheckBox9ActionPerformed(evt);
		}
		if (jCheckBox5.isSelected()) {
			jTextField17.setEditable(true);
		} else {
			jTextField17.setEditable(false);
		}
	}

	private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox7.isSelected()) {
			jTextField7.setEditable(true);
			jTextField8.setEditable(true);
			jTextField13.setEditable(true);
			jTextField14.setEditable(true);
			jCheckBox1.setEnabled(true);
			jCheckBox2.setEnabled(true);
			jCheckBox1.setSelected(true);
			jCheckBox2.setSelected(true);
		} else {
			jTextField7.setEditable(false);
			jTextField8.setEditable(false);
			jTextField13.setEditable(false);
			jTextField14.setEditable(false);
			jCheckBox1.setEnabled(false);
			jCheckBox2.setEnabled(false);
			jCheckBox1.setSelected(false);
			jCheckBox2.setSelected(false);
		}
	}

	private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox1.isSelected() == false) {
			jCheckBox7.setSelected(false);
			jCheckBox7ActionPerformed(evt);
		}
		if (jCheckBox2.isSelected()) {
			jTextField14.setEditable(true);
		} else {
			jTextField14.setEditable(false);
		}
	}

	private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox2.isSelected() == false) {
			jCheckBox7.setSelected(false);
			jCheckBox7ActionPerformed(evt);
		}
		if (jCheckBox1.isSelected()) {
			jTextField13.setEditable(true);
		} else {
			jTextField13.setEditable(false);
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

		if (jCheckBox7.isSelected() == false && jCheckBox8.isSelected() == false && jCheckBox9.isSelected() == false) {
			messageDialog("没有选侧模块");
			return;
		}

		TestEntity.IVRIP = jTextField7.getText();
		TestEntity.IVRPort = jTextField8.getText();
		TestEntity.IVRStartPhoneNumber = Long.valueOf(jTextField19.getText());
		TestEntity.IVRStopPhoneNumber = Long.valueOf(jTextField23.getText());
		TestEntity.IVRPhoneNumberStep = Long.valueOf(jTextField20.getText());
		TestEntity.IVRStartCANumber = Long.valueOf(jTextField21.getText());
		TestEntity.IVRStopCANumber = Long.valueOf(jTextField24.getText());
		TestEntity.IVRCAStep = Long.valueOf(jTextField22.getText());
		TestEntity.IVRLoginKeystoke = jTextField1.getText();
		TestEntity.IVRVideoDemandKeystoke = jTextField2.getText();
		TestEntity.IVRPlayKeystoke = jTextField3.getText();
		TestEntity.IVRLoginAmount = Long.valueOf(jTextField13.getText());
		TestEntity.IVRVideoDemandAmount = Long.valueOf(jTextField14.getText());

		TestEntity.PhoneRemoteIP = jTextField9.getText();
		TestEntity.PhoneRemotePort = jTextField10.getText();
		TestEntity.PhoneRemoteStartPhoneNumber = Long.valueOf(jTextField25.getText());
		TestEntity.PhoneRemoteStopPhoneNumber = Long.valueOf(jTextField29.getText());
		TestEntity.PhoneRemotePhoneNumberStep = Long.valueOf(jTextField26.getText());
		TestEntity.PhoneRemoteStartCANumber = Long.valueOf(jTextField27.getText());
		TestEntity.PhoneRemoteStopCANumber = Long.valueOf(jTextField30.getText());
		TestEntity.PhoneRemoteCAStep = Long.valueOf(jTextField28.getText());
		TestEntity.PhoneRemoteLoginKeystoke = jTextField4.getText();
		TestEntity.PhoneRemoteVideoDemandKeystoke = jTextField37.getText();
		TestEntity.PhoneRemotePlayKeystoke = jTextField38.getText();
		TestEntity.PhoneRemoteLoginAmount = Long.valueOf(jTextField15.getText());
		TestEntity.PhoneRemoteVideoDemandAmount = Long.valueOf(jTextField16.getText());

		TestEntity.phoneClientIP = jTextField11.getText();
		TestEntity.phoneClientPort = jTextField12.getText();
		TestEntity.phoneClientStartPhoneNumber = Long.valueOf(jTextField31.getText());
		TestEntity.phoneClientStopPhoneNumber = Long.valueOf(jTextField35.getText());
		TestEntity.phoneClientPhoneNumberStep = Long.valueOf(jTextField32.getText());
		TestEntity.phoneClientStartCANumber = Long.valueOf(jTextField33.getText());
		TestEntity.phoneClientStopCANumber = Long.valueOf(jTextField36.getText());
		TestEntity.phoneClientLoginAmount = Long.valueOf(jTextField17.getText());
		TestEntity.phoneClientCAStep = Long.valueOf(jTextField34.getText());
		TestEntity.offeringID = Long.valueOf(jTextField40.getText());
		TestEntity.phoneClientPlayKeystoke = jTextField41.getText();
		TestEntity.phoneClientVideoDemandAmount = Long.valueOf(jTextField18.getText());

		TestEntity.isIVRLogin = jCheckBox1.isSelected();
		;// 是否测试IVR登陆
		TestEntity.isIVRVideoDemand = jCheckBox2.isSelected();
		;// 是否测试IVR点播
		TestEntity.isPhoneRemoteLogin = jCheckBox3.isSelected();
		;// 是否测试手机遥控器登陆
		TestEntity.isPhoneRemoteVideoDemand = jCheckBox4.isSelected();
		;// 是否测试手机遥控器点播
		TestEntity.isPhoneClientLogin = jCheckBox5.isSelected();
		;// 是否测试手机客户端登陆
		TestEntity.isPhoneClientVideoDemand = jCheckBox6.isSelected();
		;// 是否测试手机客户端点播

		TestEntity.isIVR = jCheckBox7.isSelected();
		TestEntity.isPR = jCheckBox8.isSelected();
		TestEntity.isPC = jCheckBox9.isSelected();

		TestEntity.Timeout = Long.valueOf(jTextField5.getText());
		TestEntity.AllTestTime = Long.valueOf(jTextField6.getText());

		SaveProperty.save();
		new Function().execute();
		jButton1.setEnabled(false);
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws URISyntaxException
	 */
	public static void main(String args[]) throws URISyntaxException {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();// 得到屏幕的大小
		System.out.println(screen.getWidth());// 输出屏幕的宽度
		System.out.println(screen.getHeight());// 输出屏幕的高度

		// /**
		URI dir = Test.class.getProtectionDomain().getCodeSource().getLocation().toURI();
		File file = new File(dir);
		String path = file.getParentFile().getAbsolutePath();
		System.out.println(path + "/log4j.properties");
		PropertyConfigurator.configure(path + "/log4j.properties");

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ReadProperty();
				Test test = new Test();
				test.jTextField7.setText(TestEntity.IVRIP.toString());
				test.jTextField8.setText(TestEntity.IVRPort.toString());
				test.jTextField19.setText(TestEntity.IVRStartPhoneNumber.toString());
				test.jTextField23.setText(TestEntity.IVRStopPhoneNumber.toString());
				test.jTextField20.setText(TestEntity.IVRPhoneNumberStep.toString());
				test.jTextField21.setText(TestEntity.IVRStartCANumber.toString());
				test.jTextField24.setText(TestEntity.IVRStopCANumber.toString());
				test.jTextField22.setText(TestEntity.IVRCAStep.toString());
				test.jTextField1.setText(TestEntity.IVRLoginKeystoke.toString());
				test.jTextField2.setText(TestEntity.IVRVideoDemandKeystoke.toString());
				test.jTextField3.setText(TestEntity.IVRPlayKeystoke.toString());

				test.jTextField25.setText(TestEntity.PhoneRemoteStartPhoneNumber.toString());
				test.jTextField29.setText(TestEntity.PhoneRemoteStopPhoneNumber.toString());
				test.jTextField26.setText(TestEntity.PhoneRemotePhoneNumberStep.toString());
				test.jTextField27.setText(TestEntity.PhoneRemoteStartCANumber.toString());
				test.jTextField30.setText(TestEntity.PhoneRemoteStopCANumber.toString());
				test.jTextField28.setText(TestEntity.PhoneRemoteCAStep.toString());
				test.jTextField4.setText(TestEntity.PhoneRemoteLoginKeystoke.toString());
				test.jTextField37.setText(TestEntity.PhoneRemoteVideoDemandKeystoke.toString());
				test.jTextField38.setText(TestEntity.PhoneRemotePlayKeystoke.toString());

				test.jTextField9.setText(TestEntity.PhoneRemoteIP.toString());
				test.jTextField10.setText(TestEntity.PhoneRemotePort.toString());

				test.jTextField31.setText(TestEntity.phoneClientStartPhoneNumber.toString());

				test.jTextField11.setText(TestEntity.phoneClientIP.toString());
				test.jTextField12.setText(TestEntity.phoneClientPort.toString());
				test.jTextField35.setText(TestEntity.phoneClientStopPhoneNumber.toString());
				test.jTextField32.setText(TestEntity.phoneClientPhoneNumberStep.toString());
				test.jTextField33.setText(TestEntity.phoneClientStartCANumber.toString());
				test.jTextField36.setText(TestEntity.phoneClientStopCANumber.toString());
				test.jTextField34.setText(TestEntity.phoneClientCAStep.toString());
				test.jTextField40.setText(TestEntity.offeringID.toString());
				test.jTextField41.setText(TestEntity.phoneClientPlayKeystoke.toString());

				test.jTextField5.setText(TestEntity.Timeout.toString());
				test.jTextField6.setText(TestEntity.AllTestTime.toString());

				test.jTextField13.setText(TestEntity.IVRLoginAmount.toString());
				test.jTextField14.setText(TestEntity.IVRVideoDemandAmount.toString());
				test.jTextField15.setText(TestEntity.PhoneRemoteLoginAmount.toString());
				test.jTextField16.setText(TestEntity.PhoneRemoteVideoDemandAmount.toString());
				test.jTextField17.setText(TestEntity.phoneClientLoginAmount.toString());
				test.jTextField18.setText(TestEntity.phoneClientVideoDemandAmount.toString());

				test.jCheckBox7.setSelected(Boolean.valueOf(TestEntity.isIVR));
				test.jCheckBox8.setSelected(Boolean.valueOf(TestEntity.isPR));
				test.jCheckBox9.setSelected(Boolean.valueOf(TestEntity.isPC));

				test.jCheckBox7ActionPerformed(null);
				test.jCheckBox8ActionPerformed(null);
				test.jCheckBox9ActionPerformed(null);

				test.jCheckBox1.setSelected(Boolean.valueOf(TestEntity.isIVRLogin));
				test.jCheckBox2.setSelected(Boolean.valueOf(TestEntity.isIVRVideoDemand));

				test.jCheckBox3.setSelected(Boolean.valueOf(TestEntity.isPhoneRemoteLogin));
				test.jCheckBox4.setSelected(Boolean.valueOf(TestEntity.isPhoneRemoteVideoDemand));

				test.jCheckBox5.setSelected(Boolean.valueOf(TestEntity.isPhoneClientLogin));
				test.jCheckBox6.setSelected(Boolean.valueOf(TestEntity.isPhoneClientVideoDemand));

				if (!test.jCheckBox1.isSelected()) {
					test.jTextField13.setEditable(false);
				}
				if (!test.jCheckBox2.isSelected()) {
					test.jTextField14.setEditable(false);
				}
				if (!test.jCheckBox3.isSelected()) {
					test.jTextField15.setEditable(false);
				}
				if (!test.jCheckBox4.isSelected()) {
					test.jTextField16.setEditable(false);
				}
				if (!test.jCheckBox5.isSelected()) {
					test.jTextField17.setEditable(false);
				}
				if (!test.jCheckBox6.isSelected()) {
					test.jTextField18.setEditable(false);
				}

				test.setVisible(true);
			}
		});
		// */
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JCheckBox jCheckBox2;
	private javax.swing.JCheckBox jCheckBox3;
	private javax.swing.JCheckBox jCheckBox4;
	private javax.swing.JCheckBox jCheckBox5;
	private javax.swing.JCheckBox jCheckBox6;
	private javax.swing.JCheckBox jCheckBox7;
	private javax.swing.JCheckBox jCheckBox8;
	private javax.swing.JCheckBox jCheckBox9;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel23;
	private javax.swing.JLabel jLabel24;
	private javax.swing.JLabel jLabel25;
	private javax.swing.JLabel jLabel26;
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel28;
	private javax.swing.JLabel jLabel29;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel30;
	private javax.swing.JLabel jLabel31;
	private javax.swing.JLabel jLabel32;
	private javax.swing.JLabel jLabel33;
	private javax.swing.JLabel jLabel34;
	private javax.swing.JLabel jLabel35;
	private javax.swing.JLabel jLabel36;
	private javax.swing.JLabel jLabel37;
	private javax.swing.JLabel jLabel38;
	private javax.swing.JLabel jLabel39;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel40;
	private javax.swing.JLabel jLabel41;
	private javax.swing.JLabel jLabel42;
	private javax.swing.JLabel jLabel43;
	private javax.swing.JLabel jLabel44;
	private javax.swing.JLabel jLabel46;
	private javax.swing.JLabel jLabel47;
	private javax.swing.JLabel jLabel48;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel14;
	private javax.swing.JPanel jPanel15;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField10;
	private javax.swing.JTextField jTextField11;
	private javax.swing.JTextField jTextField12;
	private javax.swing.JTextField jTextField13;
	private javax.swing.JTextField jTextField14;
	private javax.swing.JTextField jTextField15;
	private javax.swing.JTextField jTextField16;
	private javax.swing.JTextField jTextField17;
	private javax.swing.JTextField jTextField18;
	private javax.swing.JTextField jTextField19;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField20;
	private javax.swing.JTextField jTextField21;
	private javax.swing.JTextField jTextField22;
	private javax.swing.JTextField jTextField23;
	private javax.swing.JTextField jTextField24;
	private javax.swing.JTextField jTextField25;
	private javax.swing.JTextField jTextField26;
	private javax.swing.JTextField jTextField27;
	private javax.swing.JTextField jTextField28;
	private javax.swing.JTextField jTextField29;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField30;
	private javax.swing.JTextField jTextField31;
	private javax.swing.JTextField jTextField32;
	private javax.swing.JTextField jTextField33;
	private javax.swing.JTextField jTextField34;
	private javax.swing.JTextField jTextField35;
	private javax.swing.JTextField jTextField36;
	private javax.swing.JTextField jTextField37;
	private javax.swing.JTextField jTextField38;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField40;
	private javax.swing.JTextField jTextField41;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JTextField jTextField6;
	private javax.swing.JTextField jTextField7;
	private javax.swing.JTextField jTextField8;
	private javax.swing.JTextField jTextField9;

	// End of variables declaration//GEN-END:variables

	// 输出错误
	public static void messageDialog(String str) {
		ImageIcon ico = new ImageIcon("C:/WINDOWS/pig.jpg");
		ico.setImage(ico.getImage().getScaledInstance(200, 180, Image.SCALE_DEFAULT));
		JOptionPane.showMessageDialog(null, str, "错误", 0, ico);
		// jButton1.setEnabled(true);
	}

	// 更新返回结果
	public synchronized static void printResult(String str) {

	}

	// 更新剩余时间
	public synchronized static void time(String str) {
		// jLabel24.setText(str);
	}
}
