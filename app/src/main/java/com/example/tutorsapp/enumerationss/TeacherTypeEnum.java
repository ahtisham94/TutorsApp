package com.example.tutorsapp.enumerationss;

import com.example.tutorsapp.models.TeacherType;

public enum TeacherTypeEnum {
    PROFESSIONAL_TEACHER(new TeacherType(0)),
    QURAN_TEACHER(new TeacherType(1)),
    ACADEMIC_INSTITUTE(new TeacherType(2)),
    BOOK_STORE(new TeacherType(3)),
    ACADEMY_PICK_OUTSIDE(new TeacherType(4)),
    ACADEMEY_INNER_PICK(new TeacherType(5)),
    PART_TIME_AVAILIBILITY(new TeacherType(1)),
    FULL_TIME_AVAILIBILITY(new TeacherType(2));

    public TeacherType teacherType;

    TeacherTypeEnum(TeacherType teacherType) {
        this.teacherType = teacherType;
    }
}
