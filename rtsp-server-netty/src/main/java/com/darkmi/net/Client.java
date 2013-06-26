package com.darkmi.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Client extends JFrame implements ActionListener {
	Socket sock; //定义套接字对象
	JTextArea t1 = new JTextArea();
	JTextField t2 = new JTextField(20);
	JButton b1 = new JButton("send");
	JButton b2 = new JButton("连接服务器");
	DataOutputStream out; //定义数据输出流
	DataInputStream in; //定义数据输入流

	public Client() {
		JScrollPane jsp = new JScrollPane(t1);
		this.getContentPane().add(jsp, "Center");
		JPanel p1 = new JPanel();
		p1.add(t2);
		p1.add(b1);
		JPanel p2 = new JPanel();
		p2.add(b2);
		this.getContentPane().add(p2, "North");
		this.getContentPane().add(p1, "South");
		b1.addActionListener(this);
		b2.addActionListener(this);
		setTitle("客户端");
		setSize(340, 200);
		setLocation(100, 50);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					out.writeUTF("bye"); //离开是告诉服务器
				} catch (Exception ee) {
				}
				dispose();
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			if (!t2.getText().equals("")) {
				try {
					out.writeUTF(t2.getText()); //向服务器发送信息
					t1.append("客户说:" + t2.getText() + "\n");
				} catch (Exception ee) {
				}
			}
		} else {
			try {
				sock = new Socket("127.0.0.1", 8060); //建立与服务器连接的套接字
				OutputStream os = sock.getOutputStream(); //根据套接字获得输出流
				out = new DataOutputStream(os); //根据输出流建立数据输出流（更高级的流）
				InputStream is = sock.getInputStream(); //根据套接字获得输入流
				in = new DataInputStream(is); //根据输入流建立数据输入流（更高级的流）
				Communion th = new Communion(this); //建立线程
				th.start(); //启动线程
			} catch (IOException ee) {
				JOptionPane.showMessageDialog(null, "连接服务器失败!");
				return;
			}
		}
	}

	public static void main(String args[]) {
		@SuppressWarnings("unused")
		Client mainFrame = new Client();
	}
}

class Communion extends Thread {
	Client fp;

	Communion(Client fp) {
		this.fp = fp;
	}

	public void run() {
		String msg = null;
		while (true) {
			try {
				msg = fp.in.readUTF();
				if (msg.equals("bye")) {//如果客户退出
					fp.t1.append("服务器已经停止\n");
					break;
				}
				fp.t1.append("服务器说:" + msg + "\n");
			} catch (Exception ee) {
				break;
			}
		}
		try {
			fp.out.close(); //关闭Socket输出流
			fp.in.close(); //关闭Socket输入流
			fp.sock.close(); //关闭Socket
		} catch (Exception ee) {
		}
	}
}
