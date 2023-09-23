package m22t.ansdlsrb.m22tProject.service.post;

import m22t.ansdlsrb.m22tProject.data.dto.PostDto;

import java.util.List;

public interface PostService {
    public void savePost(PostDto postDto);
    public PostDto findPostById(Long postId);
    public List<PostDto> findAllPost();
    public void updatePost(PostDto postDto);
    public void deletePost(Long postId);
}
