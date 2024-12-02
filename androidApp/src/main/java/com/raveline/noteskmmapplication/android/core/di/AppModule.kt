package com.raveline.noteskmmapplication.android.core.di

import android.app.Application
import com.raveline.noteskmmapplication.data.database.DatabaseDriverFactory
import com.raveline.noteskmmapplication.data.datasource_impl.NoteDataSourceImpl
import com.raveline.noteskmmapplication.database.NotesDatabase
import com.raveline.noteskmmapplication.domain.datasource.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun providesNoteDataSource(sqlDriver: SqlDriver): NoteDataSource {
        return NoteDataSourceImpl(NotesDatabase(sqlDriver))
    }
}