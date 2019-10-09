package com.hk.eh.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SendGridMailTemplateDTO {
    private MailUserDTO from;
    private List<MailPersonalizationDTO> personalizations;
    private List<MailContentDTO> content;
}
