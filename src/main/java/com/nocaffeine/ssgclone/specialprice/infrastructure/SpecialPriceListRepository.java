package com.nocaffeine.ssgclone.specialprice.infrastructure;
import com.nocaffeine.ssgclone.specialprice.domain.SpecialPriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpecialPriceListRepository extends JpaRepository<SpecialPriceList, Long> {
    @Query("SELECT MIN(p.price) FROM SpecialPriceList sql JOIN sql.product p WHERE sql.specialPrice.id = :specialPriceId")
    int findMinPriceBySpecialPriceId(@Param("specialPriceId")Long specialPriceId);
    List<SpecialPriceList> findBySpecialPriceId(Long specialPriceId);
}
