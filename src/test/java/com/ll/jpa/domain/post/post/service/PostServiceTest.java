package com.ll.jpa.domain.post.post.service;

import com.ll.jpa.domain.post.post.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("글 2개 생성")
    @Transactional
    void t1() {
        postService.write("title1", "content1");
        postService.write("title2", "content2");
    }

    @Test
    @DisplayName("findAll")
    void t2() {
        List<Post> all = postService.findAll();
        assertEquals(3, all.size());

        Post post = all.get(0);
        assertEquals("title1", post.getTitle());
    }

    @Test
    @DisplayName("findById")
    void t3() {
        Optional<Post> opPost = postService.findById(1);

        if (opPost.isPresent()) {
            Post post = opPost.get();
            assertEquals("title1", post.getTitle());
        }
    }

    @Test
    @DisplayName("findByTitle")
    void t4() {
        Post post = postService.findByTitle("title1").get(0);
        assertEquals(1, post.getId());
    }
}
