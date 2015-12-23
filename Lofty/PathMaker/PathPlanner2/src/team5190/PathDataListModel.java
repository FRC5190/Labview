package team5190;

import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import team5190.PathFile.PathData;

public class PathDataListModel implements ListModel<String> {

	public ArrayList<PathData> pathDatas;

	public PathDataListModel(ArrayList<PathData> pathDatas) {
		this.pathDatas = pathDatas;
	}

	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getElementAt(int arg0) {
		return pathDatas.get(arg0).name;
	}

	@Override
	public int getSize() {
		return pathDatas.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub

	}

	public PathData getDataAt(int index) {
		return pathDatas.get(index);
	}

}
