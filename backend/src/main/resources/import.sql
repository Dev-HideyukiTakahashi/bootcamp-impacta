-- -- SEED PARA BANCO DE TESTES

-- -- Inserindo Tag das Atividades
INSERT INTO tag (nome) VALUES ('Administração e Gestão');
INSERT INTO tag (nome) VALUES ('Assistência Social e Comunitária');
INSERT INTO tag (nome) VALUES ('Saúde e Bem-Estar');
INSERT INTO tag (nome) VALUES ('Captação de Recursos e Marketing');
INSERT INTO tag (nome) VALUES ('Produção e Artesanato');
INSERT INTO tag (nome) VALUES ('Alimentação Comunitária');
INSERT INTO tag (nome) VALUES ('Educação e Mentoria');
INSERT INTO tag (nome) VALUES ('Esporte, Cultura e Recreação');
INSERT INTO tag (nome) VALUES ('Comunicação e Mídia');
INSERT INTO tag (nome) VALUES ('Construção e Manutenção');
INSERT INTO tag (nome) VALUES ('Meio Ambiente e Sustentabilidade');
INSERT INTO tag (nome) VALUES ('Cuidado com Pessoas');
INSERT INTO tag (nome) VALUES ('Proteção Animal');
INSERT INTO tag (nome) VALUES ('Suporte Tecnológico');
INSERT INTO tag (nome) VALUES ('Eventos e Logística');
INSERT INTO tag (nome) VALUES ('Tradução e Comunicação Internacional');

-- Inserindo papéis (Voluntário e ONG)
INSERT INTO role (authority) VALUES ('ROLE_VOLUNTARIO');
INSERT INTO role (authority) VALUES ('ROLE_ONG');

