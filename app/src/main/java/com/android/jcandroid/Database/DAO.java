package com.android.jcandroid.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.jcandroid.Model.CustomerModel;

import java.util.ArrayList;

public class DAO {

    public static boolean createCustomer(Context context, CustomerModel customer) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_NAME, customer.getName());
        contentValues.put(DBHelper.COLUMN_PHONE, customer.getPhone());
        contentValues.put(DBHelper.COLUMN_ADDRESS, customer.getAddress());
        contentValues.put(DBHelper.COLUMN_L, customer.getL());
        contentValues.put(DBHelper.COLUMN_C, customer.getC());
        contentValues.put(DBHelper.COLUMN_W, customer.getW());
        contentValues.put(DBHelper.COLUMN_H, customer.getH());
        contentValues.put(DBHelper.COLUMN_T, customer.getT());
        contentValues.put(DBHelper.COLUMN_S, customer.getS());
        contentValues.put(DBHelper.COLUMN_B, customer.getB());
        contentValues.put(DBHelper.COLUMN_M, customer.getM());
        contentValues.put(DBHelper.COLUMN_NF, customer.getNf());
        contentValues.put(DBHelper.COLUMN_NB, customer.getNb());
        contentValues.put(DBHelper.COLUMN_CHK, customer.getChk());
        contentValues.put(DBHelper.COLUMN_GHR, customer.getGhr());
        contentValues.put(DBHelper.COLUMN_SLVR, customer.getSlvr());
        contentValues.put(DBHelper.COLUMN_P, customer.getP());
        contentValues.put(DBHelper.COLUMN_TROUSER, customer.getTrouser());
        contentValues.put(DBHelper.COLUMN_THG, customer.getThg());
        contentValues.put(DBHelper.COLUMN_PAJAMI, customer.getPajami());
        contentValues.put(DBHelper.COLUMN_PAJAMI1, customer.getPajami1());
        contentValues.put(DBHelper.COLUMN_PAJAMI2, customer.getPajami2());
        contentValues.put(DBHelper.COLUMN_PAJAMI3, customer.getPajami3());
        contentValues.put(DBHelper.COLUMN_R, customer.getR());
        contentValues.put(DBHelper.COLUMN_BR, customer.getBr());
        contentValues.put(DBHelper.COLUMN_WR, customer.getWr());
        contentValues.put(DBHelper.COLUMN_RW, customer.getRw());
        contentValues.put(DBHelper.COLUMN_RH, customer.getRh());
        contentValues.put(DBHelper.COLUMN_EXTRACOMMENTS, customer.getExtraComments());
        long status = database.insert(DBHelper.TABLE_NAME, null, contentValues);
        helper.close();
        return (status != -1);
    }

    public static ArrayList<CustomerModel> getAllCustomers(Context context) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        ArrayList<CustomerModel> customers = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                customers.add(cursorToModel(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        helper.close();
        return customers;
    }

    private static CustomerModel cursorToModel(Cursor cursor) {
        CustomerModel model = new CustomerModel();
        model.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)));
        model.setPhone(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PHONE)));
        model.setAddress(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ADDRESS)));
        model.setL(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_L)));
        model.setC(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_C)));
        model.setW(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_W)));
        model.setH(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_H)));
        model.setT(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_T)));
        model.setS(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_S)));
        model.setB(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_B)));
        model.setM(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_M)));
        model.setNf(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NF)));
        model.setNb(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NB)));
        model.setChk(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CHK)));
        model.setGhr(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_GHR)));
        model.setSlvr(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SLVR)));
        model.setP(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_P)));
        model.setTrouser(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TROUSER)));
        model.setThg(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_THG)));
        model.setPajami(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PAJAMI)));
        model.setPajami1(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PAJAMI1)));
        model.setPajami2(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PAJAMI2)));
        model.setPajami3(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PAJAMI3)));
        model.setR(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_R)));
        model.setBr(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_BR)));
        model.setWr(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_WR)));
        model.setRw(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_RW)));
        model.setRh(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_RH)));
        model.setExtraComments(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EXTRACOMMENTS)));
        return model;
    }

    public static boolean deleteCustomer(Context context, CustomerModel customer) {
        int rowId = getRowId(context, customer);
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        int status = database.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_ROWID + " = ? ", new String[]{String.valueOf(rowId)});
        return (status != 0);
    }

    public static boolean updateCustomer(Context context, CustomerModel customer, int rowId) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COLUMN_NAME, customer.getName());
        contentValues.put(DBHelper.COLUMN_PHONE, customer.getPhone());
        contentValues.put(DBHelper.COLUMN_ADDRESS, customer.getAddress());
        contentValues.put(DBHelper.COLUMN_L, customer.getL());
        contentValues.put(DBHelper.COLUMN_C, customer.getC());
        contentValues.put(DBHelper.COLUMN_W, customer.getW());
        contentValues.put(DBHelper.COLUMN_H, customer.getH());
        contentValues.put(DBHelper.COLUMN_T, customer.getT());
        contentValues.put(DBHelper.COLUMN_S, customer.getS());
        contentValues.put(DBHelper.COLUMN_B, customer.getB());
        contentValues.put(DBHelper.COLUMN_M, customer.getM());
        contentValues.put(DBHelper.COLUMN_NF, customer.getNf());
        contentValues.put(DBHelper.COLUMN_NB, customer.getNb());
        contentValues.put(DBHelper.COLUMN_CHK, customer.getChk());
        contentValues.put(DBHelper.COLUMN_GHR, customer.getGhr());
        contentValues.put(DBHelper.COLUMN_SLVR, customer.getSlvr());
        contentValues.put(DBHelper.COLUMN_P, customer.getP());
        contentValues.put(DBHelper.COLUMN_TROUSER, customer.getTrouser());
        contentValues.put(DBHelper.COLUMN_THG, customer.getThg());
        contentValues.put(DBHelper.COLUMN_PAJAMI, customer.getPajami());
        contentValues.put(DBHelper.COLUMN_PAJAMI1, customer.getPajami1());
        contentValues.put(DBHelper.COLUMN_PAJAMI2, customer.getPajami2());
        contentValues.put(DBHelper.COLUMN_PAJAMI3, customer.getPajami3());
        contentValues.put(DBHelper.COLUMN_R, customer.getR());
        contentValues.put(DBHelper.COLUMN_BR, customer.getBr());
        contentValues.put(DBHelper.COLUMN_WR, customer.getWr());
        contentValues.put(DBHelper.COLUMN_RW, customer.getRw());
        contentValues.put(DBHelper.COLUMN_RH, customer.getRh());
        contentValues.put(DBHelper.COLUMN_EXTRACOMMENTS, customer.getExtraComments());

        int status = database.update(DBHelper.TABLE_NAME, contentValues, DBHelper.COLUMN_ROWID + " = ? ", new String[]{String.valueOf(rowId)});
        return (status != 0);
    }

    public static int getRowId(Context context, CustomerModel customer) {
        int rowId = 0;
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_NAME, new String[]{"rowid"}, DBHelper.COLUMN_PHONE + " = ? ", new String[]{customer.getPhone()}, null, null, null);

        if (cursor.moveToFirst()) {
            rowId = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ROWID));
        }
        cursor.close();
        helper.close();
        return rowId;
    }

    public static void createCustomerTableFromList(ArrayList<CustomerModel> list, Context context) {

        DBHelper helper = new DBHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        database.execSQL("delete from " + DBHelper.TABLE_NAME);
        for (CustomerModel item : list) {
            ContentValues content = new ContentValues();
            content.put(DBHelper.COLUMN_NAME, item.getName());
            content.put(DBHelper.COLUMN_PHONE, item.getPhone());
            content.put(DBHelper.COLUMN_ADDRESS, item.getAddress());
            content.put(DBHelper.COLUMN_L, item.getL());
            content.put(DBHelper.COLUMN_C, item.getC());
            content.put(DBHelper.COLUMN_W, item.getW());
            content.put(DBHelper.COLUMN_H, item.getH());
            content.put(DBHelper.COLUMN_T, item.getT());
            content.put(DBHelper.COLUMN_S, item.getS());
            content.put(DBHelper.COLUMN_B, item.getB());
            content.put(DBHelper.COLUMN_M, item.getM());
            content.put(DBHelper.COLUMN_NF, item.getNf());
            content.put(DBHelper.COLUMN_NB, item.getNb());
            content.put(DBHelper.COLUMN_CHK, item.getChk());
            content.put(DBHelper.COLUMN_GHR, item.getGhr());
            content.put(DBHelper.COLUMN_SLVR, item.getSlvr());
            content.put(DBHelper.COLUMN_P, item.getP());
            content.put(DBHelper.COLUMN_TROUSER, item.getTrouser());
            content.put(DBHelper.COLUMN_THG, item.getThg());
            content.put(DBHelper.COLUMN_PAJAMI, item.getPajami());
            content.put(DBHelper.COLUMN_PAJAMI1, item.getPajami1());
            content.put(DBHelper.COLUMN_PAJAMI2, item.getPajami2());
            content.put(DBHelper.COLUMN_PAJAMI3, item.getPajami3());
            content.put(DBHelper.COLUMN_R, item.getR());
            content.put(DBHelper.COLUMN_BR, item.getBr());
            content.put(DBHelper.COLUMN_WR, item.getWr());
            content.put(DBHelper.COLUMN_RW, item.getRw());
            content.put(DBHelper.COLUMN_RH, item.getRh());
            content.put(DBHelper.COLUMN_EXTRACOMMENTS, item.getExtraComments());

            database.insert(DBHelper.TABLE_NAME, null, content);
        }
        helper.close();
    }
}