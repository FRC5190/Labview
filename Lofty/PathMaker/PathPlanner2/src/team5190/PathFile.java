package team5190;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class PathFile {

	public File file;

	public ArrayList<PathData> pathDatas = new ArrayList<PathData>();

	public PathFile() {
		File folder = null;
		try {
			folder = new File(PathFile.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
					.getParentFile();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		file = new File(folder, "pathdata.json");
		if (file.exists()) {
			loadFile(file);
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveFile(File file) {
		PathDataSave save = new PathDataSave();
		save.pathDatas = pathDatas;
		Gson gson = new Gson();
		String json = gson.toJson(save);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(json);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFile(File file) {
		Gson gson = new Gson();
		try {
			PathDataSave save = gson.fromJson(new JsonReader(new FileReader(file)), PathDataSave.class);
			pathDatas = save.pathDatas;
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static class PathDataSave {
		public ArrayList<PathData> pathDatas = new ArrayList<PathData>();
	}

	public static class PathData {
		public ArrayList<DPoint> points = new ArrayList<DPoint>();
		public int time;
		public String name;
	}

}
