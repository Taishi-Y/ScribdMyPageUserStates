package com.taishi.mypagecharts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment1 extends Fragment {
	private BarChart mChart;

	public TabFragment1() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_tab_1, container, false);;
		mChart = (BarChart) v.findViewById(R.id.chart);
		mChart.setData(generateBarData(1, 200, 7));

		return v;
	}

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
