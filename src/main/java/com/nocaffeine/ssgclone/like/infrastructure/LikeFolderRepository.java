package com.nocaffeine.ssgclone.like.infrastructure;

import com.nocaffeine.ssgclone.like.domain.LikeFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeFolderRepository extends JpaRepository<LikeFolder, Long> {
}
