import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;



public class HelloWorld {

	
	
	private Display display;
	private Shell shell;
	private Image windowImage;
	private Text textLogin;
	private Text textPassword;

	private HelloWorld() {

		this.display = Display.getDefault();
		this.shell = new Shell(display, SWT.BORDER | SWT.APPLICATION_MODAL | SWT.CLOSE);
	}

	
	public void createContents() {
		this.shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				HelloWorld.this.shutdownApplication();
			}
		});

		this.shell.setSize(260, 185);
		this.shell.setText("Login");
		if (this.windowImage != null)
			this.shell.setImage(this.windowImage);

		Composite composite = new Composite(this.shell, SWT.NONE);
		composite.setBounds(1, 0, 243, 146);
		composite.setLayout(null);

		Group group = new Group(composite, SWT.NONE);
		group.setText("Login");
		group.setBounds(14, 10, 219, 93);

		Label labelLogin = new Label(group, SWT.NONE);
		labelLogin.setAlignment(SWT.RIGHT);
		labelLogin.setBounds(16, 21, 44, 15);
		labelLogin.setText("Login");

		Label labelPassword = new Label(group, SWT.NONE);
		labelPassword.setAlignment(SWT.RIGHT);
		labelPassword.setBounds(10, 49, 50, 15);
		labelPassword.setText("Password");

		this.textLogin = new Text(group, SWT.BORDER);
		textLogin.setTextLimit(20);
		this.textLogin.setBounds(66, 21, 125, 21);


		this.textPassword = new Text(group, SWT.BORDER | SWT.PASSWORD);
		textPassword.setTextLimit(20);
		this.textPassword.setBounds(66, 49, 125, 21);


		Button btnOk = new Button(composite, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showMessage("Hello world!");
			}
		});
		btnOk.setBounds(30, 109, 75, 25);
		btnOk.setText("OK");

		Button buttonCancel = new Button(composite, SWT.NONE);
		buttonCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HelloWorld.this.shutdownApplication();
			}
		});
		buttonCancel.setLocation(130, 109);
		buttonCancel.setSize(75, 25);
		buttonCancel.setText("Cancel");

		Monitor primary = this.display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = this.shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		this.shell.setLocation(x, y);
		
	}


	
	public void open() {

		this.shell.open();
		this.shell.layout();

	}

	public final int showMessage(String msgText) {

		Shell shell = new Shell(display);
		int style = SWT.APPLICATION_MODAL | SWT.ICON_INFORMATION | SWT.OK;
		MessageBox messageBox = new MessageBox(shell, style);
		messageBox.setText(msgText);
		messageBox.setMessage(msgText);
		int response = messageBox.open();
		
		return response;
	}
	
	
	public void startApplication() {
		
		this.createContents();
		this.open();

		while (!this.display.isDisposed()) {

			if (!this.display.readAndDispatch()) {
				this.display.sleep();
			}
		}
	}
	

	public void shutdownApplication() {
		System.gc();		
		if (this.display != null) this.display.dispose();
		System.exit(0);
	}


	
	public static void main(String[] args) {

		HelloWorld app = new HelloWorld();
		app.startApplication();
	}

}