-- -- Inserindo usuários (Voluntários e ONGs)
-- Voluntários
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('joao.silva@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-02-10 10:00:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('maria.oliveira@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-01-25 11:30:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('pedro.costa@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-03-12 14:45:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('ana.souza@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-06-01 08:00:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('lucas.almeida@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-04-18 16:20:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('camila.pereira@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-07-01 10:30:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('fernanda.costa@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-05-22 12:15:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('rafael.santos@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-03-28 17:00:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('juliana.rocha@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-04-10 09:00:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('bruno.martins@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-02-15 13:50:00');
-- ONGs
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('coracao.solidario@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 2, '2023-05-15 14:00:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('esperanca.e.vida@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 2, '2023-06-10 15:30:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('maos.amigas@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 2, '2023-04-20 11:45:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('sorriso.de.crianca@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 2, '2023-07-05 16:00:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('projeto.vida.melhor@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 2, '2023-03-30 18:00:00');



 INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento) VALUES ('João Silva', '12345678901', '(11) 98765-4321', '1990-05-20');

-- -- Inserindo ONGs
-- INSERT INTO ong (nome, cnpj, telefone, usuario_id) VALUES ('Coração Solidário', '12345678000100', '(11) 1234-5678', 11);
-- INSERT INTO ong (nome, cnpj, telefone, usuario_id) VALUES ('Esperança e Vida', '22345678000100', '(21) 2345-6789', 12);
-- INSERT INTO ong (nome, cnpj, telefone, usuario_id) VALUES ('Mãos Amigas', '32345678000100', '(31) 3456-7890', 13);
-- INSERT INTO ong (nome, cnpj, telefone, usuario_id) VALUES ('Sorriso de Criança', '42345678000100', '(41) 4567-8901', 14);
-- INSERT INTO ong (nome, cnpj, telefone, usuario_id) VALUES ('Projeto Vida Melhor', '52345678000100', '(51) 5678-9012', 15);


-- -- Inserindo Voluntários
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('João Silva', '12345678901', '(11) 98765-4321', '1990-05-20', 1);
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('Maria Oliveira', '23456789012', '(21) 91234-5678', '1992-07-15', 2);
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('Pedro Costa', '34567890123', '(31) 92345-6789', '1985-03-10', 3);
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('Ana Souza', '45678901234', '(41) 93456-7890', '1995-02-28', 4);
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('Lucas Almeida', '56789012345', '(51) 94567-8901', '1990-08-25', 5);
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('Camila Pereira', '67890123456', '(61) 95678-9012', '1993-06-15', 6);
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('Fernanda Costa', '78901234567', '(71) 96789-0123', '1994-11-10', 7);
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('Rafael Santos', '89012345678', '(81) 97890-1234', '1992-01-05', 8);
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('Juliana Rocha', '90123456789', '(91) 98901-2345', '1988-09-20', 9);
-- INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id) VALUES ('Bruno Martins', '01234567890', '(61) 98012-3456', '1991-04-10', 10);


-- -- Inserindo Endereços
-- INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro, usuario_id) VALUES 
-- ('Brasil', 'SP', 'São Paulo', '12345000', 'Rua A', '10', 'Centro', 1),
-- ('Brasil', 'RJ', 'Rio de Janeiro', '22345000', 'Rua B', '15', 'Zona Norte', 2),
-- ('Brasil', 'MG', 'Belo Horizonte', '30345000', 'Rua C', '20', 'Centro', 3),
-- ('Brasil', 'PR', 'Curitiba', '40345000', 'Rua D', '25', 'Bairro Industrial', 4),
-- ('Brasil', 'BA', 'Salvador', '50345000', 'Rua E', '30', 'Centro', 5),
-- ('Brasil', 'RS', 'Porto Alegre', '60345000', 'Rua F', '35', 'Zona Sul', 6),
-- ('Brasil', 'SP', 'Campinas', '70345000', 'Rua G', '40', 'Vila Progresso', 7),
-- ('Brasil', 'RJ', 'Niterói', '80345000', 'Rua H', '45', 'Icaraí', 8),
-- ('Brasil', 'MG', 'Uberlândia', '90345000', 'Rua I', '50', 'Bairro Um', 9),
-- ('Brasil', 'SP', 'São Bernardo do Campo', '10345000', 'Rua J', '55', 'Vila Rosa', 10),
-- ('Brasil', 'SP', 'São Paulo', '01000-000', 'Rua das Flores', '123', 'Centro', 11),
-- ('Brasil', 'RJ', 'Rio de Janeiro', '20000-000', 'Avenida Atlântica', '456', 'Copacabana', 12),
-- ('Brasil', 'MG', 'Belo Horizonte', '30100-000', 'Rua das Palmeiras', '789', 'Savassi', 13),
-- ('Brasil', 'BA', 'Salvador', '40000-000', 'Avenida da Bahia', '321', 'Barra', 14),
-- ('Brasil', 'PR', 'Curitiba', '80000-000', 'Rua XV de Novembro', '654', 'Centro', 15);


-- -- Associando ROLE_VOLUNTARIO aos usuários de 1 a 10
-- INSERT INTO usuario_role (usuario_id, role_id) VALUES
-- (1, 1),
-- (2, 1),
-- (3, 1),
-- (4, 1),
-- (5, 1),
-- (6, 1),
-- (7, 1),
-- (8, 1),
-- (9, 1),
-- (10, 1);

-- -- Associando ROLE_ONG aos usuários de 11 a 15
-- INSERT INTO usuario_role (usuario_id, role_id) VALUES
--   (11, 2),
--   (12, 2),
--   (13, 2),
--   (14, 2),
--   (15, 2);

-- -- Associando endereços aos usuários (Voluntários e ONGs)
--   UPDATE usuario SET endereco_id = 1 WHERE id = 1;
--   UPDATE usuario SET endereco_id = 2 WHERE id = 2;
--   UPDATE usuario SET endereco_id = 3 WHERE id = 3;
--   UPDATE usuario SET endereco_id = 4 WHERE id = 4;
--   UPDATE usuario SET endereco_id = 5 WHERE id = 5;
--   UPDATE usuario SET endereco_id = 6 WHERE id = 6;
--   UPDATE usuario SET endereco_id = 7 WHERE id = 7;
--   UPDATE usuario SET endereco_id = 8 WHERE id = 8;
--   UPDATE usuario SET endereco_id = 9 WHERE id = 9;
--   UPDATE usuario SET endereco_id = 10 WHERE id = 10;
--   UPDATE usuario SET endereco_id = 11 WHERE id = 11;  
--   UPDATE usuario SET endereco_id = 12 WHERE id = 12;  
--   UPDATE usuario SET endereco_id = 13 WHERE id = 13;  
--   UPDATE usuario SET endereco_id = 14 WHERE id = 14;  
--   UPDATE usuario SET endereco_id = 15 WHERE id = 15;  

-- -- Inserindo Atividades
-- INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, ong_id) VALUES
--   ('Curso de Programação', 'Curso de programação para iniciantes', 'Tarde', '4 horas', 'Rua A, 10 - São Paulo', true, 1);
-- INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, ong_id) VALUES
--   ('Ação Social', 'Distribuição de alimentos para moradores de rua', 'Manhã', '3 horas', 'Rua B, 15 - Rio de Janeiro', false, 2);
-- INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, ong_id) VALUES
--   ('Plantio de Árvores', 'Atividade de plantio de árvores em áreas urbanas', 'Manhã', '5 horas', 'Rua C, 20 - Belo Horizonte', true, 3);
-- INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, ong_id) VALUES
--   ('Oficina de Artes', 'Oficina de pintura e escultura para crianças', 'Tarde', '2 horas', 'Rua D, 25 - Curitiba', false, 4);
-- INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, ong_id) VALUES
--   ('Aulas de Idiomas', 'Aulas de inglês para pessoas em situação de vulnerabilidade', 'Noite', '3 horas', 'Rua E, 30 - Salvador', true, 5);


-- -- Inserindo Histórico de Atividade
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (1, 1, '2024-04-01', true, 'REALIZADO');
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (2, 2, '2025-05-01', false, 'APROVADO');
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (3, 3, '2024-05-05', true, 'REALIZADO');
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (4, 4, '2025-06-01', false, 'APROVADO');
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (5, 5, '2024-07-01', true, 'APROVADO');
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (6, 1, '2024-04-01', true, 'REALIZADO');
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (7, 2, '2025-05-01', false, 'APROVADO');
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (8, 3, '2025-05-05', false, 'PENDENTE');
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (9, 4, '2025-06-01', false, 'APROVADO');
-- INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura) VALUES
--   (10, 5, '2024-07-01', true, 'APROVADO');


-- -- Inserindo Avaliações
-- INSERT INTO avaliacao (id, voluntario_id, ong_id, feedback, estrelas, is_avaliado) VALUES (1, 1, 1, 'Ótima experiência, aprendi muito com João.', 5, true);
-- INSERT INTO avaliacao (id, voluntario_id, ong_id, feedback, estrelas, is_avaliado) VALUES (2, 2, 2, 'Boa experiência, mas poderia melhorar a pontualidade.', 3, true);
-- INSERT INTO avaliacao (id, voluntario_id, ong_id, feedback, estrelas, is_avaliado) VALUES (3, 3, 3, 'Pedro enriqueceu a atividade com seus conhecimentos.', 5, true);
-- INSERT INTO avaliacao (id, voluntario_id, ong_id, feedback, estrelas, is_avaliado) VALUES (4, 4, 4, 'Ana é uma artista fenomenal.', 4, true);
-- INSERT INTO avaliacao (id, voluntario_id, ong_id, feedback, estrelas, is_avaliado) VALUES (5, 5, 5, 'Aulas de qualidade, agragou muito.', 5, true);

	
-- -- Associando voluntários com tags de atividades
-- INSERT INTO voluntario_tag (voluntario_id, tag_id) VALUES
--   (1, (SELECT id FROM tag WHERE nome = 'Educação e Mentoria')),
--   (1, (SELECT id FROM tag WHERE nome = 'Saúde e Bem-Estar')),
--   (2, (SELECT id FROM tag WHERE nome = 'Assistência Social e Comunitária')),
--   (2, (SELECT id FROM tag WHERE nome = 'Cuidado com Pessoas')),
--   (3, (SELECT id FROM tag WHERE nome = 'Meio Ambiente e Sustentabilidade')),
--   (3, (SELECT id FROM tag WHERE nome = 'Saúde e Bem-Estar')),
--   (4, (SELECT id FROM tag WHERE nome = 'Esporte, Cultura e Recreação')),
--   (4, (SELECT id FROM tag WHERE nome = 'Cuidado com Pessoas')),
--   (5, (SELECT id FROM tag WHERE nome = 'Educação e Mentoria')),
--   (5, (SELECT id FROM tag WHERE nome = 'Captação de Recursos e Marketing')),
--   (6, (SELECT id FROM tag WHERE nome = 'Saúde e Bem-Estar')),
--   (7, (SELECT id FROM tag WHERE nome = 'Assistência Social e Comunitária')),
--   (7, (SELECT id FROM tag WHERE nome = 'Cuidado com Pessoas')),
--   (8, (SELECT id FROM tag WHERE nome = 'Meio Ambiente e Sustentabilidade')),
--   (8, (SELECT id FROM tag WHERE nome = 'Saúde e Bem-Estar')),
--   (9, (SELECT id FROM tag WHERE nome = 'Esporte, Cultura e Recreação')),
--   (10, (SELECT id FROM tag WHERE nome = 'Educação e Mentoria')),
--   (10, (SELECT id FROM tag WHERE nome = 'Captação de Recursos e Marketing'));

-- -- Associando ONGs com tags de atividades
-- INSERT INTO ong_tag (ong_id, tag_id) VALUES
--   (1, (SELECT id FROM tag WHERE nome = 'Educação e Mentoria')),
--   (1, (SELECT id FROM tag WHERE nome = 'Meio Ambiente e Sustentabilidade')),
--   (2, (SELECT id FROM tag WHERE nome = 'Assistência Social e Comunitária')),
--   (2, (SELECT id FROM tag WHERE nome = 'Cuidado com Pessoas')),
--   (3, (SELECT id FROM tag WHERE nome = 'Meio Ambiente e Sustentabilidade')),
--   (4, (SELECT id FROM tag WHERE nome = 'Esporte, Cultura e Recreação')),
--   (4, (SELECT id FROM tag WHERE nome = 'Cuidado com Pessoas')),
--   (5, (SELECT id FROM tag WHERE nome = 'Educação e Mentoria')),
--   (5, (SELECT id FROM tag WHERE nome = 'Captação de Recursos e Marketing'));