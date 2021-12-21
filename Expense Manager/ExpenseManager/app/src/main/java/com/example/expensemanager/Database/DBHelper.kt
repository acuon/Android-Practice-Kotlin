package com.example.expensemanager.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.expensemanager.Fragments.Income
import com.example.expensemanager.Model.Money
import com.example.expensemanager.RecyclerViewAdapter.Adapter

class DBHelper(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

    companion object {

        val DB_NAME = "dataBase"
        val VERSION = 1

        val INCOME_TABLE = "income"
        val EXPENSE_TABLE = "expense"

        val ID = "id"
        val AMOUNT = "amount"
        val DATE = "date"
        val DESCRIPTION = "description"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_INCOME_TABLE =
            "CREATE TABLE $INCOME_TABLE($ID INTEGER PRIMARY KEY AUTOINCREMENT, $AMOUNT REAL, $DATE TEXT, $DESCRIPTION TEXT)"
        db?.execSQL(CREATE_INCOME_TABLE)

        val CREATE_EXPENSE_TABLE =
            "CREATE TABLE $EXPENSE_TABLE($ID INTEGER PRIMARY KEY AUTOINCREMENT, $AMOUNT REAL, $DATE TEXT, $DESCRIPTION TEXT)"
        db?.execSQL(CREATE_EXPENSE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        
    }

    fun insertIncome(amount: Double, date: String, description: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(AMOUNT, amount)
        values.put(DATE, date)
        values.put(DESCRIPTION, description)

        val idVal = db.insert(INCOME_TABLE, null, values)

        if (idVal.toInt() == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show()
        }
    }

    fun insertExpense(amount: Double, date: String, description: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(AMOUNT, amount)
        values.put(DATE, date)
        values.put(DESCRIPTION, description)

        val idVal = db.insert(EXPENSE_TABLE, null, values)

        if (idVal.toInt() == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show()
        }
    }

    fun getAllIncomes(): ArrayList<Money> {

        val listOfIncomes = ArrayList<Money>()
        val db = readableDatabase
        val query = "SELECT * FROM $INCOME_TABLE"

        val cursor = db?.rawQuery(query, null)
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val id = cursor.getInt(cursor.getColumnIndex(ID))
                val amount = cursor.getDouble(cursor.getColumnIndex(AMOUNT))
                val date = cursor.getString(cursor.getColumnIndex(DATE))
                val description = cursor.getString(cursor.getColumnIndex(DESCRIPTION))

                val money = Money()

                money.id = id
                money.amount = amount
                money.date = date
                money.description = description

                listOfIncomes.add(money)

            } while (cursor.moveToNext())
        }
        cursor?.close()
        return listOfIncomes
    }

    fun getAllExpense(): ArrayList<Money> {

        val listOfExpenses = ArrayList<Money>()
        val db = readableDatabase
        val query = "SELECT * FROM $EXPENSE_TABLE"

        val cursor = db?.rawQuery(query, null)
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val id = cursor.getInt(cursor.getColumnIndex(ID))
                val amount = cursor.getDouble(cursor.getColumnIndex(AMOUNT))
                val date = cursor.getString(cursor.getColumnIndex(DATE))
                val description = cursor.getString(cursor.getColumnIndex(DESCRIPTION))

                val money = Money()

                money.id = id
                money.amount = amount
                money.date = date
                money.description = description

                listOfExpenses.add(money)

            } while (cursor.moveToNext())
        }
        cursor?.close()
        return listOfExpenses
    }

    fun getIncomeSum(): Double {
        var sum = 0.0
        val db = readableDatabase

        var query = "SELECT SUM($AMOUNT) as Total FROM $INCOME_TABLE"

        val cursor = db?.rawQuery(query, null)

        if (cursor?.moveToFirst()!!) {
            sum = cursor.getDouble(0)
        }
        return sum
    }

    fun getExpenseSum(): Double {
        var sum = 0.0
        val db = readableDatabase

        var query = "SELECT SUM($AMOUNT) as Total FROM $EXPENSE_TABLE"

        val cursor = db?.rawQuery(query, null)

        if (cursor?.moveToFirst()!!) {
            sum = cursor.getDouble(0)
        }
        return sum
    }

    fun remove(money: Money, tableName: String) {
        val db = writableDatabase
        if(tableName == "INCOME_TABLE") {
            db.delete(INCOME_TABLE, "$ID=${money.id}", null)
        } else if (tableName == "EXPENSE_TABLE") {
            db.delete(EXPENSE_TABLE, "$ID=${money.id}", null)
        }
    }

    fun edit(money: Money, tableName: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(AMOUNT, money.amount)
        values.put(DATE, money.date)
        values.put(DESCRIPTION, money.description)

        if(tableName == "INCOME_TABLE") {
            db.update(INCOME_TABLE, values,"$ID=${money.id}", null)
        } else if (tableName == "EXPENSE_TABLE") {
            db.update(EXPENSE_TABLE, values,"$ID=${money.id}", null)
        }
    }

}