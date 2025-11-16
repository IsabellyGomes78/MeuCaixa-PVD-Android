package com.oliveira.minhacaixa;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Venda.class, Product.class, ItemVenda.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao productDao();
    public abstract VendaDao vendaDao();

    private static volatile AppDatabase INSTANCE;

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE vendas ADD COLUMN data INTEGER NOT NULL DEFAULT 0");
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "meu_caixa_database")
                            // CORREÇÃO: Destrói e recria o banco se a migração falhar.
                            .fallbackToDestructiveMigration()
                            .addMigrations(MIGRATION_3_4)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
