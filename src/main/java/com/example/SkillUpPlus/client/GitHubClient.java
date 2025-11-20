package com.example.SkillUpPlus.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GitHubClient {

    private final WebClient webClient;
    public GitHubClient(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder.baseUrl("https://api.github.com/").build();

    }
    public GitHubUserDTO getUserByUsername(String username) {

        return this.webClient.get().uri("/users/{username}", username)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        clientResponse -> clientResponse.createException().map(
                                e -> new RuntimeException("Usuário do GitHub '" + username + "' não encontrado.")
                        ))
                .bodyToMono(GitHubUserDTO.class).block();

    }

}