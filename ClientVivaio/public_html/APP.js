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
                            + '</tr>';
                }
                document.getElementById("dettagliAttivita").innerHTML = tabellaDettagliAttivita;
            },

            insertCliente: function ()
            {

                var cognome = $("#cognome").val();
                ;
                var nome = $("#nome").val();
                ;
                
                var username = $("#username").val();
                ;
                var password = $("#password").val();
                ;
                var telefono = $("#telefono").val();
                ;
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
                var url = "http://localhost:8080/clienti?username="+username+"&password="+password;
                
                $.ajax(
                        {
                            url: url,
                            method: "GET",
                            success: function (data, status) {
                            },
                            statusCode: {
                                200: function (){
                                    location.assign("index.html");
                                }
                            }
                        }
                );
            },


            getDipendenteByUsernameByPassword: function ()
            {
                var username = $("#username").val();
                var password = $("#password").val();
                var url = "http://localhost:8080/dipendenti?username="+username+"&password="+password;
                
                $.ajax(
                        {
                            url: url,
                            method: "GET",
                            success: function (data, status) {
                            },
                            statusCode: {
                                200: function (){
                                    location.assign("index.html");
                                }
                            }
                        }
                );
            }



        }