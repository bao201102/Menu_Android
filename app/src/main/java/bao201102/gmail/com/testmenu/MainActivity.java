package bao201102.gmail.com.testmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv_floating, tv_contextual;
    private ActionMode mActionMode;
    ImageButton btn_Popup;
//    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_contextual = findViewById(R.id.tv_Contextual);
        tv_floating = findViewById(R.id.tv_floating);
        btn_Popup = findViewById(R.id.btn_Popup);

        tv_contextual.setOnLongClickListener(longClickListener);
        btn_Popup.setOnClickListener(popupClickListener);
        registerForContextMenu(tv_floating);
    }

    //Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemAbout:
                Toast.makeText(this, "Selected: About",Toast.LENGTH_SHORT).show();
                intent("About");
                return true;
            case R.id.itemProfile:
                Toast.makeText(this, "Selected: Profile",Toast.LENGTH_SHORT).show();
                intent("Profile");
                return true;
            case R.id.itemSearch:
                Toast.makeText(this, "Selected: Search",Toast.LENGTH_SHORT).show();
                intent("Search");
                return true;
            case R.id.itemSub1:
                Toast.makeText(this, "Selected: Sub 1",Toast.LENGTH_SHORT).show();
                intent("Sub 1");
                return true;
            case R.id.itemSub2:
                Toast.makeText(this, "Selected: Sub 2",Toast.LENGTH_SHORT).show();
                intent("Sub 2");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Contextual Menu
    public View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mActionMode != null)
                return false;

            mActionMode = MainActivity.this.startActionMode(mActionModeCallBack);

            return true;
        }
    };

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            mode.setTitle("Choose your option");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.itemDelete:
                    Toast.makeText(MainActivity.this,"Selected: Delete",Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.itemShare:
                    Toast.makeText(MainActivity.this,"Selected: Share",Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    //Popup Menu
    public View.OnClickListener popupClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(onMenuItemClickListener);
            popup.show();
        }
    };

    public PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.itemForward:
                    Toast.makeText(MainActivity.this,"Selected: Forward",Toast.LENGTH_SHORT).show();
                    intent("Forward");
                    return true;
                case R.id.itemReply:
                    Toast.makeText(MainActivity.this,"Selected: Reply All",Toast.LENGTH_SHORT).show();
                    intent("Reply All");
                    return true;
                default:
                    return false;
            }
        }
    };

    //Floating menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.floating_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemOption_1:
                Toast.makeText(MainActivity.this,"Selected: Option 1",Toast.LENGTH_SHORT).show();
                intent("Option 1");
                return true;
            case R.id.itemOption_2:
                Toast.makeText(MainActivity.this,"Selected: Option 2",Toast.LENGTH_SHORT).show();
                intent("Option 2");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void intent(String msg){
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("msg", "Đây là trang " + msg);
        startActivity(intent);
    }

}