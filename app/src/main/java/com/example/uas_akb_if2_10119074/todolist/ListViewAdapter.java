package com.example.uas_akb_if2_10119074.todolist;
/**
 * Nama : Handrian Rivaldi
 * Kelas : IF2
 * NIM :10119074
 * Email : handrianr28@gmail.com
 * **/
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_akb_if2_10119074.R;
import com.example.uas_akb_if2_10119074.database.SQLite;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<ModelDiary> listDiary;
    private Context context;
    private TextView judul, date, month, year, isi;
    private ImageView opt;
    private Button editButton, deleteButton;
    private SQLite helper;


    public ListViewAdapter(List<ModelDiary> listDiary, Context context) {
        this.listDiary = listDiary;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listDiary.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daily, null);


        judul = view.findViewById(R.id.title_daily);
        date = view.findViewById(R.id.cardDate);
        month = view.findViewById(R.id.cardMonth);
        year = view.findViewById(R.id.cardYear);
        isi = view.findViewById(R.id.desc_daily);
        opt = view.findViewById(R.id.options);
        helper = new SQLite(view.getContext());

        judul.setText(listDiary.get(position).getJudul());
        date.setText(listDiary.get(position).getDate());
        month.setText(listDiary.get(position).getMonth());
        year.setText(listDiary.get(position).getYear());
        isi.setText(listDiary.get(position).getIsi());
        String id = listDiary.get(position).getId();

        opt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(view.getContext(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menuDelete:
                                Toast.makeText(view.getContext(),"Catatan berhasil dihapus", Toast.LENGTH_SHORT).show();
                                helper.deteleData(id);
                                listDiary.remove(position);
                                notifyDataSetChanged();
                                return true;
                            case R.id.menuUpdate:
                                Intent intent = new Intent(context, EditTodolist.class);
                                intent.putExtra("Id", listDiary.get(position).getId());
                                intent.putExtra("Judul", listDiary.get(position).getJudul());
                                intent.putExtra("Isi", listDiary.get(position).getIsi());
                                intent.putExtra("Date", listDiary.get(position).getDate());
                                intent.putExtra("Month", listDiary.get(position).getMonth());
                                intent.putExtra("Year", listDiary.get(position).getYear());

                                context.startActivity(intent);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.inflate(R.menu.menu);
                popup.show();
            }

        });
        return view;
    }
}
