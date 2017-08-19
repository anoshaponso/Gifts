package com.example.Gifts.Tabs;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.MyActivity;
import com.example.Gifts.Progress_Tab.Progress_view;
import com.example.Gifts.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;

/**
 * Created by adminslt on 11/25/2014.
 */

public class Progress extends Fragment {
    private static int[] COLORS = new int[]{Color.GREEN, Color.BLUE, Color.MAGENTA, Color.RED, Color.CYAN};
    public ArrayList<Person_Class> person = MyActivity.getPerson();
    public ArrayList<Group_Class> group = MyActivity.getGroup();
    LinearLayout layout;
    ArrayList<Gift_Class> gift = MyActivity.getGift();
    private CategorySeries mSeries = new CategorySeries("");
    private DefaultRenderer mRenderer = new DefaultRenderer();
    private GraphicalView mChartView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRenderer.setStartAngle(180);
        mRenderer.setPanEnabled(false);
        mSeries.clear();
        mRenderer.removeAllRenderers();

        int value[] = getdata();
        String name[] = {"Not purchased (" + value[0] + ")", "Purchased (" + value[1] + ")", "Shipped (" + value[2] + ")", "Ordered (" + value[3] + ")", "Wrapped (" + value[4] + ")"};
        for (int i = 0; i < 5; i++) {

            mSeries.add(name[i], value[i]);
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
            renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);

            mRenderer.addSeriesRenderer(renderer);
            mRenderer.setShowLabels(false);
            mRenderer.setLegendTextSize(25);
        }

        View rootView = inflater.inflate(R.layout.progress, container, false);
        layout = (LinearLayout) rootView.findViewById(R.id.chart);

        TextView groups = (TextView) rootView.findViewById(R.id.noOfGroups);
        TextView people1 = (TextView) rootView.findViewById(R.id.noOfPeople);
        TextView spent = (TextView) rootView.findViewById(R.id.spentAll);
        TextView groupSpent = (TextView) rootView.findViewById(R.id.groupBudget);
        TextView peopleBudget = (TextView) rootView.findViewById(R.id.peopleBudget);
        TextView noOfGifts = (TextView) rootView.findViewById(R.id.noOfGifts);
        set(groups, people1, spent, groupSpent, peopleBudget, noOfGifts);


        return rootView;
    }

    private void set(TextView groups, TextView people1, TextView spent, TextView groupSpent, TextView peopleBudget, TextView noOfGifts) {
        double spentAmount = 0.0;
        double groupBudget = 0.0;
        double peopleBudgetAmount = 0.0;
        for (int i = 0; i < group.size(); i++) {
            groupBudget = groupBudget + Double.parseDouble(group.get(i).getBudget());
        }
        groupSpent.setText(String.valueOf(groupBudget));
        for (int i = 0; i < person.size(); i++) {
            peopleBudgetAmount = peopleBudgetAmount + Double.parseDouble(person.get(i).getBudget());
        }
        peopleBudget.setText(String.valueOf(peopleBudgetAmount));
        for (int i = 0; i < gift.size(); i++) {
            spentAmount = spentAmount + Double.parseDouble(gift.get(i).getAmount());
        }
        spent.setText(String.valueOf(spentAmount));
        groups.setText(String.valueOf(group.size()));
        people1.setText(String.valueOf(person.size()));
        noOfGifts.setText(String.valueOf(gift.size()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mChartView = ChartFactory.getPieChartView(getActivity(), mSeries, mRenderer);
        mRenderer.setClickEnabled(true);
        mChartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
                if (seriesSelection == null) {
//                        Toast.makeText(getActivity(), "No chart element selected", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < mSeries.getItemCount(); i++) {
                        mRenderer.getSeriesRendererAt(i).setHighlighted(i == seriesSelection.getPointIndex());

                    }
                    if (seriesSelection.getPointIndex() == 0) {
                        Intent in = new Intent(getActivity(), Progress_view.class);
                        in.putExtra("status", "Not purchased");
                        startActivity(in);

                    } else if (seriesSelection.getPointIndex() == 1) {
                        Intent in = new Intent(getActivity(), Progress_view.class);
                        in.putExtra("status", "Purchased");
                        startActivity(in);

                    } else if (seriesSelection.getPointIndex() == 2) {
                        Intent in = new Intent(getActivity(), Progress_view.class);
                        in.putExtra("status", "Shipped");
                        startActivity(in);

                    } else if (seriesSelection.getPointIndex() == 3) {
                        Intent in = new Intent(getActivity(), Progress_view.class);
                        in.putExtra("status", "Ordered");
                        startActivity(in);

                    } else if (seriesSelection.getPointIndex() == 4) {
                        Intent in = new Intent(getActivity(), Progress_view.class);
                        in.putExtra("status", "Wrapped");
                        startActivity(in);

                    }
                    mChartView.repaint();

                }
            }
        });
        layout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public int[] getdata() {
        String status;
        int not = 0, purchased = 0, shipped = 0, ordered = 0, wrapped = 0;
        for (int i = 0; i < gift.size(); i++) {
            status = gift.get(i).getStatus();
            if (status.equals("Not purchased")) {
                not += 1;
            } else if (status.equals("Purchased")) {
                purchased += 1;
            } else if (status.equals("Shipped")) {
                shipped += 1;
            } else if (status.equals("Ordered")) {
                ordered += 1;
            } else if (status.equals("Wrapped")) {
                wrapped += 1;
            }
        }
        return new int[]{not, purchased, shipped, ordered, wrapped};
    }

    public String getspent() {
        double amount = 0.0;
        for (int i = 0; i < gift.size(); i++) {
            amount = amount + Double.parseDouble(gift.get(i).getAmount());
        }
        return String.valueOf(amount);
    }
}
