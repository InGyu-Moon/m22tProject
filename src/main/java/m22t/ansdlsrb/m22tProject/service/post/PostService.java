package m22t.ansdlsrb.m22tProject.service.post;

import m22t.ansdlsrb.m22tProject.data.dto.PostDto;

import java.util.List;

public interface PostService {
    public void savePost(PostDto postDto);
    public List<PostDto> findAllPost();
}
