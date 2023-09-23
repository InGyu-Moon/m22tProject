package m22t.ansdlsrb.m22tProject.data.repository;

import m22t.ansdlsrb.m22tProject.data.entity.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<ReserveEntity,Long> {
    List<ReserveEntity> findByNickname(String nickname);
}
