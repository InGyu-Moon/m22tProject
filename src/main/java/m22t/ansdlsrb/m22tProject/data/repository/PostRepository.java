package m22t.ansdlsrb.m22tProject.data.repository;

import m22t.ansdlsrb.m22tProject.data.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
