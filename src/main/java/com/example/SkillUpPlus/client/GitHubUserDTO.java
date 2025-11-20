package com.example.SkillUpPlus.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUserDTO {

    private String login; private Long id;

    @JsonProperty("html_url")
    private String htmlUrl;

    private Integer followers;
    private String name;

}