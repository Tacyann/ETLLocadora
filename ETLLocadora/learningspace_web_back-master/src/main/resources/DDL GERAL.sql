CREATE TABLE curso (
    id   SERIAL                 NOT NULL,
    nome character varying(100) NOT NULL,
    CONSTRAINT curso_pkey PRIMARY KEY (id)
);

CREATE TABLE DISCIPLINA (
    ID  	   SERIAL       NOT NULL,
    NOME 	   VARCHAR(100) NOT NULL,
    PERIODO	   INT 		NOT NULL,
    ID_CURSO       SERIAL       NOT NULL,
    CONSTRAINT DISCIPLINA_PK PRIMARY KEY (ID),
    CONSTRAINT DISCIPLINA_CURSO_FK FOREIGN KEY (ID)
        REFERENCES CURSO (ID)
);


CREATE TABLE TAG (
  ID		SERIAL 	   	NOT NULL,
  DESCRICAO	VARCHAR(50)	NOT NULL,
  CONSTRAINT TAG_PK PRIMARY KEY (ID)
);

CREATE TABLE public.usuario
(
  id SERIAL NOT NULL,
  matricula numeric NOT NULL,
  nome character varying(100) NOT NULL,
  senha character varying(50) NOT NULL,
  email character varying(50) NOT NULL,
  tipo character varying(20) NOT NULL,
  telefone character varying(11) NOT NULL,
  curso serial not null,
  periodo numeric not null,
  foto character varying(50),
  CONSTRAINT usuario_pkey PRIMARY KEY (id),
  CONSTRAINT uk_matricula_usuario UNIQUE (matricula),
  FOREIGN KEY (curso) REFERENCES curso (id),
  CONSTRAINT usuario_tipo_check CHECK (tipo::text = ANY (ARRAY['ADMIN'::character varying, 'ALUNO'::character varying, 'PROFESSOR'::character varying, 'MONITOR'::character varying]::text[])),
  CONSTRAINT usuario_periodo_check CHECK (periodo > 0 and periodo <= 10)
);

CREATE TABLE curso_usuario (
  id          SERIAL 	   NOT NULL,
  nome        VARCHAR(200) NOT NULL,
  instituicao VARCHAR(100) NOT NULL,
  usuario  SERIAL	   NOT NULL,
  FOREIGN KEY (usuario) REFERENCES usuario (id)
);

CREATE TABLE TOPICODISCIPLINA
(
    ID_TOPICO     SERIAL NOT NULL,
    ID_DISCIPLINA SERIAL NOT NULL
);

CREATE TABLE TOPICO
(
    ID_TOPICO     SERIAL      NOT NULL,
    DSC_TOPICO    VARCHAR(50) NOT NULL,
    ID_DISCIPLINA SERIAL      NOT NULL
);

CREATE TABLE QUIZ
(
    ID_QUIZ   SERIAL      NOT NULL,
    DSC_QUIZ  VARCHAR(50) NOT NULL,
    ID_TOPICO SERIAL      NOT NULL
);

CREATE TABLE PERGUNTA
(
    ID_PERGUNTA  SERIAL       NOT NULL,
    ID_QUIZ      SERIAL       NOT NULL,
    DSC_PERGUNTA VARCHAR(200) NOT NULL,
    URL_IMAGEM   VARCHAR(200)
);

CREATE TABLE ESCOLHA
(
    ID_ESCOLHA  SERIAL       NOT NULL,
    ID_PERGUNTA SERIAL       NOT NULL,
    EXP_ESCOLHA VARCHAR(300) NOT NULL,
    DSC_ESCOLHA VARCHAR(100) NOT NULL,
    CORRETA     BOOL         NOT NULL
);

CREATE TABLE PONTUACAO
(
    ID_USUARIO      SERIAL     NOT NULL,
    ID_QUIZ         SERIAL     NOT NULL,
    VALOR_PONTUACAO NUMERIC(3) NOT NULL
);


-- Chaves primárias
ALTER TABLE TOPICO
    ADD CONSTRAINT TOPICO_PK PRIMARY KEY (ID_TOPICO);
ALTER TABLE QUIZ
    ADD CONSTRAINT QUIZ_PK PRIMARY KEY (ID_QUIZ);
ALTER TABLE PERGUNTA
    ADD CONSTRAINT PERGUNTA_PK PRIMARY KEY (ID_PERGUNTA);
ALTER TABLE ESCOLHA
    ADD CONSTRAINT ESCOLHA_PK PRIMARY KEY (ID_PERGUNTA, ID_ESCOLHA);
ALTER TABLE PONTUACAO
    ADD CONSTRAINT PONTUACAO_PK PRIMARY KEY (ID_USUARIO, ID_QUIZ);
ALTER TABLE TOPICODISCIPLINA
    ADD CONSTRAINT TOPICODISCIPLINA_PK PRIMARY KEY (ID_TOPICO, ID_DISCIPLINA);

-- Chaves estrangeiras
ALTER TABLE TOPICO
    ADD CONSTRAINT TOPICO_DISCIPLINA_FK FOREIGN KEY (ID_DISCIPLINA) REFERENCES DISCIPLINA (ID);
ALTER TABLE QUIZ
    ADD CONSTRAINT QUIZ_TOPICO_FK FOREIGN KEY (ID_TOPICO) REFERENCES TOPICO (ID_TOPICO) ON DELETE CASCADE;
ALTER TABLE PERGUNTA
    ADD CONSTRAINT PERGUNTA_QUIZ_FK FOREIGN KEY (ID_QUIZ) REFERENCES QUIZ (ID_QUIZ) ON DELETE CASCADE;
