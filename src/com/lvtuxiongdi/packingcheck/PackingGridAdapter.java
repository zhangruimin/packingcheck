
package com.lvtuxiongdi.packingcheck;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import ca.laplanete.mobile.pageddragdropgrid.PagedDragDropGrid;
import ca.laplanete.mobile.pageddragdropgrid.PagedDragDropGridAdapter;
import com.lvtuxiongdi.packingcheck.db.DatabaseHandler;
import com.lvtuxiongdi.packingcheck.model.Packing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PackingGridAdapter implements PagedDragDropGridAdapter {

    private static final String TAG = "PackingGridAdapter";
    private static final int pageSize = 20;
    private final DatabaseHandler databaseHandler;
    private Context context;
    private PagedDragDropGrid gridview;

    List<Page> pages = new ArrayList<Page>();

    public PackingGridAdapter(Context context, PagedDragDropGrid gridview) {
        super();
        this.context = context;
        this.gridview = gridview;

        databaseHandler = ((PackingCheckApplication) context.getApplicationContext()).getDatabaseHandler();


        List<Packing> allPackings = databaseHandler.getAllPackings();
        allPackings.add(0, new Packing(1, "新的行囊", R.drawable.ic_add_packing));

        int pageNum = (allPackings.size() - 1) / pageSize + 1;
        for (int i = 0; i < pageNum; i++) {
            Page page = new Page();
            page.setItems(allPackings.subList(i * pageSize, Math.min((i + 1) * pageSize, allPackings.size())));
            pages.add(page);
        }
    }

    @Override
    public int pageCount() {
        return pages.size();
    }

    private List<Packing> itemsInPage(int page) {
        if (pages.size() > page) {
            return pages.get(page).getItems();
        }
        return Collections.emptyList();
    }

    @Override
    public View view(int page, int index) {

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        ImageView icon = new ImageView(context);
        final Packing item = getItem(page, index);
        icon.setImageResource(item.getDrawable());
        icon.setPadding(15, 15, 15, 15);

        layout.addView(icon);

        TextView label = new TextView(context);
        label.setTag("text");
        label.setText(item.getName());
        label.setTextColor(Color.BLACK);
        label.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);

        label.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        // only set selector on every other page for demo purposes
        // if you do not wish to use the selector functionality, simply disregard this code
        if (page % 2 == 0) {
            setViewBackground(layout);
            layout.setClickable(true);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, String.valueOf(item.getId()) + " clicked");
                    Intent intent = new Intent(context, PackingItemsActivity.class);
                    intent.putExtra("packing", item);
                    context.startActivity(intent);
                }
            });
            layout.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return gridview.onLongClick(v);
                }
            });

        }

        layout.addView(label);
        return layout;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setViewBackground(LinearLayout layout) {
        if (Build.VERSION.SDK_INT >= 16) {
            layout.setBackground(context.getResources().getDrawable(R.drawable.list_selector_holo_light));
        }
    }

    private Packing getItem(int page, int index) {
        List<Packing> items = itemsInPage(page);
        return items.get(index);
    }

    @Override
    public int rowCount() {
        return AUTOMATIC;
    }

    @Override
    public int columnCount() {
        return AUTOMATIC;
    }

    @Override
    public int itemCountInPage(int page) {
        return itemsInPage(page).size();
    }

    public void printLayout() {
        int i = 0;
        for (Page page : pages) {
            Log.d("Page", Integer.toString(i++));

            for (Packing item : page.getItems()) {
                Log.d("Item", Long.toString(item.getId()));
            }
        }
    }

    private Page getPage(int pageIndex) {
        return pages.get(pageIndex);
    }

    @Override
    public void swapItems(int pageIndex, int itemIndexA, int itemIndexB) {
        getPage(pageIndex).swapItems(itemIndexA, itemIndexB);
    }

    @Override
    public void moveItemToPreviousPage(int pageIndex, int itemIndex) {
        int leftPageIndex = pageIndex - 1;
        if (leftPageIndex >= 0) {
            Page startpage = getPage(pageIndex);
            Page landingPage = getPage(leftPageIndex);

            Packing item = startpage.removeItem(itemIndex);
            landingPage.addItem(item);
        }
    }

    @Override
    public void moveItemToNextPage(int pageIndex, int itemIndex) {
        int rightPageIndex = pageIndex + 1;
        if (rightPageIndex < pageCount()) {
            Page startpage = getPage(pageIndex);
            Page landingPage = getPage(rightPageIndex);

            Packing item = startpage.removeItem(itemIndex);
            landingPage.addItem(item);
        }
    }

    @Override
    public void deleteItem(int pageIndex, int itemIndex) {
        getPage(pageIndex).deleteItem(itemIndex);
    }

    @Override
    public int deleteDropZoneLocation() {
        return BOTTOM;
    }

    @Override
    public boolean showRemoveDropZone() {
        return true;
    }

}
