package com.june.apiserver.repository;

import com.june.apiserver.domain.Member;
import com.june.apiserver.domain.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void member_를_생성한다() {
        Member member = Member.builder()
                .email("")
                .nickname("")
                .password("")
                .build();

        member.addMemberRole(MemberRole.USER);
    }
}
