package com.example.hoon.spinnersample;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<ItemData> itemList = new ArrayList<>();

        ItemData data1 = new ItemData();
        String[] spinnerData1 = {"a", "b", "c"};
        data1.setValues(spinnerData1);

        ItemData data2 = new ItemData();
        String[] spinnerData2 = {"가", "나", "다"};
        data2.setValues(spinnerData2);

        itemList.add(data1);
        itemList.add(data2);

        ListViewAdapter listViewAdapter = new ListViewAdapter(itemList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(listViewAdapter);
    }


    public class ListViewAdapter extends BaseAdapter {

        private ArrayList<ItemData> dataList;

        public ListViewAdapter(ArrayList<ItemData> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }


            Spinner spinner = convertView.findViewById(R.id.spinner);

            String[] spinnerData = dataList.get(position).getValues();

            // 간단하게 데이터만 넣을때
            ArrayAdapter arrayAdapter = new ArrayAdapter(parent.getContext(), android.R.layout.simple_spinner_item, spinnerData);
            spinner.setAdapter(arrayAdapter);

            // 커스텀하게 스피너를 사용할 경우
//            CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(parent.getContext(), android.R.layout.simple_spinner_item, spinnerData);
//            spinner.setAdapter(spinnerAdapter);

            return convertView;
        }
    }

    public class CustomSpinnerAdapter extends ArrayAdapter {

        private Context context;
        private String[] values;

        public CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull String[] values) {
            super(context, resource, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Nullable
        @Override
        public Object getItem(int position) {
            return super.getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        // 선택한 데이터가 보여지는 뷰
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TextView textView = (TextView) super.getView(position, convertView, parent);
            textView.setTextColor(Color.RED);

            return textView;
        }

        // 선택할 팝업 데이터가 보여지는 뷰
        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
            textView.setTextColor(Color.BLUE);


            return textView;
        }
    }

}
