package com.example.test

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.test.data.CheckListRemoteDataSource
import com.example.test.data.CheckListRemoteDataSourceFake
import com.example.test.data.room.db.DbDataSource
import com.example.test.data.room.db.Repository
import com.example.test.data.room.db.RoomDataSource
import com.example.test.data.room.model.MyDatabase
import com.example.test.presentation.CheckListViewModel
import com.example.test.presentation.ToDoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApp : Application() {

    private val dbModule: Module
        get() = module {
            single<DbDataSource> { RoomDataSource(get()) }

            single {
                Room.databaseBuilder(
                    get(),
                    MyDatabase::class.java, "all_with_me_database"
                ).build()
            }
        }

    private val fakeRemoteModule: Module
        get() = module {
            factory<CheckListRemoteDataSource> { CheckListRemoteDataSourceFake() }
        }

    private val moduleSharedPreferences: Module
        get() = module {
            single {
                val context: Context = get()
                context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
            }
        }

    private val viewModelModule: Module
        get() = module {
            factory { Repository(RoomDataSource(get()), CheckListRemoteDataSourceFake()) }
            viewModel { CheckListViewModel(get()) }
            viewModel { ToDoViewModel(get()) }
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                fakeRemoteModule,
                viewModelModule,
                dbModule,
                moduleSharedPreferences
            )
        }
    }
}