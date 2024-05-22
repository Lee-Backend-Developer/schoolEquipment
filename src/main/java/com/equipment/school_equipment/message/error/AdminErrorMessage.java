package com.equipment.school_equipment.message.error;

public interface AdminErrorMessage {

    //Category
    String CATEGORY_ADD_ERROR = "카테고리 이름을 입력하세요.";
    String CATEGORY_EDIT_NAME_ERROR = "변경 할 카테고리 입력하세요.";

    //Equipment
    String EQUIPMENT_ADD_ERROR = "장비 이름을 입력하세요.";
    String EQUIPMENT_ADD_CONTENT_ERROR = "장비 설명을 입력하세요.";
    String EQUIPMENT_ADD_COUNT_ERROR = "장비 수량을 입력하세요.";
    String EQUIPMENT_ADD_CATEGORY_ERROR = "장비 카테고리를 선택하세요.";

    //viewWeek
    String CLASSTIMES_ADD_ERROR = "수업 이름을 입력하세요.";
    String CLASSTIMES_ADD_DAYOFWEEK_ERROR = "요일을 선택하세요.";
    String CLASSTIMES_ADD_TIME_ERROR = "수업 시간을 선택하세요.";

    //Rental
    String RENTAL_ADD_EQUIPMENT_ERROR = "장비를 선택하세요.";
    String RENTAL_ADD_WEEKDAY_ERROR = "요일을 선택하세요.";
    String RENTAL_ADD_CLASSROOM_ERROR = "수업을 선택하세요.";
    String RENTAL_ADD_COUNT_ERROR = "대여 수량을 입력하세요.";
}
