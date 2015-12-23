package team5190;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;
import team5190.PathFile.PathData;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class EditPath extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	public JSpinner spinner;
	private Window window;
	public CalculatePath calPath;

	private PathData pathData;

	/**
	 * Create the dialog.
	 * 
	 * @param window
	 */
	public EditPath(final Window window, PathData pathData) {
		super(window);
		setTitle("Edit Path");
		this.window = window;
		this.pathData = pathData;
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setBounds(100, 100, 839, 487);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][grow][grow]"));
		{
			JLabel lblName = new JLabel("Path Name");
			contentPanel.add(lblName, "cell 0 0,alignx left");
		}
		{
			textField = new JTextField();
			if (pathData != null) {
				textField.setText(pathData.name);
			}
			contentPanel.add(textField, "cell 1 0,growx");
			textField.setColumns(10);
		}
		{
			JLabel lblPathTime = new JLabel("Path Time (seconds)");
			contentPanel.add(lblPathTime, "cell 0 1");
		}
		{
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(20), null, null, new Integer(1)));
			if (pathData != null) {
				spinner.setValue(pathData.time);
			}
			contentPanel.add(spinner, "cell 1 1");
		}
		{
			JLabel lblPathPoints = new JLabel("Path Points");
			contentPanel.add(lblPathPoints, "cell 0 2");
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(null);
			contentPanel.add(panel, "cell 1 2,grow");
			{
				JButton btnNewPoint = new JButton("New Point");
				btnNewPoint.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SmoothPathTableModel model = (SmoothPathTableModel) table.getModel();
						model.addPoint();
					}
				});
				panel.setLayout(new MigLayout("", "[grow][][]", "[]"));
				panel.add(btnNewPoint, "cell 1 0,growx,aligny center");
			}
			{
				JButton btnRemovePoint = new JButton("Delete Point");
				btnRemovePoint.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int deleteRow = table.getSelectedRow();
						if (deleteRow == -1) {
							return;
						}
						SmoothPathTableModel model = (SmoothPathTableModel) table.getModel();
						model.removePoint(deleteRow);
					}
				});
				panel.add(btnRemovePoint, "cell 2 0,alignx left,aligny top");
			}
		}
		{
			SmoothPathTableModel model = new SmoothPathTableModel(this);
			if (pathData != null) {
				model.setPoints(pathData.points);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				contentPanel.add(scrollPane, "cell 0 3 2 1,grow");
				table = new JTable();
				scrollPane.setViewportView(table);
				table.setModel(model);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(null);
			contentPanel.add(panel, "cell 0 4 2 1,grow");
			panel.setLayout(new MigLayout("fill, insets 0", "[grow,fill]", "[grow,fill][fill]"));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(null);
				panel.add(panel_1, "cell 0 0,grow");
				panel_1.setLayout(new MigLayout("fill, insets 0", "[grow,fill][][fill]", "[grow,fill]"));
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2, "cell 0 0,grow");
					panel_2.setLayout(new MigLayout("fill, insets 0", "[]", "[]"));
					{
						JButton btnCalculate = new JButton("Calculate Path");
						btnCalculate.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (calPath == null || !calPath.isVisible()) {
									calPath = new CalculatePath(EditPath.this);
									calPath.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									calPath.setVisible(true);
								}
							}
						});
						panel_2.add(btnCalculate, "cell 0 0");
					}
				}
				{
					JButton btnSave = new JButton("Save");
					btnSave.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							PathData pathData = EditPath.this.pathData;
							if (pathData == null) {
								pathData = new PathData();
							}
							pathData.points = getPoints();
							pathData.time = getTime();
							pathData.name = textField.getText();
							window.pathFile.pathDatas.remove(pathData);
							window.pathFile.pathDatas.add(pathData);
							window.updateList();
							EditPath.this.dispose();
						}
					});
					panel_1.add(btnSave, "cell 1 0");
				}
				{
					JButton btnNewButton = new JButton("Cancel");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							EditPath.this.dispose();
						}
					});
					panel_1.add(btnNewButton, "cell 2 0,alignx left,aligny top");
				}
			}
		}
	}

	public ArrayList<DPoint> getPoints() {
		SmoothPathTableModel model = (SmoothPathTableModel) table.getModel();
		return model.getPoints();
	}

	public int getTime() {
		return (int) spinner.getValue();
	}

}
