insert into dettagli_attivita (tipo, nome, necessita_piante, costo_per_ora) values('lavoro', 'potatura', true, 5.0);
insert into clienti (cognome, nome, username, password, tipo, telefono) values('bachir', 'karim', 'Sfinge69', 'sfinge', 'base', '3279706977');
insert into tipi (nome, prezzo) values('tipoRosmarino', 20.0);
insert into specie (modo_coltivazione, nome) values('acqua e terra', 'specieRosmarino');
insert into dipendenti (categoria, cognome, nome, username, password, anno_assunzione, qualifica, specie_id) values('schiavo', 'rossi', 'giulio', 'qwerty', 'qwerty', '2001', 'schiavo', 1);
insert into dipendenti (categoria, cognome, nome, username, password, anno_assunzione, qualifica, specie_id) values('aaa', 'aaa', 'aaa', 'aaa', 'aaa', '2001', 'aaa', 1);
insert into piante (descrizione, nome, stagione_fioritura, specie_id, tipo_id) values('profuma','rosmarino', 'estate', 1, 1);
insert into attivita (data_effettuazione, data_prenotazione, evaso, cliente_id, dettagli_attivita_id, dipendente_id) values('2001-07-28', '2001-07-28', true, 1, 1, 1);
