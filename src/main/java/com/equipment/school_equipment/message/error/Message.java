package com.equipment.school_equipment.message.error;

public interface Message {
    // ClassPeriod
    String CLASSPERIOD_FIND_CLASSROOM_ERROR = "수업이 존재하지 않습니다.";

    // Equipment
    String EQUIPMENT_FIND_ERROR = "기자재가 존재하지 않습니다.";
    String EQUIPMENT_NAME_ERROR = "기자재 이름이 중복됩니다.";

    // Category
    String CATEGORY_FIND_ERROR = "카테고리가 존재하지 않습니다.";

    // Member
    String MEMBER_FIND_ERROR = "회원이 존재하지 않습니다.";
    String Member_Duplicate_ERROR = "이미 사용중인 아이디 입니다.";

    // Rental
    String RENTAL_FIND_ERROR = "대여가 존재하지 않습니다.";
    String RENTAL_MAX_ERROR = "보관하고 있는 장비 수량보다 대여 수량이 더 많습니다.";

    // NotificationContent
    String NOTIFICATIONCONTENT_FIND_ERROR = "공지사항이 존재하지 않습니다. 관리자 문의";

    // 공통
    String ID_VALUE_ERROR = "ID 값이 잘못되었습니다.";
}
