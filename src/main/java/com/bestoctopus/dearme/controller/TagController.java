package com.bestoctopus.dearme.controller;

import com.bestoctopus.dearme.domain.Tag;
import com.bestoctopus.dearme.service.component.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<?> addTag(@RequestParam String name){
        Tag tag = tagService.addTag(name);
        return ResponseEntity.ok().body(tag);
    }
}
