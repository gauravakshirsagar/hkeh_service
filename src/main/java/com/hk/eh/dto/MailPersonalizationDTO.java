package com.hk.eh.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailPersonalizationDTO {
    private List<MailUserDTO> to;
    private String subject;
}
