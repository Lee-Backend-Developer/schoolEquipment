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
}
