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
import java.util.Optional;

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
    public PostDto findPostById(Long postId) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);
        if(postEntityOptional.isEmpty()){
            return null;
        }
        PostEntity postEntity = postEntityOptional.get();
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(postEntity, postDto);

        return postDto;
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


    @Override
    public void updatePost(PostDto postDto) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postDto.getPostId());
        PostEntity postEntity = postEntityOptional.get();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setCost(postDto.getCost());
        postEntity.setContent(postDto.getContent());
        postRepository.save(postEntity);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
