package com.lvtuxiongdi.packingcheck.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.lvtuxiongdi.packingcheck.R;
import com.lvtuxiongdi.packingcheck.model.Packing;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "packing_check";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PACKING_TABLE = "CREATE TABLE packing(id INTEGER PRIMARY KEY, name TEXT, imageResource INTEGER" + ")";
        db.execSQL(CREATE_PACKING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS packing");

        onCreate(db);
    }

    public void addPacking(Packing packing) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", packing.getName());
        values.put("imageResource", packing.getDrawable());

        db.insert("packing", null, values);
        db.close();
    }

    public List<Packing> getAllPackings() {
		List<Packing> packingList = new ArrayList<Packing>();

        String selectQuery = "SELECT  * FROM packing";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Packing packing = new Packing();
				packing.setId(Integer.parseInt(cursor.getString(0)));
				packing.setName(cursor.getString(1));
				packing.setDrawable(cursor.getInt(2));
				packingList.add(packing);
			} while (cursor.moveToNext());
		}

		return packingList;

        /*List<Packing> items = new ArrayList<Packing>();
        items.add(new Packing(1, "新的行囊", R.drawable.ic_add_packing));
        items.add(new Packing(2, "Item 2", R.drawable.ic_launcher));
        items.add(new Packing(3, "Item 3", R.drawable.ic_launcher));
        items.add(new Packing(4, "Item 4", R.drawable.ic_launcher));
        items.add(new Packing(5, "Item 5", R.drawable.ic_launcher));
        items.add(new Packing(6, "Item 6", R.drawable.ic_launcher));
        items.add(new Packing(7, "Item 7", R.drawable.ic_launcher));
        items.add(new Packing(8, "Item 8", R.drawable.ic_launcher));
        items.add(new Packing(9, "Item 9", R.drawable.ic_launcher));
        items.add(new Packing(10, "Item 10", R.drawable.ic_launcher));
        items.add(new Packing(11, "Item 11", R.drawable.ic_launcher));
        items.add(new Packing(12, "Item 12", R.drawable.ic_launcher));
        items.add(new Packing(13, "Item 13", R.drawable.ic_launcher));
        items.add(new Packing(14, "Item 14", R.drawable.ic_launcher));
        items.add(new Packing(15, "Item 15", R.drawable.ic_launcher));
        items.add(new Packing(16, "Item 16", R.drawable.ic_launcher));
        items.add(new Packing(17, "Item 17", R.drawable.ic_launcher));
        items.add(new Packing(18, "Item 18", R.drawable.ic_launcher));
        items.add(new Packing(19, "Item 19", R.drawable.ic_launcher));
        items.add(new Packing(20, "Item 20", R.drawable.ic_launcher));
        items.add(new Packing(21, "Item 21", R.drawable.ic_launcher));
        items.add(new Packing(22, "Item 22", R.drawable.ic_launcher));
        items.add(new Packing(23, "Item 23", R.drawable.ic_launcher));
        items.add(new Packing(24, "Item 24", R.drawable.ic_launcher));
        items.add(new Packing(25, "Item 25", R.drawable.ic_launcher));
        items.add(new Packing(26, "Item 26", R.drawable.ic_launcher));
        items.add(new Packing(27, "Item 27", R.drawable.ic_launcher));
        items.add(new Packing(28, "Item 28", R.drawable.ic_launcher));
        items.add(new Packing(29, "Item 29", R.drawable.ic_launcher));
        items.add(new Packing(30, "Item 30", R.drawable.ic_launcher));
        items.add(new Packing(31, "Item 31", R.drawable.ic_launcher));
        items.add(new Packing(32, "Item 32", R.drawable.ic_launcher));
        items.add(new Packing(33, "Item 33", R.drawable.ic_launcher));
        items.add(new Packing(34, "Item 34", R.drawable.ic_launcher));
        items.add(new Packing(35, "Item 35", R.drawable.ic_launcher));
        items.add(new Packing(36, "Item 36", R.drawable.ic_launcher));
        items.add(new Packing(37, "Item 37", R.drawable.ic_launcher));
        items.add(new Packing(38, "Item 38", R.drawable.ic_launcher));
        items.add(new Packing(39, "Item 39", R.drawable.ic_launcher));
        items.add(new Packing(40, "Item 40", R.drawable.ic_launcher));
        items.add(new Packing(41, "Item 41", R.drawable.ic_launcher));
        items.add(new Packing(42, "Item 42", R.drawable.ic_launcher));
        items.add(new Packing(43, "Item 43", R.drawable.ic_launcher));
        items.add(new Packing(44, "Item 44", R.drawable.ic_launcher));
        items.add(new Packing(45, "Item 45", R.drawable.ic_launcher));
        items.add(new Packing(46, "Item 46", R.drawable.ic_launcher));
        return items;*/
    }

    public void deletePacking(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("packing", "id = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

}
