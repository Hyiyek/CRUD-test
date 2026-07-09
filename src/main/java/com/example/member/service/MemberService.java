package com.example.member.service;

import com.example.member.dto.*;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;



    // UserService 클래스
    @Transactional
    public CreateMemberResponse save(CreateMemberRequest request) {
        Member member = new Member(
                request.getName(),
                request.getEmail(),
                request.getAddress()
        );
        Member saveMember = memberRepository.save(member);
        return new CreateMemberResponse(
                saveMember.getId(),
                saveMember.getName(),
                saveMember.getEmail(),
                saveMember.getAddress()



        );
    }


    @Transactional(readOnly = true)
    public List<GetMemberResponse> getAll() { // GetUserResponse -> GetMemberResponse
        List<Member> members = memberRepository.findAll(); // User -> Member
        List<GetMemberResponse> dtos = new ArrayList<>();

        for (Member member : members) {
            GetMemberResponse dto = new GetMemberResponse(
                    member.getId(),
                    member.getName(),
                    member.getEmail(),
                    member.getAddress()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    @Transactional(readOnly = true)
    public GetMemberResponse getOne(Long userId) {
        Member member = memberRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        return new GetMemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getAddress()
        );
    }

    @Transactional
    public void delete(Long userId) {
        boolean existence = memberRepository.existsById(userId);
        if (!existence) {
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        }

        memberRepository.deleteById(userId);
    }

    public UpdateMemberResponse update(Long memberId, updateMemberRequest request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        member.update(request.getName(), request.getEmail(), request.getAddress());


        return new UpdateMemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getAddress()
        );
    }
}
