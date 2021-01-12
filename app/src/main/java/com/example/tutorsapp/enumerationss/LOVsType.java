package com.example.tutorsapp.enumerationss;

import com.example.tutorsapp.models.LOVSRequestModel;
import com.example.tutorsapp.models.TeacherType;

public enum LOVsType {
    CITES_LOVS(new LOVSRequestModel(false, "", "1904")),
    BANKS_LOVS(new LOVSRequestModel(false, "", "1905")),
    DEGREE_MAIN(new LOVSRequestModel(false, "", "1903")),
    TEACHING_CATE_MAIN(new LOVSRequestModel(false, "", "1901")),
    ACADEMY_TEACHING_CATE(new LOVSRequestModel(false, "", "1906")),
    QURAN_TEACHING_CATE_MAIN(new LOVSRequestModel(true, "", "1901"));


    public LOVSRequestModel lovsRequestModel;

    LOVsType(LOVSRequestModel teacherType) {
        this.lovsRequestModel = teacherType;
    }
}
