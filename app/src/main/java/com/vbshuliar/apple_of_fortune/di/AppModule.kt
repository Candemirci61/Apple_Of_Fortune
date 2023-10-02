package com.vbshuliar.apple_of_fortune.di

import android.app.Application
import com.vbshuliar.apple_of_fortune.gameplay.domain.use_cases.GenerateTable
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
    fun provideGenerateTable(app: Application): GenerateTable {
        return GenerateTable()
    }
}
