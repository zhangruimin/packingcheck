package com.lvtuxiongdi.packingcheck;

import android.graphics.Color;
import android.os.Bundle;
import ca.laplanete.mobile.pageddragdropgrid.PagedDragDropGrid;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class MainActivity extends SherlockActivity {

    private String CURRENT_PAGE_KEY = "CURRENT_PAGE_KEY";

    private PagedDragDropGrid gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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
        menu.add("Reset").setOnMenuItemClickListener(new com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(com.actionbarsherlock.view.MenuItem item) {
                gridview.setAdapter(new PackingGridAdapter(MainActivity.this, gridview));
                gridview.notifyDataSetChanged();

                return true;
            }
        });

        return true;
    }
}
