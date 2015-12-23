package team5190;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

public class FalconPathPlannerHook {

	private FalconPathPlanner path;
	public FalconLinePlot fig1;
	public FalconLinePlot fig2;

	public FalconPathPlannerHook(ArrayList<DPoint> waypoints, double totalTime) {
		long start = System.currentTimeMillis();
		// System.setProperty("java.awt.headless", "true"); //enable this to
		// true to emulate roboRio environment

		/*
		 * //create waypoint path double[][] waypoints = new double[][]{ {1, 1},
		 * {5, 1}, {9, 12}, {12, 9}, {15, 6}, {19, 12}, {24 , 15}, {22,22},
		 * {22,10}, {22,2} };
		 */

		// double totalTime = 20; //seconds
		double timeStep = 0.1; // period of control loop on Rio, seconds
		double robotTrackWidth = 2; // distance between left and right wheels,
									// feet

		path = new FalconPathPlanner(toDoubleArray(waypoints));
		path.calculate(totalTime, timeStep, robotTrackWidth);

		System.out.println("Time in ms: " + (System.currentTimeMillis() - start));

		if (!GraphicsEnvironment.isHeadless()) {

			fig2 = new FalconLinePlot(path.smoothCenterVelocity, null, Color.blue);
			fig2.yGridOn();
			fig2.xGridOn();
			fig2.setYLabel("Velocity (ft/sec)");
			fig2.setXLabel("time (seconds)");
			fig2.setTitle("Velocity Profile for Left and Right Wheels \n Left = Cyan, Right = Magenta");
			fig2.addData(path.smoothRightVelocity, Color.magenta);
			fig2.addData(path.smoothLeftVelocity, Color.cyan);

			fig1 = new FalconLinePlot(path.nodeOnlyPath, Color.blue, Color.green);
			fig1.yGridOn();
			fig1.xGridOn();
			fig1.setYLabel("Y (feet)");
			fig1.setXLabel("X (feet)");
			fig1.setTitle(
					"Top Down View of FRC Field (24ft x 27ft) \n shows global position of robot path, along with left and right wheel trajectories");

			// force graph to show 1/2 field dimensions of 24ft x 27 feet
			fig1.setXTic(0, 27, 1);
			fig1.setYTic(0, 24, 1);
			fig1.addData(path.smoothPath, Color.red, Color.blue);

			fig1.addData(path.leftPath, Color.magenta);
			fig1.addData(path.rightPath, Color.magenta);

			// generate poof path used in 2014 Einstein

			// System.out.println(Arrays.deepToString(path.smoothLeftVelocity[][]));

		}

		// example on printing useful path information
		// System.out.println(path.numFinalPoints);
		// System.out.println(path.pathAlpha);
	}

	private double[][] toDoubleArray(ArrayList<DPoint> waypoints) {
		double[][] doubleWayPoints = new double[waypoints.size()][2];
		for (int i = 0; i < waypoints.size(); i++) {
			DPoint waypoint = waypoints.get(i);
			doubleWayPoints[i][0] = waypoint.x;
			doubleWayPoints[i][1] = waypoint.y;
		}
		return doubleWayPoints;
	}

	public String exportPathToText() {
		String text = "";
		double[][] leftPath = path.smoothLeftVelocity;
		double[][] rightPath = path.smoothRightVelocity;
		for(int index = 0; index < leftPath.length; index++){
			double[] leftPathData = leftPath[index];
			double[] rightPathData = rightPath[index];
			String line = leftPathData[1] +"\t" + rightPathData[1];
			text += line;
			if(index + 1 < leftPath.length){
				text += "\n";
			}
		}
		return text;
	}

}
