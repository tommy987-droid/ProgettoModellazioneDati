CREATE DATABASE IF NOT EXISTS ospedale;
USE ospedale;

-- 1. Tabella paziente
CREATE TABLE paziente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    codice_fiscale CHAR(16) UNIQUE NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    data_nascita DATE NOT NULL,
    sesso ENUM('m', 'f') NOT NULL
) ENGINE=InnoDB;

-- 2. Tabella medico
CREATE TABLE medico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    specializzazione ENUM(
        'radiologia', 
        'cardiologia', 
        'neurologia',
        'ortopedia', 
        'oncologia', 
        'pediatria'
    ) NOT NULL
) ENGINE=InnoDB;

-- 3. Tabella referto
CREATE TABLE referto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_paziente INT NOT NULL,
    id_medico INT NOT NULL,
    data_visita DATE NOT NULL,
    diagnosi TEXT,
    CONSTRAINT fk_paziente_referto FOREIGN KEY (id_paziente) REFERENCES paziente(id),
    CONSTRAINT fk_medico_referto FOREIGN KEY (id_medico) REFERENCES medico(id)
) ENGINE=InnoDB;

-- 4. Tabella allegati
CREATE TABLE allegati (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_referto INT NOT NULL,
    path VARCHAR(255) NOT NULL,
    tipo ENUM('Immagine', 'Documento', 'Video') NOT NULL,
    CONSTRAINT fk_referto_allegati FOREIGN KEY (id_referto) REFERENCES referto(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- INSERIMENTO DATI PAZIENTI
INSERT INTO paziente (codice_fiscale, nome, cognome, data_nascita, sesso) VALUES
('MRCTMS87B01L219Z', 'Tommaso', 'Muraca', '1987-02-01', 'm'),
('RSSMRA92E55H501W', 'Federica', 'Aprile', '1992-05-15', 'f'),
('VRDGPP65S20F205R', 'Giacomo', 'Gigliotti', '1965-11-20', 'm'),
('BNCHGL80C50G273K', 'Cosimo', 'Bifano', '1980-03-10', 'm'),
('SPSNTN58L22A089X', 'Antonio', 'Esposito', '1958-07-22', 'm'),
('FNTLRA01T45D612J', 'Erika', 'Cavigliano', '2001-12-05', 'f'),
('FRRRRT75P30Z110V', 'Roberto', 'Ferrari', '1975-09-30', 'm'),
('MRTLNE88A52B354S', 'Elena', 'Moretti', '1988-01-12', 'f'),
('RMNMRC99D18H501O', 'Marco', 'Romano', '1999-04-18', 'm'),
('CLMFSF70M65C351Y', 'Sofia', 'Colombo', '1970-08-25', 'f');

-- INSERIMENTO DATI MEDICI (CORRETTO)
INSERT INTO medico (nome, cognome, specializzazione) VALUES
('Federica', 'Aprile', 'radiologia'),
('Alessandro', 'Neri', 'cardiologia'),
('Silvia', 'Gialli', 'neurologia'),
('Giacomo', 'Bruno', 'ortopedia'),
('Valeria', 'Viola', 'oncologia');

-- INSERIMENTO REFERTI
INSERT INTO referto (id_paziente, id_medico, data_visita, diagnosi) VALUES
(1, 1, '2026-04-02', 'influenza con febbre. riposo a casa.'),
(2, 1, '2026-04-02', 'dolore al petto generico. tutto regolare.'),
(3, 4, '2026-04-06', 'male alla gamba. sospetta caduta.'),
(4, 3, '2026-04-12', 'forte mal di testa. prendere una medicina.'),
(1, 1, '2026-04-10', 'visita di controllo. il paziente migliora.'),
(8, 1, '2026-04-28', 'tosse e dolore al petto. serve antibiotico.');

-- INSERIMENTO ALLEGATI
INSERT INTO allegati (id_referto, path, tipo) VALUES
(1, 'radiografia.jpeg', 'Immagine'),
(1, 'note_visita.txt', 'Documento'),
(1, 'video.mp4', 'Video'),
(2, 'radiografia.jpeg', 'Immagine'),
(3, 'ecografia.jpeg', 'Immagine'),
(6, 'rx_torace.jpeg', 'Immagine');