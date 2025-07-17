-- V9999__seed_data.sql
-- Script de inserção de dados iniciais (seed data) aprimorado.

-- Inserindo Tags das Atividades (re-inserindo para garantir que existam)
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

-- Senha bcrypt para '123456'
DECLARE @hashedPassword VARCHAR(255) = '$2a$10$qiT8Ap2EyOqpU8KN96Gt3u6sCmYxd0OqtN9HRI8marZHOrdPUPB16';

-- Inserindo Endereços (3 para voluntários, 3 para ONGs)
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES ('Brasil', 'SP', 'São Paulo', '01001-001', 'Rua Principal', '10', 'Centro');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES ('Brasil', 'RJ', 'Rio de Janeiro', '20000-002', 'Av. Atlântica', '20', 'Copacabana');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES ('Brasil', 'MG', 'Belo Horizonte', '30000-003', 'Rua da Paz', '30', 'Lourdes');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES ('Brasil', 'PE', 'Recife', '50000-006', 'Rua da Aurora', '60', 'Boa Vista');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES ('Brasil', 'CE', 'Fortaleza', '60000-007', 'Av. Beira Mar', '70', 'Meireles');
INSERT INTO endereco (pais, estado, cidade, cep, rua, numero, bairro) VALUES ('Brasil', 'RS', 'Porto Alegre', '90000-008', 'Rua da Praia', '80', 'Centro Histórico');


-- Inserindo 3 Usuários Voluntários e seus Voluntários
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('ana.silva@boacao.com', @hashedPassword, (SELECT id FROM role WHERE authority = 'ROLE_VOLUNTARIO'), '2024-01-01 10:00:00');
INSERT INTO voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id, endereco_id) VALUES ('Ana Silva', '11122233344', '11911111111', '1995-03-10', (SELECT id FROM usuario WHERE email = 'ana.silva@boacao.com'), (SELECT id FROM endereco WHERE rua = 'Rua Principal' AND numero = '10'));

INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('bruno.costa@boacao.com', @hashedPassword, (SELECT id FROM role WHERE authority = 'ROLE_VOLUNTARIO'), '2024-01-05 11:00:00');
INSERT INTO voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id, endereco_id) VALUES ('Bruno Costa', '22233344455', '21922222222', '1992-07-20', (SELECT id FROM usuario WHERE email = 'bruno.costa@boacao.com'), (SELECT id FROM endereco WHERE rua = 'Av. Atlântica' AND numero = '20'));

INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('carla.dias@boacao.com', @hashedPassword, (SELECT id FROM role WHERE authority = 'ROLE_VOLUNTARIO'), '2024-01-10 12:00:00');
INSERT INTO voluntario (nome_completo, cpf, telefone, data_nascimento, usuario_id, endereco_id) VALUES ('Carla Dias', '33344455566', '31933333333', '1988-11-05', (SELECT id FROM usuario WHERE email = 'carla.dias@boacao.com'), (SELECT id FROM endereco WHERE rua = 'Rua da Paz' AND numero = '30'));


-- Inserindo 3 Usuários ONG e suas ONGs
INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('ong.esperanca.viva@boacao.com', @hashedPassword, (SELECT id FROM role WHERE authority = 'ROLE_ONG'), '2024-02-01 10:00:00');
INSERT INTO ong (nome_entidade, cnpj, telefone, usuario_id, endereco_id) VALUES ('ONG Esperança Viva', '00000000000101', '1130000001', (SELECT id FROM usuario WHERE email = 'ong.esperanca.viva@boacao.com'), (SELECT id FROM endereco WHERE rua = 'Rua da Aurora' AND numero = '60'));

INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('ong.maos.unidas@boacao.com', @hashedPassword, (SELECT id FROM role WHERE authority = 'ROLE_ONG'), '2024-02-05 11:00:00');
INSERT INTO ong (nome_entidade, cnpj, telefone, usuario_id, endereco_id) VALUES ('ONG Mãos Unidas', '00000000000102', '2130000002', (SELECT id FROM usuario WHERE email = 'ong.maos.unidas@boacao.com'), (SELECT id FROM endereco WHERE rua = 'Av. Beira Mar' AND numero = '70'));

