package com.example.attendease.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.attendease.core.data.dao.AttendanceDao
import com.example.attendease.core.data.dao.ClassInfoDao
import com.example.attendease.core.data.dao.StudentAttendanceDao
import com.example.attendease.core.data.dao.StudentDao
import com.example.attendease.core.data.entity.Attendance
import com.example.attendease.core.data.entity.ClassInfo
import com.example.attendease.core.data.entity.Student
import com.example.attendease.core.data.entity.StudentAttendanceEntity
import kotlin.jvm.java

@Database(
    entities = [
        ClassInfo::class,
        Student::class,
        Attendance::class,
        StudentAttendanceEntity::class
    ],
    version = 4
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getClassInfoDao(): ClassInfoDao
    abstract fun getStudentDao(): StudentDao
    abstract fun getAttendanceDao(): AttendanceDao
    abstract fun studentAttendanceDao(): StudentAttendanceDao

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
                    .fallbackToDestructiveMigration()
                    .build()
                }
            }
            return INSTANCE
        }
    }
}