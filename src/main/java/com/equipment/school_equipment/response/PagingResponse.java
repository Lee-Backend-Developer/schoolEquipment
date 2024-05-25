package com.equipment.school_equipment.response;

import lombok.Builder;

public class PagingResponse {
    private int startPage;
    private int currentPage;
    private int endPage;
    private boolean back;
    private boolean next;

    @Builder
    public PagingResponse(int currentPage, int endPage, boolean back, boolean next) {
        this.currentPage = currentPage;
        this.endPage = endPage;
        this.back = back;
        this.next = next;
    }
}
