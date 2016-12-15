package com.taishi.mypagecharts;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment1 extends Fragment {
	private BarChart mChart;

	private PieChart pieChart;

	public TabFragment1() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_tab_1, container, false);
		mChart = (BarChart) v.findViewById(R.id.chart);
		mChart.setData(generateBarData(1, 200, 7));


		pieChart = (PieChart) v.findViewById(R.id.chart1);
		pieChart.setUsePercentValues(true);
		pieChart.getDescription().setEnabled(false);
		pieChart.setExtraOffsets(5, 10, 5, 5);

		pieChart.setDragDecelerationFrictionCoef(0.95f);

//		tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

//		pieChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
//		pieChart.setCenterText(generateCenterSpannableText());

		pieChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

		pieChart.setDrawHoleEnabled(true);
		pieChart.setHoleColor(Color.WHITE);

		pieChart.setTransparentCircleColor(Color.WHITE);
		pieChart.setTransparentCircleAlpha(110);


		pieChart.setHoleRadius(58f);
		pieChart.setTransparentCircleRadius(61f);

		pieChart.setDrawCenterText(true);

		pieChart.setRotationAngle(0);
		// enable rotation of the chart by touch
		pieChart.setRotationEnabled(true);
		pieChart.setHighlightPerTapEnabled(true);

		pieChart.setEntryLabelColor(Color.BLACK);

		// pieChart.setUnit(" €");
		// pieChart.setDrawUnitsInChart(true);

		// add a selection listener
//		pieChart.setOnChartValueSelectedListener(this);

		setData(4, 100);


		pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
		// pieChart.spin(2000, 0, 360);

		Legend l = pieChart.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
		l.setOrientation(Legend.LegendOrientation.VERTICAL);
		l.setDrawInside(false);
		l.setEnabled(false);

		return v;
	}

	private void setData(int count, float range) {

		float mult = range;

		ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

		// NOTE: The order of the entries when being added to the entries array determines their position around the center of
		// the chart.
		for (int i = 0; i < count; i++) {
			entries.add(new PieEntry((float) (Math.random() * mult) + mult / 5, mParties[i % mParties.length]));
		}

		PieDataSet dataSet = new PieDataSet(entries, "Election Results");
		dataSet.setSliceSpace(3f);
		dataSet.setSelectionShift(5f);

		// add a lot of colors

		ArrayList<Integer> colors = new ArrayList<Integer>();

//		for (int c : ColorTemplate.VORDIPLOM_COLORS)
		for (int c : ColorTemplate.VORDIPLOM_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.JOYFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.COLORFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.LIBERTY_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.PASTEL_COLORS)
			colors.add(c);

		colors.add(ColorTemplate.getHoloBlue());

		dataSet.setColors(colors);
		//dataSet.setSelectionShift(0f);


		dataSet.setValueLinePart1OffsetPercentage(80.f);
		dataSet.setValueLinePart1Length(0.2f);
		dataSet.setValueLinePart2Length(0.4f);
		//dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
		dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

		PieData data = new PieData(dataSet);
		data.setValueFormatter(new PercentFormatter());
		data.setValueTextSize(11f);
		data.setValueTextColor(Color.BLACK);
//		data.setValueTypeface(tf);
		pieChart.setData(data);

		// undo all highlights
		pieChart.highlightValues(null);

		pieChart.invalidate();
	}

	protected String[] mParties = new String[] {
			"Science Fiction", "Travel", "", "History", "Romance", "Humor", "Party G", "Party H",
			"Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
			"Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
			"Party Y", "Party Z"
	};

	protected BarData generateBarData(int dataSets, float range, int count) {

		ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();

		for(int i = 0; i < dataSets; i++) {

			ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

//            entries = FileUtils.loadEntriesFromAssets(getActivity().getAssets(), "stacked_bars.txt");

			for(int j = 0; j < count; j++) {
				entries.add(new BarEntry(j, (float) (Math.random() * range) + range / 4));
			}

			BarDataSet ds = new BarDataSet(entries, getLabel(i));
//			ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
			sets.add(ds);
		}

		BarData d = new BarData(sets);
//		d.setValueTypeface(tf);
		return d;
	}
	private String[] mLabels = new String[] { "Page Count", "Company B", "Company C", "Company D", "Company E", "Company F" };
//    private String[] mXVals = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec" };

	private String getLabel(int i) {
		return mLabels[i];
	}

}
