package com.hk.eh.dto;

import com.hk.eh.model.Member;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private List<Member> data;
}
