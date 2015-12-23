package team5190;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;
import team5190.PathFile.PathData;

public class Window extends JFrame {

	private JPanel contentPane;
	public PathFile pathFile;
	public JList<String> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UIManager.put("Label.font", new Font("Yu Gothic UI Light", Font.PLAIN, 17));
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setTitle("5190 Path Maker v1.0 - credit to team 2168 for path calculations");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				pathFile.saveFile(pathFile.file);
				System.exit(0);
			}
		});
		pathFile = new PathFile();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 839, 487);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[813px]", "[][438px]"));

		JLabel lblCreatedPaths = new JLabel("Created Paths");
		contentPane.add(lblCreatedPaths, "flowx,cell 0 0");

		list = new JList<String>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int index = list.locationToIndex(event.getPoint());
					PathDataListModel model = (PathDataListModel) list.getModel();
					PathData data = model.getDataAt(index);
					EditPath dialog = new EditPath(Window.this, data);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});
		list.setModel(new PathDataListModel(pathFile.pathDatas));
		contentPane.add(list, "cell 0 1,grow");

		JPanel panel = new JPanel();
		panel.setBorder(null);
		contentPane.add(panel, "cell 0 0,grow");

		JButton btnNewPathButton = new JButton("New Path");
		btnNewPathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditPath dialog = new EditPath(Window.this, null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		panel.setLayout(new MigLayout("fill, inset 0", "[grow][1px][89px]", "[23px]"));
		btnNewPathButton.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnNewPathButton, "cell 1 0,growx,aligny center");

		JButton btnDeletePath = new JButton("Delete Path");
		btnDeletePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				int index = list.getSelectedIndex();
				if (index == -1) {
					return;
				}
				pathFile.pathDatas.remove(index);
				updateList();
			}
		});
		panel.add(btnDeletePath, "cell 2 0,alignx left,aligny top");
	}

	public void updateList() {
		list.setModel(new PathDataListModel(pathFile.pathDatas));
	}

}
