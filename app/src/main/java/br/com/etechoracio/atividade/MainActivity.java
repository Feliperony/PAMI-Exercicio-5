package br.com.etechoracio.atividade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements CustomDialog.ItemListener, AdapterView.OnItemLongClickListener, PopupMenu.OnMenuItemClickListener{


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

        PopupMenu popup = new PopupMenu(this, view);
        popup.inflate(R.menu.edite);
        popup.setOnMenuItemClickListener(this);
        popup.show();
        selectedItem = position;
        return true;
    }

    private boolean insertMode;
    private ItemAdapter adapter;
    private ListView listView;

    private String selectedItemName;
    private int selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ItemAdapter(this);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItem(String name) {
        if (insertMode) {
            adapter.insertItem(name);
        } else {
            adapter.updateItem(selectedItem, name);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.toolAdd:
                CustomDialog Adi = new CustomDialog(this);
                Adi.show(getFragmentManager(),"Adi");
                insertMode = true ;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.adExcluir:
                adapter.removeItem(selectedItem);

                return true;

             case R.id.adEditar:
                 CustomDialog editar = new CustomDialog(this);
                 editar.show(getFragmentManager(),"editar");
                 insertMode = false ;
                 return  true;

            default:
                return super.onOptionsItemSelected(menuItem);

        }

    }
}