INSERT INTO usuario (email, senha, role_id, criado_em) VALUES ('ong.amigo.animal@boacao.com', @hashedPassword, (SELECT id FROM role WHERE authority = 'ROLE_ONG'), '2024-02-10 12:00:00');
INSERT INTO ong (nome_entidade, cnpj, telefone, usuario_id, endereco_id) VALUES ('ONG Amigo Animal', '00000000000103', '3130000003', (SELECT id FROM usuario WHERE email = 'ong.amigo.animal@boacao.com'), (SELECT id FROM endereco WHERE rua = 'Rua da Praia' AND numero = '80'));


-- Inserindo Atividades para cada ONG (2 atividades por ONG)
-- ONG 1: ONG Esperança Viva
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, data_atividade, id_ong, id_tag, titulo) VALUES
('Apoio Escolar', 'Auxílio a crianças em idade escolar com lições de casa.', 'TARDE', '3 horas', 'Rua da Esperança, 123, São Paulo - SP', 1, 'ANDAMENTO', '2024-04-01 09:00:00', '2024-08-15 14:00:00', (SELECT id FROM ong WHERE nome_entidade = 'ONG Esperança Viva'), (SELECT id FROM tag WHERE nome = 'Educação e Mentoria'), 'Reforço Escolar');
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, data_atividade, id_ong, id_tag, titulo) VALUES
('Campanha de Agasalho', 'Coleta e distribuição de agasalhos para pessoas em situação de rua.', 'MANHA', '5 horas', 'Praça Central, s/n, São Paulo - SP', 0, 'ANDAMENTO', '2024-04-10 10:00:00', '2024-07-20 09:00:00', (SELECT id FROM ong WHERE nome_entidade = 'ONG Esperança Viva'), (SELECT id FROM tag WHERE nome = 'Assistência Social e Comunitária'), 'Inverno Solidário');

-- ONG 2: ONG Mãos Unidas
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, data_atividade, id_ong, id_tag, titulo) VALUES
('Visita a Hospitais', 'Apoio emocional e recreação para pacientes hospitalizados.', 'TARDE', '4 horas', 'Hospital Municipal, 456, Rio de Janeiro - RJ', 1, 'ANDAMENTO', '2024-04-15 13:00:00', '2024-09-01 15:00:00', (SELECT id FROM ong WHERE nome_entidade = 'ONG Mãos Unidas'), (SELECT id FROM tag WHERE nome = 'Cuidado com Pessoas'), 'Sorriso no Hospital');
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, data_atividade, id_ong, id_tag, titulo) VALUES
('Aulas de Yoga', 'Sessões de yoga para a comunidade local.', 'NOITE', '2 horas', 'Centro Comunitário, 789, Rio de Janeiro - RJ', 0, 'ANDAMENTO', '2024-04-20 18:00:00', '2024-08-25 19:00:00', (SELECT id FROM ong WHERE nome_entidade = 'ONG Mãos Unidas'), (SELECT id FROM tag WHERE nome = 'Saúde e Bem-Estar'), 'Bem-Estar em Comunidade');

-- ONG 3: ONG Amigo Animal
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, data_atividade, id_ong, id_tag, titulo) VALUES
('Cuidado com Animais', 'Auxílio na alimentação e limpeza de abrigos de animais.', 'MANHA', '6 horas', 'Abrigo Patas Felizes, 101, Belo Horizonte - MG', 1, 'ANDAMENTO', '2024-05-01 08:00:00', '2024-08-10 09:00:00', (SELECT id FROM ong WHERE nome_entidade = 'ONG Amigo Animal'), (SELECT id FROM tag WHERE nome = 'Proteção Animal'), 'Dia do Voluntário Pet');
INSERT INTO atividade (nome, descricao, periodo, carga_horaria_diaria, endereco_completo, possui_certificacao, status_atividade, criado_em, data_atividade, id_ong, id_tag, titulo) VALUES
('Feira de Adoção', 'Organização e suporte em eventos de adoção de animais.', 'MANHA', '8 horas', 'Parque da Cidade, s/n, Belo Horizonte - MG', 0, 'ANDAMENTO', '2024-05-05 10:00:00', '2024-07-05 10:00:00', (SELECT id FROM ong WHERE nome_entidade = 'ONG Amigo Animal'), (SELECT id FROM tag WHERE nome = 'Eventos e Logística'), 'Adote um Amigo');


