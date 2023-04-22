package com.panilya.botscrewtesttask.dto;

import com.panilya.botscrewtesttask.database.Degree;

//public record DepartmentDegreeCountDto(Degree degree, Long count) {
//    public DepartmentDegreeCountDto(Degree degree, Long count) {
//        this.degree = degree;
//        this.count = count;
//    }
//}

public interface DepartmentDegreeCountDto {
    Degree getDegree();
    Long getCount();
}