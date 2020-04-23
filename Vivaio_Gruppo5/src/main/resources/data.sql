insert into dettagli_attivita (tipo, nome, necessita_piante) values('lavoro', 'potatura', true);
insert into clienti (cognome, nome, tipo, telefono) values('bachir', 'karim', 'base', '3279706977');
insert into tipi (nome, prezzo) values('tipoRosmarino', 20.0);
insert into specie (modo_coltivazione, nome) values('acqua e terra', 'specieRosmarino');
insert into dipendenti (categoria, cognome, nome, anno_assunzione, qualifica, costo_per_ora, specie_id) values('schiavo', 'rossi', 'giulio', '2001', 'schiavo', 5.0, 1);
insert into piante (descrizione, nome, stagione_fioritura, specie_id, tipo_id) values('profuma','rosmarino', 'estate', 1, 1);
insert into attivita (data_effettuazione, data_prenotazione, evaso, cliente_id, dettagli_attivita_id, dipendente_id) values('2001-07-28', '2001-07-28', true, 1, 1, 1);