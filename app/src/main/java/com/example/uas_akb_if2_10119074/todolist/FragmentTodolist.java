package com.example.uas_akb_if2_10119074.todolist;
/**
 * Nama : Handrian Rivaldi
 * Kelas : IF2
 * NIM :10119074
 * Email : handrianr28@gmail.com
 * **/
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uas_akb_if2_10119074.R;

import com.example.uas_akb_if2_10119074.database.SQLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentTodolist extends Fragment{
    private FloatingActionButton addButton;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private ArrayList<ModelDiary> listTask = new ArrayList<>();
    private SQLite helper;

    @Nullable
    @Override
    /*Fungsi membuat view dan menampilkan data*/
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note, container, false);

        addButton = root.findViewById(R.id.addButton);
        listView = root.findViewById(R.id.view_daily);

        helper = new SQLite(this.getActivity());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateTodolist.class));
            }
        });

        showData();

        return root;
    }

    /*Fungsi menampilkan data*/
    public void showData() {
        listTask.clear();
        Cursor res = helper.getAllData();
        while (res.moveToNext()) {
            String id = res.getString(0);
            String judul = res.getString(1);
            String isi = res.getString(2);
            String date = res.getString(3);
            String month = res.getString(4);
            String year = res.getString(5);

            listTask.add(new ModelDiary(id, judul, isi, date, month, year));
        }

        listViewAdapter = new ListViewAdapter(listTask, getActivity());
        listView.setAdapter(listViewAdapter);
        listViewAdapter.notifyDataSetChanged();
    }
}
