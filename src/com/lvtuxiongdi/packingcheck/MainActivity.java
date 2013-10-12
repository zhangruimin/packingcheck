package com.lvtuxiongdi.packingcheck;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import ca.laplanete.mobile.pageddragdropgrid.OnPageChangedListener;
import ca.laplanete.mobile.pageddragdropgrid.PagedDragDropGrid;

public class MainActivity extends Activity implements View.OnClickListener {

    private String CURRENT_PAGE_KEY = "CURRENT_PAGE_KEY";

    private PagedDragDropGrid gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_list);
        gridview = (PagedDragDropGrid) findViewById(R.id.gridview);

        ExamplePagedDragDropGridAdapter adapter = new ExamplePagedDragDropGridAdapter(this, gridview);

        gridview.setAdapter(adapter);
        gridview.setClickListener(this);

        gridview.setBackgroundColor(Color.LTGRAY);


        gridview.setOnPageChangedListener(new OnPageChangedListener() {
            @Override
            public void onPageChanged(PagedDragDropGrid sender, int newPageNumber) {
                Toast.makeText(MainActivity.this, "Page changed to page " + newPageNumber, Toast.LENGTH_SHORT).show();
            }
        });
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
                gridview.setAdapter(new ExamplePagedDragDropGridAdapter(MainActivity.this, gridview));
                gridview.notifyDataSetChanged();

                return true;
            }
        });

        return true;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Clicked View", Toast.LENGTH_SHORT).show();
    }
}