-- Associando ONGs com tags de atividades (2 tags aleatórias por ONG)
INSERT INTO ongs_tags (ong_id, tag_id) VALUES ((SELECT id FROM ong WHERE nome_entidade = 'ONG Esperança Viva'), (SELECT id FROM tag WHERE nome = 'Educação e Mentoria'));
INSERT INTO ongs_tags (ong_id, tag_id) VALUES ((SELECT id FROM ong WHERE nome_entidade = 'ONG Esperança Viva'), (SELECT id FROM tag WHERE nome = 'Assistência Social e Comunitária'));

INSERT INTO ongs_tags (ong_id, tag_id) VALUES ((SELECT id FROM ong WHERE nome_entidade = 'ONG Mãos Unidas'), (SELECT id FROM tag WHERE nome = 'Cuidado com Pessoas'));
INSERT INTO ongs_tags (ong_id, tag_id) VALUES ((SELECT id FROM ong WHERE nome_entidade = 'ONG Mãos Unidas'), (SELECT id FROM tag WHERE nome = 'Saúde e Bem-Estar'));

INSERT INTO ongs_tags (ong_id, tag_id) VALUES ((SELECT id FROM ong WHERE nome_entidade = 'ONG Amigo Animal'), (SELECT id FROM tag WHERE nome = 'Proteção Animal'));
INSERT INTO ongs_tags (ong_id, tag_id) VALUES ((SELECT id FROM ong WHERE nome_entidade = 'ONG Amigo Animal'), (SELECT id FROM tag WHERE nome = 'Eventos e Logística'));


-- Associando voluntários com tags de atividades (para Ana Silva)
INSERT INTO voluntarios_tags (voluntario_id, tag_id) VALUES (
    (SELECT id FROM voluntario WHERE nome_completo = 'Ana Silva'),
    (SELECT id FROM tag WHERE nome = 'Educação e Mentoria')
);
INSERT INTO voluntarios_tags (voluntario_id, tag_id) VALUES (
    (SELECT id FROM voluntario WHERE nome_completo = 'Ana Silva'),
    (SELECT id FROM tag WHERE nome = 'Meio Ambiente e Sustentabilidade')
);
INSERT INTO voluntarios_tags (voluntario_id, tag_id) VALUES (
    (SELECT id FROM voluntario WHERE nome_completo = 'Ana Silva'),
    (SELECT id FROM tag WHERE nome = 'Alimentação Comunitária')
);

-- Inserindo Avaliações e Histórico de Atividade para o Voluntário 1 (Ana Silva)
-- Atividade 1: Reforço Escolar (ONG Esperança Viva)
INSERT INTO avaliacao (is_avaliado, estrelas, feedback, ong_id) VALUES (
    1, 5, 'Experiência excelente, voluntária muito dedicada.',
    (SELECT id FROM ong WHERE nome_entidade = 'ONG Esperança Viva')
);
INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura, avaliacao_id, encerrado_em) VALUES (
    (SELECT id FROM voluntario WHERE nome_completo = 'Ana Silva'),
    (SELECT id FROM atividade WHERE titulo = 'Reforço Escolar'),
    '2024-04-05 10:00:00', 1, 'REALIZADO',
    (SELECT id FROM avaliacao WHERE feedback = 'Experiência excelente, voluntária muito dedicada.'),
    '2024-08-15'
);

-- Atividade 2: Adote um Amigo (ONG Amigo Animal) - Antiga Praia Limpa, ajustada para uma ONG existente
INSERT INTO avaliacao (is_avaliado, estrelas, feedback, ong_id) VALUES (
    1, 4, 'Ana ajudou muito na organização da feira de adoção.',
    (SELECT id FROM ong WHERE nome_entidade = 'ONG Amigo Animal')
);
INSERT INTO historico_atividade (voluntario_id, atividade_id, data_inscricao, certificado, status_candidatura, avaliacao_id, encerrado_em) VALUES (
    (SELECT id FROM voluntario WHERE nome_completo = 'Ana Silva'),
    (SELECT id FROM atividade WHERE titulo = 'Adote um Amigo'),
    '2024-05-12 08:00:00', 0, 'REALIZADO',
    (SELECT id FROM avaliacao WHERE feedback = 'Ana ajudou muito na organização da feira de adoção.'),
    '2024-07-05'
);