package com.nocaffeine.ssgclone.member.infrastructure;

import com.nocaffeine.ssgclone.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByUuid(String uuid);

    default Member findByUuidOrThrow(String uuid) {
        return findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    }
}
