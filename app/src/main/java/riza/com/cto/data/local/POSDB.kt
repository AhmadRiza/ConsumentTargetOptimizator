package riza.com.cto.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by riza@deliv.co.id on 9/30/19.
 */
/*

@Database(
    entities = [
        Product::class,
        Category::class,
        ProductCategoryCrossRef::class,
        Cart::class,
        POSTransaction::class,
        StockChange::class
    ],
    version = 2
)
@TypeConverters(Converters::class)
abstract class POSDB : RoomDatabase() {
    abstract fun posDao(): POSDAO

    companion object {
        @Volatile
        private var INSTANCE: POSDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): POSDB {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    POSDB::class.java,
                    "pos_database"
                )
                    .addCallback(POSDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }

        private class POSDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {


            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.posDao())
                    }
                }
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        if (BuildConfig.DEBUG) debugScript(database.posDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(posDao: POSDAO) {

        }

        suspend fun debugScript(posDao: POSDAO) {

            posDao.clearCart()

        }
    }

}

 */