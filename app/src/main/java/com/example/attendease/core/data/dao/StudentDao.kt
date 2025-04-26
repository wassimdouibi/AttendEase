package com.example.attendease.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.attendease.core.data.entity.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM students_table")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM students_table WHERE studentId = :studentId")
    fun getStudentById(studentId: Long): Student

    @Query("SELECT * FROM students_table WHERE groupe = :groupe AND section = :section")
    fun getStudentsByGroup(groupe: String, section: String): Flow<List<Student>>

    @Query("SELECT * FROM students_table WHERE section = :section")
    fun getStudentsBySection(section: String): Flow<List<Student>>

    @Insert
    fun insertStudent(student: Student)

    @Insert
    fun insertStudents(students: List<Student>)

    @Query("DELETE FROM students_table")
    fun deleteAllStudents()

    @Query("DELETE FROM students_table WHERE studentId = :studentId")
    fun deleteStudentById(studentId: Long)

    @Query("DELETE FROM students_table WHERE section = :section AND groupe = :groupe")
    fun deleteStudentsBySection(section: String, groupe: String)

    @Query("DELETE FROM students_table WHERE section = :section")
    fun deleteStudentsBySection(section: String)
}