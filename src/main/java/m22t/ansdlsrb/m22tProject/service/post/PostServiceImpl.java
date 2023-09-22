package m22t.ansdlsrb.m22tProject.service.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m22t.ansdlsrb.m22tProject.data.dto.PostDto;
import m22t.ansdlsrb.m22tProject.data.entity.PostEntity;
import m22t.ansdlsrb.m22tProject.data.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    @Override
    public void savePost(PostDto postDto) {
        PostEntity postEntity = new PostEntity();

        postEntity.setTitle(postDto.getTitle());
        postEntity.setCost(postDto.getCost());
        postEntity.setContent(postDto.getContent());
        postEntity.setNickname(postDto.getNickname());

        postRepository.save(postEntity);

    }

    @Override
    public List<PostDto> findAllPost() {
        List<PostEntity> postEntityList = postRepository.findAll();

        List<PostDto> postDtoList = new ArrayList<>();

        for (PostEntity postEntity : postEntityList) {
            PostDto postDto = new PostDto();
            // PostEntity를 PostDto로 복사합니다.
            BeanUtils.copyProperties(postEntity, postDto);
            postDtoList.add(postDto);
        }

        return postDtoList;

    }
}
