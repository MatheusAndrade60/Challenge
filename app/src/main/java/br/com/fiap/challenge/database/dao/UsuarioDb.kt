package br.com.fiap.challenge.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.challenge.model.Email
import br.com.fiap.challenge.model.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class UsuarioDb : RoomDatabase(){
    abstract fun usuarioDao() : UsuarioDao
    companion object{
        private lateinit var instace: UsuarioDb

        fun getDatabase(context: Context): UsuarioDb{
            if (!::instace.isInitialized){
                instace = Room
                    .databaseBuilder(
                        context,
                        UsuarioDb::class.java,
                        "usuarios_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instace
        }
    }
}