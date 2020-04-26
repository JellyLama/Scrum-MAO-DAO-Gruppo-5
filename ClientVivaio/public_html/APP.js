var APP = {
    setCookie: function (cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    },
    getCookie: function (cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    },
    getPiante: function ()
    {
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
                + '<th>Nome</th>'
                + '<th>Descrizione</th>'
                + '<th>Stagione di Fioritura</th>'
                + '<th>Specie</th>'
                + '<th>Coltivazione</th>'
                + '<th>Tipo</th>'
                + '<th>Prezzo</th>'
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
    getDettagliAttivita: function (bottone)
    {
        //gets all post from API
        $.ajax(
                {
                    url: "http://localhost:8080/dettagliattivita",
                    method: "GET",
                    success: function (data, status) {

                        APP.showDettagliAttivita(data, bottone);
                    }
                }
        );
    },
    showDettagliAttivita: function (dettagliAttivita, bottone)
    {
        if (bottone === "1")
        {
            var tabellaDettagliAttivita = '<tr>'
                    + '<th>Tipo</th>'
                    + '<th>Nome</th>'
                    + '<th>Necessita Piante</th>'
                    + '<th>Costo per Ora</th>'
                    + '<th>Richiedi Attività</th>'
                    + '</tr>';
            for (i = 0; i < dettagliAttivita.length; i++) {
                var id = dettagliAttivita[i].id;
                var nome = dettagliAttivita[i].tipo;
                var descrizione = dettagliAttivita[i].nome;
                var stagioneFioritura = dettagliAttivita[i].necessitaPiante;
                var costoPerOra = dettagliAttivita[i].costoPerOra;
                tabellaDettagliAttivita += '<tr>'
                        + '<td>' + nome + '</td>'
                        + '<td>' + descrizione + '</td>'
                        + '<td>' + stagioneFioritura + '</td>'
                        + '<td>' + costoPerOra + '</td>'
                        + '<td>' + '<input class="buttonRichiediAttivita" type="submit" id="' + id + '" value="Richiedi Attività">' + '</td>'
                        + '</tr>';
            }
            document.getElementById("dettagliAttivita").innerHTML = tabellaDettagliAttivita;
            var buttonsRichiediAttivita = document.getElementsByClassName("buttonRichiediAttivita");
            for (i = 0; i < buttonsRichiediAttivita.length; i++) {
                var buttonId = buttonsRichiediAttivita[i].getAttribute("id");
                $("#" + buttonId).on("click", APP.insertAttivita);
            }
        } else
        {
            var tabellaDettagliAttivita = '<tr>'
                    + '<th>Tipo</th>'
                    + '<th>Nome</th>'
                    + '<th>Necessita Piante</th>'
                    + '<th>Costo per Ora</th>'
                    + '</tr>';
            for (i = 0; i < dettagliAttivita.length; i++) {
                var id = dettagliAttivita[i].id;
                var nome = dettagliAttivita[i].tipo;
                var descrizione = dettagliAttivita[i].nome;
                var stagioneFioritura = dettagliAttivita[i].necessitaPiante;
                var costoPerOra = dettagliAttivita[i].costoPerOra;
                tabellaDettagliAttivita += '<tr>'
                        + '<td>' + nome + '</td>'
                        + '<td>' + descrizione + '</td>'
                        + '<td>' + stagioneFioritura + '</td>'
                        + '<td>' + costoPerOra + '</td>'
                        + '</tr>';
            }
            document.getElementById("dettagliAttivita").innerHTML = tabellaDettagliAttivita;
        }
    },
    insertCliente: function ()
    {
        var cognome = $("#cognome").val();
        var nome = $("#nome").val();
        var username = $("#username").val();
        var password = $("#password").val();
        var telefono = $("#telefono").val();
        if (cognome === "") {
            window.alert("il campo COGNOME non può essere vuoto!");
        } else if (nome === "") {
            window.alert("il campo NOME non può essere vuoto!");
        } else if (username === "") {
            window.alert("il campo USERNAME non può essere vuoto!");
        } else if (password === "") {
            window.alert("il campo PASSWORD non può essere vuoto!");
        } else if (telefono === "") {
            window.alert("il campo TELEFONO non può essere vuoto!");
        } else {
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
                        success: function (utente, status) {
                        },
                        statusCode: {
                            200: function (utente) {
                                APP.setCookie("idCliente", utente.id, 1);
                                location.assign("cliente.html");
                            }
                        }
                    }
            );
        }
        ;
    },
    insertAttivita: function ()
    {
        var id = this.id;
        var idCliente = APP.getCookie("idCliente");
        var date = new Date();
        var month = '' + (date.getMonth() + 1);
        var day = '' + date.getDate();
        var year = date.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        var formatted_date = year + "-" + month + "-" + day;

        var attivita = JSON.stringify(
                {
                    cliente: {id: idCliente},
                    dettagliAttivita: {id: id},
                    dataPrenotazione: formatted_date,
                    evaso: false

                });
        $.ajax(
                {
                    url: "http://localhost:8080/attivita",
                    method: "POST",
                    contentType: "application/json",
                    data: attivita,
                    success: function (data, status) {
                    },
                    statusCode: {
                        200: function () {
                            APP.getAttivitaEvase();
                            APP.getAttivitaNonEvase();
                            window.alert("Attività prenotata");
                        }
                    }
                }
        );

        ;
    },
    getDettagliAttivitaById: function (id)
    {

        var url = "http://localhost:8080/dettagliattivita/" + id;

        $.ajax(
                {
                    url: url,
                    method: "GET",
                    success: function (dettagliAttivita) {
                    },
                    statusCode: {
                        200: function (dettagliAttivita) {
                            return dettagliAttivita;
                        }
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
        } else if (password === "") {
            window.alert("il campo PASSWORD non può essere vuoto!");
        } else {
            $.ajax(
                    {
                        url: url,
                        method: "GET",
                        success: function (utente) {
                        },
                        statusCode: {
                            200: function (utente) {
                                APP.setCookie("idCliente", utente.id, 1);
                                location.assign("cliente.html");
                            }
                        }
                    }
            );
        }
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
                            location.assign("dipendente.html");
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
                    }}
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
        var idCliente = APP.getCookie("idCliente");
        var url = "http://localhost:8080/attivita?evaso=false&idCliente=" + idCliente;
        $.ajax(
                {
                    url: url,
                    method: "GET",
                    success: function (data, status) {
                        APP.showAttivitaNonEvase(data, idCliente);
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
                + '<th>Dipendente</th>'
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
            var dipendente = attivitaNonEvase[i].dipendente;
            if (dipendente === null)
            {
                var dipendente = "null";
            } else
            {
                var dipendente = attivitaNonEvase[i].dipendente.cognome + " " + attivitaNonEvase[i].dipendente.nome;
            }
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
                    + '<td>' + dipendente + '</td>'
                    + '</tr>';
        }
        document.getElementById("attivitaNonEvase").innerHTML = tabellaAttivitaNonEvase;
    },
    getAttivitaEvase: function ()
    {
        var idCliente = APP.getCookie("idCliente");
        var url = "http://localhost:8080/attivita?evaso=true&idCliente=" + idCliente;
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
                + '<th>Dipendente</th>'
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
            if (dipendente === null)
            {
                var dipendente = "null";
            } else
            {
                var dipendente = attivitaEvase[i].dipendente.cognome + " " + attivitaEvase[i].dipendente.nome;
            }
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
                    + '<td>' + dipendente + '</td>'
                    + '</tr>';
        }
        document.getElementById("attivitaEvase").innerHTML = tabellaAttivitaEvase;
    }
};