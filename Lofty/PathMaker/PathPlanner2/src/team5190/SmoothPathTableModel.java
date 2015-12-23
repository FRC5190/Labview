package team5190;

import java.util.ArrayList;

import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;

public class SmoothPathTableModel extends AbstractTableModel {
	private String[] columnNames = { "Point ID", "X", "Y" };
	private Object[][] data = new Object[0][0];

	private ArrayList<DPoint> points = new ArrayList<DPoint>();

	public EditPath editPath;

	public SmoothPathTableModel(EditPath editPath) {
		this.editPath = editPath;
		addPoint();
	}

	public void addPoint() {
		points.add(new DPoint());
		fireTableDataChanged();
	}
	
	@Override
	public void fireTableDataChanged() {
		super.fireTableDataChanged();
		SpinnerNumberModel spinnerModel = (SpinnerNumberModel) editPath.spinner.getModel();
		spinnerModel.setMinimum(points.size());
		if(editPath.calPath != null){
			editPath.calPath.calculate();
		}
	}

	public void removePoint(int deleteRow) {
		points.remove(deleteRow);
		fireTableDataChanged();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return points.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		DPoint point = points.get(row);
		if (col == 0) {
			return row;
		}
		if (col == 1) {
			return point.x;
		}
		if (col == 2) {
			return point.y;
		}
		return "IDK";
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		if (col != 0) {
			return true;
		}
		return false;
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		DPoint point = points.get(row);
		if (col == 1) {
			point.x = (double) value;
		}
		if (col == 2) {
			point.y = (double) value;
		}
		fireTableDataChanged();
	}

	public ArrayList<DPoint> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<DPoint> points) {
		this.points = points;
		fireTableDataChanged();
	}
}
