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


