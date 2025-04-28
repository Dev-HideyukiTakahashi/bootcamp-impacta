package br.com.impacta.boacao.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.JwtGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2AccessTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import br.com.impacta.boacao.config.customgrant.CustomPasswordAuthenticationConverter;
import br.com.impacta.boacao.config.customgrant.CustomPasswordAuthenticationProvider;
import br.com.impacta.boacao.config.customgrant.CustomUserAuthorities;

@Configuration
public class AuthorizationServerConfig {

    @Value("${security.client-id}")
    private String clientId; // ID do cliente para autenticação OAuth2

    @Value("${security.client-secret}")
    private String clientSecret; // Segredo do cliente para autenticação OAuth2

    @Value("${security.jwt.duration}")
    private Integer jwtDurationSeconds; // Duração do JWT em segundos

    @Autowired
    private UserDetailsService userDetailsService;

    // Configuração do filtro de segurança para as requisições de autorização OAuth2
    @Bean
    @Order(2)
    SecurityFilterChain asSecurityFilterChain(HttpSecurity http) throws Exception {

        http.securityMatcher("/oauth2/**").with(OAuth2AuthorizationServerConfigurer.authorizationServer(),
                Customizer.withDefaults());

        // Configura o endpoint de token OAuth2
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .tokenEndpoint(tokenEndpoint -> tokenEndpoint
                        .accessTokenRequestConverter(new CustomPasswordAuthenticationConverter())
                        .authenticationProvider(new CustomPasswordAuthenticationProvider(authorizationService(),
                                tokenGenerator(), userDetailsService, passwordEncoder())));

        // Configura o recurso de servidor OAuth2 (com JWT)
        http.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()));

        return http.build(); // Retorna a configuração do filtro de segurança
    }

    // Serviço de autorização OAuth2 em memória
    @Bean
    OAuth2AuthorizationService authorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }

    // Serviço de consentimento de autorização OAuth2 em memória
    @Bean
    OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService() {
        return new InMemoryOAuth2AuthorizationConsentService();
    }

    // Configuração do codificador de senha (usando BCrypt)
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Repositório de clientes registrados (armazenado em memória)
    @Bean
    RegisteredClientRepository registeredClientRepository() {

        RegisteredClient registeredClient = RegisteredClient
                .withId(UUID.randomUUID().toString()) // Gera um ID único para o cliente
                .clientId(clientId) // Define o ID do cliente
                .clientSecret(passwordEncoder().encode(clientSecret)) // Codifica o segredo do cliente
                .scope("read") // Define o escopo de leitura
                .scope("write") // Define o escopo de escrita
                .authorizationGrantType(new AuthorizationGrantType("password")) // Tipo de concessão de autorização
                .tokenSettings(tokenSettings()) // Configurações do token
                .clientSettings(clientSettings()) // Configurações do cliente
                .build(); // Constrói o cliente registrado

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    // Configurações do token (tempo de vida do token e formato)
    @Bean
    TokenSettings tokenSettings() {

        return TokenSettings.builder()
                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                .accessTokenTimeToLive(Duration.ofSeconds(jwtDurationSeconds))
                .build();
    }

    @Bean
    ClientSettings clientSettings() {
        return ClientSettings.builder().build(); // Retorna as configurações padrão do servidor de autorização
    }

    // Configurações do servidor de autorização
    @Bean
    AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    // Gerador de tokens OAuth2 (gerando JWT e token de acesso)
    @Bean
    OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator() {
        NimbusJwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource()); // Codificador JWT
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder); // Gerador JWT
        jwtGenerator.setJwtCustomizer(tokenCustomizer()); // Customizador de JWT
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(jwtGenerator, accessTokenGenerator);
    }

    // Customizador do token JWT (adiciona informações extras ao token)
    @Bean
    OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return context -> {
            OAuth2ClientAuthenticationToken principal = context.getPrincipal();
            CustomUserAuthorities user = (CustomUserAuthorities) principal.getDetails(); // Obtém os detalhes do usuário
            List<String> authorities = user.getAuthorities().stream()
                    .map(x -> x.getAuthority()).toList(); // Extrai as autoridades do usuário
            if (context.getTokenType().getValue().equals("access_token")) {
                context.getClaims()
                        .claim("authorities", authorities) // Adiciona as 'roles' ao token
                        .claim("username", user.getUsername());
            }
        };
    }

    // Decoder JWT para validar o token JWT recebido
    @Bean
    JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    // Fonte de chave JWK (gera as chaves públicas e privadas)
    @Bean
    JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    private static RSAKey generateRsa() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }
}
