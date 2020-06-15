package com.bugkillers.sea.domain.entity.member;

public enum MemberRole {
    CUSTOMER("소비자"), ARTIST("예술가");

    private String role;
    MemberRole(String role){
        this.role=role;
    }
}
