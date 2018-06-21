package group.tonight.databingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container, SimpleTextFragment.getInstance())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_simple_text:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container, SimpleTextFragment.getInstance())
                        .commit();
                break;
            case R.id.menu_simple_image:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container, SimpleImageFragment.getInstance())
                        .commit();
                break;
            case R.id.menu_simple_list:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container, SimpleListFragment.getInstance())
                        .commit();
                break;
            case R.id.menu_multi_list_item:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container, MultiListItemFragment.getInstance())
                        .commit();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
