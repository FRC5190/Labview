package team5190;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class CalculatePath extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private FalconPathPlannerHook planner;

	private EditPath editPath;
	
	private JPanel fig1;
	private JPanel fig2;
	
	/**
	 * Create the dialog.
	 * 
	 * @param editPath
	 */
	public CalculatePath(EditPath editPath) {
		super(editPath);
		setTitle("Path Calculation Live Preview");
		this.editPath = editPath;
		setBounds(100, 100, 1248, 481);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("fill, inset 0", "[600px:n:600px,grow][600px:n:600px]",
				"[400px:400px:400px,grow][grow]"));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "cell 0 1 2 1,grow");
			panel.setLayout(new MigLayout("fill, inset 0", "[][grow][]", "[][]"));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "cell 0 0");
				{
					JButton btnExportToFile = new JButton("Export");
					btnExportToFile.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent paramActionEvent) {
							Export dialog = new Export(CalculatePath.this);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						}
					});
					panel_1.add(btnExportToFile);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel.add(panel_2, "cell 2 0");
				{
					JButton btnNewButton = new JButton("Close");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent paramActionEvent) {
							CalculatePath.this.dispose();
						}
					});
					panel_2.add(btnNewButton);
				}
			}
		}
		calculate();
	}

	public void calculate(){
		planner = new FalconPathPlannerHook(editPath.getPoints(), editPath.getTime());
		if(fig1 != null){
			contentPanel.remove(fig1);
		}
		if(fig2 != null){
			contentPanel.remove(fig2);
		}
		fig1 = planner.fig1;
		fig2 = planner.fig2;
		contentPanel.add(fig1, "cell 0 0,grow");
		contentPanel.add(fig2, "cell 1 0,grow");
		contentPanel.updateUI();
	}
	
	public String exportPathToText() {
		return planner.exportPathToText();
	}

}
