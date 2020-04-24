var APP =
        {
            getPiante: function ()
            {
//gets all post from API
                $.ajax(
                        {
                            url: "http://localhost:8080/piante",
                            method: "GET",
                            success: function (data, status) {

                                APP.showPiante(data);
                            }
                        }
                );
            },
            showPiante: function (piante)
            {
                var tabellaPiante = '<tr>'
                        + '<th>nome</th>'
                        + '<th>descrizione</th>'
                        + '<th>stagione di fioritura</th>'
                        + '<th>specie</th>'
                        + '<th>modo di coltivazione</th>'
                        + '<th>tipo</th>'
                        + '<th>prezzo</th>'
                        + '</tr>';
                for (i = 0; i < piante.length; i++) {
                    var id = piante[i].id;
                    var nome = piante[i].nome;
                    var descriione = piante[i].descrizione;
                    var stagioneFioritura = piante[i].stagioneFioritura;
                    var specie = piante[i].specie.nome;
                    var modoDiColtivazione = piante[i].specie.modoColtivazione;
                    var tipo = piante[i].tipo.nome;
                    var prezzo = piante[i].tipo.prezzo;
                    tabellaPiante += '<tr>'
                            + '<td>' + nome + '</td>'
                            + '<td>' + descriione + '</td>'
                            + '<td>' + stagioneFioritura + '</td>'
                            + '<td>' + specie + '</td>'
                            + '<td>' + modoDiColtivazione + '</td>'
                            + '<td>' + tipo + '</td>'
                            + '<td>' + prezzo + '</td>'
                            + '</tr>';
                }
                document.getElementById("piante").innerHTML = tabellaPiante;
            },
            getDettagliAttivita: function ()
            {
                //gets all post from API
                $.ajax(
                        {
                            url: "http://localhost:8080/dettagliattivita",
                            method: "GET",
                            success: function (data, status) {

                                APP.showDettagliAttivita(data);
                            }
                        }
                );
            },
            showDettagliAttivita: function (dettagliAttivita)
            {
                var tabellaDettagliAttivita = '<tr>'
                        + '<th>tipo</th>'
                        + '<th>nome</th>'
                        + '<th>Necessita Piante</th>'
                        + '<th>Costo Per Ora</th>'
                        + '<th>Richiedi Attività</th>'
                        + '</tr>';
                for (i = 0; i < dettagliAttivita.length; i++) {
                    var id = dettagliAttivita[i].id;
                    var nome = dettagliAttivita[i].tipo;
                    var descriione = dettagliAttivita[i].nome;
                    var stagioneFioritura = dettagliAttivita[i].necessitaPiante;
                    var costoPerOra = dettagliAttivita[i].costoPerOra;
                    tabellaDettagliAttivita += '<tr>'
                            + '<td>' + nome + '</td>'
                            + '<td>' + descriione + '</td>'
                            + '<td>' + stagioneFioritura + '</td>'
                            + '<td>' + costoPerOra + '</td>'
                            + '<td>' + '<input type="submit" id="richiedi" value="Richiedi Attività">' + '</td>'
                            + '</tr>';
                }
                document.getElementById("dettagliAttivita").innerHTML = tabellaDettagliAttivita;
            },

            insertCliente: function ()
            {
                var cognome = $("#cognome").val();
                
                var nome = $("#nome").val();
                
                var username = $("#username").val();

                var password = $("#password").val();

                var telefono = $("#telefono").val();

                $.ajax(
                        {
                            url: "http://localhost:8080/clienti",
                            method: "POST",
                            contentType: "application/json",
                            data: JSON.stringify(
                                    {
                                        cognome: cognome,
                                        nome: nome,
                                        username: username,
                                        password: password,
                                        telefono: telefono

                                    }
                            ),
                            success: function (data, status) {


                            }
                        }
                );
            },
            getClienteByUsernameByPassword: function ()
            {
                var username = $("#username").val();
                var password = $("#password").val();
                var url = "http://localhost:8080/clienti?username=" + username + "&password=" + password;

                if (username === "") {
                    window.alert("il campo USERNAME non può essere vuoto!");
                }
                ;

                if (password === "") {
                    window.alert("il campo PASSWORD non può essere vuoto!");
                }
                ;

                $.ajax(
                        {
                            url: url,
                            method: "GET",
                            success: function (data, status) {
                            },
                            statusCode: {
                                200: function () {
                                    location.assign("cliente.html");
                                }
                            }
                        }
                );

            },

            getDipendenteByUsernameByPassword: function ()
            {
                var username = $("#username").val();
                var password = $("#password").val();
                var url = "http://localhost:8080/dipendenti?username=" + username + "&password=" + password;

                $.ajax(
                        {
                            url: url,
                            method: "GET",
                            success: function (data, status) {
                            },
                            statusCode: {
                                200: function () {
                                    location.assign("index.html");
                                }
                            }
                        }
                );
            },
            getAttivitaNonSeguite: function ()
            {
                var url = "http://localhost:8080/attivita?seguito=false";

                $.ajax(
                        {
                            url: url,
                            method: "GET",
                            success: function (data, status) {
                                APP.showAttivitaNonSeguite(data);
                            }
 
                        }
                );

            },
            showAttivitaNonSeguite: function (attivitaNonSeguite)
            {
                var tabellaAttivitaNonEseguite = '<tr>'
                        + '<th>id</th>'
                        + '<th>Data di prenotazione</th>'
                        + '<th>Data di effettuazione</th>'
                        + '<th>Evaso</th>'
                        + '<th>Cliente</th>'
                        + '<th>Nome attivita</th>'
                        + '<th>Costo orario</th>'
                        + '<th>Necessita piante</th>'
                        + '<th>Tipo</th>'
                        + '</tr>';
                for (i = 0; i < attivitaNonSeguite.length; i++) {

                    var id = attivitaNonSeguite[i].id;
                    var dataPrenotazione = attivitaNonSeguite[i].dataPrenotazione;
                    var dataEffettuazione = attivitaNonSeguite[i].dataEffettuazione;
                    var evaso = attivitaNonSeguite[i].evaso;
                    var cliente = attivitaNonSeguite[i].cliente.username;
                    var nomeAttivita = attivitaNonSeguite[i].dettagliAttivita.nome;
                    var costoOrario = attivitaNonSeguite[i].dettagliAttivita.costoPerOra;
                    var necessitaPiante = attivitaNonSeguite[i].dettagliAttivita.necessitaPiante;
                    var tipo = attivitaNonSeguite[i].dettagliAttivita.tipo;

                    tabellaAttivitaNonEseguite += '<tr>'
                            + '<td>' + id + '</td>'
                            + '<td>' + dataPrenotazione + '</td>'
                            + '<td>' + dataEffettuazione + '</td>'
                            + '<td>' + evaso + '</td>'
                            + '<td>' + cliente + '</td>'
                            + '<td>' + nomeAttivita + '</td>'
                            + '<td>' + costoOrario + '</td>'
                            + '<td>' + necessitaPiante + '</td>'
                            + '<td>' + tipo + '</td>'
                            + '</tr>';
                }
                document.getElementById("attivitaNonSeguite").innerHTML = tabellaAttivitaNonEseguite;
            },
            getAttivitaNonEvase: function ()
            {
                var url = "http://localhost:8080/attivita?evaso=false";

                $.ajax(
                        {
                            url: url,
                            method: "GET",
                            success: function (data, status) {
                                APP.showAttivitaNonEvase(data);
                            }
 
                        }
                );

            },
            showAttivitaNonEvase: function (attivitaNonEvase)
            {
                var tabellaAttivitaNonEvase = '<tr>'
                        + '<th>id</th>'
                        + '<th>Data di prenotazione</th>'
                        + '<th>Data di effettuazione</th>'
                        + '<th>Evaso</th>'
                        + '<th>Cliente</th>'
                        + '<th>Nome attivita</th>'
                        + '<th>Costo orario</th>'
                        + '<th>Necessita piante</th>'
                        + '<th>Tipo</th>'
                        + '</tr>';
                for (i = 0; i < attivitaNonEvase.length; i++) {

                    var id = attivitaNonEvase[i].id;
                    var dataPrenotazione = attivitaNonEvase[i].dataPrenotazione;
                    var dataEffettuazione = attivitaNonEvase[i].dataEffettuazione;
                    var evaso = attivitaNonEvase[i].evaso;
                    var cliente = attivitaNonEvase[i].cliente.username;
                    var nomeAttivita = attivitaNonEvase[i].dettagliAttivita.nome;
                    var costoOrario = attivitaNonEvase[i].dettagliAttivita.costoPerOra;
                    var necessitaPiante = attivitaNonEvase[i].dettagliAttivita.necessitaPiante;
                    var tipo = attivitaNonEvase[i].dettagliAttivita.tipo;

                    tabellaAttivitaNonEvase += '<tr>'
                            + '<td>' + id + '</td>'
                            + '<td>' + dataPrenotazione + '</td>'
                            + '<td>' + dataEffettuazione + '</td>'
                            + '<td>' + evaso + '</td>'
                            + '<td>' + cliente + '</td>'
                            + '<td>' + nomeAttivita + '</td>'
                            + '<td>' + costoOrario + '</td>'
                            + '<td>' + necessitaPiante + '</td>'
                            + '<td>' + tipo + '</td>'
                            + '</tr>';
                }
  //             document.getElementById("attivitaNonEvase").innerHTML = tabellaAttivitaNonEvase;
            },
            getAttivitaEvase: function ()
            {
                var url = "http://localhost:8080/attivita?evaso=true";

                $.ajax(
                        {
                            url: url,
                            method: "GET",
                            success: function (data, status) {
                                APP.showAttivitaEvase(data);
                            }
 
                        }
                );

            },
            showAttivitaEvase: function (attivitaEvase)
            {
                var tabellaAttivitaEvase = '<tr>'
                        + '<th>id</th>'
                        + '<th>Data di prenotazione</th>'
                        + '<th>Data di effettuazione</th>'
                        + '<th>Evaso</th>'
                        + '<th>Cliente</th>'
                        + '<th>Nome attivita</th>'
                        + '<th>Costo orario</th>'
                        + '<th>Necessita piante</th>'
                        + '<th>Tipo</th>'
                        + '</tr>';
                for (i = 0; i < attivitaEvase.length; i++) {

                    var id = attivitaEvase[i].id;
                    var dataPrenotazione = attivitaEvase[i].dataPrenotazione;
                    var dataEffettuazione = attivitaEvase[i].dataEffettuazione;
                    var evaso = attivitaEvase[i].evaso;
                    var cliente = attivitaEvase[i].cliente.username;
                    var nomeAttivita = attivitaEvase[i].dettagliAttivita.nome;
                    var costoOrario = attivitaEvase[i].dettagliAttivita.costoPerOra;
                    var necessitaPiante = attivitaEvase[i].dettagliAttivita.necessitaPiante;
                    var tipo = attivitaEvase[i].dettagliAttivita.tipo;

                    tabellaAttivitaEvase += '<tr>'
                            + '<td>' + id + '</td>'
                            + '<td>' + dataPrenotazione + '</td>'
                            + '<td>' + dataEffettuazione + '</td>'
                            + '<td>' + evaso + '</td>'
                            + '<td>' + cliente + '</td>'
                            + '<td>' + nomeAttivita + '</td>'
                            + '<td>' + costoOrario + '</td>'
                            + '<td>' + necessitaPiante + '</td>'
                            + '<td>' + tipo + '</td>'
                            + '</tr>';
                }
                document.getElementById("attivitaEvase").innerHTML = tabellaAttivitaEvase;
            },
            
        };