package com.example.uas_akb_if2_10119074.todolist;
/**
 * Nama : Handrian Rivaldi
 * Kelas : IF2
 * NIM :10119074
 * Email : handrianr28@gmail.com
 * **/
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.uas_akb_if2_10119074.R;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uas_akb_if2_10119074.database.SQLite;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class EditTodolist extends AppCompatActivity {
    private EditText judulEditText, isiEditText;
    private Date date;
    private SimpleDateFormat dateFormat, monthFormat, yearFormat;
    private Button submitButton;
    private SQLite helper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_diary); /*Memanggil tampilan form*/

        Bundle bundle = getIntent().getExtras();

        /*Deklrasi tanggal*/
        date = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
        monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        /*Deklrasi button*/
        submitButton = findViewById(R.id.submitButton);
        judulEditText = findViewById(R.id.judul);
        isiEditText = findViewById(R.id.isi);

        judulEditText.setText(bundle.getString("Judul"));
        isiEditText.setText(bundle.getString("Isi"));

        /*Deklarasi SQLite sebagai variabel baru*/
        helper = new SQLite(this);

        /*Fungsi ketika button submit dipencet*/
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = bundle.getString("Id");
                String judul = judulEditText.getText().toString();
                String isi = isiEditText.getText().toString();
                String formattedDate = dateFormat.format(date);
                String formattedMonth = monthFormat.format(date);
                String formattedYear = yearFormat.format(date);

                if (TextUtils.isEmpty(judul)) {
                    judulEditText.setError("Data tidak boleh kosong");
                    judulEditText.requestFocus();
                } else if (TextUtils.isEmpty(isi)) {
                    isiEditText.setError("Data tidak boleh kosong");
                    isiEditText.requestFocus();
                } else {
                    boolean isSuccess = helper.updateData(id, judul, isi, formattedDate, formattedMonth, formattedYear);

                    if (isSuccess) {
                        Toast.makeText(EditTodolist.this, "Catatan berhasil diubah", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(EditTodolist.this, FragmentTodolist.class));
                        finish();
                    } else {
                        Toast.makeText(EditTodolist.this, "Catatan gagal diubah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
