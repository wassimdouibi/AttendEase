package com.example.attendease.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.attendease.core.data.entity.ClassInfo
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ClassInfoDao {
    @Query("SELECT * FROM class_info_table")
    fun getAllClassInfo(): Flow<List<ClassInfo>>

    @Query("SELECT * FROM class_info_table WHERE date = :date")
    fun getClassInfoByDate(date: Date): Flow<List<ClassInfo>>

    @Query("SELECT * FROM class_info_table WHERE groupOrSection = :groupOrSection")
    fun getClassInfoByGroupOrSection(groupOrSection: String): Flow<List<ClassInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setClassInfo(classInfo: ClassInfo) : Long

    @Query("DELETE FROM class_info_table")
    fun deleteAllClassInfo()
}