-- -- -- SEED PARA BANCO DE TESTES

-- -- -- Inserindo Tag das Atividades
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

-- -- Inserindo Endereços
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES ('Brasil', 'SP', 'São Paulo', '01001-000', 'Rua das Palmeiras', '101', 'Bela Vista');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES('Brasil', 'RJ', 'Rio de Janeiro', '22041-001', 'Avenida Copacabana', '202', 'Copacabana');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES('Brasil', 'MG', 'Belo Horizonte', '30130-000', 'Rua Timbiras', '303', 'Funcionários');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES('Brasil', 'RS', 'Porto Alegre', '90010-003', 'Avenida Borges de Medeiros', '404', 'Centro Histórico');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES('Brasil', 'BA', 'Salvador', '40015-250', 'Rua Chile', '505', 'Centro');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES('Brasil', 'PE', 'Recife', '50030-230', 'Rua do Sol', '606', 'Boa Vista');

-- Inserindo papéis (Voluntário e ONG)
INSERT INTO role (authority) VALUES ('ROLE_VOLUNTARIO');
INSERT INTO role (authority) VALUES ('ROLE_ONG');

-- -- Inserindo usuários (Voluntários e ONGs)
-- Voluntários
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('joao.silva@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-02-10 10:00:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('hideyuki.takahashi@aluno.faculdadeimpacta.com.br', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-01-25 11:30:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('pedro.costa@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 1, '2023-03-12 14:45:00');

INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id, endereco_id) VALUES ('João Silva', '12345678901', '11987654321', '1990-05-20', 1, 1);
INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id, endereco_id) VALUES ('Maria Oliveira', '23456789012', '11987654322', '1985-08-15', 2, 2);
INSERT INTO Voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id, endereco_id) VALUES ('Pedro Costa', '34567890123', '11987654323', '1992-03-10', 3, 3);

-- -- ONGs
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('coracao.solidario@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 2, '2023-05-15 14:00:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('esperanca.e.vida@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 2, '2023-06-10 15:30:00');
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('maos.amigas@email.com', '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16', 2, '2023-04-20 11:45:00');


INSERT INTO ong (nome_entidade, cnpj, telefone, usuario_id, endereco_id) VALUES ('Coração Solidário', '96392149000132', '11948745845', 4, 4);
INSERT INTO ong (nome_entidade, cnpj, telefone, usuario_id, endereco_id) VALUES ('Esperança e Vida', '84752149000132', '119454741542', 5, 5);
INSERT INTO ong (nome_entidade, cnpj, telefone, usuario_id, endereco_id) VALUES ('Mãos Amigas', '48754149000132', '11957452148', 6, 6);


-- -- Inserindo Atividades
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, id_ong) VALUES ('Curso de Programação', 'Curso de programação para iniciantes', 'TARDE', '4 horas', 'Rua A, 10 - São Paulo', 1, 'ANDAMENTO', '2025-05-28', 1);
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, id_ong) VALUES ('Ação Social', 'Distribuição de alimentos para moradores de rua', 'MANHA', '3 horas', 'Rua B, 15 - Rio de Janeiro', 0, 'ANDAMENTO', '2025-02-18', 1);
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, id_ong) VALUES ('Plantio de Árvores', 'Atividade de plantio de árvores em áreas urbanas', 'MANHA', '5 horas', 'Rua C, 20 - Belo Horizonte', 1, 'ENCERRADA', '2024-06-20', 2);
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, id_ong) VALUES ('Oficina de Artes', 'Oficina de pintura e escultura para crianças', 'TARDE', '2 horas', 'Rua D, 25 - Curitiba', 0, 'ANDAMENTO','2024-11-11', 2);
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, id_ong) VALUES ('Aulas de Idiomas', 'Aulas de inglês para pessoas em situação de vulnerabilidade', 'NOITE', '3 horas', 'Rua E, 30 - Salvador', 1, 'CONGELADA', '2024-01-10', 3);


-- -- Inserindo Avaliações
INSERT INTO avaliacao (is_avaliado, estrelas, feedback, ong_id) VALUES (1, 4, 'Ótima experiência, aprendi muito com João.', 1);
INSERT INTO avaliacao (is_avaliado, estrelas, feedback, ong_id) VALUES (0, 0, '', 2);
INSERT INTO avaliacao (is_avaliado, estrelas, feedback, ong_id) VALUES (1, 3, 'Ajudinha Top!!', 3);

-- Inserindo Histórico de Atividade
INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura, avaliacao_id, encerrado_em) VALUES (1, 1, '2024-04-01', 1, 'REALIZADO', 1, '2024-06-01');
INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura, avaliacao_id, encerrado_em) VALUES (1, 4, '2025-06-01', 0, 'APROVADO', 2, '2024-09-01');
INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura, encerrado_em) VALUES (2, 2, '2025-05-01', 0, 'APROVADO', null);
INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura, encerrado_em) VALUES (3, 3, '2024-05-05', 1, 'REALIZADO',null);
INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura, encerrado_em) VALUES (2, 5, '2024-07-01', 1, 'REALIZADO', null);
INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura, avaliacao_id, encerrado_em) VALUES (2, 1, '2024-04-01', 1, 'REALIZADO', 3, '2024-06-01');

-- -- Associando ONGs com tags de atividades
INSERT INTO ongs_tags (ong_id, tag_id) VALUES (1, (SELECT id FROM tag WHERE nome = 'Educação e Mentoria'));
INSERT INTO ongs_tags (ong_id, tag_id) VALUES (1, (SELECT id FROM tag WHERE nome = 'Meio Ambiente e Sustentabilidade'));
INSERT INTO ongs_tags (ong_id, tag_id) VALUES (2, (SELECT id FROM tag WHERE nome = 'Assistência Social e Comunitária'));
INSERT INTO ongs_tags (ong_id, tag_id) VALUES (2, (SELECT id FROM tag WHERE nome = 'Cuidado com Pessoas'));
INSERT INTO ongs_tags (ong_id, tag_id) VALUES (3, (SELECT id FROM tag WHERE nome = 'Meio Ambiente e Sustentabilidade'));

-- -- Associando voluntários com tags de atividades
INSERT INTO voluntarios_tags (voluntario_id, tag_id) VALUES (1,(SELECT id FROM tag WHERE nome = 'Meio Ambiente e Sustentabilidade'));
