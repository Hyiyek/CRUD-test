package com.example.member.controller;

import com.example.member.dto.*;
import com.example.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @PostMapping("/members")
    public ResponseEntity<CreateMemberResponse> create(@RequestBody CreateMemberRequest request) {
        CreateMemberResponse result = memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/members")
    public ResponseEntity<List<GetMemberResponse>> getAll() {
        List<GetMemberResponse> result = memberService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/members/{membersId}")
    public ResponseEntity<GetMemberResponse> getOne(@PathVariable Long membersId) {
        GetMemberResponse result = memberService.getOne(membersId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/members/{membersId}")
    public ResponseEntity<UpdateMemberResponse> update(
            @PathVariable Long membersId,
            @RequestBody updateMemberRequest request
    ) {
        UpdateMemberResponse result = memberService.update(membersId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/members/{membersId}")
    public ResponseEntity<Void> delete(@PathVariable Long membersId) {
        memberService.delete(membersId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

