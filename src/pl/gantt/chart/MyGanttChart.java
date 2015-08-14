package pl.gantt.chart;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

public class MyGanttChart {

	private static final long serialVersionUID = 3488074583840465632L;

	public static IntervalCategoryDataset createDataset() {

		/**
		 * Creating a task series And adding planned tasks dates on the series.
		 */
		TaskSeries seriesOne = new TaskSeries("Planned Implementation");

		/** Adding data in this series **/
		seriesOne.add(new Task("Szkic pocz¹tkowy b³otnika pzedniego",
				new SimpleTimePeriod(makeDate(5, Calendar.MAY, 2015),
						makeDate(11, Calendar.MAY, 2015))));

		seriesOne.add(new Task("Zaprojektowanie narzêdzia do b³otnika przedniego",
				new SimpleTimePeriod(makeDate(5, Calendar.MAY, 2015),
						makeDate(19, Calendar.MAY, 2015))));

		seriesOne.add(new Task("Szkic pocz¹tkowy b³otnika tylniego",
				new SimpleTimePeriod(makeDate(5, Calendar.MAY, 2015),
						makeDate(12, Calendar.MAY, 2015))));

		seriesOne.add(new Task("Szkic pocz¹tkowy zderzaka przedniego",
				new SimpleTimePeriod(makeDate(5, Calendar.MAY, 2015), makeDate(
						12, Calendar.MAY, 2015))));

		seriesOne.add(new Task("Szkic pocz¹tkowy zderzaka tylniego",
				new SimpleTimePeriod(makeDate(5, Calendar.MAY, 2015), makeDate(
						12, Calendar.MAY, 2015))));

		seriesOne.add(new Task("Zaprojektowanie narzêdzia zderzak przód",
				new SimpleTimePeriod(makeDate(5, Calendar.JUNE, 2015), makeDate(
						12, Calendar.JUNE, 2015))));

		seriesOne.add(new Task("Zaprojektowanie narzêdzia zderzak ty³",
				new SimpleTimePeriod(makeDate(5, Calendar.JUNE, 2015), makeDate(
						12, Calendar.JULY, 2015))));

		seriesOne.add(new Task("Szkic test 1",
				new SimpleTimePeriod(makeDate(5, Calendar.MAY, 2015), makeDate(
						12, Calendar.MAY, 2015))));

		seriesOne.add(new Task("Zaprojektowanie narzêdzia test 1",
				new SimpleTimePeriod(makeDate(5, Calendar.JULY, 2015), makeDate(
						12, Calendar.JULY, 2015))));

		seriesOne.add(new Task("Zaprojektowanie narzêdzia test 2",
				new SimpleTimePeriod(makeDate(30, Calendar.JULY, 2015), makeDate(
						1, Calendar.SEPTEMBER, 2015))));


		/**
		 * Creating another task series
		 */
		TaskSeries seriesTwo = new TaskSeries("Actual Implementation");

		/** Adding data in this series **/
		seriesTwo.add(new Task("Szkic pocz¹tkowy b³otnika pzedniego",
				new SimpleTimePeriod(makeDate(5, Calendar.OCTOBER, 2015),
						makeDate(11, Calendar.OCTOBER, 2015))));

		seriesTwo.add(new Task("Zaprojektowanie narzêdzia do b³otnika przedniego",
				new SimpleTimePeriod(makeDate(5, Calendar.OCTOBER, 2015),
						makeDate(19, Calendar.OCTOBER, 2015))));

		seriesTwo.add(new Task("Szkic pocz¹tkowy b³otnika tylniego",
				new SimpleTimePeriod(makeDate(5, Calendar.OCTOBER, 2015),
						makeDate(12, Calendar.NOVEMBER, 2015))));

		seriesTwo.add(new Task("Szkic pocz¹tkowy zderzaka przedniego",
				new SimpleTimePeriod(makeDate(5, Calendar.NOVEMBER, 2015), makeDate(
						12, Calendar.NOVEMBER, 2015))));

		seriesTwo.add(new Task("Szkic pocz¹tkowy zderzaka tylniego",
				new SimpleTimePeriod(makeDate(5, Calendar.NOVEMBER, 2015), makeDate(
						12, Calendar.NOVEMBER, 2015))));

		seriesTwo.add(new Task("Zaprojektowanie narzêdzia zderzak przód",
				new SimpleTimePeriod(makeDate(5, Calendar.NOVEMBER, 2015), makeDate(
						12, Calendar.DECEMBER, 2015))));

		seriesTwo.add(new Task("Zaprojektowanie narzêdzia zderzak ty³",
				new SimpleTimePeriod(makeDate(5, Calendar.NOVEMBER, 2015), makeDate(
						12, Calendar.NOVEMBER, 2015))));

		seriesTwo.add(new Task("Szkic test 1",
				new SimpleTimePeriod(makeDate(5, Calendar.NOVEMBER, 2015), makeDate(
						12, Calendar.DECEMBER, 2015))));

		seriesTwo.add(new Task("Zaprojektowanie narzêdzia test 1",
				new SimpleTimePeriod(makeDate(1, Calendar.DECEMBER, 2015), makeDate(
						22, Calendar.DECEMBER, 2015))));

		seriesTwo.add(new Task("Zaprojektowanie narzêdzia test 2",
				new SimpleTimePeriod(makeDate(30, Calendar.DECEMBER, 2015), makeDate(
						31, Calendar.DECEMBER, 2015))));

		final TaskSeriesCollection collection = new TaskSeriesCollection();

		/**
		 * Adding the series to the collection Holds actual Dates.
		 */
		collection.add(seriesOne);
		collection.add(seriesTwo);

		return collection;
	}

	private static Date makeDate(final int day, final int month, final int year) {

		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final Date result = calendar.getTime();
		return result;

	}

	/**
	 * Creates a Gantt chart based on input data set
	 */
	private JFreeChart createChart(final IntervalCategoryDataset dataset) {
		final JFreeChart chart = ChartFactory.createGanttChart(
				"Wykres Gantta Szkic pocz¹tkowy b³otnika przedniego", // chart
																	// title
				"Taski", // domain axis label
				"Data", // range axis label
				dataset, // data
				true, // include legend
				true, // tooltips
				false // urls
				);
		return chart;

	}

	public void saveChart(JFreeChart chart, String fileLocation) {
		String fileName = fileLocation;
		try {
			/**
			 * This utility saves the JFreeChart as a JPEG First Parameter:
			 * FileName Second Parameter: Chart To Save Third Parameter: Height
			 * Of Picture Fourth Parameter: Width Of Picture
			 */
			ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 800, 600);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problem occurred creating chart.");
		}
	}

	public void generateGantt() {
		final MyGanttChart chartCreator = new MyGanttChart();
		System.out.println("...Creating Dataset");
		IntervalCategoryDataset dataset = createDataset();

		System.out.println("...Creating Chart");
		JFreeChart chart = chartCreator.createChart(dataset);

		String fileName = "C:/Users/Michaœ/Desktop/myGantChartDemo.jpg";

		System.out.println("...Saving the Chart");
		chartCreator.saveChart(chart, fileName);

		System.out.println("...Chart Created Successfully and Saved");
		System.out.println("Output Chart File Location: " + fileName);
	}

	/**
	 * Testing the Gantt Chart Creation
	 */
//	public static void main(final String[] args) {
//		generateGantt();
//	}
}
