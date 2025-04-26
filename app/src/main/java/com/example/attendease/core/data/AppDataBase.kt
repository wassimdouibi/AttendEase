package com.example.attendease.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.attendease.core.data.dao.AttendanceDao
import com.example.attendease.core.data.dao.ClassInfoDao
import com.example.attendease.core.data.dao.StudentAttendanceDao
import com.example.attendease.core.data.entity.Attendance
import com.example.attendease.core.data.entity.ClassInfo
import com.example.attendease.core.data.entity.StudentAttendance
import kotlin.jvm.java

@Database(
    entities = [ClassInfo::class, Attendance::class, StudentAttendance::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getClassInfoDao(): ClassInfoDao
    abstract fun getAttendanceDao(): AttendanceDao
    abstract fun getStudentAttendanceDao(): StudentAttendanceDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun buildDatabase(context: Context): AppDataBase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "attendance_database"
                    )
                    .allowMainThreadQueries()
                    .build()
                }
            }
            return INSTANCE
        }
    }
}