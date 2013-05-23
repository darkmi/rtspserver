package com.darkmi.rcp;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class RtspGui {
	private static Display display;
	private static Shell shell;
	private static Group group;
	private static Label labelHost;
	private static Text textHost;
	private static Label labelRMPort;
	private static Label labelSMPort;
	private static Text textRMPort;
	private static Text textSMPort;
	private static Label labelProvider;
	private static Text textProvider;
	private static Label labelContent;
	private static Text textContent;

	private static Group groupVideoServerSetting;
	private static Label lableVideoServerType;
	private static Combo comboVideoServerType;
	private static Label lableProtocol;
	private static Combo comboProtocol;

	private static Text textSend;
	private static Text textReceive;
	private static Composite composite;//用来放置所有按钮
	private static Button btnConnect;
	private static Button btnTeardown;
	private static Button btnSetup;
	private static Button btnPlay;
	private static Button btnPause;
	private static Button btnClose;
	private static RtspBusiness biz;
	private static boolean isConnected = false;
	private static Logger logger = Logger.getLogger(RtspGui.class);

	/**
	   * Create contents of the window
	   */
	protected void createContents() {
		biz = new RtspBusiness(this);
		shell = new Shell();
		shell.setSize(900, 650);
		shell.setText("视频服务器测试程序");

		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		layout.makeColumnsEqualWidth = true;
		shell.setLayout(layout);

		//创建配置信息录入框
		createGroup();
		//创建信息展示控件
		createMessage();
		//创建操作按钮
		createBtns();
	}

	/**
	 * 创建配置信息.
	 */
	private void createGroup() {
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		group = new Group(shell, SWT.SHADOW_IN);
		group.setText("视频服务器配置");
		group.setLayoutData(data);
		group.setLayout(new FillLayout(SWT.HORIZONTAL));

		labelHost = new Label(group, SWT.CENTER);
		labelHost.setText("主机IP");

		textHost = new Text(group, SWT.SINGLE | SWT.BORDER);
		textHost.setText("192.168.7.134");

		labelRMPort = new Label(group, SWT.CENTER);
		labelRMPort.setText("RM端口");

		textRMPort = new Text(group, SWT.SINGLE | SWT.BORDER);
		textRMPort.setText("554");

		labelSMPort = new Label(group, SWT.CENTER);
		labelSMPort.setText("SM端口");

		textSMPort = new Text(group, SWT.SINGLE | SWT.BORDER);
		textSMPort.setText("8060");

		lableVideoServerType = new Label(group, SWT.CENTER);
		lableVideoServerType.setText("视频服务器类型");

		comboVideoServerType = new Combo(group, SWT.DROP_DOWN | SWT.READ_ONLY);
		String[] vsType = { "并行视频服务器", "迪麓视频服务器", "川流视频服务器" };
		comboVideoServerType.setItems(vsType);
		comboVideoServerType.select(0);

		lableProtocol = new Label(group, SWT.CENTER);
		lableProtocol.setText("协议选择");

		comboProtocol = new Combo(group, SWT.DROP_DOWN | SWT.READ_ONLY);
		String[] protocols = { "HFC协议", "NGOD协议" };
		comboProtocol.setItems(protocols);
		comboProtocol.select(0);

		//---------------

		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		groupVideoServerSetting = new Group(shell, SWT.SHADOW_IN);
		groupVideoServerSetting.setText("点播资源配置");
		groupVideoServerSetting.setLayoutData(data);
		groupVideoServerSetting.setLayout(new FillLayout(SWT.HORIZONTAL));

		labelProvider = new Label(groupVideoServerSetting, SWT.CENTER);
		labelProvider.setText("ProviderId");

		textProvider = new Text(groupVideoServerSetting, SWT.SINGLE | SWT.BORDER);
		textProvider.setText("yongxin");

		labelContent = new Label(groupVideoServerSetting, SWT.CENTER);
		labelContent.setText("ContentId");

		textContent = new Text(groupVideoServerSetting, SWT.SINGLE | SWT.BORDER);
		textContent.setText("movie---3190---Kxingyike");

	}

	/**
	 * 创建信息展示控件.
	 */
	private void createMessage() {
		//发送显示
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		data.heightHint = 450;
		textSend = new Text(shell, SWT.MULTI | SWT.BORDER);
		textSend.setText("send message.");
		textSend.setLayoutData(data);

		//接收显示
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 2;
		data.heightHint = 450;
		textReceive = new Text(shell, SWT.MULTI | SWT.BORDER);
		textReceive.setText("receive message.");
		textReceive.setLayoutData(data);

	}

	/**
	 * 创建操作按钮.
	 */
	private void createBtns() {
		GridData data = new GridData(GridData.FILL_BOTH);
		data = new GridData(GridData.FILL_BOTH);
		data.horizontalSpan = 4;

		composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(data);
		GridLayout layout = new GridLayout();
		layout.numColumns = 7;
		//layout.marginHeight = 15;
		//FillLayout fillLayout = new FillLayout();
		composite.setLayout(layout);

		createConnectBtn(composite);
		createPlayBtn(composite);
		createPauseBtn(composite);
		createFastForwardBtn(composite);
		createRewindBtn(composite);
		createTeardownBtn(composite);
		createCloseBtn(composite);
	}

	/**
	 * 创建连接按钮
	 * @param composite
	 */
	private void createConnectBtn(Composite composite) {
		//TEARDOWN按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnConnect = new Button(composite, SWT.PUSH);
		btnConnect.setText("连接");
		btnConnect.setLayoutData(data);
		//添加事件
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				logger.debug("点击连接按钮...");
				if (null == textHost.getText() || "".equals(textHost.getText())) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请输入主机IP");
					messageBox.open();
					return;
				}
				if (null == textRMPort.getText() || "".equals(textRMPort.getText())) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请输入RM端口");
					messageBox.open();
					return;
				}
				if (null == textSMPort.getText() || "".equals(textSMPort.getText())) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请输入SM端口");
					messageBox.open();
					return;
				}
				if (null == textProvider.getText() || "".equals(textProvider.getText())) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请输入ProviderId");
					messageBox.open();
					return;
				}
				if (null == textContent.getText() || "".equals(textContent.getText())) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请输入ContentId");
					messageBox.open();
					return;
				}

				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								biz.onConnect();
								isConnected = true;
							}
						}.start();
					}
				});
			}
		});
	}

	/**
	 * 创建播放按钮
	 * @param composite
	 */
	private void createPlayBtn(Composite composite) {
		//PLAY按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnPlay = new Button(composite, SWT.PUSH);
		btnPlay.setText("播放");
		btnPlay.setLayoutData(data);
		//添加事件
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (isConnected == false) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请首先连接到视频服务器.");
					messageBox.open();
					return;
				}
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击播放按钮...");
								biz.onPlay();
							}
						}.start();
					}
				});
			}
		});
	}

	/**
	 * 创建暂停按钮
	 * @param composite
	 */
	private void createPauseBtn(Composite composite) {
		//PAUSE按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnPause = new Button(composite, SWT.PUSH);
		btnPause.setText("暂停");
		btnPause.setLayoutData(data);
		//添加事件
		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (isConnected == false) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请首先连接到视频服务器.");
					messageBox.open();
					return;
				}
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击暂停按钮...");
								biz.onPause();
							}
						}.start();
					}
				});
			}
		});
	}

	/**
	 * 创建快进按钮
	 * @param composite
	 */
	private void createFastForwardBtn(Composite composite) {
		//SETUP按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnSetup = new Button(composite, SWT.PUSH);
		btnSetup.setText("快进");
		btnSetup.setLayoutData(data);
		//添加事件
		btnSetup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (isConnected == false) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请首先连接到视频服务器.");
					messageBox.open();
					return;
				}
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击快进按钮...");
								//biz.setup();
							}
						}.start();
					}
				});
			}
		});
	}

	/**
	 * 创建快退按钮
	 * @param composite
	 */
	private void createRewindBtn(Composite composite) {
		//SETUP按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnSetup = new Button(composite, SWT.PUSH);
		btnSetup.setText("快退");
		btnSetup.setLayoutData(data);
		//添加事件
		btnSetup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (isConnected == false) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请首先连接到视频服务器.");
					messageBox.open();
					return;
				}
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击快退按钮...");
								//biz.setup();
							}
						}.start();
					}
				});
			}
		});
	}

	/**
	 * 创建停止按钮
	 * @param composite
	 */
	private void createTeardownBtn(Composite composite) {
		//TEARDOWN按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnTeardown = new Button(composite, SWT.PUSH);
		btnTeardown.setText("停止");
		btnTeardown.setLayoutData(data);
		//添加事件
		btnTeardown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (isConnected == false) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES);
					messageBox.setMessage("请首先连接到视频服务器.");
					messageBox.open();
					return;
				}
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击停止按钮...");
								biz.onTeardown();
							}
						}.start();
					}
				});
			}
		});
	}

	/**
	 * 创建断开连接按钮
	 * @param composite
	 */
	private void createCloseBtn(Composite composite) {
		//TEARDOWN按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnClose = new Button(composite, SWT.PUSH);
		btnClose.setText("断开");
		btnClose.setLayoutData(data);
		//添加事件
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				/*有待确认*/
				//shell.close();
				//display.close();
				logger.debug("点击断开连接按钮...");
			}
		});
	}

	/**
	 * 获取主机IP
	 * @return
	 */
	public String getHost() {
		return textHost.getText();
	}

	/**
	 * 获取RM端口设置
	 * @return
	 */
	public String getRMPort() {
		return textRMPort.getText();
	}

	/**
	 * 获取SM端口设置
	 * @return
	 */
	public String getSMPort() {
		return textSMPort.getText();
	}

	/**
	 * 获取providerId
	 * @return
	 */
	public String getProvider() {
		return textProvider.getText();
	}

	/**
	 * 获取内容ID
	 * @return
	 */
	public String getContent() {
		return textContent.getText();
	}

	/**
	 * 获取视频服务器类型
	 * @return
	 */
	public String getVideoServerType() {
		return comboVideoServerType.getText();
		//return textContent.getText();
	}

	/**
	 * 获取使用协议类型
	 * @return
	 */
	public String getProtocol() {
		return comboProtocol.getText();
		//return textContent.getText();
	}

	/**
	 * 将响应设置到文本框中显示.
	 * @param str
	 */
	public void setResponse(String str) {
		//String temp = textReceive.getText()
		textReceive.setText("\n\n" + textReceive.getText() + "\n\n" + str);
	}

	/**
	 * 创建窗体和控件.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * 主程序.
	 * @param args
	 */
	public static void main(String[] args) {
		logger.debug("启动测试程序...");
		try {
			RtspGui window = new RtspGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
