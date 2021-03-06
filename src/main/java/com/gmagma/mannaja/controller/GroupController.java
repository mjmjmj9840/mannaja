package com.gmagma.mannaja.controller;

import com.gmagma.mannaja.dto.GroupMemberRequestDto;
import com.gmagma.mannaja.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/createLink")
    public HashMap createLink(@RequestBody GroupMemberRequestDto requestDto) {
        String link = groupService.createGroup(requestDto);
        HashMap result = new HashMap();
        result.put("groupLink", link);

        return result;
    }
}
