package com.nocaffeine.ssgclone.like.infrastructure;

import com.nocaffeine.ssgclone.like.domain.LikeFolder;
import com.nocaffeine.ssgclone.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeFolderRepository extends JpaRepository<LikeFolder, Long> {
    List<LikeFolder> findByMember(Member member);

    List<LikeFolder> findByIdAndMember(Long id, Member member);
}
