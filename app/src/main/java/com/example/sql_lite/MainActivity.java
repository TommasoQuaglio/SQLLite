package com.example.sql_lite

import androidx.appcompat.app.AppCompatActivity

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
    private DbManager db=null;
    private CursorAdapter adapter;
    private ListView listview=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DbManager(this);
        listview= findViewById(R.id.listview);
        Cursor crs=db.query();
        adapter=new CursorAdapter(this, crs, 0)
        {
            @Override
            public View newView(Context ctx, Cursor arg1, ViewGroup arg2)
            {
                View v=getLayoutInflater().inflate(R.layout.listactivity_row, null);
                return v;
            }
            @Override
            public void bindView(View v, Context arg1, Cursor crs)
            {
                String oggetto=crs.getString(
                        crs.getColumnIndex(DatabaseStrings.FIELD_SUBJECT));
                String data=crs.getString(crs.getColumnIndex(DatabaseStrings.FIELD_DATE));
                TextView txt= v.findViewById(R.id.txt_subject);
                txt.setText(oggetto);
                txt= v.findViewById(R.id.txt_date);
                txt.setText(data);
                ImageButton imgbtn= v.findViewById(R.id.btn_delete);
                imgbtn.setOnClickListener((View view) -> {
                    int position=listview.getPositionForView(v);
                    long id=adapter.getItemId(position);
                    if (db.delete(id))
                        adapter.changeCursor(db.query());
                });
            }
            @Override
            public long getItemId(int position)
            {
                Cursor crs=adapter.getCursor();
                crs.moveToPosition(position);
                return crs.getLong(crs.getColumnIndex(DatabaseStrings.FIELD_ID));
            }
        };
        listview.setAdapter(adapter);
    }
    public void salva(View v)
    {
        EditText sub= findViewById(R.id.oggetto);
        EditText txt= findViewById(R.id.testo);
        EditText date= findViewById(R.id.data);
        if (sub.length()>0 && date.length()>0)
        {
            db.save(sub.getEditableText().toString(),
                    txt.getEditableText().toString(),
                    date.getEditableText().toString());
            adapter.changeCursor(db.query());
        }
    }
}