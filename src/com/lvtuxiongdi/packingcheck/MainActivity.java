package com.lvtuxiongdi.packingcheck;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import ca.laplanete.mobile.pageddragdropgrid.PagedDragDropGrid;

public class MainActivity extends Activity {

    private String CURRENT_PAGE_KEY = "CURRENT_PAGE_KEY";

    private PagedDragDropGrid gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_list);
        gridview = (PagedDragDropGrid) findViewById(R.id.gridview);

        PackingGridAdapter adapter = new PackingGridAdapter(this, gridview);

        gridview.setAdapter(adapter);

        gridview.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int savedPage = savedInstanceState.getInt(CURRENT_PAGE_KEY);
        gridview.restoreCurrentPage(savedPage);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(CURRENT_PAGE_KEY, gridview.currentPage());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Reset").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                gridview.setAdapter(new PackingGridAdapter(MainActivity.this, gridview));
                gridview.notifyDataSetChanged();

                return true;
            }
        });

        return true;
    }
}
