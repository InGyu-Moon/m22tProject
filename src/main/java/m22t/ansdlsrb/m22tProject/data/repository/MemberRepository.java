package m22t.ansdlsrb.m22tProject.data.repository;

import m22t.ansdlsrb.m22tProject.data.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //Optional 사용 -> MemberServiceImpl의 isEmailUnique, isNickName return값 변경
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
    Optional<MemberEntity> findByNickname(String nickname);

}
