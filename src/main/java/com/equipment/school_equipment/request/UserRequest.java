package com.equipment.school_equipment.request;

import com.equipment.school_equipment.domain.member.MemberRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
public class UserRequest{
        @NotEmpty
        private String id;
        @NotEmpty
        private String passwd;
        @NotEmpty
        private String chkPasswd;
        @NotEmpty
        private String name;
        @NotEmpty @Email
        private String email;
        private Boolean kakaoTalk;
        private MemberRole memberRole;

        @Builder
        public UserRequest(String id, String passwd, String chkPasswd, String name, String email, Boolean kakaoTalk) {
                this.id = id;
                this.passwd = passwd;
                this.chkPasswd = chkPasswd;
                this.name = name;
                this.email = email;
                this.kakaoTalk = kakaoTalk;
                this.memberRole = MemberRole.user;
        }

}