ALTER TABLE ESCOLHA
    ADD CONSTRAINT ESCOLHA_PERGUNTA_FK FOREIGN KEY (ID_PERGUNTA) REFERENCES PERGUNTA (ID_PERGUNTA) ON DELETE CASCADE;
ALTER TABLE PONTUACAO
    ADD CONSTRAINT PONTUACAO_USUARIO_FK FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO (ID);
ALTER TABLE PONTUACAO
    ADD CONSTRAINT PONTUACAO_QUIZ_FK FOREIGN KEY (ID_QUIZ) REFERENCES QUIZ (ID_QUIZ);
ALTER TABLE TOPICODISCIPLINA
    ADD CONSTRAINT TOPICODISCIPLINA_TOPICO_FK FOREIGN KEY (ID_TOPICO) REFERENCES TOPICO (ID_TOPICO);
ALTER TABLE TOPICODISCIPLINA
    ADD CONSTRAINT TOPICODISCIPLINA_DISCIPLINA_FK FOREIGN KEY (ID_DISCIPLINA) REFERENCES DISCIPLINA (ID);

CREATE TABLE POSTAGEM
(
    ID_POSTAGEM    SERIAL      NOT NULL,
    TEXTO_POSTAGEM VARCHAR(250),
    TIT_POSTAGEM   VARCHAR(40) NOT NULL,
    ID_CRIADOR     SERIAL      NOT NULL,
    DATA_POSTAGEM  DATE        NOT NULL,
    ID_DISCIPLINA  SERIAL      NOT NULL
);
CREATE TABLE COMENTARIO
(
    ID_POSTAGEM      SERIAL NOT NULL,
    ID_COMENTARIO    SERIAL NOT NULL,
    ID_USUARIO       SERIAL NOT NULL,
    TEXTO_COMENTARIO VARCHAR(250),
    DATA_COMENTARIO  DATE   NOT NULL
);
CREATE TABLE VOTACAO_POSTAGEM
(
    ID_USUARIO  SERIAL     NOT NULL,
    ID_POSTAGEM SERIAL     NOT NULL,
    VOTO        NUMERIC(1) NOT NULL CHECK (VOTO BETWEEN 0 AND 5)
);
CREATE TABLE VOTACAO_COMENTARIO
(
    ID_USUARIO    SERIAL     NOT NULL,
    ID_COMENTARIO SERIAL     NOT NULL,
    VOTO          NUMERIC(1) NOT NULL CHECK (VOTO BETWEEN 0 AND 5)
);

CREATE TABLE ANEXO_POSTAGEM
(
    ID_POSTAGEM SERIAL       NOT NULL,
    ID_ANEXO    SERIAL       NOT NULL,
    NOME_ANEXO  VARCHAR(100) NOT NULL
);

-- Chaves primárias
ALTER TABLE POSTAGEM
    ADD CONSTRAINT POSTAGEM_PK PRIMARY KEY (ID_POSTAGEM);

ALTER TABLE COMENTARIO
    ADD CONSTRAINT COMENTARIO_PK PRIMARY KEY (ID_COMENTARIO);

ALTER TABLE VOTACAO_POSTAGEM
    ADD CONSTRAINT VOTACAO_POSTAGEM_PK PRIMARY KEY (ID_POSTAGEM, ID_USUARIO);

ALTER TABLE VOTACAO_COMENTARIO
    ADD CONSTRAINT VOTACAO_COMENTARIO_PK PRIMARY KEY (ID_COMENTARIO, ID_USUARIO);


-- Chaves estrangeiras
ALTER TABLE ANEXO_POSTAGEM
    ADD CONSTRAINT ANEXO_PK PRIMARY KEY (ID_POSTAGEM, ID_ANEXO);

ALTER TABLE COMENTARIO
    ADD CONSTRAINT COMENTARIO_POSTAGEM_FK FOREIGN KEY (ID_POSTAGEM)
        REFERENCES POSTAGEM (ID_POSTAGEM) ON DELETE CASCADE;

ALTER TABLE VOTACAO_POSTAGEM
    ADD CONSTRAINT VOTACAO_POSTAGEM_FK FOREIGN KEY (ID_POSTAGEM)
        REFERENCES POSTAGEM (ID_POSTAGEM) ON DELETE CASCADE;

ALTER TABLE VOTACAO_COMENTARIO
    ADD CONSTRAINT VOTACAO_COMENTARIO_FK FOREIGN KEY (ID_COMENTARIO)
        REFERENCES COMENTARIO (ID_COMENTARIO) ON DELETE CASCADE;


ALTER TABLE ANEXO_POSTAGEM
    ADD CONSTRAINT ANEXO_POSTAGEM_FK FOREIGN KEY (ID_POSTAGEM)
        REFERENCES POSTAGEM (ID_POSTAGEM) ON DELETE CASCADE;

ALTER TABLE COMENTARIO
    ADD CONSTRAINT COMENTARIO_USUARIO_FK FOREIGN KEY (ID_USUARIO)
        REFERENCES USUARIO (ID) ON DELETE CASCADE;

ALTER TABLE POSTAGEM
 ADD CONSTRAINT POSTAGEM_DISCIPLINA_FK FOREIGN KEY (ID_DISCIPLINA)
        REFERENCES DISCIPLINA (ID);

CREATE TABLE TAG_POSTAGEM (
  POSTAGEM	SERIAL	NOT NULL,
  TAG		SERIAL	NOT NULL,
  CONSTRAINT TAG_POSTAGEM_PK PRIMARY KEY (POSTAGEM, TAG),
  FOREIGN KEY (TAG) REFERENCES TAG (ID),
  FOREIGN KEY (POSTAGEM) REFERENCES POSTAGEM (ID_POSTAGEM) ON DELETE CASCADE
);


