package com.june.apiserver.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "memberRoleList")
public class Member {
    @Id
    private String email;

    @Setter
    private String password;
    @Setter
    private String nickname;
    @Setter
    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    public void addMemberRole(MemberRole memberRole) {
        memberRoleList.add(memberRole);
    }

    public void removeMemberRole() {
        memberRoleList.clear();
    }
}
