package com.darkmi.rcp;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * SRM测试程序客户端GUI.
 * @author Administrator
 *
 */
public class SrmClientGui {
	private static Display display;
	private static Shell shell;
	private static Composite composite;
	private static Button btnSetup1;
	private static Button btnSetup2;
	private static Button btnSetup3;
	private static Button btnSetup4;
	private static Button btnSetup5;
	private static Button btnTeardown1;
	private static Button btnTeardown2;
	private static Button btnTeardown3;
	private static Button btnTeardown4;
	private static Button btnTeardown5;
	private static SrmClientBusiness biz;
	private static Logger logger = Logger.getLogger(RtspGui.class);

	protected void createContents() {
		biz = new SrmClientBusiness();
		shell = new Shell();
		shell.setText("SRM集群版测试程序");
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.makeColumnsEqualWidth = true;
		shell.setLayout(layout);

		createBtns();
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
		layout.numColumns = 5;
		composite.setLayout(layout);

		createSetupBtn1(composite);
		createSetupBtn2(composite);
		createSetupBtn3(composite);
		createSetupBtn4(composite);
		createSetupBtn5(composite);
		createTeardownBtn1(composite);
		createTeardownBtn2(composite);
		createTeardownBtn3(composite);
		createTeardownBtn4(composite);
		createTeardownBtn5(composite);
	}

	/**
	 * 创建播放按钮
	 * @param composite
	 */
	private void createSetupBtn1(Composite composite) {
		//PLAY按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnSetup1 = new Button(composite, SWT.PUSH);
		btnSetup1.setText("客户端1");
		btnSetup1.setLayoutData(data);
		//添加事件
		btnSetup1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("启动第一个测试客户端.");
								biz.sendSetup("8000302100149890");
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
	private void createSetupBtn2(Composite composite) {
		//PAUSE按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnSetup2 = new Button(composite, SWT.PUSH);
		btnSetup2.setText("客户端2");
		btnSetup2.setLayoutData(data);
		//添加事件
		btnSetup2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("启动第二个测试客户端.");
								biz.sendSetup("8000302100139735");
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
	private void createSetupBtn3(Composite composite) {
		//SETUP按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnSetup3 = new Button(composite, SWT.PUSH);
		btnSetup3.setText("客户端3");
		btnSetup3.setLayoutData(data);
		//添加事件
		btnSetup3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("启动第三个测试客户端.");
								biz.sendSetup("8000302100139701");
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
	private void createSetupBtn4(Composite composite) {
		//SETUP按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnSetup4 = new Button(composite, SWT.PUSH);
		btnSetup4.setText("客户端4");
		btnSetup4.setLayoutData(data);
		//添加事件
		btnSetup4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("启动第四个测试客户端.");
								biz.sendSetup("8000302110012400");
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
	private void createSetupBtn5(Composite composite) {
		//SETUP按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnSetup5 = new Button(composite, SWT.PUSH);
		btnSetup5.setText("客户端5");
		btnSetup5.setLayoutData(data);
		//添加事件
		btnSetup5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("启动第五个测试客户端.");
								biz.sendSetup("8000302110012390");
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
	private void createTeardownBtn1(Composite composite) {
		//TEARDOWN按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnTeardown1 = new Button(composite, SWT.PUSH);
		btnTeardown1.setText("停止1");
		btnTeardown1.setLayoutData(data);
		//添加事件
		btnTeardown1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击停止按钮...");
								biz.sendTeardown("8000302100149890");
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
	private void createTeardownBtn2(Composite composite) {
		//TEARDOWN按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnTeardown2 = new Button(composite, SWT.PUSH);
		btnTeardown2.setText("停止1");
		btnTeardown2.setLayoutData(data);
		//添加事件
		btnTeardown2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击停止按钮...");
								biz.sendTeardown("8000302100139735");
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
	private void createTeardownBtn3(Composite composite) {
		//TEARDOWN按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnTeardown3 = new Button(composite, SWT.PUSH);
		btnTeardown3.setText("停止1");
		btnTeardown3.setLayoutData(data);
		//添加事件
		btnTeardown3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击停止按钮...");
								biz.sendTeardown("8000302100139701");
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
	private void createTeardownBtn4(Composite composite) {
		//TEARDOWN按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnTeardown4 = new Button(composite, SWT.PUSH);
		btnTeardown4.setText("停止1");
		btnTeardown4.setLayoutData(data);
		//添加事件
		btnTeardown4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击停止按钮...");
								biz.sendTeardown("8000302110012400");
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
	private void createTeardownBtn5(Composite composite) {
		//TEARDOWN按钮
		GridData data = new GridData(GridData.FILL_BOTH);
		btnTeardown5 = new Button(composite, SWT.PUSH);
		btnTeardown5.setText("停止1");
		btnTeardown5.setLayoutData(data);
		//添加事件
		btnTeardown5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				display.asyncExec(new Runnable() {
					public void run() {
						new Thread() {
							public void run() {
								logger.debug("点击停止按钮...");
								biz.sendTeardown("8000302110012390");
							}
						}.start();
					}
				});
			}
		});
	}

	/**
	 * 创建窗体和控件.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		shell.pack();
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
			SrmClientGui window = new SrmClientGui();
			window.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//	/**
	//	 * 创建连接按钮
	//	 * @param composite
	//	 */
	//	private void createConnectBtn(Composite composite) {
	//		//TEARDOWN按钮
	//		GridData data = new GridData(GridData.FILL_BOTH);
	//		btnConnect = new Button(composite, SWT.PUSH);
	//		btnConnect.setText("连接");
	//		btnConnect.setLayoutData(data);
	//		//添加事件
	//		btnConnect.addMouseListener(new MouseAdapter() {
	//			@Override
	//			public void mouseDown(MouseEvent e) {
	//				logger.debug("点击连接按钮...");
	//				display.asyncExec(new Runnable() {
	//					public void run() {
	//						new Thread() {
	//							public void run() {
	//								biz.onConnect();
	//								isConnected = true;
	//							}
	//						}.start();
	//					}
	//				});
	//			}
	//		});
	//	}

}
